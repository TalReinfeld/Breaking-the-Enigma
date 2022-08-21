package exceptions;

public class EmptyInputException extends RuntimeException{
    private final String EXCEPTION_MESSAGE = "You did not entered any notes";

    public EmptyInputException(){

    }
    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
