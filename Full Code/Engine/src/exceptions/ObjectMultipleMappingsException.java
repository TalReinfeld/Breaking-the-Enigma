package exceptions;

public class ObjectMultipleMappingsException extends RuntimeException{
    private String objName;
    private String idOfProblematicObject;
    private String problematicNote;
    private final String EXCEPTION_MESSAGE = "The %s with the id - %s has multiple mapping to the note - %s.";

    public ObjectMultipleMappingsException(String objName, String idOfProblematicObject, String problematicNote){
        this.objName = objName;
        this.idOfProblematicObject = idOfProblematicObject;
        this.problematicNote = problematicNote;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, objName, idOfProblematicObject, problematicNote);
    }
}
