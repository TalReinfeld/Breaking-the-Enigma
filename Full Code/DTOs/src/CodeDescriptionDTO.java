import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeDescriptionDTO implements Serializable {
    private List<Integer> chosenRotorsIds = new ArrayList<>();
    private List<Integer> rotorsDistanceFromNotch = new ArrayList<>();
    private List<String> firstLocationsChosenRotors = new ArrayList<>();
    private String chosenReflector;
    private List<String> chosenPlugs = new ArrayList<>();

    public CodeDescriptionDTO(List<Integer> chosenRotorsIds, List<String> firstLocationsChosenRotors, List<Integer> rotorsDistanceFromNotch, String chosenReflector, List<String> chosenPlugs){
        this.chosenRotorsIds = chosenRotorsIds;
        this.firstLocationsChosenRotors = firstLocationsChosenRotors;
        this.rotorsDistanceFromNotch = rotorsDistanceFromNotch;
        this.chosenReflector = chosenReflector;
        this.chosenPlugs = chosenPlugs;
    }

    public CodeDescriptionDTO(CodeDescriptionDTO codeDescriptionDTO){
        this.chosenRotorsIds = codeDescriptionDTO.chosenRotorsIds;
        this.firstLocationsChosenRotors = codeDescriptionDTO.firstLocationsChosenRotors;
        this.rotorsDistanceFromNotch = codeDescriptionDTO.rotorsDistanceFromNotch;
        this.chosenReflector = codeDescriptionDTO.chosenReflector;
        this.chosenPlugs = codeDescriptionDTO.chosenPlugs;
    }

    /**
     * Gets the value of the chosenReflector property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getChosenReflector() {
        return chosenReflector;
    }

    /**
     * Gets the value of the firstLocationsChosenRotors property.
     *
     * @return
     *     possible object is
     *     {@link List<String> }
     *
     */
    public List<String> getFirstLocationsChosenRotors() {
        return firstLocationsChosenRotors;
    }

    /**
     * Gets the value of the rotorsDistanceFromNotch property.
     *
     * @return
     *     possible object is
     *     {@link List<Integer> }
     *
     */
    public List<Integer> getRotorsDistanceFromNotch() {
        return rotorsDistanceFromNotch;
    }

    /**
     * Gets the value of the chosenRotorsIds property.
     *
     * @return
     *     possible object is
     *     {@link List<Integer> }
     *
     */
    public List<Integer> getChosenRotorsIds() {
        return chosenRotorsIds;
    }

    /**
     * Gets the value of the chosenPlugs property.
     *
     * @return
     *     possible object is
     *     {@link List<String> }
     *
     */
    public List<String> getChosenPlugs() {
        return chosenPlugs;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        chosenRotorsIdsAndDistanceFromNotchToString(output);
        firstLocationsChosenRotorsToString(output);
        chosenReflectorToString(output);
        chosenPlugsToString(output);
        return output.toString();
    }

    /**
     * Add a string representation of the object to the buffer (e.g. <3(2),2(1)>)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void chosenRotorsIdsAndDistanceFromNotchToString(StringBuffer buffer){
        buffer.append('<');
        for (int i = chosenRotorsIds.size() - 1; i >= 0; i--) {
            buffer.append(chosenRotorsIds.get(i));
            buffer.append(String.format("(%d),", rotorsDistanceFromNotch.get(i)));
        }
        buffer.replace(buffer.length() - 1, buffer.length(), ">");
    }

    /**
     * Add a string representation of the object to the buffer (e.g. <ABCD>)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void firstLocationsChosenRotorsToString(StringBuffer buffer){
        buffer.append('<');
        for (int i = firstLocationsChosenRotors.size() - 1; i >= 0; i--) {
            buffer.append(firstLocationsChosenRotors.get(i));
        }
        buffer.append('>');
    }

    /**
     * Add a string representation of the object to the buffer (e.g. <II>)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void chosenReflectorToString(StringBuffer buffer){
        buffer.append(String.format("<%s>", chosenReflector));
    }

    /**
     * Add a string representation of the object to the buffer (e.g. <A|B,C|D>)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void chosenPlugsToString(StringBuffer buffer){
        if(this.chosenPlugs.size() != 0){
            buffer.append('<');
            for (int i = 0; i < chosenPlugs.size(); i++) {
                buffer.append(chosenPlugs.get(i).toString().charAt(0));
                buffer.append('|');
                buffer.append(chosenPlugs.get(i).toString().charAt(1));
                buffer.append(',');
            }
            buffer.setCharAt(buffer.length() - 1, '>');
        }
    }
}
