package me.ironexception.universalfriends.association;

import java.util.Arrays;

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
    ALLY(1),

    /**
     * Targeted upon.
     */
    ENEMY(-1),

    /**
     * Acts as any other player.
     */
    NEUTRAL(0);

    double value;

    Association(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    /**
     * Fetch a {@link Association} by its value.
     * Might return null if there is no corresponding
     * @param value
     * @return
     */
    public static Association byValue(double value) {
        return Arrays.stream(values()).filter(association -> association.getValue() == value).findAny().orElseGet(null);
    }

}
