package me.ironexception.universalfriends.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.ironexception.universalfriends.UniversalFriends;
import me.ironexception.universalfriends.player.IPerson;
import me.ironexception.universalfriends.player.Person;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonLoader {

    private final Path path;

    private PersonLoader(Path path) {
        this.path = path;
    }

    public static PersonLoader loader() {
        return loader(Paths.get(UniversalFriends.STANDARD_FILE_NAME));
    }

    public static PersonLoader loader(Path path) {
        return new PersonLoader(path);
    }

    public Configuration<IPerson> loadFriendConfiguration() throws IOException, PersonLoaderException {
        return loadFriendConfiguration(Files.newInputStream(path));
    }

    public Configuration<IPerson> loadFriendConfiguration(InputStream stream) throws PersonLoaderException {
        return loadFriendConfiguration(new BufferedReader(new InputStreamReader(stream)));
    }

    public Configuration<IPerson> loadFriendConfiguration(Reader reader) throws PersonLoaderException {
        final Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(reader);
        JsonObject rootObject;
        try {
            rootObject = jsonElement.getAsJsonObject();
        } catch (Exception e) {
            throw new PersonLoaderException("Root element of friends file must be a JSON object", e);
        }
        Meta meta = gson.fromJson(rootObject.get("meta"), Meta.class);
        Type friendType = new TypeToken<Person>(){}.getType();
        List<IPerson> friends = new ArrayList<>();
        if (rootObject.has("list")) {
            JsonElement listElement = rootObject.get("list");
            JsonArray list;
            try {
                list = listElement.getAsJsonArray();
            } catch (Exception e) {
                throw new PersonLoaderException("List element of friends file must be a JSON array", e);
            }
            for (JsonElement element : list) {
                friends.add(gson.fromJson(element, Person.class));
            }
        }
        return new Configuration<>(meta, friends);
    }

}
