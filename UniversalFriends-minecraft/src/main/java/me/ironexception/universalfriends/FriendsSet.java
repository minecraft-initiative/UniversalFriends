package me.ironexception.universalfriends;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class FriendsSet extends HashSet<GameProfilePerson> {

    // package-private by design
    Set<Consumer<GameProfilePerson>> addCallbacks;
    Set<Consumer<GameProfilePerson>> removeCallbacks;

    public FriendsSet(Collection<? extends GameProfilePerson> c) {
        super(c);

        addCallbacks = new HashSet<>();
        removeCallbacks = new HashSet<>();
    }

    /**
     * Adds a GameProfilePerson representing a friend's profile to user's profile
     *
     * @param gameProfilePerson GameProfilePerson to be added to as a friend to user's account
     * @return boolean Gives caller indication of successful add of GameProfilePerson to their account
     */
    @Override
    public boolean add(GameProfilePerson gameProfilePerson) {
        boolean added = super.add(gameProfilePerson);
        if (added && addCallbacks != null) { // addCallbacks is initialised in the constructor, but addAll (-> add) is called in the superconstructor of this class. Hence, we need to check if addCallbacks has been initialised by now.
            addCallbacks.forEach(consumer -> consumer.accept(gameProfilePerson));
        }
        return added;
    }

    /**
     * Removes a GameProfilePerson representing a friend from user's profile
     *
     * @param o Represents a GameProfilePerson to be removed
     * @return boolean Gives caller indication of successful removal of GameProfilePerson from their account
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            // Considering we can only add objects of type GameProfilePerson, we assume this cast will succeed.
            // If anyone did dirty reflection tricks to add something illegal to our set, that's their problem.
            removeCallbacks.forEach(consumer -> consumer.accept((GameProfilePerson) o));
        }
        return super.remove(o);
    }

}
