package me.ironexception.universalfriends;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.person.IPerson;

import java.util.UUID;

public class GameProfilePerson implements IPerson {

    GameProfile profile;
    private double value;
    private JsonObject meta;

    public GameProfilePerson(GameProfile profile, double value, JsonObject meta) {
        this.profile = profile;
        this.value = value;
        this.meta = meta;
    }

    public GameProfilePerson(GameProfile profile, double value) {
        this(profile, value, null);
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
    public JsonObject getMeta() {
        return meta;
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
