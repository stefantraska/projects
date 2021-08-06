package processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class OrdersProcessor {

	public static void main(String[] args) throws InterruptedException{
		// Get data from user
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter item's data filename: ");
		String itemFilename = sc.next();
		
		System.out.println("Enter 'y' for multiple threads,"
				+ " any other character otherwise: ");
		String multipleThreadsString = sc.next();
		boolean multipleThreads;
		if(multipleThreadsString.equals("y")) {
			multipleThreads = true;
		}else {
			multipleThreads = false;
		}
		
		System.out.println("Enter number of orders to process: ");
		int ordersToProcess = sc.nextInt();
		
		System.out.println("Enter order's base filename: ");
		String baseFilename = sc.next();
		
		System.out.println("Enter result's filename: ");
		String resultFilename = sc.next();
		sc.close();
		
		long startTime = System.currentTimeMillis();
		
		SingleOrder[] orders;
		MultipleOrders multipleOrders;
		HashMap<Integer, String> result = new HashMap<>();
		HashMap<String, Double> itemList = new HashMap<>();
		
		// Create hash map of items and their prices
		Scanner itemScanner;
		try {
			itemScanner = new Scanner(new File(itemFilename));
			while(itemScanner.hasNext()) {
				String item = itemScanner.next();
				double price = itemScanner.nextDouble();
				itemList.put(item, price);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		SummaryProcessor summary = new SummaryProcessor(itemList);
		
		if(multipleThreads) {
			// Thread for each file
			
			Object lock = new Object();
			orders = new SingleOrder[ordersToProcess];
			
			// Create and start all threads
			for(int i = 0; i < ordersToProcess; i++) {
				orders[i] 
					= new SingleOrder(baseFilename, itemList, result, summary, 
							lock, i+1);
			}
			for(SingleOrder s : orders) {
				s.start();
			}
			for(SingleOrder s : orders) {
				s.join();
			}
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(resultFilename));
				for(int j = 1001; j <= 1000+ordersToProcess; j++) {
					writer.write(result.get(j));
				}
				writer.write(summary.printSummary());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));
			System.out.println("Results can be found in the file: " + resultFilename);
			
		}else {
			// Single Thread
			
			multipleOrders = new MultipleOrders(baseFilename, itemList, result, 
					summary, ordersToProcess);
			
			multipleOrders.start();
			multipleOrders.join();
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(resultFilename));
				for(int j = 1001; j <= 1000+ordersToProcess; j++) {
					writer.write(result.get(j));
				}
				writer.write(summary.printSummary());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Processing time (msec): " + (endTime - startTime));
			System.out.println("Results can be found in the file: " + resultFilename);
		}
	}

}
