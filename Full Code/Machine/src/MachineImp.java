import parts.PlugBoardImp;
import parts.ReflectorImp;
import parts.RotorImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MachineImp implements MachineAbilities, Serializable {
    private String abc;
    private PlugBoardImp plugBoard;
    private List<RotorImp> rotors = new ArrayList<>();
    private ReflectorImp reflector;

    public MachineImp(String abc, PlugBoardImp plugBoard, List<RotorImp> rotors, ReflectorImp reflector){
        this.abc = abc;
        this.plugBoard = plugBoard;
        this.rotors = rotors;
        this.reflector = reflector;
    }

    public List<RotorImp> getRotors() {
        return rotors;
    }

    public ReflectorImp getReflector() {
        return reflector;
    }

    public PlugBoardImp getPlugBoard() {
        return plugBoard;
    }

    public int encryptWithPlugBoard(String note){//פונקציית Z
        return plugBoard.StringToIndex(note);
    }

    public String decryptWithPlugBoard(int index){
        return plugBoard.indexToString(index);
    }

    public int useRotors(int entranceIndex, boolean fromRightToLeft){
        int exitIndex = entranceIndex;
        if(fromRightToLeft){
            rotateRotors(0);
            for (RotorImp rotor : rotors) {
                exitIndex = rotor.indexToIndex(exitIndex, fromRightToLeft);
            }
        }
        else{
            for (int i = rotors.size() - 1; i >= 0; i--) {
                exitIndex = rotors.get(i).indexToIndex(exitIndex, fromRightToLeft);
            }
        }
        return exitIndex;
    }

    public void rotateRotors(int rotorIndex){
        rotors.get(rotorIndex).rotate();
        if((rotors.get(rotorIndex).getPosition() == rotors.get(rotorIndex).getNotch()) && (rotorIndex < rotors.size() - 1)){
            rotateRotors(rotorIndex + 1);
        }
    }

    public int useReflector(int entranceIndex){//פונקציית Z
        return reflector.indexToIndex(entranceIndex);
    }

    @Override
    public String encrypt(String note) {
        int index = encryptWithPlugBoard(note);
        index = useRotors(index, true);
        index = useReflector(index);
        index = useRotors(index, false);
        return decryptWithPlugBoard(index);
    }
}
