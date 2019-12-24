package me.ironexception.universalfriends.association;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {

    @Test
    @DisplayName("Grab associations by value")
    void byValue() {
        assertEquals(Association.ALLY, Association.byValue(1), "ByValue works for allies");
        assertEquals(Association.NEUTRAL, Association.byValue(0), "ByValue works for neutrals");
        assertEquals(Association.ENEMY, Association.byValue(-1), "ByValue works for enemies");
    }

}