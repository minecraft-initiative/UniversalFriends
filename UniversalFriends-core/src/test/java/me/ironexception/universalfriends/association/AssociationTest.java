package me.ironexception.universalfriends.association;

import me.ironexception.universalfriends.Standard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {

    @Test
    @DisplayName("Grab associations by value")
    void byValue() {
        assertEquals(Association.ALLY, Association.byExactValue(Standard.STANDARD_ALLY), "ByValue works for allies");
        assertEquals(Association.NEUTRAL, Association.byExactValue(Standard.STANDARD_NEUTRAL), "ByValue works for neutrals");
        assertEquals(Association.ENEMY, Association.byExactValue(Standard.STANDARD_ENEMY), "ByValue works for enemies");
    }

}