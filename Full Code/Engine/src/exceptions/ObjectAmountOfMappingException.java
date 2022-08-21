package exceptions;

public class ObjectAmountOfMappingException extends RuntimeException{
    private String objName;
    private int maximalRange;
    private int amountOfMappings;
    private String id;
    private final String EXCEPTION_MESSAGE = "The %s with the id - %s has %d different mappings when the amount of mappings should be %d.";

    public ObjectAmountOfMappingException(String objName, int maximalRange, int amountOfMappings, String id){
        this.objName = objName;
        this.maximalRange = maximalRange;
        this.amountOfMappings = amountOfMappings;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, objName, id, amountOfMappings, maximalRange);
    }
}
