package lyc.compiler.files;

import lyc.compiler.polaca.Polaca;

import java.io.FileWriter;
import java.io.IOException;

public class IntermediateCodeGenerator implements FileGenerator {

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        fileWriter.write("+------------POLACA----------+\n");

        Polaca polaca = Polaca.getSingletonInstance();
        fileWriter.write(polaca.toString());
    }
}
