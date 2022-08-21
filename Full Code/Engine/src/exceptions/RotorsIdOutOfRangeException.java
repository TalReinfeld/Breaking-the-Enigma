package exceptions;

import java.util.ArrayList;
import java.util.List;

public class RotorsIdOutOfRangeException extends RuntimeException{
    private int maximalRange;
    private int id;
    private final String EXCEPTION_MESSAGE = "There is a Rotor with the id - %d when the minimal id is 1 and the maximal id is %d.";

    public RotorsIdOutOfRangeException(int maximalRange, int id){
        this.maximalRange = maximalRange;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id, maximalRange);
    }
}
