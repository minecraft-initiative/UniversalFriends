package me.ironexception.universalfriends.person;

import com.google.gson.JsonObject;
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
     * @return The person's metadata
     */
    JsonObject getMeta();

    /**
     * @return The user's value to the person.
     */
    double getValue();

    /**
     * Changes the user's value to the person.
     *
     * @param value The new value.
     */
    void setValue(double value);

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
