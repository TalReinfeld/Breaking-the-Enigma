import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try{
            EngineImp engine = new EngineImp();
            engine.readSystemDetailsFile("C:\\Users\\talre\\source\\repos\\ex1-sanity-small (2).xml");


//        List<Integer> chosenRotorsIds = new ArrayList<>();
//        chosenRotorsIds.add(1);
//        chosenRotorsIds.add(2);
//
//
//        List<String> firstLocationsChosenRotors = new ArrayList<>();
//        firstLocationsChosenRotors.add("C");
//        firstLocationsChosenRotors.add("C");
//
//        List<Integer> rotorsDistanceFromNotch = new ArrayList<>();
//        int distance1 = 1;
//        rotorsDistanceFromNotch.add(distance1);
//        int distance2 = 4;
//        rotorsDistanceFromNotch.add(distance2);
//
//        String chosenReflector = "I";
//
//        List<String> chosenPlugs = new ArrayList<>();
//        chosenPlugs.add("AF");
//
//        CodeDescriptionDTO machineDetails = new CodeDescriptionDTO(chosenRotorsIds, firstLocationsChosenRotors, rotorsDistanceFromNotch, chosenReflector, chosenPlugs);
//        engine.initMachineManually(machineDetails);
          engine.initMachineAutomatically();
          MachineSpecificationsDTO details1 = engine.machineSpecifications();
          System.out.println(details1.toString());
          String input = "ABCDEF";
          engine.inputProcessing(input);
          MachineSpecificationsDTO details2 = engine.machineSpecifications();
          System.out.println("\n\n");
          System.out.println(details2.toString());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
