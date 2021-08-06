package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage>{
	
	private String title;
	private ArrayList<Element> webElements;
	
	public WebPage(String title) {
		this.title = title;
		webElements = new ArrayList<Element>();
	}
	
	// Adds element to the webpage (in the body)
	public int addElement(Element element) {
		webElements.add(element);
		if(element instanceof TagElement) {
			return ((TagElement) element).getId();
		}
		return -1;
	}
	
	/*
	 * Creates basic template for HTML page with head and body, then adds
	 * elements inside the body tags with appropriate indentation
	 */
	public String getWebPageHTML(int indentation) {
		// HTML base code
		String code = "<!doctype html>\n<html>\n" 
						+ Utilities.spaces(indentation) + "<head>\n" 
						+ Utilities.spaces(2 * indentation) 
						+ "<meta charset=\"utf-8\"/>\n"
						+ Utilities.spaces(2 * indentation) + "<title>"
						+ title + "</title>\n"
						+ Utilities.spaces(indentation) + "</head>\n"
						+ Utilities.spaces(indentation) + "<body>\n";
		
		// Add elements in body
		for(int i = 0; i < webElements.size(); i++) {
			code += webElements.get(i).genHTML(2*indentation);
			if(i != webElements.size()-1) {
				code += "\n";
			}
		}
		// Add end tags for body and HTML tags
		code += "\n" + Utilities.spaces(indentation) + "</body>\n</html>";
		return code;
	}
	// saves the HTML code into a file
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}
	
	// returns an element based on the corresponding ID
	public Element findElem(int id) {
		Element t = null;
		
		// Loops through elements, checks for ID match
		for(int i = 0; i < webElements.size(); i++) {
			if(webElements.get(i) instanceof TagElement 
					&& id == ((TagElement)webElements.get(i)).getId()) {
				t = webElements.get(i);
			}
		}
		if(t == null) {
			return null;
		}
		return t;
	}
	
	/*
	 * Returns frequencies of list, paragraph, and table in the HTML.
	 * Also returns the percent of table cells utilized.
	 */ 
	public String stats() {
		int listCounter = 0;
		int pCounter = 0;
		int tableCounter = 0;
		double utilTotal = 0;
		String answer = "";
		
		// Loops through elements, checks to update statistics
		for(int i = 0; i < webElements.size(); i++) {
			if(webElements.get(i) instanceof TagElement 
					&& (((TagElement)webElements.get(i)).getEndTag().equals("</ol>"))
					|| ((TagElement)webElements.get(i)).getEndTag().equals("</ul>")) {
				listCounter++;
			}
			if(webElements.get(i) instanceof TagElement 
					&& (((TagElement)webElements.get(i)).getEndTag().equals("</p>"))) {
				pCounter++;
			}
			if(webElements.get(i) instanceof TagElement 
				&& (((TagElement)webElements.get(i)).getEndTag().equals("</table>"))) {
				tableCounter++;
				utilTotal += ((TableElement)webElements.get(i)).getTableUtilization();
			}
		}
		answer += "List Count: " + listCounter + "\n";
		answer += "Paragraph Count: " + pCounter + "\n";
		answer += "Table Count: " + tableCounter + "\n";
		answer += "TableElement Utilization: " + utilTotal/tableCounter*100 + "\n";
		
		return answer;
	}
	
	// Compares two webpages by their title
	public int compareTo(WebPage webPage) {
		return title.compareTo(webPage.title);
	}
	
	// Enables or disables IDs for elements
	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}
}
