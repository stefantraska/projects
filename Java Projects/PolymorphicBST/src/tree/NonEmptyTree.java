package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. 
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	 private K key;
	 private V value;
	 private Tree<K, V> left;
	 private Tree<K, V> right;

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	// Returns the value of a certain key
	public V search(K key) {
		int comparison = this.key.compareTo(key);
		
		if(comparison == 0) {
			return value;
		}else if(comparison > 0) {
			return left.search(key);
		}
		return right.search(key);
	}
	
	// Inserts a key at the appropriate location
	public NonEmptyTree<K, V> insert(K key, V value) {
		int comparison = this.key.compareTo(key);
		
		if(comparison == 0) {
			this.key = key;
			this.value = value;
			return this;
		}else if(comparison > 0) {
			left = left.insert(key, value);
			return this;
		}else {
			right = right.insert(key, value);
			return this;
		}
		
	}
	
	// Removes the specified key and replaces it with the appropriate value
	public Tree<K, V> delete(K key) {
		int comparison = this.key.compareTo(key);
		
		if(comparison == 0) {
			try {
				K leftMaxKey = left.max();
				this.value = left.search(leftMaxKey);
				left = left.delete(leftMaxKey);
				this.key = leftMaxKey;
			}catch(TreeIsEmptyException e) {
				try{
					K rightMinKey = right.min();
					this.value = right.search(rightMinKey);
					right = right.delete(rightMinKey);
					this.key = rightMinKey;
				}catch(TreeIsEmptyException f) {
					return EmptyTree.getInstance();
				}
			}
			return this;
		}else if(comparison > 0) {
			left = left.delete(key);
			return this;
		}else {
			right = right.delete(key);
			return this;
		}
	}
	
	// Returns the maximum key value of the tree
	public K max() {
		try {
			return right.max();
		}catch(TreeIsEmptyException e) {
			return key;
		}
	}

	// Returns the minimum key value of the tree
	public K min() {
		try {
			return left.min();
		}catch(TreeIsEmptyException e) {
			return key;
		}
	}
	
	// Returns the number of keys in the tree
	public int size() {
		return 1 + right.size() + left.size();
	}

	// Adds all keys of the tree to a Collection
	public void addKeysToCollection(Collection<K> c) {
		c.add(key);
		left.addKeysToCollection(c);
		right.addKeysToCollection(c);
	}
	
	// Returns a subtree containing only values from fromKey to toKey
	public Tree<K,V> subTree(K fromKey, K toKey) {
		NonEmptyTree<K,V> tree = new NonEmptyTree<K, V>(key, value, left, right);
		int minComparison = key.compareTo(fromKey);
		int maxComparison = key.compareTo(toKey);
		
		if(maxComparison <= 0 && minComparison >= 0) {
			tree.left = left.subTree(fromKey, toKey);
			tree.right = right.subTree(fromKey, toKey);
			return tree;
		}else if(maxComparison <= 0) {
			return right.subTree(fromKey, toKey);
		}else{
			return left.subTree(fromKey, toKey);
		}
	}
	
	// Returns the height of the tree
	public int height() {
		int leftHeight = 1 + left.height();
		int rightHeight = 1 + right.height();
		
		if(leftHeight > rightHeight) {
			return leftHeight;
		}
		return rightHeight;
	}
	
	// left, root, right traversal
	public void inorderTraversal(TraversalTask<K,V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}
	
	// right, root, left traversal
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {	
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}
	
	// Used for testing purposes
	// In order traversal
	public String toString() {
		return left.toString() + value.toString() + right.toString();
	}
}
