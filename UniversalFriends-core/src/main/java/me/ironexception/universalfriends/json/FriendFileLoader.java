package me.ironexception.universalfriends.json;

import com.google.gson.*;
import me.ironexception.universalfriends.Standard;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.IPerson;
import me.ironexception.universalfriends.person.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class FriendFileLoader<T extends Configuration<S>, S extends IPerson> {

    private BiFunction<Bounds, Set<S>, T> factory;
    private Path readPath;
    private Class<S> personClass;

    private FriendFileLoader(Class<S> personClass) {
        this.personClass = personClass;
    }

    public static <T extends Configuration<S>, S extends IPerson> FriendFileLoader<T, S> loader(Class<S> personClass) {
        return new FriendFileLoader<>(personClass);
    }

    public static FriendFileLoader<Configuration<Person>, Person> defaultLoader() {
        return loader(Person.class)
                .withDefaultFactory()
                .withDefaultPath();
    }

    public FriendFileLoader<T, S> withFactory(BiFunction<Bounds, Set<S>, T> factory) {
        this.factory = factory;
        return this;
    }

    public FriendFileLoader<Configuration<S>, S> withDefaultFactory() {
        FriendFileLoader<Configuration<S>, S> loader = new FriendFileLoader<>(this.personClass);
        loader.readPath = this.readPath;
        loader.factory = (bounds, set) -> new Configuration<S>(bounds, new HashSet<>(set));
        return loader;
    }

    public FriendFileLoader<T, S> withPath(Path path) {
        this.readPath = path;
        return this;
    }

    public FriendFileLoader<T, S> withDefaultPath() {
        return withPath(Paths.get(Standard.STANDARD_FILE_NAME));
    }

    public T load() throws IOException, FriendFileLoaderException {
        final Gson gson = new Gson();
        JsonObject rootObject = tryAsObject(new JsonParser().parse(new BufferedReader(new InputStreamReader(Files.newInputStream(this.readPath)))), "Root element of friends file must be a JSON object");
        JsonObject boundsObject = tryAsObject(rootObject.get("bounds"), "Bounds element of friends file must be a JSON object");
        Bounds bounds = gson.fromJson(boundsObject, Bounds.class);
        JsonArray listArray = tryAsArray(rootObject.get("list"));
        Set<S> persons = new HashSet<>();
        for (JsonElement element : listArray) {
            persons.add(gson.fromJson(element, this.personClass));
        }
        return this.factory.apply(bounds, persons);
    }

    private JsonObject tryAsObject(JsonElement element, String exception) throws FriendFileLoaderException {
        try {
            return element.getAsJsonObject();
        } catch (IllegalStateException e) {
            throw new FriendFileLoaderException(exception);
        }
    }

    private JsonArray tryAsArray(JsonElement element) throws FriendFileLoaderException {
        try {
            return element.getAsJsonArray();
        } catch (IllegalStateException e) {
            throw new FriendFileLoaderException("List element of friends list must be a JSON array");
        }
    }

}
