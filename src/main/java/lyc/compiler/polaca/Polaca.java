package lyc.compiler.polaca;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Polaca {

    public static List<String> infixToRPN(String expression) {
        List<String> output = new ArrayList<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            if (Character.isDigit(token)) {
                output.add(String.valueOf(token));
            } else if (token == '(') {
                operators.push(token);
            } else if (token == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.pop();
            } else if (getPrecedence(token) > 0) {
                while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(token)) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }

        return output;
    }

    public static List<String> generateIntermediateCode(List<String> rpnTokens) {
        Stack<String> stack = new Stack<>();
        List<String> instructions = new ArrayList<>();
        int tempVarCount = 0;

        for (String token : rpnTokens) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(token);
            } else {
                String rightOperand = stack.pop();
                String leftOperand = stack.pop();
                String tempVar = "t" + tempVarCount++;
                instructions.add(tempVar + " = " + leftOperand + " " + token + " " + rightOperand);
                stack.push(tempVar);
            }
        }

        return instructions;
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
