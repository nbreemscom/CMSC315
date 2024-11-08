package sorting;

import java.util.Random;

public class Sorting {

	public static void printMyArray(int[] x) {
		for (int i=0; i < x.length; i++) System.out.print(x[i] + " ");
		System.out.println();
	}
	
	public static void swap(int x[], int a, int b) {
		int temp = x[a];
		x[a] = x[b];
		x[b] = temp;
	}
	
	public static void insertionSort(int[] x) {
		if (x.length < 2) return;
		
		for (int i = 1; i < x.length; i++) {
			for (int j = i; (j > 0 && x[j] < x[j- 1]); j--) {
				swap(x, j, j-1);
			}
		}
	}
	
	public static void bubbleSort(int[] x) {
		for (int j = 1; j < x.length; j++) {
			for (int i = 0; i < x.length - j;  i++) {
				if (x[i] > x[i+1]) {
					swap(x, i, i+1);
				}
			}
		}
	}
	
	public static int findIndexOfSmallest(int x[], int startingIndex) {
		int smallestIndex = startingIndex;
		for (int p = startingIndex + 1; p < x.length; p++) {
			if (x[p] < x[smallestIndex]) {
				smallestIndex = p;
			}
		}
		return smallestIndex;
	}
	
	public static void selectionSort(int[] x) { 
		for (int i = 0; i < x.length -1; i++) {
			int j = findIndexOfSmallest(x, i);
			swap(x, i, j);
		}
	}
	
	public static void mergeSort(int[] x) {
		int y[] = new int[x.length];
		System.arraycopy(x, 0, y, 0, x.length);
		
		mergeSort(y, x, 0, x.length);
	}
	
	public static void mergeSort(int[] src, int[] dest, int low, int high) {
		int length = high - low;
		
		if (length < 7) {
			for (int i = low; i < high; i++) {
				for (int j = i; j > low && dest[j-1] > dest[j]; j--) {
					swap(dest, j, j-1);
				}
			}
			return;
		}
		int mid = (low + high) >> 1; // Bitshift right by one, to divide by two fast!
		mergeSort(dest, src, low, mid);
		mergeSort(dest, src, mid, high);
		
		// Do the merge step
		for (int i = low, p = low, q = mid; i < high; i++) {
			if (q >= high ||  p < mid && src[p] <= src[q]) {
				dest[i] = src[p++];
			} else {
				dest[i] = src[q];
				q++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		int x[] = new int[30];
		int y[] = new int[30];
		Random rng = new Random();
		
		for (int i = 0; i < x.length; i++) {
			x[i]= rng.nextInt(100);
		}
		
		printMyArray(x);
		System.arraycopy(x, 0, y, 0, x.length);
		System.out.println("Insertion Sort:");
		insertionSort(y);
		printMyArray(y);
		
		System.arraycopy(x, 0, y, 0, x.length);
		System.out.println("Bubble sort");
		bubbleSort(y);
		printMyArray(y);

		System.arraycopy(x, 0, y, 0, x.length);
		System.out.println("Selection sort");
		selectionSort(y);
		printMyArray(y);

		System.arraycopy(x, 0, y, 0, x.length);
		System.out.println("Merge sort");
		mergeSort(y);
		printMyArray(y);

	}

}
