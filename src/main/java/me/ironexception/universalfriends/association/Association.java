package me.ironexception.universalfriends.association;

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
    ALLY(1),

    /**
     * Targeted upon.
     */
    ENEMY(-1),

    /**
     * Acts as any other player.
     */
    NEUTRAL(0);

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
     * Fetch a {@link Association} by its value.
     *
     * @param value The friendliness value
     * @return The association related to the friendliness value. <code>null</code> if there is no {@link Association} with the provided value.
     * @author 086
     */
    public static Association byValue(double value) {
        return Arrays.stream(values()).filter(association -> association.getValue() == value).findAny().orElseGet(null);
    }

    /**
     * Fetch the {@link Association} closest to a friendliness value.
     * <p>Edge cases: <code>0.5</code> becomes {@link Association#ALLY} and <code>-0.5</code> becomes {@link Association#ENEMY}</p>
     * @param value The friendliness value
     * @return      The {@link Association} with a friendliness value closest to the provided value
     */
    public static Association closestToValue(double value) {
        if (value == Double.MIN_VALUE) return Association.ENEMY;
        if (value == Double.MAX_VALUE) return Association.ALLY;
        return Arrays.stream(values()).min(Comparator.comparingDouble(a -> Math.abs(a.value - value))).get();
    }

}
