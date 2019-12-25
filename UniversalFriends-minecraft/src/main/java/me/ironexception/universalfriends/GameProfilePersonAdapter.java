package me.ironexception.universalfriends;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mojang.authlib.GameProfile;

import java.io.IOException;
import java.util.UUID;

public class GameProfilePersonAdapter extends TypeAdapter<GameProfilePerson> {

    final Gson embedded = new Gson();

    @Override
    public void write(JsonWriter writer, GameProfilePerson person) throws IOException {
        writer.beginObject();

        writer.name("name");
        writer.value(person.getName());

        writer.name("id");
        writer.value(person.getId().toString());

        writer.name("value");
        writer.value(person.getValue());

        writer.name("meta");
        embedded.toJson(embedded.toJson(person.getMeta()), JsonObject.class, writer);

        writer.endObject();
    }

    @Override
    public GameProfilePerson read(JsonReader reader) throws IOException {
        UUID uuid = null;
        String name = null;
        double value = 0;
        JsonObject meta = null;

        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                switch (reader.nextName()) {
                    case "name":
                        name = reader.nextString();
                        break;
                    case "id":
                        uuid = UUID.fromString(reader.nextString());
                        break;
                    case "value":
                        value = reader.nextDouble();
                        break;
                    case "meta":
                        meta = embedded.fromJson(reader, JsonObject.class);
                        break;
                }
            }
        }
        reader.endObject();

        return new GameProfilePerson(new GameProfile(uuid, name), value, meta);
    }
}
