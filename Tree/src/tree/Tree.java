package tree;

public class Tree<T> {

	Node<T> root;
	
	public Tree() {
		root = null;
	}
	
	public static void main(String[] args) {
		Tree<String> t = new Tree<String>();
		
		t.root = new Node<String>("P", null);
		t.root.left = new Node<String>("M", t.root);
		t.root.right = new Node<String>("D", t.root);
		t.root.left.left = new Node<String>("F", t.root.left);
		t.root.left.right = new Node<String>("X", t.root.left);
		t.root.right.right = new Node<String>("N", t.root.right);
		t.root.right.right.left = new Node<String>("R", t.root.right.right);
		t.root.right.right.right = new Node<String>("G", t.root.right.right);
		
		System.out.println("This tree has " + t.getNumLeaves() + " leaves.");
		System.out.println("This tree has " + t.getNumNodes() + " nodes.");
		System.out.println("The height of this tree is: " + t.root.getHeight());
	}

	private int getNumLeaves() {
		return root.getNumLeaves();
	}
	
	private int getNumNodes() {
		return root.getNumNodes();
	}

}
