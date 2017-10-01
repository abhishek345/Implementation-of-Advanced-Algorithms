package cs6301.g21;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * Implement three versions of Select to find the k largest elements
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/09/29
 *
 */
public class Select {
	
	/**
	 * Creates a Priority Queue (Max Heap) and calls remove() k times 
	 * to obtain the k largest elements
	 * 
	 * @param A : integer array containing elements
	 * @param k : number of largest elements to be found
	 * @return : integer array containing k largest elements
	 */
	public static int[] SelectA(int[] A, int k){
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(A.length, new maxComparator());
		
		for(int i:A){
			maxHeap.add(i);
		}
		
		int[] max= new int[k];
		for(int i=0;i<k;i++){
			max[i]=maxHeap.remove();
		}
		return max;
	}
	
	/**
	 * Creates a Priority Queue (Min Heap) to add the first k elements
	 * of an array. The following elements are added to the heap only if
	 * it is greater than the root of the heap. Before adding the element,
	 * the root is removed.
	 * 
	 * @param A : integer array containing elements 
	 * @param k : number of largest elements to be found
	 * @return : integer array containing k largest elements
	 */
	public static int[] SelectB(int[] A, int k){
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		
		for(int i=0;i<k;i++){
			minHeap.add(A[i]);
		}
		
		for(int i=k;i<A.length;i++){
			if (A[i]>minHeap.peek()){
				minHeap.remove();
				minHeap.add(A[i]);
			}
		}
		
		int[] max= new int[k];
		int i=0;
		for(int m:minHeap){
//			System.out.println(m);
			max[i++]=m;
		}
		
		return max;
	}
	
	
	
	/**
	 * Third version of Select algorithm that runs in O(n) time
	 * 
	 * @param A : integer array containing elements
	 * @param k : number of largest elements to be found
	 * @return : integer array containing k largest elements
	 */
	public static int[] SelectC(int[] A, int k){
		int[] max= new int[k];
		int n=A.length;
		if(k<=0)
			return max;
		if(k>=n)
			return A;
		Select(A, 0, n, k);
		int j=0;
		for(int i=n-k;i<n;i++){
			max[j++]=A[i];
//			System.out.println(A[i]);
		}
		return max;
	}
		
	/**
	 * Partitions the array into two parts based on a pivot element.
	 * Elements to the left of pivot are smaller than pivot and those
	 * to the right are larger.
	 * If the pivot is the k element from the
	 * end of the array, then all the elements to the right of pivot and
	 * the pivot are the k largest elements
	 * If the pivot is the > kth  element from the
	 * end of the array, then select the k largest elements on the right
	 * side of pivot 
	 * If the pivot is the smaller than kth element from the
	 * end of the array, then select the k-index(pivot)+1 largest elements on the left
	 * side of pivot 
	 * 
	 * @param A : integer array containing elements
	 * @param p : first index of the array
	 * @param n : number of elements in the array
	 * @param k : number of largest elements to be found
	 * @return : index of pivot
	 */
	public static Integer Select(int[] A, int p, int n, int k){
		int r= p+n-1;
		if(n<17){
			InsertionSort.nSquareSort(A,p,r);
			return A[p+n-k];
		}
		else{
			int q= PartitionVariations.partition2(A,p,r);
			int left= q-p;
			int right= r-q;
			
			if(right>=k)
				return Select(A, q+1, right, k);
			else if(right+1==k)
				return A[q];
			else
				return Select(A, p,left+1, k-(1+right));
		}
	}
	
	/*comparator class used to implement Max Heaps (Priority Queue)*/
	static class maxComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg1-arg0;
		}
		
	}
	

	/**
	 * Main function. Calls each verison of select for different sizes
	 * of arrays randing from 8M to 512M. Time taken for each version is
	 * displayed.
	 * 
	 * @param args : command line arguments
	 * @throws FileNotFoundException : Exception if no file is provided as input or no file found
	 */
	public static void main(String[] args) throws FileNotFoundException{
		String filename=null;
		int k=1000,n = 64000000;
		
		int[] A1= new int[n];
		int[] A2= new int[n];
		int[] A3= new int[n];
		
		Random r= new Random();
		for(int i=0;i<n;i++){
			A1[i]=r.nextInt(n);
		}
		Shuffle.shuffle(A1);
		
		for(int i=0;i<n;i++){
			A2[i]=A1[i];
			A3[i]=A1[i];
		}
//		
//		for(int i=0;i<n;i++){
////			System.out.println(in.nextInt());
//			A1[i]=in.nextInt();
//			A2[i]=A1[i];
//			A3[i]=A1[i];
//		}
			
		System.out.println("Select Algorithm 1");
		Timer t= new Timer();
		int[] max= SelectA(A1,k);
		t.end();
//		for(int m:max)
//			System.out.println(m);
		System.out.println(t.toString()+"\n");
		
		System.out.println("Select Algorithm 2");
		t.start();
		max=SelectB(A2,k);
		t.end();
//		for(int m:max)
//			System.out.println(m);
		System.out.println(t.toString()+"\n");
		
		System.out.println("Select Algorithm 3");
		t.start();
		max=SelectC(A3,k);
		t.end();
//		for(int m:max)
//			System.out.println(m);
		System.out.println(t.toString()+"\n");
		
	}
}
