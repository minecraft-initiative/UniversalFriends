package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.friend.IPerson;

import java.util.List;

public class Configuration<T extends IPerson> {

    private final Meta meta;
    private final List<T> list;

    public Configuration(Meta meta, List<T> list) {
        this.meta = meta;
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public Meta getMeta() {
        return meta;
    }

}
