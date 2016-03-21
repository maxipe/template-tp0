package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

public class Set implements Expression{
    private ArrayList<Expression> characters;
    private int currentIndex = 0;

    public Set(ArrayList<Expression> characters) {
        this.characters = new ArrayList<Expression>(characters);
    }

    @Override
    public String getString() {
        if (currentIndex == characters.size()) {
            return characters.get(currentIndex - 1).getString();
        }
        String string =  characters.get(currentIndex).getString();
        currentIndex++;
        return string;
    }

    @Override
    public boolean returnedAllPossibilities() {
        return currentIndex == characters.size();
    }

    @Override
    public void resetPossibilities() {
        currentIndex = 0;
    }
}
