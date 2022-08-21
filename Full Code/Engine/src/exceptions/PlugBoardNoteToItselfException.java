package exceptions;

import java.util.ArrayList;
import java.util.List;

public class PlugBoardNoteToItselfException extends RuntimeException{
    private String note;
    private final String EXCEPTION_MESSAGE = "There is a Plug from %s to itself.";

    public PlugBoardNoteToItselfException(String note){
        this.note = note;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, note);
    }
}
