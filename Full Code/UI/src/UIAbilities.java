import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public interface UIAbilities {
    void readSystemDetailsFile(BufferedReader in);
    void machineSpecifications();
    void initMachineManually(BufferedReader in);
    void initMachineAutomatically();
    void inputProcessing(BufferedReader in);
    void resetMachine();
    void historyAndStatistic();
    void exit();
    void saveDetails(BufferedReader in) throws IOException;
    void loadDetails(BufferedReader in) throws Exception;
}
