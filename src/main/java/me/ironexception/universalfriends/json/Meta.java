package me.ironexception.universalfriends.json;

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

    public double getMaximum() {
        return maximum;
    }

    public double getMinimum() {
        return minimum;
    }

}
