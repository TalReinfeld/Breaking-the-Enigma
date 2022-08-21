package exceptions;

import java.util.ArrayList;
import java.util.List;

public class RehearsalsInReflectorsIdsException extends RuntimeException{
    private String id;
    private final String EXCEPTION_MESSAGE = "There is more then one reflector with the id - %s.";

    public RehearsalsInReflectorsIdsException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id);
    }
}
