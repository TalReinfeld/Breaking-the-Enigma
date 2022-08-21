import java.io.IOException;

public interface EngineAbilities {
    void readSystemDetailsFile(String FILE_NAME) throws Exception;
    MachineSpecificationsDTO machineSpecifications();
    void initMachineManually(CodeDescriptionDTO machineDetails);
    void initMachineAutomatically();
    String inputProcessing(String input);
    void resetMachine();
    HistoryAndStatisticDTO historyAndStatistic();
    void exit();
    void saveDetails(String fileName) throws IOException;
    void loadDetails(String fileName) throws Exception;
}
