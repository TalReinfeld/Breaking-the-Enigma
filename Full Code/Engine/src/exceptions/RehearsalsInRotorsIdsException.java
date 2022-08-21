package exceptions;

import java.util.ArrayList;
import java.util.List;

public class RehearsalsInRotorsIdsException extends RuntimeException{
    private int id;
    private final String EXCEPTION_MESSAGE = "There is more then one rotor with the id - %d.";

    public RehearsalsInRotorsIdsException(int id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id);
    }
}
