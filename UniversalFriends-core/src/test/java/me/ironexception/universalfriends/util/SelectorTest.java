package me.ironexception.universalfriends.util;

import me.ironexception.universalfriends.TestUtil;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.json.FriendFileLoader;
import me.ironexception.universalfriends.json.FriendFileLoaderException;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SelectorTest {

    private static Configuration<Person> configuration;

    @Test
    @BeforeAll
    public static void init() throws URISyntaxException, IOException, FriendFileLoaderException {
        configuration = FriendFileLoader.defaultLoader()
                .withPath(TestUtil.getPath("friends_selections.json"))
                .load();
    }

    private static boolean setHasNames(Set<Person> set, String... names) {
        return set.stream().map(Person::getName).collect(Collectors.toSet()).equals(new HashSet<>(Arrays.asList(names)));
    }

    @Test
    @DisplayName("Friends")
    void friends() {
        assertTrue(setHasNames(
                Selector.create(configuration).friends().selectMatchingAll(),
                "John", "Mark"));
    }

    @Test
    @DisplayName("Enemies")
    void enemies() {
        assertTrue(setHasNames(
                Selector.create(configuration).enemies().selectMatchingAll(),
                "Ben", "Jack"));
    }

    @Test
    @DisplayName("Exact value")
    void exactValue() {
        assertTrue(setHasNames(
                Selector.create(configuration).exactValue(-1).selectMatchingAll(),
                "Ben"));
    }

    @Test
    @DisplayName("Association")
    void association() {
        assertTrue(setHasNames(
                Selector.create(configuration).association(Association.NEUTRAL).selectMatchingAll(),
                "Alice"));
    }

    @Test
    @DisplayName("Match all")
    void selectMatchingAll() {
        assertTrue(setHasNames(
                Selector.create(configuration).enemies().friends().selectMatchingAll()
        )); // No results, because no one is both a friend and an enemy.
    }

    @Test
    @DisplayName("None")
    void selectMatchingNone() {
        assertTrue(setHasNames(
                Selector.create(configuration).enemies().friends().selectMatchingNone(),
                "Alice")); // Only alice isn't an enemy or a friend.
    }

    @Test
    @DisplayName("Any")
    void selectMatchingAny() {
        assertTrue(setHasNames(
                Selector.create(configuration).enemies().friends().selectMatchingAny(),
                "John", "Ben", "Mark", "Jack")); // Only alice isn't an enemy or a friend.
    }
}