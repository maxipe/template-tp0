package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

public class Set implements Expression{
    private ArrayList<Character> characters;
    private int currentIndex = 0;

    public Set(ArrayList<Character> characters) {
        this.characters = new ArrayList<Character>(characters);
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
