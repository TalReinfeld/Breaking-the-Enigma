package exceptions;

import java.util.ArrayList;
import java.util.List;

public class ReflectorsIdsByOrderException extends RuntimeException{
    private String id;
    private final String EXCEPTION_MESSAGE = "There is not a Rotor with the id - %s.";

    public ReflectorsIdsByOrderException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id);
    }
}
