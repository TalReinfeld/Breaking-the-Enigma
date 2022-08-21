package exceptions;

import parts.ReflectorImp;

import java.util.ArrayList;
import java.util.List;

public class ReflectorNotExistException extends RuntimeException{

    private String chosenReflector;
    private final String EXCEPTION_MESSAGE = "The Reflector with the id - %s does not exists.";

    public ReflectorNotExistException(String chosenReflector){
        this.chosenReflector = chosenReflector;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, chosenReflector);
    }
}
