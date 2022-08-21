package exceptions;

public class BeforeChargingTheMachineException extends RuntimeException{
    private final String EXCEPTION_MESSAGE = "The Machine is not charged yet.";

    public BeforeChargingTheMachineException(){

    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
