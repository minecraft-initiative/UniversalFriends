package me.ironexception.universalfriends.json;

import me.ironexception.universalfriends.IFriend;

import java.util.List;

public class Configuration<T extends IFriend> {

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
