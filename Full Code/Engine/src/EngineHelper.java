import engineHelpers.GeneralHelperUnit;
import engineHelpers.ManuallyUnit;
import engineHelpers.RandomUnit;
import engineHelpers.ValidationUnit;
import exceptions.*;
import parts.PlugBoardImp;
import parts.ReflectorImp;
import parts.RotorImp;
import schema.generated.*;

import java.io.Serializable;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EngineHelper implements Serializable {
    private GeneralHelperUnit generalHelperUnit = new GeneralHelperUnit();
    private ValidationUnit validationUnit = new ValidationUnit();
    private ManuallyUnit manuallyUnit = new ManuallyUnit();
    private RandomUnit randomUnit = new RandomUnit();


    /*-------------------------------------------------General helper unit functions----------------------------------*/
    //This method checks if an integer is in a specific range.
    public boolean checkIfTheNumberIsInRange(int intInput, int range){
        return generalHelperUnit.checkIfTheNumberIsInRange(intInput, range);
    }
    //This method checks if a String has a numeric value.
    public boolean checkIfAStringGotANumericValue(String input){
        return generalHelperUnit.checkIfAStringGotANumericValue(input);
    }
    //This method converts a reflector id to a number.
    public int translateIdFromStringToInt(String id){
        return generalHelperUnit.translateIdFromStringToInt(id);
    }
    //This method converts a number to a reflector id.
    public String translateIdFromIntToString(int id){
        return generalHelperUnit.translateIdFromIntToString(id);
    }
    //This method creates a string of all the possible reflectors ids.
    public String makeAListOfPossibleReflectors(int size){
        return generalHelperUnit.makeAListOfPossibleReflectors(size);
    }
    //This method checks if a plug is already exists.
    public boolean plugAlreadyExists(List<String> plugs, String plug){
        return generalHelperUnit.plugAlreadyExists(plugs, plug);
    }
    /*-------------------------------------------------------manually option---------------------------------------------------*/
    //This method initialized a plug board by a list notes.
    public PlugBoardImp initPlugBoard(List<String> chosenPlugs, String abc){
        return manuallyUnit.initPlugBoard(chosenPlugs, abc);
    }
    //This method initialized a list of rotors by a list of ids.
    public List<RotorImp> initRotors(List<Integer> chosenRotorsIds, List<RotorImp> allTheRotors){
        return manuallyUnit.initRotors(chosenRotorsIds, allTheRotors);
    }
    //This method finds a rotor by its id.
    public RotorImp findRotorById(int id, List<RotorImp> allTheRotors){
        return manuallyUnit.findRotorById(id, allTheRotors);
    }
    //This method finds a reflector by its id.
    public ReflectorImp findReflectorById(String id, List<ReflectorImp> allTheReflectors){
        return manuallyUnit.findReflectorById(id, allTheReflectors);
    }
    /*-------------------------------------------------------Random unit methods-----------------------------------------------------*/
    //This method generate a random number in the range of 0-upperbound.
    public int getRandomNumber(int upperbound){
        return randomUnit.getRandomNumber(upperbound);
    }
    //This method sets a random of numbers (could be 0 also) of plugs in the machine.
    public List<String> setRandomPlugs(String abc){
        return randomUnit.setRandomPlugs(abc);
    }
    //This method sets a random of numbers (>1) of rotors from the engine to the machine.
    public List<Integer> setRandomRotors(List<RotorImp> allTheRotors){
        return randomUnit.setRandomRotors(allTheRotors);
    }
    /*------------------------------------------------------------------Validation unit methods---------------------------------------------------------*/
    //This method checks all input that will enter to the engine.
    public void checkInputFromJAXB(CTEEnigma enigma) {
        validationUnit.checkInputFromJAXB(enigma);
    }
    //This method checks the validation of the abc.
    public void checkAbc(String abc){
        validationUnit.checkAbc(abc);
    }
    //This method checks if the amount of object that was provided is equals to the amount of rotors that the machine use.
    public void checkAmountOfObjects(int size, String objName, String usersInput, int maxRotorsInMachine){
        validationUnit.checkAmountOfObjects(size, objName, usersInput, maxRotorsInMachine);
    }
    //This method checks all the possible problems regarding the rotors.
    public void checkRotors(CTEMachine machine){
        validationUnit.checkRotors(machine);
    }
    //This method checks the validation of all the rotors.
    public void checkTheListOfRotors(List<CTERotor> rotors, String tempAbc, String abc){
        validationUnit.checkTheListOfRotors(rotors, tempAbc, abc);
    }
    //This method checks the mapping validation of a rotor.
    public String searchForProblematicNote(List<CTEPositioning> ring){
        return validationUnit.searchForProblematicNote(ring);
    }
    //This method checks the validation of all the reflectors.
    public void checkReflectors(List<CTEReflector> reflectors, String tempAbc){
        validationUnit.checkReflectors(reflectors, tempAbc);
    }
    //This method checks the mapping validation of a reflector.
    public void checkProblematicMappingInReflector(CTEReflector reflector, String tempAbc){
        validationUnit.checkProblematicMappingInReflector(reflector, tempAbc);
    }
    //This method checks if the string includes only notes from the ABC.
    public void checkIfTheInputIsLegal(String input, String abc){
        validationUnit.checkIfTheInputIsLegal(input, abc);
    }
    public void checkUsersRotorsIds(List<Integer> chosenRotorsIds, List<RotorImp> allTheRotors) {
        validationUnit.checkUsersRotorsIds(chosenRotorsIds, allTheRotors);
    }
    public void checkUsersRotorsLocations(List<String> locations, List<Integer> chosenRotorsIds, String abc){
        validationUnit.checkUsersRotorsLocations(locations, chosenRotorsIds, abc);
    }
    public boolean checkIfReflectorExists(String id, List<ReflectorImp> allTheReflectors){
        return validationUnit.checkIfReflectorExists(id, allTheReflectors);
    }
    //This method checks if an id of a rotor exists.
    public void checkIfIdOfRotorExists(String id, List<RotorImp> allTheRotors){
        validationUnit.checkIfIdOfRotorExists(id, allTheRotors);
    }
    //This method checks if the plugs that the user entered is legal.
    public void checkUsersPlugs(List<String> chosenPlugs, String abc){
        validationUnit.checkUsersPlugs(chosenPlugs, abc);
    }

}
