package ar.fiuba.tdd.template.tp0;

public class Dot implements Expression {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890`[]\\;',./~!@#$%^&*()_+{}|:\"<>?";
    private int currentIndex = 0;

    @Override
    public char getCharacter() {
        if (currentIndex == alphabet.length()) {
            return alphabet.charAt(currentIndex - 1);
        }
        char character = alphabet.charAt(currentIndex);
        currentIndex++;
        return character;
    }

    @Override
    public boolean returnedAllPossibilities() {
        return currentIndex == alphabet.length();
    }
}
