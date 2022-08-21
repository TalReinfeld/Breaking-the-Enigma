package exceptions;

public class NotEnoughObjectsException extends RuntimeException{
    private String objName;
    private String usersInput;
    private final String EXCEPTION_MESSAGE = "There are not enough %s in your list (your input - %s).";

    public NotEnoughObjectsException(String objName, String usersInput){
        this.objName = objName;
        this.usersInput = usersInput;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, objName, usersInput);
    }
}
