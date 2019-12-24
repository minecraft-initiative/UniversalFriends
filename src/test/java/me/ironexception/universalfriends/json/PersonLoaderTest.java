package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.UniversalFriends;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.person.IPerson;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class PersonLoaderTest {

    @Test
    void loadFriendConfiguration() throws URISyntaxException {
        // Construct a loader to load from the friends.json file in the resources directory
        PersonLoader loader = PersonLoader.loader(Paths.get(getClass().getClassLoader().getResource(UniversalFriends.STANDARD_FILE_NAME).toURI()));

        // Make sure no exceptions are thrown while reading the file
        AtomicReference<Configuration<IPerson>> atomicConfiguration = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            atomicConfiguration.set(loader.loadFriendConfiguration());
        });
        Configuration<IPerson> configuration = atomicConfiguration.get();

        // All meta (minimum, maximum) values must be correct
        Meta meta = configuration.getMeta();
        assertEquals(-2, meta.getMinimum(), "Meta minimum value");
        assertEquals(2, meta.getMaximum(), "Meta maximum value");

        // The persons list must exist and be of size 1
        List<IPerson> personList = configuration.getList();
        assertNotNull(personList, "Friend list is nonnull");
        assertEquals(1, personList.size());

        // The sole person in the persons list must have the correct attributes
        IPerson person = personList.get(0);
        assertEquals(person.getId(), UUID.fromString("8a2a3ef5-8d27-41b9-a69a-cbb05ac0ed1d"), "UUID is the same");
        assertEquals(person.getName(), "foo", "Name is the same");
        assertEquals(0, person.getValue(), "Value is the same");
        assertEquals(Association.NEUTRAL, person.getAssociation(), "Inferred association is the same");
    }

}