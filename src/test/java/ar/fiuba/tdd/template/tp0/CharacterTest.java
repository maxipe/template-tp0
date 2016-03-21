package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void testGetCharacter() throws Exception {
        Character character = new Character('a');
        assertEquals(character.getString(), "a");
    }

    @Test
    public void testReturnedAllPossibilitiesIsFalse() throws Exception {
        Character character = new Character('a');
        assertFalse(character.returnedAllPossibilities());
    }

    @Test
    public void testReturnedAllPossibilitiesIsTrue() throws Exception {
        Character character = new Character('a');
        character.getString();
        assertTrue(character.returnedAllPossibilities());
    }
}