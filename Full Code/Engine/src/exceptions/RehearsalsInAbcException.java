package exceptions;

import java.util.ArrayList;
import java.util.List;

public class RehearsalsInAbcException extends Exception{
    private List<Character> abc = new ArrayList<>();
    private final String EXCEPTION_MESSAGE = "The note - " + findTheRehearsalNote() + " - appears more then once in the ABC.";

    public RehearsalsInAbcException(List<Character> abc){
        this.abc = abc;
    }

    public char findTheRehearsalNote(){
        for (int i = 0; i < abc.size(); i++) {
            for (int j = 0; j < i; j++) {
                if(abc.get(i) == abc.get(j)){
                    return abc.get(i);
                }
            }
        }
        return 0;
    }
    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
