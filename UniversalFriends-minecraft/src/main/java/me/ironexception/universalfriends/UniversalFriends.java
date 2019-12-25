package me.ironexception.universalfriends;

import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.json.FriendFileLoader;
import me.ironexception.universalfriends.json.FriendFileLoaderException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UniversalFriends extends Configuration<GameProfilePerson> {

    public static UniversalFriends INSTANCE;

    static {

        try {
            UniversalFriends.INSTANCE = FriendFileLoader
                    .<UniversalFriends, GameProfilePerson>loader(GameProfilePerson.class)
                    .withFactory((bounds, set) -> new UniversalFriends(bounds, new HashSet<>(set)))
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

}