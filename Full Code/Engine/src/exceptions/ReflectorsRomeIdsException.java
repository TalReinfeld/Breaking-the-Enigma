package exceptions;

import java.util.ArrayList;
import java.util.List;

public class ReflectorsRomeIdsException extends RuntimeException{
    private String id;
    private final String EXCEPTION_MESSAGE = "There is a Reflector with the id - %s.";

    public ReflectorsRomeIdsException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id);
    }
}
