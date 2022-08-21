package exceptions;

import parts.Reflector;
import parts.ReflectorImp;

import java.util.ArrayList;
import java.util.List;

public class ReflectorInputEqualsOutputException extends RuntimeException{
    private String id;
    private int position;

    private final String EXCEPTION_MESSAGE = "The Reflector with the id - %s is mapping %d to itself.";

    public ReflectorInputEqualsOutputException(String id, int position){
        this.id = id;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id, position);
    }
}
