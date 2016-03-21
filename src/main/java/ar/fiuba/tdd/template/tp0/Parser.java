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
            if (isQuantifier(index)) {
                addQuantifier();
            } else if (isCharacterIndicator(index)) {
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

    private boolean isQuantifier(int index) {
        char character = fullExpression.charAt(index);
        return character == '?' || character == '*' || character == '+';
    }

    private boolean isCharacterIndicator(int index) {
        if (fullExpression.charAt(index) != '\\') {
            return false;
        }
        verifyItIsNotLastPosition();
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

    private void verifyItIsNotLastPosition() {
        if (lastPosition()) {
            throw new IllegalArgumentException();
        }
    }

    private int setClosingIndex() {
        verifyItIsNotLastPosition();

        int closingIndex = index + 1;

        while (closingIndex < fullExpression.length()) {
            if (isCharacterIndicator(closingIndex)) {
                closingIndex++;
            } else {
                if (isClosingSet(closingIndex)) {
                    return closingIndex;
                }
                if (isDot(closingIndex) || isOpeningSet(closingIndex)) {
                    break;
                }
            }
            closingIndex++;
        }
        throw new IllegalArgumentException();
    }

    private void addQuantifier() {

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
