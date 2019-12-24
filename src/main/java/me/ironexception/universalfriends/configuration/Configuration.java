package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.person.IPerson;

import java.util.List;

/**
 * Represents the friend file
 * @param <T>   The class this {@link Configuration} uses to represent persons
 */
public class Configuration<T extends IPerson> {

    private final Bounds bounds;
    private final List<T> list;

    public Configuration(Bounds bounds, List<T> list) {
        this.bounds = bounds;
        this.list = list;
    }

    /**
     * Returns the friend list
     * @return  the friend list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Returns the friend file's bounds
     * @return  the bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

}
