package exceptions;

public class InvalidInputException extends RuntimeException{
    private final String EXCEPTION_MESSAGE = "Invalid input";

    public InvalidInputException(){

    }
    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
