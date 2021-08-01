package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	
	@Test
	public void testSearch() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		assertTrue(ptree.get(5) == null);
		ptree.put(5, "Five");
		assertEquals(ptree.get(5), "Five");
		ptree.put(1, "One");
		assertEquals(ptree.get(1), "One");
		ptree.put(3, "Three");
		assertEquals(ptree.get(3), "Three");
		ptree.remove(5);
		assertTrue(ptree.get(5) == null);
		ptree.put(10, "Ten");
		assertEquals(ptree.get(10), "Ten");
		ptree.put(6, "Six");
		assertEquals(ptree.get(6), "Six");
		ptree.remove(10);
		assertTrue(ptree.get(10) == null);
		ptree.put(2, "Two");
		assertEquals(ptree.get(2), "Two");
		ptree.remove(6);
		assertTrue(ptree.get(6) == null);
		ptree.put(7, "Seven");
		assertEquals(ptree.get(7), "Seven");
		ptree.put(4, "Four");
		assertEquals(ptree.get(4), "Four");
		ptree.remove(4);
		assertTrue(ptree.get(4) == null);
		assertEquals(ptree.get(7), "Seven");
		ptree.remove(7);
		assertTrue(ptree.get(7) == null);
		assertEquals(ptree.get(1), "One");
		ptree.remove(1);
		assertTrue(ptree.get(1) == null);
		assertEquals(ptree.get(3), "Three");
		ptree.remove(3);
		assertTrue(ptree.get(3) == null);
	}
	
	@Test
	public void testInsert() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(5, "Five");
		assertEquals(ptree.toString(), "Five");
		ptree.put(1, "One");
		assertEquals(ptree.toString(), "OneFive");
		ptree.put(3, "Three");
		assertEquals(ptree.toString(), "OneThreeFive");
		ptree.put(10, "Ten");
		assertEquals(ptree.toString(), "OneThreeFiveTen");
		ptree.put(6, "Six");
		assertEquals(ptree.toString(), "OneThreeFiveSixTen");
		ptree.put(2, "Two");
		assertEquals(ptree.toString(), "OneTwoThreeFiveSixTen");
		ptree.put(7, "Seven");
		assertEquals(ptree.toString(), "OneTwoThreeFiveSixSevenTen");
		ptree.put(4, "Four");
		assertEquals(ptree.toString(), "OneTwoThreeFourFiveSixSevenTen");
	}
	
	@Test
	public void testDelete() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		assertEquals(ptree.toString(), "");
		ptree.put(3, "Three");
		ptree.put(1, "One");
		ptree.put(2, "Two");
		ptree.put(5, "Five");
		ptree.put(4, "Four");
		ptree.put(7, "Seven");
		ptree.put(6, "Six");
		ptree.remove(2);
		assertEquals(ptree.toString(), "OneThreeFourFiveSixSeven");
		ptree.remove(4);
		assertEquals(ptree.toString(), "OneThreeFiveSixSeven");
		ptree.remove(3);
		assertEquals(ptree.toString(), "OneFiveSixSeven");
		ptree.remove(7);
		assertEquals(ptree.toString(), "OneFiveSix");
		ptree.remove(5);
		assertEquals(ptree.toString(), "OneSix");
		ptree.remove(1);
		assertEquals(ptree.toString(), "Six");
		ptree.remove(6);
		assertEquals(ptree.toString(), "");
		assertEquals(0, ptree.size());
	}
	
	@Test
	public void testMin() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		try {
			assertEquals(null, ptree.getMin());
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
		
		ptree.put(10, "Ten");
		assertEquals(ptree.getMin(), new Integer(10));
		ptree.put(8, "Eight");
		assertEquals(ptree.getMin(), new Integer(8));
		ptree.put(9, "Nine");
		assertEquals(ptree.getMin(), new Integer(8));
		ptree.put(7, "Seven");
		assertEquals(ptree.getMin(), new Integer(7));
		ptree.remove(7);
		assertEquals(ptree.getMin(), new Integer(8));
		ptree.put(5, "Five");
		assertEquals(ptree.getMin(), new Integer(5));
		ptree.put(6, "Six");
		assertEquals(ptree.getMin(), new Integer(5));
		ptree.put(4, "Four");
		assertEquals(ptree.getMin(), new Integer(4));
		ptree.remove(4);
		ptree.remove(5);
		assertEquals(ptree.getMin(), new Integer(6));
		ptree.put(3, "Three");
		assertEquals(ptree.getMin(), new Integer(3));
		ptree.put(2, "Two");
		assertEquals(ptree.getMin(), new Integer(2));
		ptree.put(1, "One");
		assertEquals(ptree.getMin(), new Integer(1));
		ptree.remove(1);
		ptree.remove(2);
		assertEquals(ptree.getMin(), new Integer(3));
	}
	
	@Test
	public void testMax() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		try {
			assertEquals(null, ptree.getMax());
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
		
		ptree.put(1, "One");
		assertEquals(ptree.getMax(), new Integer(1));
		ptree.put(8, "Eight");
		assertEquals(ptree.getMax(), new Integer(8));
		ptree.put(9, "Nine");
		assertEquals(ptree.getMax(), new Integer(9));
		ptree.put(7, "Seven");
		assertEquals(ptree.getMax(), new Integer(9));
		ptree.remove(9);
		assertEquals(ptree.getMax(), new Integer(8));
		ptree.put(10, "Ten");
		assertEquals(ptree.getMax(), new Integer(10));
		ptree.put(6, "Six");
		assertEquals(ptree.getMax(), new Integer(10));
		ptree.put(12, "Twelve");
		assertEquals(ptree.getMax(), new Integer(12));
		ptree.remove(12);
		ptree.remove(10);
		assertEquals(ptree.getMax(), new Integer(8));
		ptree.put(11, "Eleven");
		assertEquals(ptree.getMax(), new Integer(11));
		ptree.put(4, "Four");
		assertEquals(ptree.getMax(), new Integer(11));
		ptree.remove(11);
		ptree.remove(8);
		assertEquals(ptree.getMax(), new Integer(7));
	}
	
	@Test
	public void testSubMap() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		PolymorphicBST<Integer,String> pEmpty = ptree.subMap(1, 10);
		assertEquals(pEmpty.toString(), "");
		
		ptree.put(7, "Seven");
		ptree.put(3, "Three");
		ptree.put(1, "One");
		ptree.put(2, "Two");
		ptree.put(5, "Five");
		ptree.put(4, "Four");
		ptree.put(9, "Nine");
		ptree.put(8, "Eight");
		ptree.put(11, "Eleven");
		ptree.put(10, "Ten");
		ptree.put(12, "Twelve");
		
		PolymorphicBST<Integer,String> ptreeTenToTwelve = ptree.subMap(10, 12);
		assertEquals(ptreeTenToTwelve.toString(), "TenElevenTwelve");
		
		PolymorphicBST<Integer,String> ptreeNineToTwelve = ptree.subMap(9, 12);
		assertEquals(ptreeNineToTwelve.toString(), "NineTenElevenTwelve");
		
		PolymorphicBST<Integer,String> ptreeEightToTwelve = ptree.subMap(8, 12);
		assertEquals(ptreeEightToTwelve.toString(), "EightNineTenElevenTwelve");
		
		PolymorphicBST<Integer,String> ptreeSevenToTwelve = ptree.subMap(7, 12);
		assertEquals(ptreeSevenToTwelve.toString(), "SevenEightNineTenElevenTwelve");
		
		PolymorphicBST<Integer,String> ptreeOneToSix = ptree.subMap(1, 6);
		assertEquals(ptreeOneToSix.toString(), "OneTwoThreeFourFive");
		
		PolymorphicBST<Integer,String> ptreeTwoToFive = ptree.subMap(2, 5);
		assertEquals(ptreeTwoToFive.toString(), "TwoThreeFourFive");
		
		PolymorphicBST<Integer,String> ptreeThreeToFive = ptree.subMap(3, 5);
		assertEquals(ptreeThreeToFive.toString(), "ThreeFourFive");
		
		PolymorphicBST<Integer,String> ptreeFourToFive = ptree.subMap(4, 5);
		assertEquals(ptreeFourToFive.toString(), "FourFive");
		
		PolymorphicBST<Integer,String> ptreeFiveToSix = ptree.subMap(5, 6);
		assertEquals(ptreeFiveToSix.toString(), "Five");
		
		PolymorphicBST<Integer,String> ptreeSix = ptree.subMap(6, 6);
		assertEquals(ptreeSix.toString(), "");
		
		PolymorphicBST<Integer,String> ptreeFull = ptree.subMap(1, 12);
		assertEquals(ptreeFull.toString(), "OneTwoThreeFourFiveSevenEightNineTenElevenTwelve");
		
	}
	
	@Test
	public void testHeight() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		assertEquals(ptree.height(), 0);
		ptree.put(7, "Seven");
		assertEquals(ptree.height(), 1);
		ptree.put(3, "Three");
		assertEquals(ptree.height(), 2);
		ptree.put(1, "One");
		assertEquals(ptree.height(), 3);
		ptree.put(5, "Five");
		assertEquals(ptree.height(), 3);
		ptree.put(2, "Two");
		assertEquals(ptree.height(), 4);
		ptree.put(4, "Four");
		assertEquals(ptree.height(), 4);
		ptree.remove(4);
		ptree.remove(2);
		assertEquals(ptree.height(), 3);
		ptree.put(9, "Nine");
		assertEquals(ptree.height(), 3);
		ptree.remove(1);
		ptree.remove(5);
		assertEquals(ptree.height(), 2);
		ptree.put(8, "Eight");
		assertEquals(ptree.height(), 3);
		ptree.put(11, "Eleven");
		assertEquals(ptree.height(), 3);
		ptree.put(10, "Ten");
		assertEquals(ptree.height(), 4);
		ptree.remove(10);
		assertEquals(ptree.height(), 3);
		ptree.remove(11);
		assertEquals(ptree.height(), 3);
		ptree.remove(8);
		assertEquals(ptree.height(), 2);
		ptree.remove(3);
		assertEquals(ptree.height(), 2);
		ptree.remove(9);
		assertEquals(ptree.height(), 1);
		ptree.remove(7);
		assertEquals(ptree.height(), 0);
	}
	
	@Test
	public void testSize() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		assertEquals(ptree.size(), 0);
		ptree.put(7, "Seven");
		assertEquals(ptree.size(), 1);
		ptree.put(3, "Three");
		assertEquals(ptree.size(), 2);
		ptree.put(1, "One");
		assertEquals(ptree.size(), 3);
		ptree.put(5, "Five");
		assertEquals(ptree.size(), 4);
		ptree.put(2, "Two");
		assertEquals(ptree.size(), 5);
		ptree.put(4, "Four");
		assertEquals(ptree.size(), 6);
		ptree.remove(4);
		assertEquals(ptree.size(), 5);
		ptree.remove(2);
		assertEquals(ptree.size(), 4);
		ptree.put(9, "Nine");
		assertEquals(ptree.size(), 5);
		ptree.remove(1);
		ptree.remove(5);
		assertEquals(ptree.size(), 3);
		ptree.put(8, "Eight");
		assertEquals(ptree.size(), 4);
		ptree.put(11, "Eleven");
		assertEquals(ptree.size(), 5);
		ptree.put(10, "Ten");
		assertEquals(ptree.size(), 6);
		ptree.remove(10);
		assertEquals(ptree.size(), 5);
		ptree.remove(11);
		assertEquals(ptree.size(), 4);
		ptree.remove(8);
		assertEquals(ptree.size(), 3);
		ptree.remove(3);
		assertEquals(ptree.size(), 2);
		ptree.remove(9);
		assertEquals(ptree.size(), 1);
		ptree.remove(7);
		assertEquals(ptree.size(), 0);
	}
	
	@Test
	public void testKeySet() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);
			}
		};
		
		Iterator<Integer> emptyIter = ptree.keySet().iterator();
		assertEquals(emptyIter.hasNext(), false);
		
		ptree.put(7, "Seven");
		arrayList.add(7);
		ptree.put(3, "Three");
		arrayList.add(3);
		ptree.put(1, "One");
		arrayList.add(1);
		ptree.put(2, "Two");
		arrayList.add(2);
		ptree.put(5, "Five");
		arrayList.add(5);
		ptree.put(4, "Four");
		arrayList.add(4);
		ptree.put(9, "Nine");
		arrayList.add(9);
		ptree.put(8, "Eight");
		arrayList.add(8);
		ptree.put(11, "Eleven");
		arrayList.add(11);
		ptree.put(10, "Ten");
		arrayList.add(10);
		ptree.put(12, "Twelve");
		arrayList.add(12);
		arrayList.sort(comparator);
		
		Iterator<Integer> iter = ptree.keySet().iterator();
		int index = 0;
		while(iter.hasNext()) {
			Integer i = iter.next();
			assertEquals(i, arrayList.get(index++));
		}
		
	}
	
	@Test
	public void testInOrderTraversal() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		PlaceKeysValuesInArrayLists<Integer, String> p 
			= new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[]");
		assertEquals(p.getValues().toString(), "[]");
		
		ptree.put(7, "Seven");
		ptree.put(3, "Three");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[3, 7]");
		assertEquals(p.getValues().toString(), "[Three, Seven]");
		
		ptree.put(1, "One");
		ptree.put(2, "Two");
		ptree.put(5, "Five");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[1, 2, 3, 5, 7]");
		assertEquals(p.getValues().toString(), "[One, Two, Three, Five, Seven]");
		
		ptree.remove(3);
		ptree.remove(5);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[1, 2, 7]");
		assertEquals(p.getValues().toString(), "[One, Two, Seven]");
		
		ptree.put(4, "Four");
		ptree.put(9, "Nine");
		ptree.put(8, "Eight");
		ptree.put(11, "Eleven");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[1, 2, 4, 7, 8, 9, 11]");
		assertEquals(p.getValues().toString(), "[One, Two, Four, Seven, Eight, Nine, Eleven]");
		ptree.put(10, "Ten");
		ptree.put(12, "Twelve");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[1, 2, 4, 7, 8, 9, 10, 11, 12]");
		assertEquals(p.getValues().toString(), "[One, Two, Four, Seven, Eight, "
				+ "Nine, Ten, Eleven, Twelve]");
		
		ptree.remove(7);
		ptree.remove(8);
		ptree.remove(9);
		ptree.remove(10);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[1, 2, 4, 11, 12]");
		assertEquals(p.getValues().toString(), "[One, Two, Four, "
				+ "Eleven, Twelve]");
		
		ptree.remove(1);
		ptree.remove(2);
		ptree.remove(4);
		ptree.remove(11);
		ptree.remove(12);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(p);
		assertEquals(p.getKeys().toString(), "[]");
		assertEquals(p.getValues().toString(), "[]");
	}
	
	@Test
	public void testRightRootLeftTraversal() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		PlaceKeysValuesInArrayLists<Integer, String> p 
			= new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[]");
		assertEquals(p.getValues().toString(), "[]");
		
		ptree.put(7, "Seven");
		ptree.put(3, "Three");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[7, 3]");
		assertEquals(p.getValues().toString(), "[Seven, Three]");
		
		ptree.put(1, "One");
		ptree.put(2, "Two");
		ptree.put(5, "Five");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[7, 5, 3, 2, 1]");
		assertEquals(p.getValues().toString(), "[Seven, Five, Three, Two, One]");
		
		ptree.remove(3);
		ptree.remove(5);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[7, 2, 1]");
		assertEquals(p.getValues().toString(), "[Seven, Two, One]");
		
		ptree.put(4, "Four");
		ptree.put(9, "Nine");
		ptree.put(8, "Eight");
		ptree.put(11, "Eleven");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[11, 9, 8, 7, 4, 2, 1]");
		assertEquals(p.getValues().toString(), "[Eleven, Nine, Eight, Seven, Four, Two, One]");
		ptree.put(10, "Ten");
		ptree.put(12, "Twelve");
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[12, 11, 10, 9, 8, 7, 4, 2, 1]");
		assertEquals(p.getValues().toString(), "[Twelve, Eleven, Ten, Nine, Eight, Seven, "
				+ "Four, Two, One]");
		
		ptree.remove(7);
		ptree.remove(8);
		ptree.remove(9);
		ptree.remove(10);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[12, 11, 4, 2, 1]");
		assertEquals(p.getValues().toString(), "[Twelve, Eleven, Four, Two, One]");
		
		ptree.remove(1);
		ptree.remove(2);
		ptree.remove(4);
		ptree.remove(11);
		ptree.remove(12);
		
		p = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(p);
		assertEquals(p.getKeys().toString(), "[]");
		assertEquals(p.getValues().toString(), "[]");
	}
	
}