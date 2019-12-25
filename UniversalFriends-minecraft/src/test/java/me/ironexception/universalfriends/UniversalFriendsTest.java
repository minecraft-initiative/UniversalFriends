package me.ironexception.universalfriends;

import com.mojang.authlib.GameProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class UniversalFriendsTest {

    @Test
    @DisplayName("UniversalFriends instance")
    public void testInstance() {
        assertNotNull(UniversalFriends.INSTANCE);
    }

    @Test
    @DisplayName("Callbacks")
    public void testCallbacks() {
        AtomicBoolean callbackCalled = new AtomicBoolean(false);
        AtomicBoolean personIsEqual = new AtomicBoolean(false);
        final GameProfilePerson person = new GameProfilePerson(new GameProfile(UUID.randomUUID(), "bar"), Standard.STANDARD_NEUTRAL);

        UniversalFriends.installAddCallback(profilePerson -> {
            callbackCalled.set(true);
            personIsEqual.set(profilePerson == person);
        });

        UniversalFriends.addPerson(person);

        assertTrue(callbackCalled.get(), "Callback was called");
        assertTrue(personIsEqual.get(), "Correct person was retrieved");
    }

    @Test
    @DisplayName("GameProfile loads correctly")
    public void testGameProfileAdapter() {
        UniversalFriends.INSTANCE.getFriendList()
                .stream()
                .findAny().ifPresent(person -> {
                    boolean nameCorrect = person.getName().equals("foo");
                    boolean idCorrect = person.getId().toString().equals("8a2a3ef5-8d27-41b9-a69a-cbb05ac0ed1d");
                    boolean valueCorrect = person.getValue() == 0;
                    boolean metaCorrect = person.getMeta().has("displayName") && person.getMeta().get("displayName").getAsString().equals("bar");

                    assertTrue(nameCorrect);
                    assertTrue(idCorrect);
                    assertTrue(metaCorrect);
                    assertTrue(valueCorrect);
                });
    }

}