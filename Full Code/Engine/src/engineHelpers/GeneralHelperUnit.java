package engineHelpers;

import exceptions.InvalidInputException;

import java.io.Serializable;
import java.util.List;

public class GeneralHelperUnit implements Serializable {
    /*------------------------------------------------------------------general methods-------------------------------------------------------------*/

    /**
     * Checks if an integer is in a specific range.
     *
     * @param intInput
     *     allowed object is
     *     {@link int }
     *
     * @param range
     *     allowed object is
     *     {@link int }
     *
     * @exception InvalidInputException if the number is out of range.
     *
     * @return
     *     possible object is
     *     {@link boolean }
     */
    public boolean checkIfTheNumberIsInRange(int intInput, int range){
        for (int i = 0; i < range; i++) {
            if(intInput == (i + 1)){
                return true;
            }
        }
        throw new InvalidInputException();
    }

    /**
     * Checks if a String has a numeric value.
     *
     * @param input
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link boolean }
     */
    public boolean checkIfAStringGotANumericValue(String input){
        try{
            int intValue = Integer.valueOf(input);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Converts a reflector id to a number.
     *
     * @param id
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link int }
     */
    public static int translateIdFromStringToInt(String id){
        switch (id){
            case "I": return 0;
            case "II": return 1;
            case "III": return 2;
            case "IV": return 3;
            case "V": return 4;
        }
        return -1;
    }

    /**
     * Converts a number to a reflector id.
     *
     * @param id
     *     allowed object is
     *     {@link int }
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public static String translateIdFromIntToString(int id){
        switch (id){
            case 0: return "I";
            case 1: return "II";
            case 2: return "III";
            case 3: return "IV";
            case 4: return "V";
        }
        return "-1";
    }

    /**
     * Creates a string of all the possible reflectors ids.
     *
     * @param size
     *     allowed object is
     *     {@link int }
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public static String makeAListOfPossibleReflectors(int size){
        String possibleIds = new String();
        for (int i = 0; i < size; i++) {
            possibleIds = possibleIds + translateIdFromIntToString(i) + ", ";
        }
        return possibleIds.substring(0, possibleIds.length() - 2);
    }

    /**
     * Checks if a plug is already exists.
     *
     * @param plugs
     *     allowed object is
     *     {@link List<String> }
     *
     * @param plug
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link boolean }
     */
    public boolean plugAlreadyExists(List<String> plugs, String plug){
        for (int i = 0; i < plugs.size(); i++) {
            if(plugs.get(i).contains(plug)){
                return true;
            }
        }
        return false;
    }
}
