package exceptions;

import parts.RotorImp;

import java.util.ArrayList;
import java.util.List;

public class RotorNotchIsOutOfRangeException extends RuntimeException{
    private int range;
    private int idOfProblematicRotor;
    private int problematicNotchPosition;
    private final String EXCEPTION_MESSAGE = "The notch of the Rotor with the id - %d is on position - %d when the minimal position is 1 and the maximal position is %d.";

    public RotorNotchIsOutOfRangeException(int range, int idOfProblematicRotor, int problematicNotchPosition){
        this.range = range;
        this.idOfProblematicRotor = idOfProblematicRotor;
        this.problematicNotchPosition = problematicNotchPosition;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, idOfProblematicRotor, problematicNotchPosition, range);
    }
}
