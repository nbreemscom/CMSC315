package tree;

public class Tree<T> {

	Node<T> root;
	
	public Tree() {
		root = null;
	}
	
	public static void main(String[] args) {
		Tree<String> t = new Tree<String>();
		
		t.root = new Node<String>("A", null);
		t.root.left = new Node<String>("B", t.root);
		t.root.left.right = new Node<String>("D", t.root.left);
		t.root.right = new Node<String>("C", t.root);
		t.root.right.left = new Node<String>("E", t.root.right);
		t.root.right.left.left = new Node<String>("G", t.root.right.left);
		t.root.right.right = new Node<String>("F", t.root.right);
		t.root.right.right.left = new Node<String>("H", t.root.right.right);
		t.root.right.right.right = new Node<String>("I", t.root.right.right);
		
		System.out.println("This tree has " + t.getNumLeaves() + " leaves.");
		System.out.println("This tree has " + t.getNumNodes() + " nodes.");
		System.out.println("The height of this tree is: " + t.root.getHeight());
		t.printTree();
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
