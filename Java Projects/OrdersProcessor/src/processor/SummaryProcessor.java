package processor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SummaryProcessor {

	/*
	 * This class deals with managing the totals for
	 * a certain data set.
	 * */
	
	private HashMap<String, Integer> allOrders;
	private HashMap<String, Double> itemList;
	private ArrayList<String> items;
	
	public SummaryProcessor(HashMap<String, Double> itemList) {
		allOrders = new HashMap<>();
		this.itemList = itemList;
		items = new ArrayList<String>();
		for(String s : itemList.keySet()) {
			items.add(s);
			allOrders.put(s, 0);
		}
		Collections.sort(items);
	}
	
	// Adds a client's order to the hashmap
	public void addOrder(HashMap<String, Integer> purchaseList) {
		for(String s : items) {
			if(!purchaseList.containsKey(s)) {
				continue;
			}
			synchronized(this) {
				allOrders.put(s, allOrders.get(s) + purchaseList.get(s));
			}
		}
	}
	
	// Returns the String of the totals of items bought
	public String printSummary() {
		String result = "***** Summary of all orders *****\n";
		double grandTotal = 0;
		for(String s : items) {
			if(allOrders.get(s) == 0) {
				continue;
			}
			int quantity = allOrders.get(s);
			double price = itemList.get(s);
			double totalCost = quantity * price;
			result += "Summary - Item's name: " + s + ", "
					+ "Cost per item: " 
					+ NumberFormat.getCurrencyInstance().format(price) + ", "
					+ "Number sold: " + quantity + ", "
					+ "Item's Total: " 
					+ NumberFormat.getCurrencyInstance().format(totalCost) + "\n";
			grandTotal += totalCost;
		}
		result += "Summary Grand Total: " 
				+ NumberFormat.getCurrencyInstance().format(grandTotal);
		return result + "\n";
	}
	
}
