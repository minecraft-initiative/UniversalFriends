package me.ironexception.universalfriends.json;

public class FriendLoadException extends Exception {

    public FriendLoadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FriendLoadException(String s) {
        super(s);
    }
}
