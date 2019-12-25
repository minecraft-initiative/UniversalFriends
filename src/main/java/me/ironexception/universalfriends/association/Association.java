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
     * Fetch a {@link Association} by it's exact value.
     *
     * @param value The friendliness value
     * @return The association related to the friendliness value. <code>null</code> if there is no {@link Association} with the provided value.
     * @author 086
     */
    public static Association byExactValue(final double value) {
        return Arrays.stream(values()).filter(association -> association.getValue() == value).findAny().orElseGet(null);
    }

    /**
     * Fetch a {@link Association} by what the value means.
     * If the provided value is lower than the standardised neutral value, this method will return {@link Association#ENEMY}
     * If the provided value is higher than the standardised neutral value, this method will return {@link Association#ALLY}
     * If the provided value is equal to the standardised neutral value, this method will return {@link Association#NEUTRAL}
     *
     * @param value The friendliness value
     * @return      The {@link Association} with a friendliness value depending to the provided value
     * @author IronException
     */
    public static Association byValue(final double value) {
        if (value < Standard.STANDARD_NEUTRAL) return Association.ENEMY;
        if (value > Standard.STANDARD_NEUTRAL) return Association.ALLY;
        return Association.NEUTRAL;
    }

}
