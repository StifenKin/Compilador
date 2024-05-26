package lyc.compiler.files;

import java.util.Stack;

public class ExpressionConverter {

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

    public static String infixToRPN(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            // Si el token es un número
            if (Character.isDigit(token)) {
                output.append(token).append(' ');
            }
            // Si el token es un paréntesis izquierdo
            else if (token == '(') {
                operators.push(token);
            }
            // Si el token es un paréntesis derecho
            else if (token == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.append(operators.pop()).append(' ');
                }
                operators.pop(); // Quitar el '('
            }
            // Si el token es un operador
            else if (getPrecedence(token) > 0) {
                while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(token)) {
                    output.append(operators.pop()).append(' ');
                }
                operators.push(token);
            }
        }

        // Vaciar la pila de operadores
        while (!operators.isEmpty()) {
            output.append(operators.pop()).append(' ');
        }

        return output.toString().trim();
    }
}