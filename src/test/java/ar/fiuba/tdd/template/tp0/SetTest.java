package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Maximiliano on 21/03/2016.
 */
public class SetTest {

    private Set initializeSet() {
        Character first = new Character('a');
        Character second = new Character('b');
        Character third = new Character('c');
        ArrayList<Expression> characters = new ArrayList<Expression>() {
            {
                add(first);
                add(second);
                add(third);
            }
        };
        return new Set(characters);
    }

    @Test
    public void testGetCharacter() throws Exception {
        Set set = initializeSet();
        assertEquals(set.getCharacter(), 'a');
        assertEquals(set.getCharacter(), 'b');
    }

    @Test
    public void testReturnedAllPossibilitiesIsFalse() throws Exception {
        Set set = initializeSet();
        assertFalse(set.returnedAllPossibilities());
    }

    @Test
    public void testReturnedAllPossibilitiesIsTrue() throws Exception {
        Set set = initializeSet();
        set.getCharacter();
        set.getCharacter();
        set.getCharacter();
        assertTrue(set.returnedAllPossibilities());
    }
}