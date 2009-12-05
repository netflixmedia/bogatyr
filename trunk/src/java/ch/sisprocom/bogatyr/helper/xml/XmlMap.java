package ch.sisprocom.bogatyr.helper.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "map")
public class XmlMap {
	
    @XmlElement(name = "entry", required = true)
    private final List<XmlEntry> entrys = new ArrayList<XmlEntry>();
    
    public List<XmlEntry> getEntrys() {
        return this.entrys;
    }
}
