import com.sun.deploy.util.StringUtils;
import exceptions.*;
import parts.RotorImp;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIImp implements UIAbilities{
    private EngineImp engine = new EngineImp();
    public UIImp(){

    }
    public void run(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while (!input.equals("8")){
            try{
                printMenu();
                input = in.readLine();
                chooseOption(input, in);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void readSystemDetailsFile(BufferedReader in) {
        boolean done = false;
        while(!done){
            System.out.println("Please enter a xml file name including the path (e.g. C:\\Users\\talre\\source\\repos\\ex1-sanity-small.xml)");
            String input = "";
            try{
                input = in.readLine();
                engine.readSystemDetailsFile(input);
                System.out.println("The file was loaded successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
            }
        }
    }

    @Override
    public void machineSpecifications() {
        System.out.println(engine.machineSpecifications().toString());
    }

    @Override
    public void initMachineManually(BufferedReader in) {
        if(!engine.isAfterReadingEngineDetails()){
            throw new BeforeReadingEngineDetailsException();
        }
        List<Integer> chosenRotorsIds = new ArrayList<>();
        List<Integer> rotorsDistanceFromNotch = new ArrayList<>();
        List<String> firstLocationsChosenRotors = new ArrayList<>();
        String chosenReflector;
        List<String> chosenPlugs = new ArrayList<>();
        try{
            chosenRotorsIds = makeAListOfRotorsIds(in);
            firstLocationsChosenRotors = makeAListOfStartingPositions(in);
            rotorsDistanceFromNotch = makeAListOfDistancesFromNotches(chosenRotorsIds, firstLocationsChosenRotors);
            chosenReflector = chooseReflector(in);
            chosenPlugs = makeAListOfChosenPlugs(in);
            engine.initMachineManually(new CodeDescriptionDTO(chosenRotorsIds, firstLocationsChosenRotors, rotorsDistanceFromNotch, chosenReflector, chosenPlugs));
            System.out.println("The machine is ready for use!");
        } catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void initMachineAutomatically() {
        engine.initMachineAutomatically();
        System.out.println("The machine is ready for use!");
    }

    @Override
    public void inputProcessing(BufferedReader in) {
        boolean done = false;
        while(!done){
            if(!engine.isAfterReadingEngineDetails()){
                throw new BeforeReadingEngineDetailsException();
            }
            if(!engine.isAfterChargingTheMachine()){
                throw new BeforeChargingTheMachineException();
            }
            System.out.println(String.format("Please enter a string of legal notes from the ABC (%s):", engine.getAbc()));
            String input = "";
            try{
                input = in.readLine();
                input = input.toUpperCase();
                System.out.println(engine.inputProcessing(input));
                System.out.println("Your message was encrypted successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
            }
        }
    }

    @Override
    public void resetMachine() {
        engine.resetMachine();
        System.out.println("The machine is reset!");
    }

    @Override
    public void historyAndStatistic() {
        System.out.println(engine.historyAndStatistic().toString());
    }

    @Override
    public void exit() {
        System.out.println("See you next time!");
    }

    @Override
    public void saveDetails(BufferedReader in) throws IOException {
        System.out.println("Please enter a file name including the path (e.g. C:\\Users\\talre\\source\\repos\\ex1-sanity-small.dat)");
        String input = in.readLine();
        engine.saveDetails(input);
        System.out.println("The file was loaded successfully!");
    }

    @Override
    public void loadDetails(BufferedReader in) throws Exception {
        boolean done = false;
        while(!done){
            System.out.println("Please enter a xml file name including the path (e.g. C:\\Users\\talre\\source\\repos\\ex1-sanity-small.dat)");
            String input = "";
            try{
                input = in.readLine();
                engine.loadDetails(input);
                System.out.println("The file was loaded successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
            }
        }
    }


    /*----------------------------------------------------------------------------general methods---------------------------------------------------------------------*/
    //This method prints the menu to the console.
    public void printMenu(){
        System.out.println("                  MENU");
        System.out.println("Please choose one of the following numbers:");
        System.out.println("1 - Read file");
        System.out.println("2 - Show engine specifications");
        System.out.println("3 - Charge machine manually");
        System.out.println("4 - Charge machine automatically");
        System.out.println("5 - Enter input");
        System.out.println("6 - Reset machine");
        System.out.println("7 - Show History and statistic");
        System.out.println("8 - Exit");
        System.out.println("9 - Save details");
        System.out.println("10 - Load details");
    }
    //This method takes the user to the action he selected from the menu.
    public void chooseOption(String userChoice, BufferedReader in) throws Exception{
        switch (userChoice){
            case "1":
                readSystemDetailsFile(in);
                break;
            case "2":
                machineSpecifications();
                break;
            case "3":
                initMachineManually(in);
                break;
            case "4":
                initMachineAutomatically();
                break;
            case "5":
                inputProcessing(in);
                break;
            case "6":
                resetMachine();
                break;
            case "7":
                historyAndStatistic();
                break;
            case "8":
                exit();
                break;
            case "9":
                saveDetails(in);
                break;
            case "10":
                loadDetails(in);
                break;
            default:
                throw new InvalidInputException();
        }
    }
    //This method appears when the user made a mistake with his input and checks if he wants to continue from the same position or go back to the menu.
    public boolean problemWithTheInput(BufferedReader in){
        while (true){
            try {
                System.out.println("Please choose one of the following numbers:");
                System.out.println("1 - Continue from this position");
                System.out.println("2 - Back to the menu");
                String input = in.readLine();;
                if(input.equals("1")){
                    return false;
                } else if (input.equals("2")) {
                    return true;
                }
                else{
                    throw new InvalidInputException();
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    /*------------------------------------------------------------------charge machine manually methods---------------------------------------------------------------*/
    //This method creates a list of the chosen plugs from the users input.
    public List<String> makeAListOfChosenPlugs(BufferedReader in) throws IOException {
        String input = "";
        List<String> plugs = new ArrayList<>();
        boolean done = false;
        while(!done){
            System.out.println("Please enter a string that representing the plugs (e.g. -ABCD- for the plugs AB and CD):\n");
            try{
                input = in.readLine();
                input = input.toUpperCase();
                if(input.length() % 2 == 1) {
                    throw new StringIsOddException(input.length());
                }
                plugs = engine.plugsFomStringToListOfStrings(input);
                engine.getEngineHelper().checkUsersPlugs(plugs, engine.getAbc());
                System.out.println("Your Plugs were entered successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
                if(done){
                    throw e;
                }
            }
        }
        return plugs;
    }
    //This method brings the chosen reflector's id from the users input.
    public String chooseReflector(BufferedReader in) throws IOException {
        String input = "";
        String allTheReflectors = "";
        String chosenReflector = "";
        for (int i = 0; i < engine.getAllTheReflectors().size(); i++) {
            allTheReflectors = allTheReflectors +  (i + 1) + ") " + engine.getAllTheReflectors().get(i).getId() + "\n";
        }
        boolean done = false;
        while(!done){
            System.out.println("Please choose one of the following numbers:");
            System.out.println(allTheReflectors);
            try{
                input = in.readLine();
                if(!engine.getEngineHelper().checkIfAStringGotANumericValue(input)){
                    throw new InvalidInputException();
                }
                engine.getEngineHelper().checkIfTheNumberIsInRange(Integer.valueOf(input), engine.getAllTheReflectors().size());
                chosenReflector = engine.getAllTheReflectors().get(Integer.valueOf(input) - 1).getId();
                System.out.println("Your Reflector was entered successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
                if(done){
                    throw e;
                }
            }
        }
        return chosenReflector;
    }
    //This method calculates and provides a list of the current distances of the rotors from their notches.
    public List<Integer> makeAListOfDistancesFromNotches(List<Integer> chosenRotorsIds, List<String> firstLocationsChosenRotors){
        List<Integer> distancesFromNotches = new ArrayList<>();
        for (int i = 0; i < chosenRotorsIds.size(); i++) {
            RotorImp rotor = engine.getEngineHelper().findRotorById(chosenRotorsIds.get(i), engine.getAllTheRotors());
            rotor.setPosition(rotor.convertStringPositionToIntPosition(firstLocationsChosenRotors.get(i)));
            distancesFromNotches.add((rotor.getNotch() - rotor.convertStringPositionToIntPosition(firstLocationsChosenRotors.get(i)) + rotor.getRightRing().size()) % rotor.getRightRing().size());
        }
        return distancesFromNotches;
    }
    //This method creates a list of the starting positions for each rotor by the users input.
    public List<String> makeAListOfStartingPositions(BufferedReader in) throws IOException {
        List<String> startingPositions = new ArrayList<>();
        boolean done = false;
        String input = "";
        while(!done){
            System.out.println(String.format("Please enter a string with the length of %d of notes from the ABC that will set the starting positions for each Rotor (e.g. 4D8):", engine.getMaxRotorsInMachine()));
            try{
                input = in.readLine();
                input = input.toUpperCase();
                startingPositions = engine.startingPositionsFromStringToListOfStrings(input);
                System.out.println("Your positions were entered successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
                if(done){
                    throw e;
                }
            }
        }
        engine.getEngineHelper().checkAmountOfObjects(startingPositions.size(), "Starting Positions", input, engine.getMaxRotorsInMachine());
        return startingPositions;
    }
    //This method creates a list of the chosen rotors from the users input.
    public List<Integer> makeAListOfRotorsIds(BufferedReader in) throws IOException {
        List<Integer> chosenRotorsIds = new ArrayList<>();
        String rotorsIds = "";
        for (int i = 0; i < engine.getAllTheRotors().size(); i++) {
            rotorsIds = rotorsIds + engine.getAllTheRotors().get(i).getId() + ',';
        }
        rotorsIds = rotorsIds.substring(0, rotorsIds.length() - 1);
        boolean done = false;
        while(!done){
            System.out.println(String.format("Please choose %d of the following Rotors seperated by a comma - %s (e.g. 55,63,13):", engine.getMaxRotorsInMachine(), rotorsIds));
            String input = "";
            try{
                input = in.readLine();
                chosenRotorsIds = engine.rotorsFromStringToListOfIds(input);
                System.out.println("Your Rotors were entered successfully!");
                done = true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                done = problemWithTheInput(in);
                if(done){
                    throw e;
                }
            }
        }
        return chosenRotorsIds;
    }
}

