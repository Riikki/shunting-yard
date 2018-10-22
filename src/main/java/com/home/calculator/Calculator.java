package com.home.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private List<String> stack = new ArrayList<>();

    public String getResult(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            String token = String.valueOf(expression.charAt(i));

            if (isNumber(token)) {
                stack.add(token);
            } else if (isOperator(token)) {
                calculate(token);
            }
        }
        return stack.get(0);
    }

    private void calculate(String token) {
        String firstValue = stack.get(stack.size() - 2);
        String secondValue = stack.get(stack.size() - 1);

        String result;

        switch (token) {
            case "+":
                double sum = Double.valueOf(firstValue) + Double.valueOf(secondValue);
                result = String.valueOf(sum);
                break;
            case "-":
                double sub = Double.valueOf(firstValue) - Double.valueOf(secondValue);
                result = String.valueOf(sub);
                break;
            case "*":
                double mul = Double.valueOf(firstValue) * Double.valueOf(secondValue);
                result = String.valueOf(mul);
                break;
            case "/":
                double div = Double.valueOf(firstValue) / Double.valueOf(secondValue);
                result = String.valueOf(div);
                break;
            default:
                throw new RuntimeException("Unsupported operation" + token);
        }
        stack = stack.subList(0, stack.size() - 2);
        stack.add(result);
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
}
