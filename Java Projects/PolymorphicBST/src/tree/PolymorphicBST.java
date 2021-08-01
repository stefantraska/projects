package tree;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * This class represents the polymorphic tree. 
 * The implementation uses classes implementing the Tree interface to represent the
 * actual search tree. Some methods of this class has been implemented for you.
 *  
 */
public class PolymorphicBST<K extends Comparable<K>, V>  {
	Tree<K,V> root = EmptyTree.getInstance();

	/**
	 * Find the value the key is mapped to
	 * 
	 * @param k -
	 *            Search key
	 * @return value k is mapped to, or null if there is no mapping for the key
	 */
	public V get(K k) {
		return root.search(k);
	}

	/**
	 * Update the mapping for the key
	 * 
	 * @param k -
	 *            key value
	 * @param v -
	 *            value the key should be bound to
	 */
	public void put(K k, V v) {
		root = root.insert(k, v);
	}

	/**
	 * Return number of keys bound by this map
	 * 
	 * @return number of keys bound by this map
	 */
	public int size() {
		return root.size();
	}

	/**
	 * Remove any existing binding for a key
	 * 
	 * @param k -
	 *            key to be removed from the map
	 */
	public void remove(K k) {
		root = root.delete(k);
	}

	/**
	 * Return a Set of all the keys in the map
	 * 
	 * @return Set of all the keys in the map
	 */

	public Set<K> keySet() {
		Set<K> result = new HashSet<K>();
		root.addKeysToCollection(result);
		return result;
	}

	/**
	 * Return the minimum key value in the map
	 * 
	 * @return the minimum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	 public K getMin() {
		 try {
			 return root.min();
		 }catch(TreeIsEmptyException e) {
			 throw new NoSuchElementException();
		 }
	}

	/**
	 * Return the maximum key value in the map
	 * 
	 * @return the maximum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	public K getMax() throws NoSuchElementException{
		try {
			return root.max();
		}catch(TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Return a string representation of the tree.  You don't need
	 * to implement this method.
	 */
	public String toString() {
		return root.toString();
	}

	/**
	 * Returns a subtree containing the values fromKey-toKey.  It will
	 * include fromKey and toKey if they are found in the original tree.
	 * The values for fromKey and toKey do not actually need to be in the tree.
	 * You can assume than fromKey is less than or equal to toKey.
	 * The new tree does not need to preserve the shape of the original
	 * subtree. Your implementation must be efficient avoiding unnecessary
	 * traversals of the tree.  Also you MAY not add an auxiliary method
	 * to support the implementation of this method.   
	 * 
	 * @return PolymorphicBST consisting of subset of SearchTreeMap
	 */
	public PolymorphicBST<K, V> subMap(K fromKey, K toKey) {
		PolymorphicBST<K, V> bst = new PolymorphicBST<K, V>();
		
		bst.root = root.subTree(fromKey, toKey);
		
		return bst;
	}
	
	/**
	 * Clears the tree by setting the root to EmptyTree
	 */
	public void clear() {
		root = EmptyTree.getInstance();
	}
	
	/**
	 * Returns height of the tree.
	 * @return height of the tree.
	 */
	public int height() {
		return root.height();
	}
	
	/**
	 * Performs an inorder traversal applying the task to eat tree key,value pair.
	 * @param p
	 */
	public void inorderTraversal(TraversalTask<K,V> p) {
		root.inorderTraversal(p);
	}
	
	/**
	 * Performs the specified task on the tree using a right tree, root, left tree
	 * traversal.
	 * @param p object defining task
	 */
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		root.rightRootLeftTraversal(p);
	}
}