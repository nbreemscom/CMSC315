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
		System.out.println("Bubble sort");
		selectionSort(y);
		printMyArray(y);

	}

}
