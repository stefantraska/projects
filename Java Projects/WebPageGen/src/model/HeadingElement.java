package model;

public class HeadingElement extends TagElement{

	public HeadingElement(Element content, int level,
							String attributes) {
		super("h" + level, true, content, attributes);
	}
}
