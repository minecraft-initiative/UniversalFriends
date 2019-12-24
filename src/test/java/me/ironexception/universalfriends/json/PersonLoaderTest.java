package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.UniversalFriends;
import me.ironexception.universalfriends.player.IPerson;
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
        PersonLoader loader = PersonLoader.loader(Paths.get(getClass().getClassLoader().getResource(UniversalFriends.STANDARD_FILE_NAME).toURI()));
        AtomicReference<Configuration<IPerson>> atomicConfiguration = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            atomicConfiguration.set(loader.loadFriendConfiguration());
        });
        Configuration<IPerson> configuration = atomicConfiguration.get();
        Meta meta = configuration.getMeta();
        assertEquals(-1, meta.getMinimum(), "Meta minimum value");
        assertEquals(1, meta.getMaximum(), "Meta maximum value");
        List<IPerson> friendList = configuration.getList();
        assertNotNull(friendList, "Friend list is nonnull");
        assertEquals(1, friendList.size());
        IPerson friend = friendList.get(0);
        assertEquals(friend.getId(), UUID.fromString("8a2a3ef5-8d27-41b9-a69a-cbb05ac0ed1d"), "UUID is the same");
        assertEquals(friend.getName(), "foo", "Name is the same");
    }

}