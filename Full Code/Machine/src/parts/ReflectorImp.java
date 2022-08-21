package parts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReflectorImp implements Reflector, Serializable {

    private List<Integer> reflector = new ArrayList<>();
    private String id;
    public ReflectorImp(int size){
        for (int i = 0; i < size; i++) {
            reflector.add(i);
        }
    }
    public ReflectorImp(List<Integer> reflector, String id){
        this.id = id;
        this.reflector = reflector;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getReflector() {
        return reflector;
    }

    public void swap(int input1, int input2){
        reflector.set(input1, input2);
        reflector.set(input2, input1);
    }
    @Override
    public int indexToIndex(int index) {
        return reflector.get(index);
    }
}
