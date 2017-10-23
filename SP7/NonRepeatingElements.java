package cs6301.g21;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Given an array A, return an array B that has those elements of A that
 * occur exactly once, in the same order in which they appear in A
 * @author  Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0:10/15/2017
 * @since 1.0
 */

public class NonRepeatingElements {

	/**
	 * finds out elements that occur only once in input array
	 * @param A : input Array
	 * @return : Array returning elements which are present exactly once
	 */
	public<T extends Comparable<? super T>> Comparable[] exactlyOnce(T[] A){
		
		ArrayList<T> bList = new ArrayList<T>();
		Map<T,Integer> treeMap = new TreeMap<T,Integer>();
		
		for(int i=0; i<A.length; i++){
		    if(treeMap.get(A[i]) != null)
		    	treeMap.put(A[i], treeMap.get(A[i])+1);
		    else
		    	treeMap.put(A[i], 1);
		}
	
		for(int i=0; i<A.length; i++){
			if(treeMap.get(A[i]) == 1)
				bList.add(A[i]);
		}
	
		Comparable[] bArray= new Comparable[bList.size()];
		int count = 0;
	
		for(T element: bList){
			bArray[count]=element;
			count++;
		}
		
		return bArray;	
	}
	
	/**
	 * Main function
	 * @param args : command line input
	 */
	public static void main(String args[]){
		
		NonRepeatingElements nre = new NonRepeatingElements();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n = in.nextInt();
		Integer a[] = new Integer[n];
			
		System.out.println("Enter the values of array");
		for(int i=0 ;i<n; i++){
			a[i] = in.nextInt();
		}
		
		Comparable[] b = nre.exactlyOnce(a);
		System.out.println("Elements Occuring Exactly once are");
		for(Comparable x : b){
			System.out.print(x + " ");
		}
	}
}
