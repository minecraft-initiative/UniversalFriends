package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.person.IPerson;

import java.util.List;

/**
 * Represents the friend file
 * @param <T>   The class this {@link Configuration} uses to represent persons
 */
public class Configuration<T extends IPerson> {

    private final Meta meta;
    private final List<T> list;

    public Configuration(Meta meta, List<T> list) {
        this.meta = meta;
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
     * Returns the friend file's meta
     * @return  the meta
     */
    public Meta getMeta() {
        return meta;
    }

}
