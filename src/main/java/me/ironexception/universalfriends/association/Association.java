package me.ironexception.universalfriends.association;

/**
 * The relation that the friend has with the user.
 *
 * @author Jackson
 * Created 12/23/19.
 */
public enum Association {

    /**
     * Unable to be attacked.
     */
    ALLY,

    /**
     * Targeted upon.
     */
    ENEMY,

    /**
     * Acts as any other player.
     */
    NEUTRAL
}
