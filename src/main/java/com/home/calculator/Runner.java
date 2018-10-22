package com.home.calculator;

public class Runner {

    public static void main(String[] args) {
        String expression = args[0];

        Parser parser = new Parser();
        Calculator calculator = new Calculator();

        String parsed = parser.parse(expression);
        String result = calculator.getResult(parsed);

        System.out.println("Result: " + result);
    }


}
