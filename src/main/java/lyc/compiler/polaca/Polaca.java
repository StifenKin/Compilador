package lyc.compiler.polaca;

import java.util.ArrayList;

public class Polaca {

    private final ArrayList<String> listadoPolacaInversa;
    private static Polaca polacaInversa;

    public Polaca() {
        this.listadoPolacaInversa = new ArrayList<String>();
    }
    
    public void addPolaca(String operando){
        listadoPolacaInversa.add(operando);
    }

    public static Polaca getSingletonInstance() {
        if (polacaInversa == null){
            polacaInversa = new Polaca();
        }

        return polacaInversa;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(String s: listadoPolacaInversa){
            result.append(String.format("%s ", s));
        }
        return result.toString();
    }
}
