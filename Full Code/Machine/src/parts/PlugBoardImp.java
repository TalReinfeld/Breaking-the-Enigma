package parts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlugBoardImp implements PlugBoard, Serializable {

    List<String> plugs = new ArrayList<>();
    List<String> plugsAfterEncrypt = new ArrayList<>();

    public PlugBoardImp(String abc){
        for (int i = 0; i < abc.length(); i++) {
            plugs.add(abc.substring(i, i + 1));
            plugsAfterEncrypt.add(abc.substring(i, i + 1));
        }
    }

    public PlugBoardImp(){

    }

    public void setAbc(String abc){
        for (int i = 0; i < abc.length(); i++) {
            plugs.add(abc.substring(i, i + 1));
            plugsAfterEncrypt.add(abc.substring(i, i + 1));
        }
    }

    public void setPlug(String note1, String note2){
        int index = plugs.indexOf(note1);
        plugsAfterEncrypt.set(index, note2);
        index = plugs.indexOf(note2);
        plugsAfterEncrypt.set(index, note1);
    }

    public List<String> getPlugs() {
        return plugs;
    }

    public List<String> getPlugsAfterEncrypt() {
        return plugsAfterEncrypt;
    }

    @Override
    public String encrypt(String input) {
        return plugsAfterEncrypt.get(plugs.indexOf(input));
    }

    @Override
    public int StringToIndex(String note) {
        return plugsAfterEncrypt.indexOf(note);
    }

    @Override
    public String indexToString(int index) {
        return plugsAfterEncrypt.get(index);
    }

}
