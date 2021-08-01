package model;

public class ImageElement extends TagElement{
	private String imageURL;
	
	public ImageElement(String imageURL, int width, int height,
						String alt, String attributes) {
		super("img", false, new TextElement(""), attributes);
		
		if(attributes == null) {
			attributes = "";
		}
		//Adds image attributes
		setAttributes("src=\"" + imageURL + "\" "
						+ "width=\"" + width + "\" "
						+ "height=\"" + height + "\" "
						+ "alt=\"" + alt + "\"" + attributes);
		this.imageURL = imageURL;
	}
	
	// Returns image URL
	public String getImageURL() {
		return imageURL;
	}
}
