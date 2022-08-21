package exceptions;

public class RotorDoesNotExistsException extends RuntimeException{
    private String problematicRotor;
    private final String EXCEPTION_MESSAGE = "The Rotor %s does not exists.";

    public RotorDoesNotExistsException(String problematicRotor) {
        this.problematicRotor = problematicRotor;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, problematicRotor);
    }
}
