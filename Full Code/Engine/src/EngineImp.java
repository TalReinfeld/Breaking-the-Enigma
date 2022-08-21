import engineHelpers.ValidationUnit;
import exceptions.*;

import javafx.util.Pair;
import parts.PlugBoardImp;
import parts.ReflectorImp;
import parts.RotorImp;
import schema.generated.*;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.temporal.ValueRange;
import java.util.*;

public class EngineImp implements EngineAbilities, Serializable{
    private int maxRotorsInMachine;
    private String abc;
    private List<RotorImp> allTheRotors = new ArrayList<>();
    private List<ReflectorImp> allTheReflectors = new ArrayList<>();
    private PlugBoardImp plugBoard = new PlugBoardImp();
    private boolean afterReadingEngineDetails = false;
    private boolean afterChargingTheMachine = false;
    private MachineImp theMachine;
    private CodeDescriptionDTO theFirstMachine;
    private int amountOfMessagesProcessed;
    private List<Pair<CodeDescriptionDTO,List<Pair<Pair<String, String>, Long>>>> history = new ArrayList<>();
    private EngineHelper engineHelper = new EngineHelper();

    public EngineImp(){

    }
    public EngineImp(EngineImp engineImp){
        this.maxRotorsInMachine = engineImp.maxRotorsInMachine;
        this.abc = engineImp.abc;
        this.allTheRotors = engineImp.allTheRotors;
        this.allTheReflectors = engineImp.allTheReflectors;
        this.plugBoard = engineImp.plugBoard;
        this.afterReadingEngineDetails = engineImp.afterReadingEngineDetails;
        this.afterChargingTheMachine = engineImp.afterChargingTheMachine;
        this.theMachine = engineImp.theMachine;
        this.theFirstMachine = engineImp.theFirstMachine;
        this.amountOfMessagesProcessed = engineImp.amountOfMessagesProcessed;
        this.history = engineImp.history;
        this.engineHelper = engineImp.engineHelper;
    }

    public String getAbc() {
        return abc;
    }

    public List<RotorImp> getAllTheRotors() {
        return allTheRotors;
    }

    public List<ReflectorImp> getAllTheReflectors() {
        return allTheReflectors;
    }

    public void resetMessagesProcessed(){
        amountOfMessagesProcessed = 0;
    }

    public int getMaxRotorsInMachine() {
        return maxRotorsInMachine;
    }

    public boolean isAfterReadingEngineDetails() {
        return afterReadingEngineDetails;
    }

    public boolean isAfterChargingTheMachine() {
        return afterChargingTheMachine;
    }

    public EngineHelper getEngineHelper() {
        return engineHelper;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }


    @Override
    public void readSystemDetailsFile(String FILE_NAME) throws Exception{

        try {
            InputStream inputStream = new FileInputStream(new File(FILE_NAME));
            CTEEnigma cteEnigma = deserializeFrom(inputStream);
            engineHelper.checkInputFromJAXB(cteEnigma);
            initEngine(cteEnigma);
        } catch (JAXBException | FileNotFoundException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public MachineSpecificationsDTO machineSpecifications() {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        if(!afterChargingTheMachine){
            throw new BeforeChargingTheMachineException();
        }
        CodeDescriptionDTO currCodeDescription = makeCodeDescriptionDTO(theMachine);
        MachineSpecificationsDTO details = new MachineSpecificationsDTO(allTheRotors.size(), allTheReflectors.size(), amountOfMessagesProcessed, currCodeDescription, theFirstMachine);
        return details;
    }

    @Override
    public void initMachineManually(CodeDescriptionDTO machineDetails) {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        PlugBoardImp plugBoard = engineHelper.initPlugBoard(machineDetails.getChosenPlugs(), abc);
        List<RotorImp> rotors = engineHelper.initRotors(machineDetails.getChosenRotorsIds(), allTheRotors);
        for (int i = 0; i < rotors.size(); i++) {
            rotors.get(i).setPosition(rotors.get(i).getRightRing().indexOf(machineDetails.getFirstLocationsChosenRotors().get(i)));
        }
        ReflectorImp reflector = engineHelper.findReflectorById(machineDetails.getChosenReflector(), allTheReflectors);
        theFirstMachine = new CodeDescriptionDTO(machineDetails);
        theMachine = new MachineImp(abc, plugBoard, rotors, reflector);
        history.add(new Pair<>(theFirstMachine, new ArrayList<>()));
        afterChargingTheMachine = true;
    }

    @Override
    public void initMachineAutomatically() {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        PlugBoardImp plugBoard = engineHelper.initPlugBoard(engineHelper.setRandomPlugs(abc), abc);
        List<RotorImp> rotors = engineHelper.initRotors(engineHelper.setRandomRotors(allTheRotors), allTheRotors);
        for (int i = 0; i < rotors.size(); i++) {
            rotors.get(i).setPosition(engineHelper.getRandomNumber(abc.length()));
        }

        ReflectorImp reflector = engineHelper.findReflectorById(engineHelper.translateIdFromIntToString(engineHelper.getRandomNumber(allTheReflectors.size())), allTheReflectors);
        theMachine = new MachineImp(abc, plugBoard, rotors, reflector);
        theFirstMachine = new CodeDescriptionDTO(makeCodeDescriptionDTO(theMachine));
        history.add(new Pair<>(theFirstMachine, new ArrayList<>()));
        afterChargingTheMachine = true;
    }

    @Override
    public String inputProcessing(String input) {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        if(!afterChargingTheMachine){
            throw new BeforeChargingTheMachineException();
        }
        if(input.length() == 0){
            throw new EmptyInputException();
        }
        engineHelper.checkIfTheInputIsLegal(input, abc);
        long startTime = System.nanoTime();
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = output + theMachine.encrypt(input.substring(i, i + 1));
        }
        long endTime = System.nanoTime();
        amountOfMessagesProcessed++;
        history.get(history.size() - 1).getValue().add(new Pair(new Pair(input, output), endTime - startTime));
        return output.toString();
    }

    @Override
    public void resetMachine() {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        if(!afterChargingTheMachine){
            throw new BeforeChargingTheMachineException();
        }
        for (int i = 0; i < theMachine.getRotors().size(); i++) {
            theMachine.getRotors().get(i).setPosition(theMachine.getRotors().get(i).getRightRing().indexOf(theFirstMachine.getFirstLocationsChosenRotors().get(i)));
        }
    }

    @Override
    public HistoryAndStatisticDTO historyAndStatistic() {
        if(!afterReadingEngineDetails){
            throw new BeforeReadingEngineDetailsException();
        }
        return new HistoryAndStatisticDTO(history);
    }

    @Override
    public void exit() {

    }

    @Override
    public void saveDetails(String fileName) throws IOException{
        writeToFile(fileName);
    }

    @Override
    public void loadDetails(String fileName) throws Exception {
        EngineImp copyMe = readFromFile(fileName);
        this.maxRotorsInMachine = copyMe.maxRotorsInMachine;
        this.abc = copyMe.abc;
        this.allTheRotors = copyMe.allTheRotors;
        this.allTheReflectors = copyMe.allTheReflectors;
        this.plugBoard = copyMe.plugBoard;
        this.afterReadingEngineDetails = copyMe.afterReadingEngineDetails;
        this.afterChargingTheMachine = copyMe.afterChargingTheMachine;
        this.theMachine = copyMe.theMachine;
        this.theFirstMachine = copyMe.theFirstMachine;
        this.amountOfMessagesProcessed = copyMe.amountOfMessagesProcessed;
        this.history = copyMe.history;
        this.engineHelper = copyMe.engineHelper;
    }
    /*------------------------------------------------------------------CTE <----> regular objects------------------------------------------*/
    /**
     * This method creates a CTE enigma from the xml file.
     */

    private static schema.generated.CTEEnigma deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("schema.generated");
        Unmarshaller u = jc.createUnmarshaller();
        return (CTEEnigma) u.unmarshal(in);
    }

    /**
     * This method initialized an engine from a CTE-enigma.
     */
    //
    public void initEngine(CTEEnigma enigma){
        maxRotorsInMachine = enigma.getCTEMachine().getRotorsCount();
        abc = enigma.getCTEMachine().getABC().replaceAll("\\r|\\n|\\t","");
        initAllRotorsFromCTEObjects(enigma.getCTEMachine().getCTERotors());
        initAllReflectorsFromCTEObjects(enigma.getCTEMachine().getCTEReflectors());
        afterReadingEngineDetails = true;
        afterChargingTheMachine = false;
        resetMessagesProcessed();
    }
    //This method initialized a list of reflectors from a CTE-reflectors.
    public void initAllReflectorsFromCTEObjects(CTEReflectors cteReflectors){
        allTheReflectors.clear();
        for (int i = 0; i < cteReflectors.getCTEReflector().size(); i++) {
            initReflectorFromCTEObject(cteReflectors.getCTEReflector().get(i));
        }
    }
    //This method initialized a reflector from a CTE-reflector.
    public void initReflectorFromCTEObject(CTEReflector cteReflector){
        List<Integer> notesInUse = new ArrayList<>();
        List<Integer> inputsOutputs = new ArrayList<>(cteReflector.getCTEReflect().size() * 2);
        for (int i = 0; i < cteReflector.getCTEReflect().size() * 2; i++) {
            inputsOutputs.add(0);
        }
        String id = cteReflector.getId();
        for (int i = 0; i < cteReflector.getCTEReflect().size(); i++) {
            if(notesInUse.contains(cteReflector.getCTEReflect().get(i).getOutput() - 1)){
                throw new ObjectMultipleMappingsException("Reflector", cteReflector.getId(), String.valueOf(cteReflector.getCTEReflect().get(i).getOutput()));
            }
            inputsOutputs.set(cteReflector.getCTEReflect().get(i).getInput() - 1, cteReflector.getCTEReflect().get(i).getOutput() - 1);
            notesInUse.add(cteReflector.getCTEReflect().get(i).getOutput() - 1);
            if(notesInUse.contains(cteReflector.getCTEReflect().get(i).getInput() - 1)){
                throw new ObjectMultipleMappingsException("Reflector", cteReflector.getId(), String.valueOf(cteReflector.getCTEReflect().get(i).getInput()));
            }
            inputsOutputs.set(cteReflector.getCTEReflect().get(i).getOutput() - 1, cteReflector.getCTEReflect().get(i).getInput() - 1);
            notesInUse.add(cteReflector.getCTEReflect().get(i).getInput() - 1);
        }
        allTheReflectors.add(new ReflectorImp(inputsOutputs, id));
    }
    //This method initialized a list of rotors from a CTE-rotors.
    public void initAllRotorsFromCTEObjects(CTERotors cteRotors){
        allTheRotors.clear();
        for (int i = 0; i < cteRotors.getCTERotor().size(); i++) {
            initRotorFromCTEObject(cteRotors.getCTERotor().get(i));
        }
    }
    //This method initialized a rotor from a CTE-rotor.
    public void initRotorFromCTEObject(CTERotor cteRotor){
        Map<String, Boolean> rightNotesInUse = new HashMap<>();
        Map<String, Boolean> leftNotesInUse = new HashMap<>();
        int id = cteRotor.getId();
        int notch = cteRotor.getNotch() - 1;
        List<String> rightRing = new ArrayList<>(cteRotor.getCTEPositioning().size());
        List<String> leftRing = new ArrayList<>(cteRotor.getCTEPositioning().size());
        for (int i = 0; i < cteRotor.getCTEPositioning().size(); i++) {
            if(rightNotesInUse.containsKey(cteRotor.getCTEPositioning().get(i).getRight().toUpperCase())){
                throw new ObjectMultipleMappingsException("Rotor", String.valueOf(cteRotor.getId()), cteRotor.getCTEPositioning().get(i).getRight().toUpperCase());
            }
            if(leftNotesInUse.containsKey(cteRotor.getCTEPositioning().get(i).getLeft().toUpperCase())){
                throw new ObjectMultipleMappingsException("Rotor", String.valueOf(cteRotor.getId()), cteRotor.getCTEPositioning().get(i).getLeft().toUpperCase());
            }
            rightRing.add(cteRotor.getCTEPositioning().get(i).getRight().toUpperCase());
            leftRing.add(cteRotor.getCTEPositioning().get(i).getLeft().toUpperCase());
            rightNotesInUse.put(cteRotor.getCTEPositioning().get(i).getRight().toUpperCase(), true);
            leftNotesInUse.put(cteRotor.getCTEPositioning().get(i).getLeft().toUpperCase(), true);
        }
        allTheRotors.add(new RotorImp(leftRing, rightRing, id, notch));
    }
    //This method write the Engine details to a file.
    public void writeToFile(String fileName) throws IOException{
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(this);
            out.flush();
        }
    }
    //This method read the Engine details from a file.
    public EngineImp readFromFile(String fileName) throws Exception{
        try(ObjectInputStream in = (new ObjectInputStream(new FileInputStream(fileName)))){
            EngineImp tempEngine = (EngineImp) in.readObject();
            return tempEngine;
        }
    }
    /*------------------------------------------------------------------from ui to engine methods---------------------------------------------------*/
    //This method convert a string to a List of rotors ids.
    public List<Integer> rotorsFromStringToListOfIds(String usersInput){
        List<Integer> ids = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < usersInput.length(); i++) {
            if(i == usersInput.length() - 1){
                temp = temp + usersInput.substring(i, i + 1);
            }
            if((usersInput.charAt(i) == ',') || (i == usersInput.length() - 1)){
                engineHelper.checkIfIdOfRotorExists(temp, allTheRotors);
                if(ids.contains(Integer.valueOf(temp))){
                    throw new RehearsalsInRotorsIdsException(Integer.valueOf(temp));
                }
                ids.add(Integer.valueOf(temp));
                temp = "";
            }
            else{
                temp = temp + usersInput.substring(i, i + 1);
            }
        }
        engineHelper.checkAmountOfObjects(ids.size(), "Rotors", usersInput, maxRotorsInMachine);
        Collections.reverse(ids);
        return ids;
    }
    //This method convert a string to a List of starting positions.
    public List<String> startingPositionsFromStringToListOfStrings(String startingPositions){
        engineHelper.checkAmountOfObjects(startingPositions.length(), "Starting Positions", startingPositions, maxRotorsInMachine);
        List<String> startingPositionsInList = new ArrayList<>();
        for (int i = 0; i < startingPositions.length(); i++) {
            if(!abc.contains(startingPositions.substring(i, i + 1))){
                throw new IllegalNoteException(startingPositions.substring(i, i + 1));
            }
            startingPositionsInList.add(startingPositions.substring(i, i + 1));
        }
        Collections.reverse(startingPositionsInList);
        return startingPositionsInList;
    }
    //This method convert a string to a list of plugs.
    public List<String> plugsFomStringToListOfStrings(String input){
        List<String> plugs = new ArrayList<>();
        if(input.length() > 0){
            String temp = "";
            for (int i = 0; i < input.length(); i++) {
                if((i % 2 == 0) && (i != 0)){
                    plugs.add(temp);
                    temp = "";
                }
                temp = temp + input.substring(i, i + 1);
            }
            plugs.add(temp);
        }
        return plugs;
    }
    //This method converts a machine to a DTO-code description.
    public CodeDescriptionDTO makeCodeDescriptionDTO(MachineImp machine){
        List<Integer> chosenRotorsIds = new ArrayList<>();
        List<String> firstLocationsChosenRotors = new ArrayList<>();
        List<Integer> rotorsCurrDistanceFromNotch = new ArrayList<>();
        List<String> plugs = new ArrayList<>();
        machine.getRotors().forEach(RotorImp -> {
            rotorsCurrDistanceFromNotch.add((RotorImp.getNotch() - RotorImp.getPosition() + RotorImp.getRightRing().size()) % RotorImp.getRightRing().size());
            chosenRotorsIds.add(RotorImp.getId());
            firstLocationsChosenRotors.add(RotorImp.getRightRing().get(RotorImp.getPosition()));
        });
        for (int i = 0; i < machine.getPlugBoard().getPlugs().size(); i++) {
            if(!(machine.getPlugBoard().getPlugs().get(i).equals(machine.getPlugBoard().getPlugsAfterEncrypt().get(i))) && (!engineHelper.plugAlreadyExists(plugs, machine.getPlugBoard().getPlugs().get(i)))){
                plugs.add(machine.getPlugBoard().getPlugs().get(i)+machine.getPlugBoard().getPlugsAfterEncrypt().get(i));
            }
        }
        return new CodeDescriptionDTO(chosenRotorsIds, firstLocationsChosenRotors, rotorsCurrDistanceFromNotch, machine.getReflector().getId(), plugs);
    }

}