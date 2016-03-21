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
        assertEquals(expressions.get(0).getString(), "a");
        assertEquals(expressions.get(1).getString(), "b");
        assertEquals(expressions.get(2).getString(), "c");
    }

    @Test
    public void testParseCharactersOnly() throws Exception {
        Parser parser = new Parser("a\\bc");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertEquals(expressions.get(0).getString(), "a");
        assertEquals(expressions.get(1).getString(), "b");
        assertEquals(expressions.get(2).getString(), "c");
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
        assertEquals(expressions.get(0).getString(), "a");
        assertEquals(expressions.get(1).getString(), ".");
        assertTrue(expressions.get(2) instanceof Dot);
        assertEquals(expressions.get(3).getString(), "b");
    }

    @Test
    public void testParseSetSimpleCharactersOnly() throws Exception {
        Parser parser = new Parser("[abc]");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof Set);
        Set set = (Set)expressions.get(0);
        assertEquals(set.getString(), "a");
        assertEquals(set.getString(), "b");
        assertEquals(set.getString(), "c");
    }

    @Test
    public void testParseSetOnly() throws Exception {
        Parser parser = new Parser("[a\\bc]");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof Set);
        Set set = (Set)expressions.get(0);
        assertEquals(set.getString(), "a");
        assertEquals(set.getString(), "b");
        assertEquals(set.getString(), "c");
    }

    @Test
    public void testParseDotsSetsAndCharacters() throws Exception {
        Parser parser = new Parser(".[a\\bc]q\\\\");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof Dot);
        assertTrue(expressions.get(1) instanceof Set);

        Set set = (Set)expressions.get(1);
        assertEquals(set.getString(), "a");
        assertEquals(set.getString(), "b");
        assertEquals(set.getString(), "c");

        assertEquals(expressions.get(2).getString(), "q");
        assertEquals(expressions.get(3).getString(), "\\");
    }

    @Test
    public void testParser() throws Exception {
        Parser parser = new Parser(".?[a\\bc]+q\\\\*");
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();
        assertTrue(expressions.get(0) instanceof ZeroOrOne);
        assertTrue(expressions.get(1) instanceof Many);
        assertEquals(expressions.get(2).getString(), "q");
        assertTrue(expressions.get(3) instanceof Many);
    }

}