package model;

public class TagElement implements Element{
	private String tagName;
	private boolean endTag;
	private Element content;
	private String attributes;
	private int id;
	
	private static int idCount;
	private static boolean idEnabled;
	
	public TagElement(String tagName, boolean endTag,
						Element content, String attributes) {
		this.tagName = tagName;
		this.endTag = endTag;
		this.content = content;
		this.attributes = attributes;
		id = idCount;
		idCount++;
	}
	// Returns string with HTML for tag and elements enclosed
	public String genHTML(int indentation) {
		return Utilities.spaces(indentation) + getStartTag() 
				+ content.genHTML(0) + getEndTag();
	}
	
	// Enables or disables IDs for elements
	public static void enableId(boolean choice) {
		if(choice) {
			idEnabled = true;
		}else {
			idEnabled = false;
		}
	}
	
	// Returns the end tag for the element if applicable
	public String getEndTag() {
		if(endTag) {
			return "</" + tagName + ">";
		}
		return "";
	}
	
	// Returns ID for the element
	public int getId() {
		return id;
	}
	
	// Returns start tag for the element with ID and attributes if applicable
	public String getStartTag() {
		if(attributes == null) {
			if(!idEnabled) {
				return "<" + tagName + ">";
			}
			return "<" + tagName + " id=\"" + getStringId() + "\">";
		}
		if(!idEnabled) {
			return "<" + tagName + " " + attributes + ">";
		}
		return "<" + tagName + " id=\"" + getStringId() 
				+ "\" " + attributes + ">";
	}
	
	// Creates a unique ID for the element
	public String getStringId() {
		return tagName + id;
	}
	
	// Resets ID counter to 1
	public static void resetIds() {
		idCount = 1;
	}
	
	// Setter method for attributes
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
}
