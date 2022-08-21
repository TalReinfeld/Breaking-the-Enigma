package engineHelpers;

import parts.RotorImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUnit implements Serializable {
    /*---------------------------------------------------random option methods------------------------------------------------*/

    /**
     * Generates a random number in the range of 0-upperbound.
     *
     * @param upperbound
     *     allowed object is
     *     {@link int }
     *
     * @return
     *     possible object is
     *     {@link int }
     */
    public int getRandomNumber(int upperbound){
        Random rand = new Random();
        return rand.nextInt(upperbound);
    }

    /**
     * Sets a random of numbers (could be 0 also) of plugs in the machine.
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link List<String> }
     */
    public List<String> setRandomPlugs(String abc){
        int size = getRandomNumber(abc.length()/2);
        List<String> plugs = new ArrayList<>(size);
        List<Boolean> notUsedNotes = new ArrayList<>(abc.length());
        for (int i = 0; i < abc.length(); i++) {
            notUsedNotes.add(false);
        }
        for (int i = 0; i < size; i++) {
            int firstPlugLocation = getRandomNumber(abc.length());
            int secondPlugLocation = getRandomNumber(abc.length());
            if((firstPlugLocation == secondPlugLocation) || (notUsedNotes.get(firstPlugLocation)) || (notUsedNotes.get(secondPlugLocation))){
                i--;
            }
            else{
                char firstPlug = abc.charAt(firstPlugLocation);
                char secondPlug = abc.charAt(secondPlugLocation);
                String connectedPlugs = "";
                connectedPlugs = connectedPlugs + firstPlug + secondPlug;
                plugs.add(connectedPlugs);
                notUsedNotes.set(abc.indexOf(firstPlug), true);
                notUsedNotes.set(abc.indexOf(secondPlug), true);
            }
        }
        return plugs;
    }

    /**
     * Sets a random of numbers (>1) of rotors from the engine to the machine.
     *
     * @param allTheRotors
     *     allowed object is
     *     {@link List<RotorImp> }
     *
     * @return
     *     possible object is
     *     {@link List<Integer> }
     */
    public List<Integer> setRandomRotors(List<RotorImp> allTheRotors){
        int size = getRandomNumber(allTheRotors.size() - 1) + 2;
        List<Integer> rotors = new ArrayList<>(size);
        List<Boolean> UsedNotes = new ArrayList<>(allTheRotors.size());
        for (int i = 0; i < allTheRotors.size(); i++) {
            UsedNotes.add(false);
        }
        for (int i = 0; i < size; i++) {
            int randomNumber = getRandomNumber(allTheRotors.size());
            if(UsedNotes.get(randomNumber)){
                i--;
            }
            else{
                rotors.add(allTheRotors.get(randomNumber).getId());
                UsedNotes.set(randomNumber, true);
            }
        }
        return rotors;
    }
}
