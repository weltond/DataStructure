package com.weltond.stack.Hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author weltond
 * @project LeetCode
 * @date 1/24/2019
 *
 * Ideas:
 * 1. Compute the parenthesis level of each operator.
 * 2. Use a STACK to maintain the execution order of operators.
 *      a. If top operator < current, push
 *      b. If top operator > current, pop
 * 3. Use a separate stack to maintain operands.
 *      a. when we scan an operand, push
 *      b. when we pop an operator, pop two operands and push the result
 */
public class LC224BasicCalculator {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(calculate(s));
    }
    // Time = O(n), Space = O(n)
    public static int calculate(String expression) {
        Deque<OperatorLevel> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();

        int parenthesisLevel = 0;
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
        //for (ExpressionToken token = tokenizer.nextToken(); tokenizer.hasMoreTokens(); token = tokenizer.nextToken()) {
        while (tokenizer.hasMoreTokens()) {
            ExpressionToken token = tokenizer.nextToken();
            System.out.println("get: " + token.toString());
            switch (token.getType()) {
                case NUMBER:
                    operandStack.addLast(token.intValue());
                    break;
                case PARENTHESIS:
                    char parenthesis = token.charValue();
                    if (parenthesis == '(') {
                        parenthesisLevel++;
                    } else {
                        parenthesisLevel--;
                    }
                    break;
                case OPERATOR:
                    while (!operatorStack.isEmpty() && operatorStack.getLast().getLevel() >= parenthesisLevel) {
                        evaluateTopOperator(operatorStack, operandStack);
                        System.out.println(parenthesisLevel);
                    }
                    operatorStack.addLast(
                            new OperatorLevel(token.charValue(), parenthesisLevel)
                    );
                    break;
            }
        }
        while (!operatorStack.isEmpty()) {
            evaluateTopOperator(operatorStack, operandStack);
        }
        return operandStack.getLast();
    }

    private static void evaluateTopOperator(Deque<OperatorLevel> operatorStack, Deque<Integer> operandStack) {
        int rightOperand = operandStack.removeLast();
        int leftOperand = operandStack.removeLast();
        System.out.println(leftOperand + ", " + rightOperand);
        char op = operatorStack.removeLast().getOperator();
        int result = 0;

        switch (op) {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            default:
                assert false : String.format("Operator '%c' not supported.", op);
        }
        operandStack.addLast(result);
    }
}

class ExpressionTokenizer {
    private int currentPosition;
    private int maxPosition;
    private char[] arr;
    ExpressionTokenizer(String expression) {
        currentPosition = 0;
        maxPosition = expression.length();
        this.arr = expression.toCharArray();
    }
    ExpressionToken nextToken(){
        StringBuilder token = new StringBuilder();
        boolean endOfToken = false;
        Type type = Type.UNKNOWN;
        while (!endOfToken && hasMoreTokens()) {
            while (hasMoreTokens() && arr[currentPosition] == ' ') {
                currentPosition++;
            }
            if (!hasMoreTokens()) break;
            switch (arr[currentPosition]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (type != Type.NUMBER) {
                        type = Type.OPERATOR;
                        token.append(arr[currentPosition]);
                        currentPosition++;
                    }
                    endOfToken = true;
                    break;
                case '(':
                case ')':
                    if (type != Type.NUMBER) {
                        type = Type.PARENTHESIS;
                        token.append(arr[currentPosition]);
                        currentPosition++;
                    }
                    endOfToken = true;
                    break;
                case ' ':
                    endOfToken = true;
                    currentPosition++;
                    break;
                default:
                    if(Character.isDigit(arr[currentPosition]) || arr[currentPosition] == '.') {
                        token.append(arr[currentPosition]);
                        type = Type.NUMBER;
                    } else {
                        System.out.println("Syntax error at position: " + currentPosition);
                    }
                    currentPosition++;
                    break;
            }
        }
        return new ExpressionToken(type, token.toString());
    }

    boolean hasMoreTokens(){
        return currentPosition < maxPosition;
    }
}

enum Type {
    NUMBER, //e.g. 23
    OPERATOR,   // +,-
    PARENTHESIS, // (, )
    UNKNOWN
}

class ExpressionToken {
    private String token;
    private Type type;

    ExpressionToken(Type type, String token){
        this.type = type;
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Token is ");
        sb.append(token);
        sb.append(", and Type is ");
        sb.append(type);
        return sb.toString();
    }

    Type getType() {
        return this.type;
    }

    // Only when the type is NUMBER.
    int intValue(){
        if (type == Type.NUMBER) {
            return Integer.valueOf(token);
        }
        return 0;
    }

    // Only when the type is OPERATOR or PARENTHESIS
    char charValue(){
        if (type == Type.OPERATOR || type == Type.PARENTHESIS) {
            return token.toCharArray()[0];
        }
        return ' ';
    }
}

class OperatorLevel {
    private char op;
    private int level;

    public OperatorLevel(char op, int level) {
        this.op = op;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public char getOperator() {
        return op;
    }
}
