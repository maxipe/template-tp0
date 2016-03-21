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
            if (isCharacterIndicator()) {
                index++;
                addCharacter();

            } else if (isDot()) {
                addDot();
            } else {
                addCharacter();
            }
            index++;
        }
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    private boolean isCharacterIndicator() {
        if (fullExpression.charAt(index) != '\\') {
            return false;
        }
        if (lastPosition()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private boolean isDot() {
        return fullExpression.charAt(index) == '.';
    }

    private boolean isOpeningSet(char character) {
        return fullExpression.charAt(index) == '[';
    }

    private boolean isClosingSet(char character) {
        return fullExpression.charAt(index) == ']';
    }

    private boolean lastPosition() {
        return index == fullExpression.length() - 1;
    }

    private void addCharacter() {
        expressions.add(new Character(fullExpression.charAt(index)));
    }

    private void addDot() {
        expressions.add(new Dot());
    }

}
