package com.weltond.stack.Hard;

import java.rmi.Naming;
import java.util.Collection;

/**
 * @author weltond
 * @project LeetCode
 * @date 1/25/2019
 */
public class Tokenizer {
    int pos;
    char[] expression;

    Tokenizer(String expression) {
        this.expression = expression.toCharArray();
        this.pos = 0;
    }

    enum Type { OPERATOR, NUMBER, UNKNOWN }

    class Lexeme {
        String type, token;
        Lexeme(String type, String token) {
            this.type = type;
            this.token = token;
        }
    }

    Lexeme getNextToken() {
        StringBuilder token = new StringBuilder();
        boolean endOfToken = false;
        Type type = Type.UNKNOWN;
        //Collection
        while (!endOfToken && hasMoreTokens()) {
            while(hasMoreTokens() && expression[pos] == ' ')
                pos++;
            if (!hasMoreTokens()) break;
            switch (expression[pos]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if(type != Type.NUMBER) {
                        type = Type.OPERATOR;
                        token.append(expression[pos]);
                        pos++;
                    }
                    endOfToken = true;
                    break;
                case ' ':
                    endOfToken = true;
                    pos++;
                    break;
                default:
                    if(Character.isDigit(expression[pos]) || expression[pos] == '.') {
                        token.append(expression[pos]);
                        type = Type.NUMBER;
                    } else {
                        System.out.println("Systax error at position: " + pos);
                    }
                    pos++;
                    break;
            }
        }
        System.out.println(pos);
        return new Lexeme(type.name().toLowerCase(), token.toString());
    }

    boolean hasMoreTokens() {
        return pos < expression.length;
    }

    public static void main(String[] args) {
        String expression = " 2-1 + 2 ";
        Tokenizer tokenizer = new Tokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            Lexeme nextToken = tokenizer.getNextToken();
            System.out.print("Type: " + nextToken.type + "\tLexeme: " + nextToken.token + "\n");
        }
    }
}