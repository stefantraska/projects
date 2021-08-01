package processor;

import java.util.*;
import java.io.*;

public class MultipleOrders extends OrderThread{

	/*
	 * This class is a single thread for an entire data set
	 * */
	
	private int ordersToProcess;
	
	public MultipleOrders(String filename, HashMap<String, Double> itemList, 
			HashMap<Integer, String> result, SummaryProcessor summary, 
			int ordersToProcess) {
		
		super(filename, itemList, result, summary);
		this.ordersToProcess = ordersToProcess;
	}
	
	// Loops through orders, gets String for each client, and adds it to hash map
	@Override
	public void run() {
		for(int i = 1; i<= ordersToProcess; i++) {
			int clientId = 1000+i;
			System.out.println("Reading order for client with id: " + clientId);
			String clientFilename = filename + i + ".txt";
			HashMap<String, Integer> purchaseList 
				= processClient(clientFilename);
			String clientDetails 
				= clientOrderDetails(purchaseList, itemList);
			result.put(clientId, clientDetails);
			summary.addOrder(purchaseList);
		}
	}
}