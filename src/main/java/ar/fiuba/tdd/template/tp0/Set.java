package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

public class Set implements Expression{
    private ArrayList<Expression> characters;
    private int currentIndex = 0;

    public Set(ArrayList<Expression> characters) {
        this.characters = new ArrayList<Expression>(characters);
    }

    @Override
    public char getCharacter() {
        if (currentIndex == characters.size()) {
            return characters.get(currentIndex - 1).getCharacter();
        }
        char character =  characters.get(currentIndex).getCharacter();
        currentIndex++;
        return character;
    }

    @Override
    public boolean returnedAllPossibilities() {
        return currentIndex == characters.size();
    }
}
