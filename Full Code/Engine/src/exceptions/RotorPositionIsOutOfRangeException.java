package exceptions;

import parts.RotorImp;

import java.util.ArrayList;
import java.util.List;

public class RotorPositionIsOutOfRangeException extends RuntimeException{
    private int id;
    private String position;
    private final String EXCEPTION_MESSAGE = "The Rotor with the id - %d has a position (%s) that is out of range.";

    public RotorPositionIsOutOfRangeException(int id, String position){
        this.id = id;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id, position);
    }
}
