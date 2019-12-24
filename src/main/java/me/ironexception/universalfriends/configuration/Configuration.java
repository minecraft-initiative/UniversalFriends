package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.person.IPerson;

import java.util.Set;

/**
 * Represents the friend file
 * @param <T>   The class this {@link Configuration} uses to represent persons
 */
public class Configuration<T extends IPerson> {

    private final Bounds bounds;
    private final Set<T> set;

    public Configuration(Bounds bounds, Set<T> set) {
        this.bounds = bounds;
        this.set = set;
    }

    /**
     * Returns the friend list
     * @return  the friend list
     */
    public Set<T> getFriendList() {
        return set;
    }

    /**
     * Returns the friend file's bounds
     * @return  the bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

}
