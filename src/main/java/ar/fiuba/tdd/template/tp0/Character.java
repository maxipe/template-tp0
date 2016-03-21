package ar.fiuba.tdd.template.tp0;

public class Character implements Expression {
    private char character;
    private boolean hasReturnedAllPossibilities = false;

    public Character(char character) {
        this.character = character;
    }

    @Override
    public char getCharacter() {
        this.hasReturnedAllPossibilities = true;
        return character;
    }

    @Override
    public boolean returnedAllPossibilities() {
        return this.hasReturnedAllPossibilities;
    }
}
