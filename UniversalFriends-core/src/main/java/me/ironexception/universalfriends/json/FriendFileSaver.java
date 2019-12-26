package me.ironexception.universalfriends.json;

import com.google.gson.*;
import me.ironexception.universalfriends.Standard;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.IPerson;
import me.ironexception.universalfriends.person.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FriendFileSaver {

    public static <T extends Configuration<S>, S extends IPerson> void save(T configuration) throws IOException {
        FriendFileSaver.save(configuration, new Gson());
    }

    public static <T extends Configuration<S>, S extends IPerson> void save(T configuration, Gson gson) throws IOException {
        FriendFileSaver.save(configuration, gson, Files.newBufferedWriter(Paths.get(Standard.STANDARD_FILE_NAME)));
    }

    public static <T extends Configuration<S>, S extends IPerson> void save(T configuration, Gson gson, Writer writer) throws IOException {
        JsonObject object = new JsonObject();
        object.add("bounds", gson.toJsonTree(configuration.getBounds()));
        object.add("list", gson.toJsonTree(configuration.getFriendList()));
        writer.write(gson.toJson(object));
        writer.close();
    }

}
