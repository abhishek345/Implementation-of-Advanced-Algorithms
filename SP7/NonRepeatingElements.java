package cs6301.g21;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class NonRepeatingElements {

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
	
		for(T item: bList){
			bArray[count]=item;
			count++;
		}
		
		return bArray;	
	}
	
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
