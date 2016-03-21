package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

public class Parser {
    private String fullExpression;
    private int index;
    private ArrayList<Expression> expressions = new ArrayList<Expression>();

    public Parser(String fullExpression) {
        this.fullExpression = fullExpression;
        this.index = 0;
    }

    public void parse() {
        while (index < fullExpression.length()) {
            if (isCharacterIndicator(index)) {
                index++;
                addCharacter();
            } else if (isDot(index)) {
                addDot();
            } else if (isOpeningSet(index)) {
                addSet(setClosingIndex());
            } else {
                addCharacter();
            }
            index++;
        }
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    private boolean isCharacterIndicator(int index) {
        if (fullExpression.charAt(index) != '\\') {
            return false;
        }
        if (lastPosition()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private boolean isDot(int index) {
        return fullExpression.charAt(index) == '.';
    }

    private boolean isOpeningSet(int index) {
        return fullExpression.charAt(index) == '[';
    }

    private boolean isClosingSet(int index) {
        return fullExpression.charAt(index) == ']';
    }

    private boolean lastPosition() {
        return index == fullExpression.length() - 1;
    }

    private int setClosingIndex() {
        if (lastPosition()) {
            throw new IllegalArgumentException();
        }
        int x = index + 1;

        while (x < fullExpression.length()) {
            if (isCharacterIndicator(x)) {
                x++;
            } else {
                if (isClosingSet(x)) {
                    return x;
                }
                if (isDot(x) || isOpeningSet(x)) {
                    break;
                }
            }
            x++;
        }
        throw new IllegalArgumentException();
    }

    private void addCharacter(ArrayList<Expression> characters) {
        characters.add(new Character(fullExpression.charAt(index)));
    }

    private void addCharacter() {
        addCharacter(this.expressions);
    }

    private void addDot() {
        expressions.add(new Dot());
    }

    private void addSet(int closingIndex) {
        ArrayList<Expression> characters = new ArrayList<Expression>();

        index++;
        while (index < closingIndex) {
            if (isCharacterIndicator(index)) {
                index++;
                addCharacter(characters);
            } else {
                addCharacter(characters);
            }
            index++;
        }
        expressions.add(new Set(characters));
    }
}
