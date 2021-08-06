package model;

public class AnchorElement extends TagElement{
	
	private String url, linkText;
	
	public AnchorElement(String url, String linkText,
			String attributes) {
		super("a", true, new TextElement(linkText), attributes);
		if(attributes == null) {
			setAttributes("href=\"" + url + "\"");
		}else {
			setAttributes("href=\"" + url + "\"" + attributes);
		}
		
		this.url = url;
		this.linkText = linkText;
	}
	
	// Returns string with HTML for anchor
	public String genHTML(int indentation) {
		return super.genHTML(indentation);
	}
	
	// Returns text enclosed in anchor
	public String getLinkText() {
		return linkText;
	}
	
	// Returns URL text
	public String getUrlText() {
		return url;
	}
}
