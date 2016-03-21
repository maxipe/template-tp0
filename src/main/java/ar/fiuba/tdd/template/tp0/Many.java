package ar.fiuba.tdd.template.tp0;

public class Many implements Expression {
    private Expression expression;
    private boolean minimumOne;
    private int numberOfRepetitions;

    public Many(Expression expression, boolean minimumOne) {
        this.expression = expression;
        this.minimumOne = minimumOne;
        this.numberOfRepetitions = minimumOne ? 1 : 0;;
    }

    public Many(Expression expression) {
        this(expression, false);
    }

    @Override
    public String getString() {
        String string = expression.getString();
        if (expression.returnedAllPossibilities()) {
            numberOfRepetitions++;
            expression.resetPossibilities();
        }
        return new String(new char[numberOfRepetitions]).replace("\0", string);
    }

    @Override
    public boolean returnedAllPossibilities() {
        return false;
    }

    @Override
    public void resetPossibilities() {
        numberOfRepetitions = minimumOne ? 1 : 0;
        expression.resetPossibilities();
    }
}
