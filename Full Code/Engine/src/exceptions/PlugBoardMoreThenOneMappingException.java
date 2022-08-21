package exceptions;

import java.util.ArrayList;
import java.util.List;

public class PlugBoardMoreThenOneMappingException extends RuntimeException{
    private String note;
    private final String EXCEPTION_MESSAGE = "There is a more then one plug to %s.";

    public PlugBoardMoreThenOneMappingException(String note){
        this.note = note;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, note);
    }
}
