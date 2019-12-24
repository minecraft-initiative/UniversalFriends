package me.ironexception.universalfriends.friend;

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
     * The association that the person has to the user.
     */
    private Association association;

    public Person(UUID id, String name, Association association) {
        this.id = id;
        this.name = name;
        this.association = association;
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
    public Association getAssociation() {
        return association;
    }

    @Override
    public void setAssociation(Association association) {
        this.association = association;
    }
}
