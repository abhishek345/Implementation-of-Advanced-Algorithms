package cs6301.g21;

import java.util.Scanner;
import java.util.TreeMap;

public class HowMany {

	static int howMany(int[] A, int X){
		TreeMap<Integer, Integer> tMap = new TreeMap<>();
		int count = 0, i = 0;
		
		for(int t : A){
			if(tMap.containsKey(t)){
				i = tMap.get(t);
				tMap.put(t, i+1);
			}
			else
				tMap.put(t, 1);
		}
		
		for(int t : A){
			if(t < X - t)
				count += tMap.get(X-t);
		}
		
		return count;
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n = in.nextInt();
		int a[] = new int[n];
			
		System.out.println("Enter the values of array");
		for(int i=0 ;i<n; i++){
			a[i] = in.nextInt();
		}
		
		System.out.println("Enter the Target Sum: ");
		int sum = in.nextInt();
		System.out.println("Number of pairs that sum to the target are : " + howMany(a,sum));
	}
}
