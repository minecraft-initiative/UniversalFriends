package me.ironexception.universalfriends.json;

import com.google.gson.JsonObject;
import me.ironexception.universalfriends.TestUtil;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FriendFileLoaderTest {

    @Test
    @DisplayName("Default loader works")
    void load() throws URISyntaxException, IOException, FriendFileLoaderException {
        FriendFileLoader<Configuration<Person>, Person> loader = FriendFileLoader.defaultLoader()
                .withPath(TestUtil.getPath("friends.json"));

        assertDoesNotThrow((Executable) loader::load, "Loads without exceptions");
        Configuration<Person> configuration = loader.load();
        Set<Person> set = configuration.getFriendList();
        assertEquals(1, set.size(), "One person was loaded");

        Person person = set.stream().findAny().get();
        assertEquals("foo", person.getName());
        assertEquals("8a2a3ef5-8d27-41b9-a69a-cbb05ac0ed1d", person.getId().toString());
        assertEquals(0, person.getValue());

        JsonObject meta = person.getMeta();
        assertTrue(meta.has("displayName"), "Meta has displayName");
        assertEquals("bar", meta.get("displayName").getAsString());
    }

}