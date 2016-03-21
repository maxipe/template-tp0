package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import static org.junit.Assert.*;

public class DotTest {

    @Test
    public void testGetCharacter() throws Exception {
        Dot dot = new Dot();
        assertEquals(dot.getString(), "A");
    }

    @Test
    public void testGetDifferentCharacter() throws Exception {
        Dot dot = new Dot();
        dot.getString();
        assertNotEquals(dot.getString(), 'A');
    }

    @Test
    public void testReturnedAllPossibilitiesIsTrue() throws Exception {
        Dot dot = new Dot();
        for (int x = 0; x < 100; x++) {
            dot.getString();
        }
        assertTrue(dot.returnedAllPossibilities());
    }

    @Test
    public void testReturnedAllPossibilitiesIsFalse() throws Exception {
        Dot dot = new Dot();
        assertFalse(dot.returnedAllPossibilities());
    }
}