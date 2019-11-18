package unam.ciencias.computoconcurrente;

import java.util.Arrays;

public class Sorting {
  
  

    public static void parallelMergeSort(int[] a) {
		int cores = Runtime.getRuntime().availableProcessors();
		parallelMergeSort(a, cores);
    }
    
    public synchronized static Runnable parallelMergeSort(int[] a, int threadCount) {
		if (threadCount <= 1) {
			mergeSort(a);
		} else if (a.length >= 2) {

			int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
            Thread lThread = new Thread( parallelMergeSort(left,  threadCount / 2));
			Thread rThread = new Thread( parallelMergeSort(right, threadCount / 2));
			lThread.start();
			rThread.start();
			
			try {
				lThread.join();
				rThread.join();
			} catch (InterruptedException ie) {}
			

	
	
			merge(left, right, a);
		}
        return null;
    }

	public static void mergeSort(int[] a) {
		if (a.length >= 2) {
		
			int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			
			mergeSort(left);
			mergeSort(right);
			
			merge(left, right, a);
		}
	}
	

	public static void merge(int[] left, int[] right, int[] a) {
		int i1 = 0;
		int i2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
				a[i] = left[i1];
				i1++;
			} else {
				a[i] = right[i2];
				i2++;
			}
		}
	}
   
	public static final void swap(int[] a, int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	

	public static void shuffle(int[] a) {
		for (int i = 0; i < a.length; i++) {
			
			int randomIndex = (int) (Math.random() * a.length - i);
			swap(a, i, i + randomIndex);
		}
	}
	
	// Returns true if the given array is in sorted ascending order.
	public static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	



}
