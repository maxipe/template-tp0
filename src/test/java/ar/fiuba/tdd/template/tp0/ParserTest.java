package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testParseSimpleCharactersOnly() throws Exception {
        Parser parser = new Parser("abc");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertEquals(expressions.get(0).getCharacter(), 'a');
        assertEquals(expressions.get(1).getCharacter(), 'b');
        assertEquals(expressions.get(2).getCharacter(), 'c');
    }

    @Test
    public void testParseCharactersOnly() throws Exception {
        Parser parser = new Parser("a\\bc");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertEquals(expressions.get(0).getCharacter(), 'a');
        assertEquals(expressions.get(1).getCharacter(), 'b');
        assertEquals(expressions.get(2).getCharacter(), 'c');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidCharacter() throws Exception {
        Parser parser = new Parser("ab\\");
        parser.parse();
    }

    @Test
    public void testParseDotOnly() throws Exception {
        Parser parser = new Parser(".");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof Dot);
    }

    @Test
    public void testParseDotsOnly() throws Exception {
        Parser parser = new Parser("...");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof Dot);
        assertTrue(expressions.get(1) instanceof Dot);
        assertTrue(expressions.get(2) instanceof Dot);
    }

    @Test
    public void testParseCharactersAndDots() throws Exception {
        Parser parser = new Parser("a\\..b");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertEquals(expressions.get(0).getCharacter(), 'a');
        assertEquals(expressions.get(1).getCharacter(), '.');
        assertTrue(expressions.get(2) instanceof Dot);
        assertEquals(expressions.get(3).getCharacter(), 'b');
    }

}