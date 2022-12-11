package projects;

import datastructures.stacks.MyLinkedStack;
import datastructures.stacks.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class MyPostfixEvaluator {

    private final char ADD = '+';
    private final char SUBTRACT = '-';
    private final char MULTIPLY = '*';
    private final char DIVIDE = '/';
    private final char EXPONENT  = '^';

    private final Stack<Integer> stack;

    public MyPostfixEvaluator() {
        this.stack = new MyLinkedStack<>();
    }

    public int evaluate(String expression) {
        int result;
        try (BufferedReader reader = new BufferedReader(new StringReader(expression))) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (isOperator(character)) {
                    System.out.println(character + " is an operand");
                    Integer operand2 = stack.pop();
                    Integer operand1 = stack.pop();
                    System.out.println("Calculating " + operand1 + character + operand2);
                    stack.push(calculate(character, operand1, operand2));
                } else {
                    stack.push(Integer.parseInt(String.valueOf(character)));
                }
            }
            result = stack.pop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private int calculate(char operator, Integer operand1, Integer operand2) {
        int result = 0;
        switch (operator) {
            case ADD -> result = operand1 + operand2;
            case SUBTRACT -> result = operand1 - operand2;
            case MULTIPLY -> result = operand1 * operand2;
            case DIVIDE -> result = operand1 / operand2;
            case EXPONENT -> result = exp(operand1, operand2);
        }
        return result;
    }

    private int exp(int operand1, int operand2) {
        if (operand2 == 0) return 1;
        int product = operand1;
        for (int i = 1; i < operand2; i++) {
            product *= product;
        }
        return product;
    }

    private boolean isOperator(char character) {
        return (character == ADD ||
                character == SUBTRACT ||
                character == MULTIPLY ||
                character == DIVIDE ||
                character == EXPONENT);
    }
}

