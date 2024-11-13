import java.util.ArrayList;
import java.util.Random;

public class Heap {
	private ArrayList<Integer> heap;
	
	public Heap() {
		heap = new ArrayList<Integer>();
	}
	
	public void swap(int a, int b) {
		int temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
	
	public static int parent(int i) {
		if (i < 0) throw new java.lang.UnsupportedOperationException();
		// return (i-1) / 2;
		return (i-1) >> 1;
	}
	
	public static int leftChild(int i) {
		return 2 * i + 1;
		//return i << 1 + 1;
	}
	
	public static int rightChild(int i) {
		return 2 * i + 2;
		//return i << 1 + 2;
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public void add(int x) {
		heap.add(x);
		percolateUp(heap.size() -1);
	}
	
	public void percolateUp(int i) {
		while (i != 0 && heap.get(i) < heap.get(parent(i))) {
			swap(i, parent(i));
			i = parent(i);
		}
	}
	
	public void percolateDown(int i) {
		if (leftChild(i) >= heap.size()) {
			return;
		} 
		
		if (rightChild(i) >= heap.size()) {
			if (heap.get(i) > heap.get(leftChild(i))) {
				swap(i, leftChild(i));
			}
			return;
		}
		
		if (heap.get(leftChild(i)) < heap.get(rightChild(i))) {
			if (heap.get(i) > heap.get(leftChild(i))) {
				swap(i, leftChild(i));
				percolateDown(leftChild(i));
			}
		} else {
			if (heap.get(i) > heap.get(rightChild(i))) {
				swap(i, rightChild(i));
				percolateDown(rightChild(i));
			}
		}
		
	}
	
	public int remove() {
		int temp = heap.get(0);
		
		swap(0, heap.size() -1);
		heap.remove(heap.size()-1);
		percolateDown(0);
		
		return temp;
	}
	
	public static void main(String[] args) {
		Heap a = new Heap();
		Random rng = new Random();
		
		for (int i = 0; i < 25; i++) {
			int x = rng.nextInt(100);
			a.add(x);
			System.out.print(x + " ");
		}
		System.out.println();
		
		System.out.println(a.heap);
		while (!a.isEmpty()) {
			System.out.print(a.remove() + " ");
		}
		System.out.println();
	}
	
	
}
