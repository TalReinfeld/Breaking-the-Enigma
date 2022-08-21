package engineHelpers;

import parts.PlugBoardImp;
import parts.ReflectorImp;
import parts.RotorImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManuallyUnit implements Serializable {
    /*---------------------------------------------------manually option methods-----------------------------------------------*/

    /**
     * Initialized a PlugBoardImp by a list notes.
     *
     * @param chosenPlugs
     *     allowed object is
     *     {@link List<String> }
     *
     * @param abc
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link PlugBoardImp }
     */
    public PlugBoardImp initPlugBoard(List<String> chosenPlugs, String abc){
        PlugBoardImp plugBoard = new PlugBoardImp(abc);
        for (int i = 0; i < chosenPlugs.size(); i++) {
            plugBoard.setPlug(chosenPlugs.get(i).substring(0, 1), chosenPlugs.get(i).substring(1, 2));
        }
        return plugBoard;
    }

    /**
     * Initialized a list of RotorImp by a list of ids.
     *
     * @param chosenRotorsIds
     *     allowed object is
     *     {@link List<Integer> }
     *
     * @param allTheRotors
     *     allowed object is
     *     {@link List<RotorImp> }
     *
     * @return
     *     possible object is
     *     {@link List<RotorImp> }
     */
    public List<RotorImp> initRotors(List<Integer> chosenRotorsIds, List<RotorImp> allTheRotors){
        List<RotorImp> rotors = new ArrayList<>();
        for (int i = 0; i < chosenRotorsIds.size(); i++) {
            rotors.add(findRotorById(chosenRotorsIds.get(i), allTheRotors));
        }
        return rotors;
    }

    /**
     * Finds a rotor by its id.
     *
     * @param id
     *     allowed object is
     *     {@link int }
     *
     * @param allTheRotors
     *     allowed object is
     *     {@link List<RotorImp> }
     *
     * @return
     *     possible object is
     *     {@link RotorImp }
     */
    public RotorImp findRotorById(int id, List<RotorImp> allTheRotors){
        for (int i = 0; i < allTheRotors.size(); i++) {
            if(allTheRotors.get(i).getId() == id){
                return allTheRotors.get(i);
            }
        }
        return null;
    }

    /**
     * Finds a reflector by its id.
     *
     * @param allTheReflectors
     *     allowed object is
     *     {@link List<ReflectorImp> }
     *
     * @param id
     *     allowed object is
     *     {@link String }
     *
     * @return
     *     possible object is
     *     {@link ReflectorImp }
     */
    public ReflectorImp findReflectorById(String id, List<ReflectorImp> allTheReflectors){
        for (int i = 0; i < allTheReflectors.size(); i++) {
            if(allTheReflectors.get(i).getId().equals(id)){
                return allTheReflectors.get(i);
            }
        }
        return null;
    }
}
