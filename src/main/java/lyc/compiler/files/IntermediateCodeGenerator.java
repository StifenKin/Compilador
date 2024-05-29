package lyc.compiler.files;

import lyc.compiler.polaca.Polaca;
import lyc.compiler.polaca.PolacaManager;
import lyc.compiler.utils.PolacaConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IntermediateCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("+------------POLACA----------+\n");

        // Leer las líneas del archivo de entrada
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input/test.txt"));

        for (String line : lines) {
            // Generar tokens de la línea en notación Polaca Inversa
            List<String> polacaTokens = PolacaConverter.infixToPolaca(line.trim());

            // Crear objetos Polaca y agregarlos a PolacaManager
            for (String token : polacaTokens) {
                PolacaManager.addPolaca(new Polaca(token));
            }
        }

        // Escribir los elementos de Polaca como un array en el archivo
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i < PolacaManager.polacaList.size(); i++) {
            output.append(PolacaManager.polacaList.get(i).getPolaca());
            if (i < PolacaManager.polacaList.size() - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        fileWriter.write(output.toString());
    }
}
