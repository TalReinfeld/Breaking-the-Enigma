import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryAndStatisticDTO implements Serializable {
    private List<Pair<CodeDescriptionDTO,List<Pair<Pair<String, String>, Long>>>> history = new ArrayList<>();

    public HistoryAndStatisticDTO(List<Pair<CodeDescriptionDTO,List<Pair<Pair<String, String>, Long>>>> history){
        this.history = history;
    }
    public HistoryAndStatisticDTO(){

    }
    @Override
    public String toString(){
        String history = "";
        if(this.history.isEmpty()){
            history = "There is no data in the history yet.";
        }
        else{
            history = "History details:\n";
            for (int i = 0; i < this.history.size(); i++) {
                history = history + "Code description " + this.history.get(i).getKey().toString() + ":\n";
                for (int j = 0; j < this.history.get(i).getValue().size(); j++) {
                    history = history + "\t" + (j + 1) + ".<" + this.history.get(i).getValue().get(j).getKey().getKey() + "> --> <" + this.history.get(i).getValue().get(j).getKey().getValue() + "> ( " + this.history.get(i).getValue().get(j).getValue() + " nano-seconds)\n";
                }
            }
        }
        return history;
    }
}
