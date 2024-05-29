package lyc.compiler.polaca;

public class Polaca {
    private final String value;
    private final boolean isJumpInstruction;

    public Polaca(String value) {
        this.value = value;
        this.isJumpInstruction = false;
    }

    public Polaca(String operator, String operand1, String operand2) {
        this.value = operand1 + " " + operand2 + " " + operator;
        this.isJumpInstruction = false;
    }

    public Polaca(String value, boolean isJumpInstruction) {
        this.value = value;
        this.isJumpInstruction = isJumpInstruction;
    }

    public String getPolaca() {
        return value;
    }

    public boolean isJumpInstruction() {
        return isJumpInstruction;
    }
}
