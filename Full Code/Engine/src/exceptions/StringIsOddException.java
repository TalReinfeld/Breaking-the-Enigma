package exceptions;

public class StringIsOddException extends RuntimeException{
    private int stringLength;
    private final String EXCEPTION_MESSAGE = "The amount of notes (%d) is odd.";

    public StringIsOddException(int stringLength) {
        this.stringLength = stringLength;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, stringLength);
    }
}



