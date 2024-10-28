package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {

	Node<T> root;
	int size;
	
	public Tree() {
		root = null;
		size = 0;
	}
	
	public static void main(String[] args) {
		Tree<String> t = new Tree<String>();

		t.add("C");
		t.add("A");
		t.add("D");
		t.add("B");
		t.add("E");
		
		System.out.println("This tree has " + t.getNumLeaves() + " leaves.");
		System.out.println("This tree has " + t.getNumNodes() + " nodes.");
		System.out.println("The height of this tree is: " + t.root.getHeight());
		t.printTree();
		
		t.preOrderTraversal();
		t.inOrderTraversal();
		t.postOrderTraversal();
		t.breadthFirstTraversal();
		
	}

	private void clear() {
		root = null;
		size = 0;
	}
	
	public boolean contains(T o) {
		if (root == null)
			return false;
		
		Node<T> cursor = root;
		while (true) {
			int c = o.compareTo(cursor.element);
			if (c == 0) 
				return true;
			else if (c < 0) { 
				if (cursor.left != null) {
					cursor = cursor.left;
				} else {
					return false;
				}
			} else { 
				if (cursor.right != null) {
					cursor = cursor.right;
				} else {
					return false;
				}
			}
		}
	}
	
	
	public boolean add(T o) {
		if (root == null) {
			root = new Node<T>(o, null);
			size++;
			return true;
		}
		
		Node<T> cursor = root;
		while (true) {
			int c = o.compareTo(cursor.element);
			if (c == 0) {
				// Item is already in the tree!
				return false;
			} else if (c < 0) {
				// item is less than the cursor
				if (cursor.left != null) {
					cursor = cursor.left;
				} else {
					// add item to cursor.left
					cursor.left = new Node<T>(o, cursor);
					size++;
					return true;
				}
			} else {
				// item is greater than the cursor
				if (cursor.right != null)
					cursor = cursor.right;
				else {
					cursor.right = new Node<T>(o, cursor);
					size++;
					return true;
				}
			}
		}	
	}

	private void breadthFirstTraversal() {
		Queue<Node<T>> q = new LinkedList<Node<T>>();
		q.offer(root);
		
		while (!q.isEmpty()) {
			Node<T> t = q.poll();
			System.out.print(t.element + " ");
			if (t.left != null)
				q.offer(t.left);
			if (t.right != null)
				q.offer(t.right);
		}
	}

	private void preOrderTraversal() {
		root.preOrderTraversal();
		System.out.println();
	}
	
	private void inOrderTraversal() {
		root.inOrderTraversal();
		System.out.println();
	}
	private void postOrderTraversal() {
		root.postOrderTraversal();
		System.out.println();
	}
	
	private int getNumLeaves() {
		return root.getNumLeaves();
	}
	
	private int getNumNodes() {
		return root.getNumNodes();
	}

	public void printTree() {
		root.printTree(root, null, false);
	}
	
}
