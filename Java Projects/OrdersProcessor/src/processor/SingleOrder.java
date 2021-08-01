package processor;

import java.util.*;
import java.io.*;

public class SingleOrder extends OrderThread{
	
	/*
	 * This class is a thread for one order
	 * */
	
	private Object lock;
	private int clientId;
	
	public SingleOrder(String filename, HashMap<String, Double> itemList, 
			HashMap<Integer, String> result, SummaryProcessor summary, 
			Object lock, int clientNumber) {
		
		super(filename + clientNumber + ".txt", itemList, result, summary);
		this.lock = lock;
		this.clientId = clientNumber+1000;
	}
	
	// Gets String for the client and adds it to hash map
	@Override
	public void run() {
		System.out.println("Reading order for client with id: " + clientId);
		HashMap<String, Integer> purchaseList 
			= processClient(filename);
		String clientDetails 
			= clientOrderDetails(purchaseList, itemList);
		synchronized(lock) {
			result.put(clientId, clientDetails);
		}
		summary.addOrder(purchaseList);
	}

}
