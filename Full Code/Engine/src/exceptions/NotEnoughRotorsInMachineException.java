package exceptions;

public class NotEnoughRotorsInMachineException extends RuntimeException{
    private int rotorsInTheMachine;
    private int rotorsInTheEngine;
    private final String EXCEPTION_MESSAGE = "The amount of Rotors that the Machine needs (%d) is equal or greater then the total amount of rotors (%d).";

    public NotEnoughRotorsInMachineException(int rotorsInTheMachine, int rotorsInTheEngine){
        this.rotorsInTheMachine = rotorsInTheMachine;
        this.rotorsInTheEngine = rotorsInTheEngine;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, rotorsInTheMachine, rotorsInTheEngine);
    }
}
