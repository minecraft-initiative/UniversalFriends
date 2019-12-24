package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.person.IPerson;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Operations {

    public static <T extends IPerson> List<T> getByFriendliness(Configuration<T> configuration, double value) {
        return filterMatchingPersons(configuration, t -> t.getValue() == value);
    }

    public static <T extends IPerson> List<T> getByCloseToAssociation(Configuration<T> configuration, Association association) {
        return filterMatchingPersons(configuration, t -> Association.closestToValue(t.getValue()) == association);
    }

    public static <T extends IPerson> List<T> getByAssociation(Configuration<T> configuration, Association association) {
        double value = association.getValue();
        return getByFriendliness(configuration, value);
    }

    public static <T extends IPerson> List<T> getInFriendlinessRange(Configuration<T> configuration, double rangeLower, double rangeUpper) {
        return filterMatchingPersons(configuration, t -> t.getValue() >= rangeLower && t.getValue() <= rangeUpper);
    }

    public static <T extends IPerson> List<T> filterMatchingPersons(Configuration<T> configuration, Predicate<T> predicate) {
        return configuration.getList().stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * <p>
     *     Introduces a new {@link IPerson} to a {@link Configuration}, making sure the friendliness value bounds are satisfied.
     * </p>
     *
     * <p>
     *     If the friendliness value of the new {@link IPerson} exceeds the standardised bounds (minimum <code>-2</code> and maximum <code>2</code>), it will be scaled down to the nearest boundary.
     *     All other persons in this configuration will be scaled down by the same amount.
     * </p>
     *
     * For example, a friends list with the following friendliness values:
     * <ul>
     *     <li>1</li>
     *     <li>0</li>
     *     <li>-2</li>
     * </ul>
     * We introduce a new {@link IPerson} with a friendliness value of <code>4</code>:
     * <ul>
     *     <li>...</li>
     *     <li>4</li>
     * </ul>
     * Because <code>4</code> is out of bounds, it is brought down to the nearest boundary of value <code>2</code>. This represents a multiplication by <code>0.5</code>. All friendliness values within the friends list will be multiplied by that amount. We end up with a new list where all the proportions between friendliness values have stayed the same.
     * <ul>
     *     <li>0.5</li>
     *     <li>0</li>
     *     <li>-1</li>
     *     <li>2</li>
     * </ul>
     *
     * @param configuration The {@link Configuration} to mutate
     * @param person        The {@link IPerson} to add
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> introduceNewSafe(Configuration<T> configuration, T person) {
        Bounds bounds = configuration.getBounds();
        configuration.getList().add(person);
        double value = person.getValue();
        double multiplier;
        if (value < bounds.getMinimum()) {
            multiplier = bounds.getMinimum() / value;
        } else if (value > bounds.getMaximum()) {
            multiplier = bounds.getMaximum() / value;
        } else {
            return configuration; // This value is within bounds, we do not need to (re)scale the configuration.
        }

        multiplyFriendlinessValues(configuration, multiplier);
        return configuration;
    }

    /**
     * Halves every friendliness value within a configuration
     * @see Operations#multiplyFriendlinessValues(Configuration, double)
     * @param configuration The {@link Configuration} to mutate
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> halveFriendlinessValues(Configuration<T> configuration) {
        return multiplyFriendlinessValues(configuration, 0.5d);
    }

    /**
     * Mutates a configuration, multiplying all person's friendliness values by a given multiplier.
     * @param configuration The {@link Configuration} to mutate
     * @param multiplier    The multiplier to multiply the friendliness values by
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> multiplyFriendlinessValues(Configuration<T> configuration, double multiplier) {
        consume(configuration, t -> {
            t.setValue(t.getValue() * multiplier);
        });
        return configuration;
    }

    public static <T extends IPerson> Configuration<T> multiplyFriendlinessValuesInRange(Configuration<T> configuration, double multiplier, double rangeLower, double rangeUpper) {
        consumeWithFilter(configuration, t -> !(t.getValue() < rangeLower || t.getValue() > rangeUpper), t -> {
            t.setValue(t.getValue() * multiplier);
        });
        return configuration;
    }

    private static <T extends IPerson> void consume(Configuration<T> configuration, Consumer<T> consumer) {
        configuration.getList().forEach(consumer);
    }

    private static <T extends IPerson> void consumeWithFilter(Configuration<T> configuration, Predicate<T> predicate, Consumer<T> consumer) {
        configuration.getList().stream().filter(predicate).forEach(consumer);
    }

}
