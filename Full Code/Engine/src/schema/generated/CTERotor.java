//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.08.09 at 06:40:35 PM IDT 
//


package schema.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{}CTE-Positioning"/>
 *       &lt;/sequence>
 *       &lt;attribute name="notch" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ctePositioning"
})
@XmlRootElement(name = "CTE-Rotor")
public class CTERotor {

    @XmlElement(name = "CTE-Positioning", required = true)
    protected List<CTEPositioning> ctePositioning;
    @XmlAttribute(name = "notch", required = true)
    protected int notch;
    @XmlAttribute(name = "id", required = true)
    protected int id;

    /**
     * Gets the value of the ctePositioning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ctePositioning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCTEPositioning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTEPositioning }
     * 
     * 
     */
    public List<CTEPositioning> getCTEPositioning() {
        if (ctePositioning == null) {
            ctePositioning = new ArrayList<CTEPositioning>();
        }
        return this.ctePositioning;
    }

    /**
     * Gets the value of the notch property.
     * 
     */
    public int getNotch() {
        return notch;
    }

    /**
     * Sets the value of the notch property.
     * 
     */
    public void setNotch(int value) {
        this.notch = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
