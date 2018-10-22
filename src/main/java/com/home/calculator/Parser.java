package com.home.calculator;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private String SIMPLE_OPERATORS = "+-";
    private String COMPLEX_OPERATORS = "*/";

    private List<String> stackOperations = new ArrayList<>();
    private List<String> stackRPN = new ArrayList<>();

    public String parse(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            String token = String.valueOf(expression.charAt(i));

            if (isOpenBracket(token)) {
                stackOperations.add(token);
            } else if (isCloseBracket(token)) {
                pushStackToRPN();
            } else if (isNumber(token)) {
                stackRPN.add(token);
            } else if (isOperator(token)) {
                if ((COMPLEX_OPERATORS.contains(token) && isOperationStackContainsSimpleOperator()) ||
                        (SIMPLE_OPERATORS.contains(token) && isOpenBracket(getLastStackOperation()))) {
                    stackOperations.add(0, token);
                } else {
                    pushStackToRPN();
                    stackOperations.add(token);
                }
            }
        }
        pushStackToRPN();

        return String.join("", stackRPN);
    }

    private void pushStackToRPN() {
        stackOperations.stream()
                .filter(operation -> !isOpenBracket(operation))
                .forEach(operation -> stackRPN.add(operation));
        stackOperations = new ArrayList<>();
    }

    private String getLastStackOperation() {
        if (stackOperations.size() == 0) {
            return "";
        }
        return stackOperations.get(stackOperations.size() - 1);
    }

    private boolean isOperationStackContainsSimpleOperator() {
        return stackOperations.stream().anyMatch(operator -> SIMPLE_OPERATORS.contains(operator));
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isOperator(String token) {
        return SIMPLE_OPERATORS.contains(token) || COMPLEX_OPERATORS.contains(token);
    }
}
