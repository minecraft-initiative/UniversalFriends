package me.ironexception.universalfriends.player;

import me.ironexception.universalfriends.association.Association;

import java.util.UUID;

/**
 * The "object" class for a person.
 *
 * <p>
 * Friends are immutable!
 * </p>
 */
public class Person implements IPerson {

    /**
     * The unique identifier for the person.
     */
    private final UUID id;

    /**
     * The person's name.
     */
    private final String name;

    /**
     * The value that the person has to the user.
     */
    private double value;

    public Person(final UUID id, final String name, final double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getValue() {
        return value;
    }

    /**
     * Basically converts the value into a usable enum.
     *
     * @return the association to another player.
     */
    @Override
    public Association getAssociation() {
        return getValue() == 0 ? Association.NEUTRAL : (getValue() > 0) ? Association.ALLY : Association.ENEMY;
    }

    @Override
    public void setValue(final double value) {
        this.value = value;
    }


    /**
     * Converts the enum into the value.
     *
     * @param association The new association.
     */
    @Override
    public void setAssociation(final Association association) {
        if(association == Association.NEUTRAL)
            value = 0;
        else if(association == Association.ALLY)
            value = 1;
        else if(association == Association.ENEMY)
            value = -1;
    }
}
