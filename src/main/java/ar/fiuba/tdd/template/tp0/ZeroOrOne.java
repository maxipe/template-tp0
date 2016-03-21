package ar.fiuba.tdd.template.tp0;

public class ZeroOrOne implements Expression {
    private Expression expression;
    private boolean hasUsedAllPossibilitiesOnce = false;
    private boolean hasReturnedAllPossibilities = false;

    public ZeroOrOne(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getString() {
        String string = expression.getString();
        if (!hasUsedAllPossibilitiesOnce) {
            string = "";
        }
        if (expression.returnedAllPossibilities()) {
            if (hasUsedAllPossibilitiesOnce) {
                hasReturnedAllPossibilities = true;
            } else {
                hasUsedAllPossibilitiesOnce = true;
                expression.resetPossibilities();
            }
        }
        return string;
    }

    @Override
    public boolean returnedAllPossibilities() {
        return hasReturnedAllPossibilities;
    }

    @Override
    public void resetPossibilities() {
        hasUsedAllPossibilitiesOnce = false;
        hasReturnedAllPossibilities = false;
        expression.resetPossibilities();
    }
}
