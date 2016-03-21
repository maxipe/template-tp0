package ar.fiuba.tdd.template.tp0;

public class Character implements Expression {
    private char character;
    private boolean hasReturnedAllPossibilities = false;

    public Character(char character) {
        this.character = character;
    }

    @Override
    public String getString() {
        this.hasReturnedAllPossibilities = true;
        return java.lang.Character.toString(character);
    }

    @Override
    public boolean returnedAllPossibilities() {
        return this.hasReturnedAllPossibilities;
    }

    @Override
    public void resetPossibilities() {
        hasReturnedAllPossibilities = false;
    }
}
