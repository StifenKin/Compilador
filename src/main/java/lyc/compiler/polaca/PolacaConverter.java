package lyc.compiler.utils;

import java.util.*;

public class PolacaConverter {

    private static final Map<String, Integer> PRECEDENCE_MAP = new HashMap<>();

    static {
        PRECEDENCE_MAP.put("+", 1);
        PRECEDENCE_MAP.put("-", 1);
        PRECEDENCE_MAP.put("*", 2);
        PRECEDENCE_MAP.put("/", 2);
        PRECEDENCE_MAP.put("^", 3);
        PRECEDENCE_MAP.put(">", 0);
        PRECEDENCE_MAP.put("<", 0);
        PRECEDENCE_MAP.put(">=", 0);
        PRECEDENCE_MAP.put("<=", 0);
        PRECEDENCE_MAP.put("==", 0);
        PRECEDENCE_MAP.put("!=", 0);
    }

    public static List<String> infixToPolaca(String infix) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/^()><= ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            }
            if (isNumber(token) || isVariable(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) && hasHigherPrecedence(stack.peek(), token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private static boolean isNumber(String token) {
        return token.matches("_\\d+(\\.\\d+)?");
    }

    private static boolean isVariable(String token) {
        return token.matches("_[a-zA-Z][a-zA-Z0-9]*");
    }

    private static boolean isOperator(String token) {
        return PRECEDENCE_MAP.containsKey(token);
    }

    private static boolean hasHigherPrecedence(String op1, String op2) {
        return PRECEDENCE_MAP.get(op1) >= PRECEDENCE_MAP.get(op2);
    }
}
