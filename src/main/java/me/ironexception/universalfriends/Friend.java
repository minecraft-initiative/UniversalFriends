package me.ironexception.universalfriends;

import java.util.UUID;

public class Friend implements IFriend {

    private UUID id;
    private String name;

    public Friend(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

}
