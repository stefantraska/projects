package model;

import java.util.ArrayList;

public class ListElement extends TagElement{
	
	boolean ordered;
	ArrayList<Element> listItems;
	
	public ListElement(boolean ordered, String attributes) {
		super(ordered ? "ol" : "ul", true, new TextElement(""), attributes);
		
		this.ordered = ordered;
		listItems = new ArrayList<Element>();
	}
	
	// Returns string with HTML for list and elements enclosed
	public String genHTML(int indentation) {
		String code = "";

		code += Utilities.spaces(indentation) + getStartTag();
		
		for(int i = 0; i < listItems.size(); i++) {
			code += "\n" + Utilities.spaces(indentation);
			code += "<li>" + listItems.get(i).genHTML(0) + "</li>";
		}
		
		return code + "\n" + Utilities.spaces(indentation) + getEndTag();
	}
	
	//Adds an element to the paragraph.
	public void addItem(Element item) {
		listItems.add(item);
	}
}
