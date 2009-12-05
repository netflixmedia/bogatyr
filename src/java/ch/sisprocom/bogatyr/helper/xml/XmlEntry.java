package ch.sisprocom.bogatyr.helper.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"key", "value"})
@XmlRootElement(name = "entry")
public class XmlEntry {

    @XmlElement(name = "key", required = true)
    private final String key;
    @XmlElement(name = "value", required = true)
    private final String value;
    
    public XmlEntry() {
    	this.key = null;
    	this.value = null;
    }

    public XmlEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
