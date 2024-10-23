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
	
	public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public static void printTree(Node subRoot, Trunk prev, boolean isLeft)
    {
    	// printTree method sourced from https://www.techiedelight.com/c-program-print-binary-tree/
    	
        if (subRoot == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(subRoot.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ",———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + subRoot.element);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(subRoot.left, trunk, false);
    }

}

/* This class is used for printing the tree */
class Trunk
{
    Trunk prev;
    String str;
 
    Trunk(Trunk prev, String str)
    {
        this.prev = prev;
        this.str = str;
    }
};