package parts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RotorImp implements Rotor, Serializable {

    private List<String> rightRing = new ArrayList<>();
    private List<String> leftRing = new ArrayList<>();
    private int id;
    private int notch;
    private int position;

    public RotorImp(List<String> abcLeft, List<String> abcRight, int id, int notch){
        for (int i = 0; i < abcRight.size(); i++) {
            this.rightRing = abcRight;
            this.leftRing = abcLeft;
        }
        this.id = id;
        this.notch = notch;
        this.position = 0;
    }

    public int convertStringPositionToIntPosition(String position){
        for (int i = 0; i < rightRing.size(); i++) {
            if(rightRing.get(i).equals(position)){
                return i;
            }
        }
        return -1;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public int getNotch() {
        return notch;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public List<String> getLeftRing() {
        return leftRing;
    }

    public List<String> getRightRing() {
        return rightRing;
    }

    @Override
    public int indexToIndex(int index, boolean fromRightToLeft) {
        int size = this.rightRing.size();
        if(fromRightToLeft){
            return (leftRing.indexOf(rightRing.get((index + this.position) % size)) - position + size) % size;
        }
        else{
            return (rightRing.indexOf(leftRing.get((index + this.position) % size)) - position + size) % size;
        }
    }

    @Override
    public void rotate() {
        this.position++;
        if(this.position == this.rightRing.size()){
            this.position = 0;
        }
    }
}
