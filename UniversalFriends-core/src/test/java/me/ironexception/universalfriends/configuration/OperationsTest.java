package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.TestUtil;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.json.FriendFileLoader;
import me.ironexception.universalfriends.json.FriendFileLoaderException;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    private Configuration<Person> configuration;

    @BeforeEach
    public void beforeAll() throws URISyntaxException, IOException, FriendFileLoaderException {
        configuration = FriendFileLoader.defaultLoader()
                .withPath(TestUtil.getPath("friends_operations.json"))
                .load();
    }

    @Test
    @DisplayName("Get by range")
    void getInFriendlinessRange() {
        assertEquals(2, Operations.getInFriendlinessRange(configuration, 0, 2).size());
    }

    @Test
    @DisplayName("Get by friendliness")
    void getByFriendliness() {
        assertEquals("bar", Operations.getByFriendliness(configuration, 2).stream().findAny().get().getName());
    }

    @Test
    @DisplayName("Get by association")
    void getByAssociation() {
        assertEquals("baz", Operations.getByAssociation(configuration, Association.ENEMY).stream().findAny().get().getName());
    }

}