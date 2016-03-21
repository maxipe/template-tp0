package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {
    private static final int MAX_LENGTH = 50;

    private boolean validate(String regEx, int numberOfResults) {
        RegExGenerator generator = new RegExGenerator(MAX_LENGTH);

        List<String> results = generator.generate(regEx, numberOfResults);
        // force matching the beginning and the end of the strings
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        return results
                .stream()
                .reduce(true,
                    (acc, item) -> {
                        Matcher matcher = pattern.matcher(item);
                        return acc && matcher.find();
                    },
                    (item1, item2) -> item1 && item2);
    }

    @Test
    public void testAnyCharacter() {
        assertTrue(validate(".", 1));
    }

    @Test
    public void testMultipleCharacters() {
        assertTrue(validate("...", 1));
    }

    @Test
    public void testLiteral() {
        assertTrue(validate("\\@", 1));
    }

    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 1));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 1));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 1));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 1));
    }

    @Test
    public void testMixedExpression() {
        assertTrue(validate("[abc]+gk?j.+p*[aqw]+", 1));
    }

    private boolean differentResults(String regEx, int numberOfResults) {
        RegExGenerator generator = new RegExGenerator(MAX_LENGTH);
        List<String> results = generator.generate(regEx, numberOfResults);
        java.util.Set<String> uniqueResults = new HashSet<>(results);
        return results.size() == uniqueResults.size();
    }

    @Test
    public void testAnyCharacterDifferentResults() {
        assertTrue(differentResults(".", 20));
    }

    @Test
    public void testZeroOrOneDifferentResults() {
        assertTrue(differentResults("a?", 2));
    }

    @Test
    public void testZeroOrOneMoreResultsThanPosible() {
        assertFalse(differentResults("a?", 3));
    }

    @Test
    public void testZeroOrManyDifferentResults() {
        assertTrue(differentResults("a*", 5));
    }

    @Test
    public void testOneOrManyDifferentResults() {
        assertTrue(differentResults("a+", 5));
    }

    @Test
    public void testComplexRegExDifferentResults() {
        assertTrue(differentResults("a*q+[lat]?f\\l\\\\+w?.q", 60));
    }
}
