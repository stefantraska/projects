package model;

public class TextElement implements Element{
	private String text;
	
	public TextElement(String text) {
		this.text = text;
	}
	
	// Prints indentation then the text
	public String genHTML(int indentation) {
		return Utilities.spaces(indentation) + text;
	}
}
