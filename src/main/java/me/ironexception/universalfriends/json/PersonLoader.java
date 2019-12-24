package me.ironexception.universalfriends.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.ironexception.universalfriends.UniversalFriends;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.IPerson;
import me.ironexception.universalfriends.person.Person;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load JSON files containing standardised person (friend) data
 *
 * @see PersonLoader#loader()
 * @see PersonLoader#loadFriendConfiguration()
 */
public class PersonLoader {

    private final Path path;

    private PersonLoader(Path path) {
        this.path = path;
    }

    /**
     * Creates a new {@link PersonLoader} reading from the standardised, default file name for person data. The current working directory is assumed to be the home of the file to read from.
     * @return The created {@link PersonLoader}
     */
    public static PersonLoader loader() {
        return loader(Paths.get(UniversalFriends.STANDARD_FILE_NAME));
    }

    /**
     * Creates a new {@link PersonLoader} from a custom path
     * @param path  The path to read from
     * @return      The created {@link PersonLoader}
     */
    public static PersonLoader loader(Path path) {
        return new PersonLoader(path);
    }

    public Configuration<Person> loadFriendConfiguration() throws IOException, PersonLoaderException {
        return loadFriendConfiguration(Person.class);
    }

    /**
     * @return                          A {@link Configuration} representing the loaded file
     * @param <T>                       The type of {@link IPerson} to load
     * @throws IOException              If something went wrong while reading the file. This can happen if the file doesn't exist or the process doesn't have access to it.
     * @throws PersonLoaderException    If the file did not adhere to the standard
     */
    public <T extends IPerson> Configuration<T> loadFriendConfiguration(Class<T> personClass) throws IOException, PersonLoaderException {
        return loadFriendConfiguration(Files.newInputStream(path), personClass);
    }

    public <T extends IPerson> Configuration<T> loadFriendConfiguration(InputStream stream, Class<T> personClass) throws PersonLoaderException {
        return loadFriendConfiguration(new BufferedReader(new InputStreamReader(stream)), personClass);
    }

    public <T extends IPerson> Configuration<T> loadFriendConfiguration(Reader reader, Class<T> personClass) throws PersonLoaderException {
        final Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(reader);
        JsonObject rootObject;
        try {
            rootObject = jsonElement.getAsJsonObject();
        } catch (Exception e) {
            throw new PersonLoaderException("Root element of friends file must be a JSON object", e);
        }
        Bounds bounds = gson.fromJson(rootObject.get("bounds"), Bounds.class);
        Type friendType = new TypeToken<T>(){}.getType();
        List<T> friends = new ArrayList<>();
        if (rootObject.has("list")) {
            JsonElement listElement = rootObject.get("list");
            JsonArray list;
            try {
                list = listElement.getAsJsonArray();
            } catch (Exception e) {
                throw new PersonLoaderException("List element of friends file must be a JSON array", e);
            }
            for (JsonElement element : list) {
                friends.add(gson.fromJson(element, personClass));
            }
        }
        return new Configuration<T>(bounds, friends);
    }

}
