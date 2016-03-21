package ar.fiuba.tdd.template.tp0;

public class Dot implements Expression {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890`[]\\;',./~!@#$%^&*()_+{}|:\"<>?";
    private int currentIndex = 0;

    @Override
    public String getString() {
        if (currentIndex == alphabet.length()) {
            return java.lang.Character.toString(alphabet.charAt(currentIndex - 1));
        }
        char character = alphabet.charAt(currentIndex);
        currentIndex++;
        return java.lang.Character.toString(character);
    }

    @Override
    public boolean returnedAllPossibilities() {
        return currentIndex == alphabet.length();
    }

    @Override
    public void resetPossibilities() {
        currentIndex = 0;
    }
}
