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
        return isZeroOrOne(index) || isZeroOrMany(index) || isOneOrMany(index);
    }

    private boolean isZeroOrOne(int index) {
        return compareChar(index,'?');
    }

    private boolean isZeroOrMany(int index) {
        return compareChar(index,'*');
    }

    private boolean compareChar(int index, char symbol) {
        return fullExpression.charAt(index) == symbol;
    }

    private boolean isOneOrMany(int index) {
        return compareChar(index,'+');
    }

    private boolean isCharacterIndicator(int index) {
        if (fullExpression.charAt(index) != '\\') {
            return false;
        }
        verifyItIsNotLastPosition();
        return true;
    }

    private boolean isDot(int index) {
        return compareChar(index,'.');
    }

    private boolean isOpeningSet(int index) {
        return compareChar(index,'[');
    }

    private boolean isClosingSet(int index) {
        return compareChar(index,']');
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
        if (index == 0) {
            throw new IllegalArgumentException();
        }

        Expression expression = expressions.remove(expressions.size() - 1);
        if (isZeroOrOne(index)) {
            expressions.add(new ZeroOrOne(expression));
        } else if (isZeroOrMany(index)) {
            expressions.add(new Many(expression));
        } else {
            expressions.add(new Many(expression, true));
        }
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
