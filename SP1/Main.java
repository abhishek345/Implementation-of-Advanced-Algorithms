package cs6301.g21;

import java.util.Random;

/**
 * Implementation of Merge Sort on integer and generic arrays
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */


public class Main {

	/**
	 * Main function that calls the Merge sort function and a n Square Sort function for integer and generic data type arrays.
	 * Array sizes vary from 1 Million to 16 Million elements.
	 * The time taken by each function to sort the arrays is measured.
	 * 
	 * @param args : String : command line input
	 */
	public static void main(String[] args){
		
		int n=1000000;
		Random r = new Random();
		
		Timer t= new Timer();

		/** 
		 * Running following loop for 1 Million to 16 Million elements
		 */
		for(int i=1;i<=16;i++){
			int size=n*i;
			
			/* Creation of arrays of type int and Integer*/
			int[] mergeArr= new int[size];
			int[] mergeTemp= new int[size];
			
			int[] nSqArr= new int[size];
			
			Integer[] iMergeArr= new Integer[size];
			Integer[] iMergeTemp= new Integer[size];
			
			Integer[] iNSqArr= new Integer[size];

			System.out.println("For Size: " + size);
			
			/* Assigning "size" random elements to integer array*/
			for(int j=0;j<size;j++){
				mergeArr[j]=r.nextInt(size);
			}
			
			Shuffle.shuffle(mergeArr);
			
			/*Assigning the same set of elements to another integer array*/
			for(int j=0;j<size;j++){
				nSqArr[j]=mergeArr[j];
			}
			
			/*Assigning the same set of elements to an object array of type integer */
			for(int j=0;j<size;j++){
				iMergeArr[j]=new Integer(mergeArr[j]);
				iNSqArr[j]=new Integer(mergeArr[j]);
			}
			
			System.out.println("Merge Sort on integer data");
			t.start();
			MergeSort.mergeSort(mergeArr,mergeTemp);
			System.out.println(t.end());
			System.out.println();
			System.out.println();
			
			System.out.println("Merge Sort on generic data");
			t.start();
			MergeSort.mergeSort(iMergeArr,iMergeTemp);
			System.out.println(t.end());
			System.out.println();
			System.out.println();
			
			
			System.out.println("N Square Sort on integer data");
			t.start();
			InsertionSort.nSquareSort(nSqArr);
			System.out.println(t.end());
			System.out.println();
			System.out.println();
			
			System.out.println("N Square Sort on generic data");
			t.start();
			InsertionSort.nSquareSort(iNSqArr);
			System.out.println(t.end());
			System.out.println();
			System.out.println();

			
		}
	}
		
}
