package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.Standard;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.IPerson;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class PersonLoaderTest {

    @Test
    @DisplayName("General loading of persons")
    void loadFriendConfiguration() throws URISyntaxException {
        // Construct a loader to load from the friends.json file in the resources directory
        PersonLoader loader = PersonLoader.loader(Paths.get(getClass().getClassLoader().getResource(Standard.STANDARD_FILE_NAME).toURI()));

        // Make sure no exceptions are thrown while reading the file
        AtomicReference<Configuration<Person>> atomicConfiguration = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            atomicConfiguration.set(loader.loadFriendConfiguration());
        });
        Configuration<Person> configuration = atomicConfiguration.get();

        // All bounds (minimum, maximum) values must be correct
        Bounds bounds = configuration.getBounds();
        assertEquals(-2, bounds.getMinimum(), "Bounds minimum value");
        assertEquals(2, bounds.getMaximum(), "Bounds maximum value");

        // The persons list must exist and be of size 1
        List<Person> personList = configuration.getList();
        assertNotNull(personList, "Friend list is nonnull");
        assertEquals(1, personList.size());

        // The sole person in the persons list must have the correct attributes
        IPerson person = personList.get(0);
        assertEquals(person.getId(), UUID.fromString("8a2a3ef5-8d27-41b9-a69a-cbb05ac0ed1d"), "UUID is the same");
        assertEquals(person.getName(), "foo", "Name is the same");
        assertEquals(0, person.getValue(), "Value is the same");
        assertEquals(Association.NEUTRAL, person.getAssociation(), "Inferred association is the same");
    }

    @Test
    @DisplayName("Custom person types")
    void loadFriendConfigurationCustomTypes() throws IOException, PersonLoaderException, URISyntaxException {
        Configuration<CustomPerson> configuration = PersonLoader.loader(Paths.get(getClass().getClassLoader().getResource("friends_with_meta.json").toURI())).loadFriendConfiguration(CustomPerson.class);
        CustomPerson person = configuration.getList().get(0);
        assertEquals(10, person.metaValue, "Custom meta value is correct");
    }

    private static class CustomPerson extends Person {
        private final int metaValue;

        public CustomPerson(UUID id, String name, double value, int metaValue) {
            super(id, name, value);
            this.metaValue = metaValue;
        }
    }

}