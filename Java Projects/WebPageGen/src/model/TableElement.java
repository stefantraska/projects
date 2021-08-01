package model;

public class TableElement extends TagElement{
	private Element[][] table;
	
	
	public TableElement(int rows, int cols, String attributes) {
		//fix null
		super("table", true, new TextElement(""), attributes);
		table = new Element[rows][cols];
	}
	
	// Return string with HTML for table and the cells enclosed
	public String genHTML(int indentation) {
		String code = "";
		
		code += Utilities.spaces(indentation);
		
		code += getStartTag() + "\n";
		
		// Loop through table array and print elements in table format
		for(int r = 0; r < table.length; r++) {
			code += Utilities.spaces(indentation) + "<tr>";
			for(int c = 0; c < table[0].length; c++) {
				if(table[r][c] != null) {
					code += "<td>" + table[r][c].genHTML(0) + "</td>";
				}else{
					code += "<td></td>";
				}
			}
			code += "</tr>\n";
		}
		
		return code + Utilities.spaces(indentation) + getEndTag();
	}
	
	//Adds an element to the paragraph.
	public void addItem(int rowIndex, int colIndex, Element item) {
		table[rowIndex][colIndex] = item;
	}
	
	// Calculate the percent of table cells utilized
	public double getTableUtilization() {
		double tablesUtilized = 0;
		for(int r = 0; r < table.length; r++) {
			for(int c = 0; c < table[0].length; c++) {
				if(table[r][c] != null) {
					tablesUtilized++;
				}
			}
		}
		return (tablesUtilized) / (table.length*table[0].length);
	}
	
}
