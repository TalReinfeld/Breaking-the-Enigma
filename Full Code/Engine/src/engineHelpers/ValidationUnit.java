package engineHelpers;

import exceptions.*;
import parts.ReflectorImp;
import parts.RotorImp;
import schema.generated.*;

import java.io.Serializable;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import engineHelpers.*;
public class ValidationUnit implements Serializable {
    /*------------------------------------------------------------------checks for option 1---------------------------------------------------------*/

    /**
     * Checks all input that will enter to the engine.
     *
     * @param enigma
     *     allowed object is
     *     {@link CTEEnigma }
     *
     */
    public void checkInputFromJAXB(CTEEnigma enigma) {
        checkAbc(enigma.getCTEMachine().getABC());
        checkRotors(enigma.getCTEMachine());
        checkReflectors(enigma.getCTEMachine().getCTEReflectors().getCTEReflector(), enigma.getCTEMachine().getABC());
    }

    /**
     * Checks the validation of the abc.
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @exception StringIsOddException if there is an odd number of abc.
     */
    public void checkAbc(String abc){
        abc = abc.replaceAll("\\r|\\n|\\t", "");
        if(abc.length() % 2 == 1){
            throw new StringIsOddException(abc.length());
        }
    }

    /**
     * Checks if the amount of object that was provided is equals to the amount of rotors that the machine use.
     *
     * @param size
     *     allowed object is
     *     {@link int }
     *
     * @param objName
     *     allowed object is
     *     {@link String }
     *
     * @param usersInput
     *     allowed object is
     *     {@link String }
     *
     * @param maxRotorsInMachine
     *     allowed object is
     *     {@link int }
     *
     * @exception NotEnoughObjectsException if size < maxRotorsInMachine.
     * @exception TooManyObjectsException if size > maxRotorsInMachine.
     */
    public void checkAmountOfObjects(int size, String objName, String usersInput, int maxRotorsInMachine){
        if(size < maxRotorsInMachine){
            throw new NotEnoughObjectsException(objName, usersInput);
        }
        if(size > maxRotorsInMachine){
            throw new TooManyObjectsException(objName, usersInput);
        }
    }

    /**
     * Checks all the possible problems regarding the rotors.
     *
     * @param machine
     *     allowed object is
     *     {@link CTEMachine }
     *
     * @exception NotEnoughRotorsInMachineException if the machine needs more than the rotors in engine has.
     * @exception AmountOfRotorsLessThen2Exception if the machine needs less than 2 rotors.
     */
    public void checkRotors(CTEMachine machine){
        if(machine.getRotorsCount() > machine.getCTERotors().getCTERotor().size()){
            throw new NotEnoughRotorsInMachineException(machine.getRotorsCount(), machine.getCTERotors().getCTERotor().size());
        }
        if(machine.getRotorsCount() < 2){
            throw new AmountOfRotorsLessThen2Exception(machine.getRotorsCount());
        }
        checkTheListOfRotors(machine.getCTERotors().getCTERotor(), machine.getABC(), machine.getABC());
    }

    /**
     * Checks the validation of all the rotors.
     *
     * @param rotors
     *     allowed object is
     *     {@link List<CTERotor> }
     *
     * @param tempAbc
     *     allowed object is
     *     {@link String }
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @exception RotorsIdOutOfRangeException if the id of a rotor is not in the range.
     * @exception RehearsalsInRotorsIdsException if 2 or more rotors got the same id.
     * @exception RotorNotchIsOutOfRangeException if the notch of a rotor is not in the range.
     * @exception ObjectMultipleMappingsException if rotor has a multiple mappings inside its rings.
     * @exception ObjectAmountOfMappingException if rotor has more mappings than the amount of notes in the ABC.
     * @exception RotorsIdsByOrderException if the rotors does not have an id from 1 to the amount of rotors.
     */
    public void checkTheListOfRotors(List<CTERotor> rotors, String tempAbc, String abc){
        List<Boolean> idAlreadyExists = new ArrayList<>(rotors.size());
        for (int i = 0; i < rotors.size(); i++) {
            idAlreadyExists.add(false);
        }
        for (int i = 0; i < rotors.size(); i++) {
            if(rotors.get(i).getId() > rotors.size() || rotors.get(i).getId() < 1){
                throw new RotorsIdOutOfRangeException(rotors.size(), rotors.get(i).getId());
            }
            if(idAlreadyExists.get(rotors.get(i).getId() - 1)){
                throw new RehearsalsInRotorsIdsException(rotors.get(i).getId());
            }
            if(rotors.get(i).getNotch() > rotors.get(i).getCTEPositioning().size() || rotors.get(i).getNotch() < 1){
                throw new RotorNotchIsOutOfRangeException(rotors.get(i).getCTEPositioning().size(), rotors.get(i).getId(), rotors.get(i).getNotch());
            }
            String checkIfNoteAppearMoreThenOnce = searchForProblematicNote(rotors.get(i).getCTEPositioning());
            if(checkIfNoteAppearMoreThenOnce != "\n"){
                throw new ObjectMultipleMappingsException("Rotor", String.valueOf(rotors.get(i).getId()), checkIfNoteAppearMoreThenOnce);
            }
            tempAbc = tempAbc.replaceAll("\\r|\\n|\\t", "");
            if(rotors.get(i).getCTEPositioning().size() < tempAbc.length()){
                throw new ObjectAmountOfMappingException("Rotor", abc.length(), rotors.get(i).getCTEPositioning().size(),String.valueOf(rotors.get(i).getId()));
            }
            idAlreadyExists.set(rotors.get(i).getId() - 1, true);
        }
        for (int i = 0; i < idAlreadyExists.size(); i++) {
            if(!idAlreadyExists.get(i)){
                throw new RotorsIdsByOrderException(i + 1);
            }
        }
    }

    /**
     * Checks the mapping validation of a rotor.
     *
     * @param ring
     *     allowed object is
     *     {@link List<CTEPositioning> }
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public String searchForProblematicNote(List<CTEPositioning> ring){
        for (int i = 0; i < ring.size(); i++) {
            for (int j = 0; j < i; j++) {
                if(ring.get(i).getLeft().equals(ring.get(j).getLeft())){
                    return ring.get(i).getLeft();
                }
                if(ring.get(i).getRight() == ring.get(j).getRight()){
                    return ring.get(i).getRight();
                }
            }
        }
        return "\n";
    }

    /**
     * Checks the validation of all the reflectors.
     *
     * @param reflectors
     *     allowed object is
     *     {@link List<CTERotor> }
     *
     * @param tempAbc
     *     allowed object is
     *     {@link String }
     *
     * @exception ReflectorsRomeIdsException if a reflector got an id that is different than {I,II,III,IV,V}.
     * @exception ReflectorsIdOutOfRangeException if a reflector got an id that is not in the range.
     * @exception RehearsalsInReflectorsIdsException if there are 2 or more reflectors with the same id.
     * @exception ReflectorsIdsByOrderException if the reflectors does not have an id from I to the amount of rotors (in Rome number).
     */
    public void checkReflectors(List<CTEReflector> reflectors, String tempAbc){
        List<Boolean> idAlreadyExists = new ArrayList<>(reflectors.size());
        for (int i = 0; i < reflectors.size(); i++) {
            idAlreadyExists.add(false);
        }
        for (int i = 0; i < reflectors.size(); i++) {
            String id = reflectors.get(i).getId();
            if(GeneralHelperUnit.translateIdFromStringToInt(id) == -1){
                throw new ReflectorsRomeIdsException(id);
            }
            if(GeneralHelperUnit.translateIdFromStringToInt(id) >= reflectors.size()){
                String possibleIds = GeneralHelperUnit.makeAListOfPossibleReflectors(reflectors.size());
                throw new ReflectorsIdOutOfRangeException(possibleIds, id);
            }
            if(idAlreadyExists.get(GeneralHelperUnit.translateIdFromStringToInt(id))){
                throw new RehearsalsInReflectorsIdsException(reflectors.get(i).getId());
            }
            checkProblematicMappingInReflector(reflectors.get(i), tempAbc);
            idAlreadyExists.set(GeneralHelperUnit.translateIdFromStringToInt(id), true);
        }
        for (int i = 0; i < reflectors.size(); i++) {
            if(!idAlreadyExists.get(i)){
                throw new ReflectorsIdsByOrderException(GeneralHelperUnit.translateIdFromIntToString(i));
            }
        }
    }

    /**
     * Checks the mapping validation of a reflector.
     *
     * @param reflector
     *     allowed object is
     *     {@link CTEReflector }
     *
     * @param tempAbc
     *     allowed object is
     *     {@link String }
     *
     * @exception ReflectorInputEqualsOutputException if a reflector mapping an input to itself.
     * @exception ReflectorReflectIsOutOfRangeException if a reflector mapping an input/output that is out of range.
     * @exception ObjectAmountOfMappingException if a reflector got more mappings than the notes in the ABC.
     */
    public void checkProblematicMappingInReflector(CTEReflector reflector, String tempAbc){
        for (int i = 0; i < reflector.getCTEReflect().size(); i++){
            if(reflector.getCTEReflect().get(i).getInput() == reflector.getCTEReflect().get(i).getOutput()){
                throw new ReflectorInputEqualsOutputException(reflector.getId(), reflector.getCTEReflect().get(i).getInput());
            }
            if(!ValueRange.of(1, reflector.getCTEReflect().size() * 2).isValidIntValue(reflector.getCTEReflect().get(i).getInput())){
                throw new ReflectorReflectIsOutOfRangeException(reflector.getCTEReflect().size() * 2, reflector.getId(), String.valueOf(reflector.getCTEReflect().get(i).getInput()));
            }
            if(!ValueRange.of(1, reflector.getCTEReflect().size() * 2).isValidIntValue(reflector.getCTEReflect().get(i).getOutput())){
                throw new ReflectorReflectIsOutOfRangeException(reflector.getCTEReflect().size() * 2, reflector.getId(), String.valueOf(reflector.getCTEReflect().get(i).getOutput()));
            }
            tempAbc = tempAbc.replaceAll("\\r|\\n|\\t", "");
            if(reflector.getCTEReflect().size() < (tempAbc.length() / 2)){
                throw new ObjectAmountOfMappingException("Reflector", (tempAbc.length() / 2), reflector.getCTEReflect().size(),reflector.getId());
            }
        }
    }

    /**
     * Checks if the string includes only notes from the ABC.
     *
     * @param input
     *     allowed object is
     *     {@link String }
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @exception IllegalNoteException if there is a note in the input that does not exist in the ABC.
     */
    public void checkIfTheInputIsLegal(String input, String abc){
        for (int i = 0; i < input.length(); i++) {
            if(!abc.contains(input.substring(i, i + 1))){
                throw new IllegalNoteException(input.substring(i, i + 1));
            }
        }
    }
    /*------------------------------------------------------------------checks for option 3---------------------------------------------------------*/

    /**
     * Checks the ids of the rotors that will enter to the machine.
     *
     * @param chosenRotorsIds
     *     allowed object is
     *     {@link List<Integer> }
     *
     * @param allTheRotors
     *     allowed object is
     *     {@link List<RotorImp> }
     *
     * @exception IllegalNoteException if there is a rotor that does not exist in the engine.
     */
    public void checkUsersRotorsIds(List<Integer> chosenRotorsIds, List<RotorImp> allTheRotors) {
        for (Integer chosenRotorsId : chosenRotorsIds) {
            if ((chosenRotorsId > allTheRotors.size()) || (chosenRotorsId < 1)) {
                throw new RotorsIdOutOfRangeException(allTheRotors.size(), chosenRotorsId);
            }
        }
    }

    /**
     * Checks the positions of the rotors that will enter to the machine.
     *
     * @param locations
     *     allowed object is
     *     {@link List<String> }
     *
     * @param chosenRotorsIds
     *     allowed object is
     *     {@link List<Integer> }
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @exception RotorPositionIsOutOfRangeException if there is a position that does not exist in the ABC.
     */
    public void checkUsersRotorsLocations(List<String> locations, List<Integer> chosenRotorsIds, String abc){
        for (int i = 0; i < locations.size(); i++) {
            if(!abc.contains(locations.get(i))){
                throw new RotorPositionIsOutOfRangeException(chosenRotorsIds.get(i), locations.get(i));
            }
        }
    }

    /**
     * Checks the reflector that will enter to the machine.
     *
     * @param id
     *     allowed object is
     *     {@link String }
     *
     * @param allTheReflectors
     *     allowed object is
     *     {@link List<ReflectorImp> }
     *
     * @return
     *     possible object is
     *     {@link boolean }
     */
    public boolean checkIfReflectorExists(String id, List<ReflectorImp> allTheReflectors){
        for (ReflectorImp allTheReflector : allTheReflectors) {
            if (allTheReflector.getId().equals(id))
                return true;
        }
        return false;
    }

    /**
     * Checks if an id of a rotor exists.
     *
     * @param id
     *     allowed object is
     *     {@link String }
     *
     * @param allTheRotors
     *     allowed object is
     *     {@link List<RotorImp> }
     *
     * @exception RotorDoesNotExistsException if there is a rotor that the machine need and its id does not exist in the rotors from the engine.
     */
    public void checkIfIdOfRotorExists(String id, List<RotorImp> allTheRotors){
        for (RotorImp allTheRotor : allTheRotors) {
            if (String.valueOf(allTheRotor.getId()).equals(id)) {
                return;
            }
        }
        throw new RotorDoesNotExistsException(id);
    }

    /**
     * Checks if the plugs that the user entered is legal.
     *
     * @param chosenPlugs
     *     allowed object is
     *     {@link List<String> }
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @exception PlugBoardNoteToItselfException if there is a plug to itself (e.g. f->f).
     * @exception PlugBoardNoteDoesNotExistsException if there is a plug that contains notes that does not appear in the ABC.
     * @exception PlugBoardMoreThenOneMappingException if there are 2 or more plugs with the same note.
     */
    public void checkUsersPlugs(List<String> chosenPlugs, String abc){
        if(chosenPlugs.size() > 0){
            List<Boolean> plugsInUse = new ArrayList<>();
            for (int i = 0; i < abc.length(); i++) {
                plugsInUse.add(false);
            }
            for (String chosenPlug : chosenPlugs) {
                if (chosenPlug.substring(0, 1).equals(chosenPlug.substring(1, 2))) {
                    throw new PlugBoardNoteToItselfException(chosenPlug.substring(0, 1));
                }
                if (!abc.contains(chosenPlug.substring(0, 1))) {
                    throw new PlugBoardNoteDoesNotExistsException(chosenPlug.substring(0, 1), abc);
                }
                if (!abc.contains(chosenPlug.substring(1, 2))) {
                    throw new PlugBoardNoteDoesNotExistsException(chosenPlug.substring(1, 2), abc);
                }
                if (plugsInUse.get(abc.indexOf(chosenPlug.substring(0, 1)))) {
                    throw new PlugBoardMoreThenOneMappingException(chosenPlug.substring(0, 1));
                }
                if (plugsInUse.get(abc.indexOf(chosenPlug.substring(1, 2)))) {
                    throw new PlugBoardMoreThenOneMappingException(chosenPlug.substring(1, 2));
                }
                plugsInUse.set(abc.indexOf(chosenPlug.substring(0, 1)), true);
                plugsInUse.set(abc.indexOf(chosenPlug.substring(1, 2)), true);
            }
        }
    }
}
