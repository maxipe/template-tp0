package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {
    private int maxLength;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        if (maxLength <= 0) {
            return null;
        }
        ArrayList<String> strings = new ArrayList<String>();

        Parser parser = new Parser(regEx);
        parser.parse();
        ArrayList<Expression> expressions = parser.getExpressions();

        for (int number = 0; number < numberOfResults; number++) {
            StringBuilder string = new StringBuilder();
            expressions.forEach((expression) -> {
                    string.append(expression.getString());
                }
            );
            strings.add(string.toString());
        }

        return strings;
    }
}