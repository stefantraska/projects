package performanceReport;

import java.util.Random;
import java.util.TreeMap;

import tree.PolymorphicBST;

/**
 * Provides examples on how to get timing information. 
 * @author cmsc132
 *
 */
public class TreeSpeed {

	public static void main(String[] args) {
		timePolymorphicTree();
		timeTreeMap();
	}
	 
	private static void timePolymorphicTree(){
		Random r;
		long time;
		PolymorphicBST<Integer,Integer> myTree;

		// Build tree with 5000 random numbers between 1 and 500000
		r = new Random(100L); 
		time = System.currentTimeMillis();
		myTree = new PolymorphicBST<Integer,Integer>();
		for (int i = 1; i<5000; i++) {
			int v = r.nextInt(500000); 
			myTree.put(v, i);
		}
		time = System.currentTimeMillis() - time;
		System.out.println("PolymorphicBST Time(msec) = "+time);

		// Build tree with 5000 numbers in sequence
		time = System.currentTimeMillis();
		myTree = new PolymorphicBST<Integer,Integer>();
		for (int i = 1; i<5000; i++) {
			myTree.put(i, i);
		}
		time = System.currentTimeMillis() - time;
		System.out.println("PolymorphicBST Time(msec) = "+time);
	}

	private static void timeTreeMap(){
		Random r;
		long time;
		TreeMap<Integer,Integer> myTree;

		// Build tree with 5000 random numbers between 1 and 500000
		r = new Random(100L); 
		time = System.currentTimeMillis();
		myTree = new TreeMap<Integer,Integer>();
		for (int i = 1; i<5000; i++) {
			int v = r.nextInt(500000); 
			myTree.put(v, i);
		}
		time = System.currentTimeMillis() - time;
		System.out.println("TreeMap Time(msec) = "+time);

		// Build tree with 5000 numbers in sequence
		time = System.currentTimeMillis();
		myTree = new TreeMap<Integer,Integer>();
		for (int i = 1; i<5000; i++) {
			myTree.put(i, i);
		}
		time = System.currentTimeMillis() - time;
		System.out.println("TreeMap Time(msec) = "+time);
	}
}
