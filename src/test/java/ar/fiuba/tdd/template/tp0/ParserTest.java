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


}