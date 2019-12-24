package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.json.PersonLoader;
import me.ironexception.universalfriends.json.PersonLoaderException;
import me.ironexception.universalfriends.person.IPerson;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    private Configuration<Person> configuration;

    @BeforeEach
    public void beforeAll() throws URISyntaxException, IOException, PersonLoaderException {
        configuration = PersonLoader.loader(Paths.get(getClass().getClassLoader().getResource("friends_operations.json").toURI())).loadFriendConfiguration();
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
    @DisplayName("Get by close to association")
    void getByCloseToAssociation() {
        assertEquals("bar", Operations.getByCloseToAssociation(configuration, Association.ALLY).stream().findAny().get().getName());
    }

    @Test
    @DisplayName("Get by association")
    void getByAssociation() {
        assertEquals("baz", Operations.getByAssociation(configuration, Association.ENEMY).stream().findAny().get().getName());
    }

}