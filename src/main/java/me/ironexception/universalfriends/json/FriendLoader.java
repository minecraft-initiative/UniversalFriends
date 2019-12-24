package me.ironexception.universalfriends.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.ironexception.universalfriends.Friend;
import me.ironexception.universalfriends.UniversalFriends;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FriendLoader {

    private final Path path;

    private FriendLoader(Path path) {
        this.path = path;
    }

    public static FriendLoader loader() {
        return loader(Paths.get(UniversalFriends.STANDARD_FILE_NAME));
    }

    public static FriendLoader loader(Path path) {
        return new FriendLoader(path);
    }

    public Configuration<Friend> loadFriendConfiguration() throws IOException, FriendLoadException {
        return loadFriendConfiguration(Files.newInputStream(path));
    }

    public Configuration<Friend> loadFriendConfiguration(InputStream stream) throws FriendLoadException {
        return loadFriendConfiguration(new BufferedReader(new InputStreamReader(stream)));
    }

    public Configuration<Friend> loadFriendConfiguration(Reader reader) throws FriendLoadException {
        final Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(reader);
        JsonObject rootObject;
        try {
            rootObject = jsonElement.getAsJsonObject();
        } catch (Exception e) {
            throw new FriendLoadException("Root element of friends file must be a JSON object", e);
        }
        Meta meta = gson.fromJson(rootObject.get("meta"), Meta.class);
        Type friendType = new TypeToken<Friend>(){}.getType();
        List<Friend> friends = new ArrayList<>();
        if (rootObject.has("list")) {
            JsonElement listElement = rootObject.get("list");
            JsonArray list;
            try {
                list = listElement.getAsJsonArray();
            } catch (Exception e) {
                throw new FriendLoadException("List element of friends file must be a JSON array", e);
            }
            for (JsonElement element : list) {
                friends.add(gson.fromJson(element, Friend.class));
            }
        }
        return new Configuration<>(meta, friends);
    }

}
