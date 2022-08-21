package parts;

public interface PlugBoard {
    int StringToIndex(String note);
    String indexToString(int index);
    String encrypt(String input);
}
