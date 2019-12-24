package me.ironexception.universalfriends.json;

/**
 * Represents the friend file's meta. This class carries friendliness values' maximum and minimum, which is defined as <code>2</code> and <code>-2</code>, respectively, by the standard.
 */
public class Meta {

    private final double minimum;
    private final double maximum;

    public Meta(double minimum, double maximum) {
        this.minimum = minimum;
        this.maximum = maximum;

        checkBounds();
    }

    private void checkBounds() throws IllegalStateException {
        if (maximum < minimum) {
            throw new IllegalStateException("Defined maximum is lower than defined minimum");
        }
    }

    /**
     * The assumed maximum value for friendliness values
     * @return  the maximum value
     */
    public double getMaximum() {
        return maximum;
    }

    /**
     * The assumed minimum value for friendliness values
     * @return  the minimum value
     */
    public double getMinimum() {
        return minimum;
    }

}
