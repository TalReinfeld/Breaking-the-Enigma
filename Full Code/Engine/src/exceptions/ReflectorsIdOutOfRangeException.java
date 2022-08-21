package exceptions;

public class ReflectorsIdOutOfRangeException extends RuntimeException{
    private String possibleIds;
    private String id;
    private final String EXCEPTION_MESSAGE = "There is a Reflector with the id - %s when when you can only use the ids- %s.";

    public ReflectorsIdOutOfRangeException(String possibleIds, String id){
        this.possibleIds = possibleIds;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, id, possibleIds);
    }
}
