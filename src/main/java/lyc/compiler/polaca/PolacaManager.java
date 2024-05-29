package lyc.compiler.polaca;

import java.util.ArrayList;
import java.util.List;

public class PolacaManager {
    public static List<Polaca> polacaList = new ArrayList<>();

    public static void addPolaca(Polaca polaca) {
        polacaList.add(polaca);
    }

    public static String negateComparator(String comparator) {
        return switch (comparator) {
            case "BLE" -> "BGT";
            case "BGE" -> "BLT";
            case "BLT" -> "BGE";
            case "BGT" -> "BLE";
            case "BNE" -> "BEQ";
            case "BEQ" -> "BNE";
            default -> null;
        };
    }
}
