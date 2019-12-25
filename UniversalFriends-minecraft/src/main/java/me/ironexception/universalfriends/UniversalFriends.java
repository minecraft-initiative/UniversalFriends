package me.ironexception.universalfriends;

import com.mojang.authlib.GameProfile;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.json.FriendFileLoader;
import me.ironexception.universalfriends.json.FriendFileLoaderException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class UniversalFriends extends Configuration<GameProfilePerson> {

    public static UniversalFriends INSTANCE;

    static {

        try {
            UniversalFriends.INSTANCE = FriendFileLoader
                    .<UniversalFriends, GameProfilePerson>loader(GameProfilePerson.class)
                    .withFactory((bounds, set) -> new UniversalFriends(bounds, new FriendsSet(set)))
                    .withDefaultPath()
                    .load();
        } catch (IOException | FriendFileLoaderException e) {
            e.printStackTrace();

            UniversalFriends.INSTANCE = new UniversalFriends(new Bounds(
                    Standard.STANDARD_MIN_FRIENDLINESS,
                    Standard.STANDARD_MAX_FRIENDLINESS
            ), new HashSet<>());
        }

    }

    private UniversalFriends(Bounds bounds, Set<GameProfilePerson> set) {
        super(bounds, set);
    }

    /**
     * Add a person to the friend list
     * @param profilePerson The {@link GameProfilePerson} to add
     */
    public static void addPerson(GameProfilePerson profilePerson) {
        UniversalFriends.INSTANCE.getFriendList().add(profilePerson);
    }

    /**
     * Add a person by their {@link GameProfile}, assuming a friendliness value of <code>0</code> ({@link Standard#STANDARD_NEUTRAL}).
     * @param profile   The {@link GameProfile} of the person to add
     */
    public static void addPerson(GameProfile profile) {
        GameProfilePerson profilePerson = new GameProfilePerson(profile, Standard.STANDARD_NEUTRAL);
        addPerson(profilePerson);
    }

    /**
     * Remove a person from the friend list
     * @param person    The {@link GameProfilePerson} to remove
     * @return          Whether or not this person was in the friend list
     */
    public static boolean removePerson(GameProfilePerson person) {
        return UniversalFriends.INSTANCE.getFriendList().remove(person);
    }

    /**
     * Remove a person from the friend list by their {@link GameProfile}
     * @param profile   The {@link GameProfile} of the person to remove
     * @return          Whether or not this person was in the friend list
     */
    public static boolean removePerson(GameProfile profile) {
        return UniversalFriends.INSTANCE.getFriendList()
                .stream()
                .filter(profilePerson -> profilePerson.getProfile().equals(profile))        // Filter people with equal GameProfiles
                .map((Function<GameProfilePerson, Object>) UniversalFriends::removePerson)  // Remove them from the friend list
                .anyMatch(o -> o == Boolean.TRUE);                                          // Return true if any were removed
    }

    /**
     * Installs a person add callback, which will be called every time a new person is added to the friend list.
     * @param consumer  The callback to add
     */
    public static void installAddCallback(Consumer<GameProfilePerson> consumer) {
        ((FriendsSet) UniversalFriends.INSTANCE.getFriendList()).addCallbacks.add(consumer);
    }

    /**
     * Installs a person remove callback, which will be called every time a person is removed from the friend list.
     * @param consumer  The callback to add
     */
    public static void installRemoveCallback(Consumer<GameProfilePerson> consumer) {
        ((FriendsSet) UniversalFriends.INSTANCE.getFriendList()).removeCallbacks.add(consumer);
    }

}