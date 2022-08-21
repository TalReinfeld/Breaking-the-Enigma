package exceptions;

public class TooManyObjectsException extends RuntimeException{
    private String objName;
    private String usersInput;
    private final String EXCEPTION_MESSAGE = "There are too many %s in your list (your input - %s).";

    public TooManyObjectsException(String objName, String usersInput){
        this.objName = objName;
        this.usersInput = usersInput;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, objName, usersInput);
    }
}
