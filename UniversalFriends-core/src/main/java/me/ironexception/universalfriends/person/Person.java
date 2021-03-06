package me.ironexception.universalfriends.person;

import com.google.gson.JsonObject;
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

    /**
     * The metadata for this person
     */
    private final JsonObject meta;

    public Person(final UUID id, final String name, final double value, JsonObject meta) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.meta = meta;
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
    public JsonObject getMeta() {
        return meta;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(final double value) {
        this.value = value;
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

    /**
     * Converts the enum into the value.
     *
     * @param association The new association.
     */
    @Override
    public void setAssociation(final Association association) {
        this.value = association.getValue();
    }
}
