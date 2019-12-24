package me.ironexception.universalfriends;

import com.mojang.authlib.GameProfile;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.person.IPerson;

import java.util.UUID;

public class GameProfilePerson implements IPerson {

    GameProfile profile;
    private double value;

    public GameProfilePerson(GameProfile profile, double value) {
        this.profile = profile;
        this.value = value;
    }

    public GameProfilePerson(GameProfile profile) {
        this(profile, Association.NEUTRAL.getValue());
    }

    public GameProfile getProfile() {
        return profile;
    }

    @Override
    public String getName() {
        return getProfile().getName();
    }

    @Override
    public UUID getId() {
        return getProfile().getId();
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Association getAssociation() {
        return Association.byValue(getValue());
    }

    @Override
    public void setAssociation(Association association) {
        this.value = association.getValue();
    }

}
