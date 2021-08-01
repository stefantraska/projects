package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.*;

public class PublicTests {
	/* We use this string to prevent any hardcoding of results. */
	/* The submit server uses a different value for TESTS_TAG   */
	public static final String TESTS_TAG = "\n\nEndTest";
	
	@Test
	public void pubTextElementTest1() {
		int indentation = 3;
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TextElement element = new TextElement("John");
		answer = element.genHTML(indentation);
	
		answer += "\nSecond Element\n";
		element = new TextElement("University of MD");
		answer += element.genHTML(indentation);

		answer += "\nThird Element\n";
		indentation = 6;
		element = new TextElement("University of MD");
		answer += element.genHTML(indentation);

		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubTextElementTest1.txt", answer));
	}
	
	@Test
	public void pubHeadingElementTestA() {
		int indentation = 3, level = 1;
		String answer = "";
		String attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(false);
		HeadingElement element = new HeadingElement(new TextElement("Introduction"), level, attributes);
		answer = element.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubHeadingElementTestA.txt", answer));
	}
	
	@Test
	public void pubHeadingElementTest1() {
		int indentation = 3, level = 1;
		String answer = "";
		String attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(false);
		HeadingElement element = new HeadingElement(new TextElement("Introduction"), level, attributes);
		answer = element.genHTML(indentation);
	
		answer += "\nSecond Element\n";
		element = new HeadingElement(new TextElement("Introduction"), level + 1, attributes);
		answer += element.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubHeadingElementTest1.txt", answer));
	}
	
	@Test
	public void pubAnchorElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(false);
		AnchorElement element = new AnchorElement("http://www.cs.umd.edu", "UMD", attributes);
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		
		element = new AnchorElement("http://www.cs.umd.edu", "UMD", attributes);
		answer += element.genHTML(indentation);
		
		answer += "\nThirdElement\n";
		indentation = 6;
		element = new AnchorElement("http://www.cs.umd.edu", "UMD", attributes);;
		answer += element.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubAnchorElementTest1.txt", answer));
	}
	
	@Test
	public void pubImageElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null, alt = "Testudo Image";
		int width = 84, height = 111;
		
		TagElement.resetIds();
		TagElement.enableId(true);
		ImageElement element = new ImageElement("testudo.jpg", width, height, alt, attributes);
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		
		indentation = 6;
		element = new ImageElement("testudo.jpg", width, height, alt, attributes);
		answer += element.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubImageElementTest1.txt", answer));
	}
	
	@Test
	public void pubParagraphElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(true);
		ParagraphElement element = new ParagraphElement(attributes);
		element.addItem(new TextElement("Fear the turtle"));
		element.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", attributes));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		
		TagElement.enableId(false);
		indentation = 6;
		attributes = "style=\"color:red\"";
		ParagraphElement element2 = new ParagraphElement(attributes);
		element2.addItem(new TextElement("Fear the turtle"));
		element2.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", ""));
		answer += element2.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubParagraphElementTest1.txt", answer));
	}
	
	@Test
	public void pubListElementTest1() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		boolean orderedList = false;
		TagElement.resetIds();
		TagElement.enableId(true);
		ListElement element = new ListElement(orderedList, attributes);
		element.addItem(new TextElement("Superman"));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		
		indentation = 6;
		orderedList = true;
		attributes = "reversed";
		ListElement element2 = new ListElement(orderedList, attributes);
		element2.addItem(new TextElement("Superman"));
		element2.addItem(new TextElement("Batman"));
		answer += element2.genHTML(indentation);
		
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubListElementTest1.txt", answer));
	}
	
	@Test
	public void pubTableTest1() {
		int indentation = 3;
		String attributes = "border=\"1\"", answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		TableElement tableElement = new TableElement(2, 2, attributes);
		tableElement.addItem(0, 0, new TextElement("John"));
		tableElement.addItem(0, 1, new TextElement("Laura"));
		tableElement.addItem(1, 0, new TextElement("Rose"));
		
		answer += tableElement.genHTML(indentation);
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubTableTest1.txt", answer));
	}
	
	@Test
	public void pubWebPageTest1() {
		int indentation = 3;
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		WebPage webPage = new WebPage("Example1");
		answer = webPage.getWebPageHTML(indentation);
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubWebPageTest1.txt", answer));
	}
	
	@Test
	public void pubWebPageTest2() {
		WebPage webPage = new WebPage("Example1");
		int indentation = 3;
		String headingAttributes = null, paragraphAttributes = null, answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		webPage.addElement(new HeadingElement(new TextElement("Introduction"), 1, headingAttributes));
		ParagraphElement paragraph = new ParagraphElement(paragraphAttributes);
		paragraph.addItem(new TextElement("Fear the turtle"));
		paragraph.addItem(new ImageElement("testudo.jpg", 200, 300, "Testudo Image", ""));
		webPage.addElement(paragraph);

		answer += webPage.getWebPageHTML(indentation);
		answer += TESTS_TAG;
		
		assertTrue(TestsSupport.isCorrect("pubWebPageTest2.txt", answer));
	}
	
	@Test
	public void pubWebPageTest3() {
		WebPage webPage = new WebPage("Example2");
		int indentation = 3;
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(false);
		TableElement tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Dog"));
		tableElement.addItem(1, 1, new TextElement("Cat"));
		webPage.addElement(tableElement);

		tableElement = new TableElement(2, 2, null);
		tableElement.addItem(0, 0, new TextElement("Red"));
		tableElement.addItem(0, 1, new TextElement("Blue"));
		tableElement.addItem(1, 0, new TextElement("Green"));
		tableElement.addItem(1, 1, new TextElement("Yellow"));
		webPage.addElement(tableElement);
		
		webPage.addElement(new ListElement(true, null));

		answer += webPage.getWebPageHTML(indentation);
		answer += "\n" + webPage.stats();
		answer += TESTS_TAG;
		System.out.println(answer);
		assertTrue(TestsSupport.isCorrect("pubWebPageTest3.txt", answer));
	}
}
