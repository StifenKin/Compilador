package lyc.compiler.files;

import lyc.compiler.polaca.Polaca;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IntermediateCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("+------------POLACA----------+\n");

        // Ejemplo de expresión infija
        String expression = "3 + 5 * ( 2 - 8 )";

        // Convertir la expresión infija a RPN usando RPNConverter
        List<String> rpnTokens = Polaca.infixToRPN(expression);

        // Escribir los tokens de la polaca como un array en el archivo
        fileWriter.write("Tokens: " + rpnTokens + "\n");

        // Generar código intermedio a partir de la lista de tokens RPN usando RPNConverter
        List<String> intermediateCode = Polaca.generateIntermediateCode(rpnTokens);

        // Escribir el código intermedio en el archivo
        for (String instruction : intermediateCode) {
            fileWriter.write(instruction + "\n");
        }
    }
}
