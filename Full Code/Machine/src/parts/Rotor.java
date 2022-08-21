package parts;

public interface Rotor {
    int indexToIndex(int index, boolean fromRightToLeft);
    void rotate();
}
