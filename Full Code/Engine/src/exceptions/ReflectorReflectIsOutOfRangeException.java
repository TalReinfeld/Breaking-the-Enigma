package exceptions;

public class ReflectorReflectIsOutOfRangeException extends RuntimeException{
    private int range;
    private String idOfProblematicReflector;
    private String problematicPosition;
    private final String EXCEPTION_MESSAGE = "The Reflector with the id - %s got a position - %s when the minimal position is 1 and the maximal position is %d.";

    public ReflectorReflectIsOutOfRangeException(int range, String idOfProblematicReflector, String problematicPosition){
        this.range = range;
        this.idOfProblematicReflector = idOfProblematicReflector;
        this.problematicPosition = problematicPosition;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, idOfProblematicReflector, problematicPosition, range);
    }
}
