package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement{
	
	ArrayList<Element> items;
	
	public ParagraphElement(String attributes) {
		super("p", true, new TextElement(""), attributes);
		items = new ArrayList<Element>();
	}
	
	// Return string with HTML for paragraph and elements enclosed
	public String genHTML(int indentation) {
		String code = "";
		
		code += Utilities.spaces(indentation) + getStartTag() + "\n";
		
		for(int i = 0; i < items.size(); i++) {
			code += Utilities.spaces(2 * indentation)
					+ items.get(i).genHTML(0) + "\n";
		}
		
		return code + Utilities.spaces(indentation) + getEndTag();
	}
	
	//Adds an element to the paragraph.
	public void addItem(Element item) {
		items.add(item);
	}
}
