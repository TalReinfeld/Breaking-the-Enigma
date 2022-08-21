package exceptions;

public class IllegalNoteException extends RuntimeException{
    private String problematicNote;
    private final String EXCEPTION_MESSAGE = "The note %s does not appear in the ABC.";

    public IllegalNoteException(String problematicNote) {
        this.problematicNote = problematicNote;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, problematicNote);
    }
}
