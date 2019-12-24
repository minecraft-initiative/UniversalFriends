package me.ironexception.universalfriends.friend;

import me.ironexception.universalfriends.association.Association;

import java.util.UUID;

/**
 * The outline for all people in the API.
 */
public interface IPerson {

    /**
     * @return Tells the name of the person.
     */
    String getName();

    /**
     * @return The person's Universal Unique Identifier.
     */
    UUID getId();

    /**
     * @return The user's association with the person.
     */
    Association getAssociation();

    /**
     * Changes the user's association with the person.
     *
     * @param association The new association.
     */
    void setAssociation(Association association);
}
