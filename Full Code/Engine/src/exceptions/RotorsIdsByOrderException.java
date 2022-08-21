package exceptions;

import java.util.ArrayList;
import java.util.List;

public class RotorsIdsByOrderException extends RuntimeException{
    private int id;
    private final String EXCEPTION_MESSAGE = "There is not a rotor with the id - %d.";

    public RotorsIdsByOrderException(int id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id);
    }
}
