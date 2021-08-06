package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class OrderThread extends Thread{
	
	/*
	 * This class defines a thread for an order. Threads can 
	 * process client orders and generate a String summarizing that order.
	 * Parent of SingleOrder and MultipleOrders.
	 * */
	
	protected String filename;
	protected HashMap<String, Double> itemList;
	protected HashMap<Integer, String> result;
	protected SummaryProcessor summary;
	
	public OrderThread(String filename, HashMap<String, Double> itemList, 
			HashMap<Integer, String> result, SummaryProcessor summary) {
		this.filename = filename;
		this.itemList = itemList;
		this.result = result;
		this.summary = summary;
	}
	
	// Returns a hash map of the items the client bought and the quantity purchased
	public HashMap<String, Integer> processClient(String filename) {
		
		int clientId = 0;
		HashMap<String, Integer> purchaseList = new HashMap<String, Integer>();
		try {
			Scanner itemScanner = new Scanner(new File(filename));
			itemScanner.next();
			clientId = itemScanner.nextInt();
			purchaseList.put("ClientId", clientId);
			while(itemScanner.hasNext()) {
				String purchase = itemScanner.next();
				if(purchaseList.containsKey(purchase)) {
					purchaseList.put(purchase, 1 + purchaseList.get(purchase));
				}else {
					purchaseList.put(purchase, 1);
				}
				itemScanner.next();
			}
			itemScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return purchaseList;
	}
	
	// Returns a String summarizing the client's order
	public String clientOrderDetails(HashMap<String, Integer> purchaseList, 
			HashMap<String, Double> itemList) {
		
		ArrayList<String> itemNames = new ArrayList<>();
		for(String s : itemList.keySet()) {
			itemNames.add(s);
		}
		Collections.sort(itemNames);
		
		String details = "";
		double totalCost = 0;
		
		int clientId = purchaseList.get("ClientId");
		purchaseList.remove("ClientId");
		
		details += "----- Order details for client with Id: " + clientId + " -----\n";
		for(String s : itemNames) {
			if(!purchaseList.containsKey(s)) {
				continue;
			}
			int quantity = purchaseList.get(s);
			double price = itemList.get(s);
			details+= "Item's name: " + s + ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(price) 
					+ ", Quantity: " + quantity
					+ ", Cost: " + NumberFormat.getCurrencyInstance().format((price * quantity)) + "\n";
			totalCost += (price * quantity);
		}
		details += "Order Total: " + NumberFormat.getCurrencyInstance().format(totalCost);
		
		return details + "\n";
	}
}
