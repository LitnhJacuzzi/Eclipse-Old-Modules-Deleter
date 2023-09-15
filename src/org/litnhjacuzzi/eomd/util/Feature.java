package org.litnhjacuzzi.eomd.util;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feature")
public class Feature 
{
	@XmlAttribute
	public String id;
	
	@XmlAttribute
	public String version;
}
