package tree;

public class Node<T> {

	T element;
	Node<T> parent;
	Node<T> left;
	Node<T> right;
	
	public Node(T x, Node<T> p) {
		element = x;
		parent = p;
		left = null;
		right = null;
	}

	public int getNumLeaves() {
		if (left == null && right == null)
			return 1;

		return (left == null ? 0 : left.getNumLeaves()) +
				(right == null ? 0 : right.getNumLeaves()) ;
	}
	
	public int getNumNodes() {
		if (left == null && right == null)
			return 1;

		return 1 + (left == null ? 0 : left.getNumNodes()) +
				(right == null ? 0 : right.getNumNodes()) ;

	}
	
	public int getHeight() {
		if (left == null && right == null)
			return 0;
		
		return 1 + Math.max(
				left == null ? -1 : left.getHeight(),
				right == null ? -1 : right.getHeight()
				);
	}
}
