package net.lakkie.pixely.saving;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SimpleDocumentInterface
{

	Document doc;
	
	public SimpleDocumentInterface(Document doc)
	{
		this.doc = doc;
	}
	
	public String get(String key)
	{
		Element elem = this.doc.getElementById(key);
		if (elem == null)
		{
			elem = this.makeEmptyNode(key);
		}
		return elem.getNodeValue();
	}
	
	private Element makeEmptyNode(String key)
	{
		Element elem = this.doc.createElement("data");
		elem.setAttribute("id", key);
		elem.setIdAttribute(key, false);
		elem.setNodeValue("");
		return elem;
	}
	
}
