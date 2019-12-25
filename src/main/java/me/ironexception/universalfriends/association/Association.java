package me.ironexception.universalfriends.association;

import me.ironexception.universalfriends.Standard;

import java.util.Arrays;
import java.util.Comparator;

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
    ALLY(Standard.STANDARD_ALLY),

    /**
     * Targeted upon.
     */
    ENEMY(Standard.STANDARD_ENEMY),

    /**
     * Acts as any other player.
     */
    NEUTRAL(Standard.STANDARD_NEUTRAL);

    /**
     * The friendliness value of the association.
     */
    private final double value;

    Association(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    /**
     * Fetch a {@link Association} by its exact value.
     *
     * @param value The friendliness value
     * @return The association related to the friendliness value. <code>null</code> if there is no {@link Association} with the provided value.
     * @author 086
     */
    public static Association byExactValue(final double value) {
        return Arrays.stream(values()).filter(association -> association.getValue() == value).findAny().orElseGet(null);
    }

    /**
     * Fetch the {@link Association} convert value to association.
     * No matter how friendly someone is as long as he is friendly he gets the ally association. The same goes for enemies.
     * So values below 0 are enemies and above are allies.
     *
     * @param value The friendliness value
     * @return      The {@link Association} with a friendliness value depending to the provided value
     * @author IronException
     */
    public static Association byValue(final double value) {
        if (value < 0) return Association.ENEMY;
        if (value > 0) return Association.ALLY;
        return Association.NEUTRAL;
    }

}
