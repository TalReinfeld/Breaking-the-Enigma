package exceptions;

public class BeforeReadingEngineDetailsException extends RuntimeException{
    private final String EXCEPTION_MESSAGE = "There is no available details in the Engine.";

    public BeforeReadingEngineDetailsException(){

    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
