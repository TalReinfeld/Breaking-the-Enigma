import java.io.Serializable;

public class MachineSpecificationsDTO implements Serializable {
    private int totalRotors;
    private int amountOfReflectors;
    private int amountOfMessagesProcessed;
    private CodeDescriptionDTO currCodeDescription;
    private CodeDescriptionDTO oldCodeDescription;

    public MachineSpecificationsDTO(int totalRotors, int amountOfReflectors, int amountOfMessagesProcessed, CodeDescriptionDTO currCodeDescription, CodeDescriptionDTO oldCodeDescription){
        this.totalRotors = totalRotors;
        this.amountOfReflectors = amountOfReflectors;
        this.amountOfMessagesProcessed = amountOfMessagesProcessed;
        this.currCodeDescription = currCodeDescription;
        this.oldCodeDescription = oldCodeDescription;
    }

    @Override
    public String toString(){
        StringBuffer output = new StringBuffer();
        amountOfPossibleRotorsToString(output);
        amountOfReflectorsToString(output);
        amountOfMessagesProcessedToString(output);
        output.append("4.The original code description: ");
        output.append(oldCodeDescription.toString());
        output.append("\n5.The current code description: ");
        output.append(currCodeDescription.toString());

        return output.toString();
    }

    /**
     * Add a string representation of the object to the buffer (e.g. 1.The total rotors in use are: 2/3)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void amountOfPossibleRotorsToString(StringBuffer buffer){
        buffer.append("1.The total rotors in use are: ");
        buffer.append(currCodeDescription.getChosenRotorsIds().size());
        buffer.append('/');
        buffer.append(totalRotors);
        buffer.append('\n');
    }

    /**
     * Add a string representation of the object to the buffer (e.g. 2.The total reflectors in The Engine are: 3)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void amountOfReflectorsToString(StringBuffer buffer){
        buffer.append("2.The total reflectors in The Engine are: ");
        buffer.append(amountOfReflectors);
        buffer.append('\n');
    }

    /**
     * Add a string representation of the object to the buffer (e.g. 3.The amount of messages processed by the machine so far is: 3)
     *
     * @param buffer
     *     allowed object is
     *     {@link StringBuffer }
     *
     */
    public void amountOfMessagesProcessedToString(StringBuffer buffer){
        buffer.append("3.The amount of messages processed by the machine so far is: ");
        buffer.append(amountOfMessagesProcessed);
        buffer.append('\n');
    }
}
