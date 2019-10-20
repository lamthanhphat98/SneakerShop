package pdf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Report complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="Report">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="picture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="view" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Report", propOrder = {
    "id",
    "name",
    "price",
    "picture",
    "view"
})
public class Report {

    protected Integer id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected BigDecimal price;
    @XmlElement(required = true)
    protected String picture;
    protected int view;

    public Report() {
    }

    public Report(Integer id, String name, BigDecimal price, String picture, int view) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.view = view;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the price property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the picture property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPicture(String value) {
        this.picture = value;
    }

    /**
     * Gets the value of the view property.
     *
     */
    public int getView() {
        return view;
    }

    /**
     * Sets the value of the view property.
     *
     */
    public void setView(int value) {
        this.view = value;
    }

}
