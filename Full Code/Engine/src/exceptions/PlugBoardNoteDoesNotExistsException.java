package exceptions;

import java.util.ArrayList;
import java.util.List;

public class PlugBoardNoteDoesNotExistsException extends RuntimeException{
    private String note;
    private String abc;
    private final String EXCEPTION_MESSAGE = "There is a Plug with the note %s that does not exists in the ABC (possible notes: %s).";

    public PlugBoardNoteDoesNotExistsException(String note, String abc){
        this.note = note;
        this.abc = abc;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, note, abc);
    }
}
