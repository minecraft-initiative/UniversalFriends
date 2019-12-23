package me.ironexception.universal.friends;


import java.util.UUID;

/**
 * Makes it possible to test whether a player is a friend, enemy or something in between.
 *
 * <p>
 * Therefore you have these methods:
 * - addFriend
 * - addEnemy
 * - setNeutral
 * - setValue
 * <p>
 * and functions:
 * - isFriend
 * - isEnemy
 * - isNeutral
 * - getValue
 * </p>
 */
public class UniversalFriends {

    /**
     * // TODO
     */
    public UniversalFriends() {

    }

    /**
     * Saves that the player behind this name is your friend.
     * Behind the scenes this is saving that this name has the neutral friend value 1.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as a friend.
     * </p>
     *
     * @param name of the player you want to add as a friend.
     */
    public void addFriend(String name) {
        setValue(name, 1);
    }

    /**
     * Saves that the player behind this uuid is your friend.
     * Behind the scenes this is saving that this uuid has the neutral friend value 1.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as a friend.
     * </p>
     *
     * @param uuid of the player you want to add as a friend.
     */
    public void addFriend(UUID uuid) {
        setValue(uuid, 1);
    }


    /**
     * Saves that the player behind this name is your enemy.
     * Behind the scenes this is saving that this name has the neutral enemy value -1.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as an enemy.
     * </p>
     *
     * @param name of the player you want to add as an enemy.
     */
    public void addEnemy(String name) {
        setValue(name, -1);
    }

    /**
     * Saves that the player behind this uuid is your enemy.
     * Behind the scenes this is saving that this uuid has the neutral enemy value -1.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as an enemy.
     * </p>
     *
     * @param uuid of the player you want to add as an enemy.
     */
    public void addEnemy(UUID uuid) {
        setValue(uuid, -1);
    }


    /**
     * Saves that the player behind this name is neutral.
     * Behind the scenes this is saving that this name has the neutral value 0.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as an enemy.
     * </p>
     *
     * @param name of the player you want to save as neutral.
     */
    public void setNeutral(String name) {
        setValue(name, 0);
    }

    /**
     * Saves that the player behind this uuid is neutral.
     * Behind the scenes this is saving that this uuid has the neutral value 0.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the player is added as an enemy.
     * </p>
     *
     * @param uuid of the player you want to save as neutral.
     */
    public void setNeutral(UUID uuid) {
        setValue(uuid, 0);
    }


    /**
     * Saves the value that the player behind this name should have.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the players value is changed.
     * </p>
     *
     * @param name of the player you want to change the value of.
     */
    public void setValue(String name, float friendValue) {
        // TODO implement
    }

    /**
     * Saves the value that the player behind this uuid should have.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures the players value is changed.
     * </p>
     *
     * @param uuid of the player you want to change the value of.
     */
    public void setValue(UUID uuid, float friendValue) {
        // TODO implement
    }


    /**
     * Returns whether the player behind the name is marked as your friend or not.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     * Ensures that true is returned when the player is a friend.
     *
     * @param name of the player you want to know is a friend.
     */
    public boolean isFriend(String name) {
        return getValue(name) > 0;
    }

    /**
     * Returns whether the player behind the uuid is marked as your friend or not.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player is a friend.
     * </p>
     *
     * @param uuid of the player you want to know is a friend.
     */
    public boolean isFriend(UUID uuid) {
        return getValue(uuid) > 0;
    }


    /**
     * Returns whether the player behind the name is marked as your enemy or not.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player is a enemy.
     * </p>
     *
     * @param name of the player you want to know is an enemy.
     */
    public boolean isEnemy(String name) {
        return getValue(name) < 0;
    }

    /**
     * Returns whether the player behind the uuid is marked as your enemy or not.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player is a enemy.
     * </p>
     *
     * @param uuid of the player you want to know is an enemy.
     */
    public boolean isEnemy(UUID uuid) {
        return getValue(uuid) < 0;
    }


    /**
     * Returns whether the player behind the name is neutral.
     * (players that have no value assigned to are neutral by default)
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player is neutral.
     * </p>
     *
     * @param name of the player you want to know is neutral.
     */
    public boolean isNeutral(String name) {
        return getValue(name) == 0;
    }

    /**
     * Returns whether the player behind the uuid is neutral.
     * (players that have no value assigned to are neutral by default)
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player is neutral.
     * </p>
     *
     * @param uuid of the player you want to know is neutral.
     */
    public boolean isNeutral(UUID uuid) {
        return getValue(uuid) == 0;
    }


    /**
     * Returns the value of the player behind the name.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that the value of the player is returned.
     * </p>
     *
     * @param name of the player you want to know the value of.
     */
    public float getValue(String name) {
        // TODO implement
        return 0;
    }

    /**
     * Returns the value of the player behind the uuid.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that the value of the player is returned.
     * </p>
     *
     * @param uuid of the player you want to know the value of.
     */
    public float getValue(UUID uuid) {
        // TODO implement
        return 0;
    }


    /**
     * Returns whether the player behind the name has a value assigned to it.
     *
     * <p>
     * Requires that the inputted name exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player doesn't have a value assigned to it.
     * </p>
     *
     * @param name of the player you want to know has a value.
     */
    public boolean hasAValue(String name) {
        // TODO implement
        return false;
    }

    /**
     * Returns whether the player behind the uuid has a value assigned to it.
     *
     * <p>
     * Requires that the inputted uuid exists and belongs to a minecraft player.
     * </p>
     *
     * <p>
     * Ensures that true is returned when the player doesn't have a value assigned to it.
     * </p>
     *
     * @param uuid of the player you want to know has a value.
     */
    public boolean hasAValue(UUID uuid) {
        // TODO implement
        return false;
    }


}
