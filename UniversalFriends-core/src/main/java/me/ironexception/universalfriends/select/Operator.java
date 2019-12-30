package me.ironexception.universalfriends.select;

import me.ironexception.universalfriends.Standard;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.IPerson;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A utility class to perform a selection on the friend list of a {@link Configuration}.
 * @param <P>   The type of {@link IPerson} the {@link Configuration} holds
 * @param <T>   The type of {@link Configuration} to select in
 */
public class Operator<P extends IPerson, T extends Configuration<P>> {

    private final T configuration;
    private final Set<Predicate<P>> predicates;

    private Operator(T configuration) {
        this.configuration = configuration;
        this.predicates = new HashSet<>();
    }

    public static <P extends IPerson, T extends Configuration<P>> Operator<P, T> create(T configuration) {
        return new Operator<>(configuration);
    }

    /**
     * Select only {@link IPerson} instances with a value exclusively higher than {@link Standard#STANDARD_NEUTRAL}
     * @return  The mutated {@link Operator}
     */
    public Operator<P, T> friends() {
        predicates.add(p -> p.getValue() > Standard.STANDARD_NEUTRAL);
        return this;
    }

    /**
     * Select only {@link IPerson} instances with a value exclusively lower than {@link Standard#STANDARD_NEUTRAL}
     * @return  The mutated {@link Operator}
     */
    public Operator<P, T> enemies() {
        predicates.add(p -> p.getValue() < Standard.STANDARD_NEUTRAL);
        return this;
    }

    /**
     * Select only {@link IPerson} instances with a value exactly equal to the provided value
     * @param value The friendliness value to select for
     * @return      The mutated {@link Operator}
     */
    public Operator<P, T> exactValue(double value) {
        predicates.add(p -> p.getValue() == value);
        return this;
    }

    /**
     * Select only {@link IPerson} instances whose association is equal to the provided association
     * @param association   The association to select for
     * @return              The mutated {@link Operator}
     * @see IPerson#getAssociation()
     */
    public Operator<P, T> association(Association association) {
        predicates.add(p -> p.getAssociation() == association);
        return this;
    }

    /**
     * Returns a set of {@link IPerson} instances from this {@link Operator}'s {@link Configuration} that match all selection predicates
     * @return  The {@link Set} the selection provided
     */
    public Set<P> selectMatchingAll() {
        return select(p -> predicates.stream().allMatch(predicate -> predicate.test(p)));
    }

    /**
     * Returns a set of {@link IPerson} instances from this {@link Operator}'s {@link Configuration} that match none of the selection predicates
     * @return  The {@link Set} the selection provided
     */
    public Set<P> selectMatchingNone() {
        return select(p -> predicates.stream().noneMatch(predicate -> predicate.test(p)));
    }

    /**
     * Returns a set of {@link IPerson} instances from this {@link Operator}'s {@link Configuration} that match any of the selection predicates
     * @return  The {@link Set} the selection provided
     */
    public Set<P> selectMatchingAny() {
        return select(p -> predicates.stream().anyMatch(predicate -> predicate.test(p)));
    }

    private Set<P> select(Predicate<P> predicate) {
        return Collections.unmodifiableSet(this.configuration.getFriendList().stream().filter(predicate).collect(Collectors.toSet()));
    }

}
