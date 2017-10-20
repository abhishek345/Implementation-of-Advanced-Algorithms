package cs6301.g21;

import java.util.Scanner;
import java.util.TreeMap;

public class HowMany {

	static int howMany(int[] A, int X){
		TreeMap<Integer, Integer> tMap = new TreeMap<>();
		int count = 0, i = 0, result = 0, temp, count1, count2;
		
		for(int t : A){
			if(tMap.containsKey(t)){
				i = tMap.get(t);
				tMap.put(t, i+1);
			}
			else
				tMap.put(t, 1);
		}
		
		
		for(int t : tMap.keySet()){
			temp = X - t;	
			result = 0;
			if(tMap.get(t) != null || tMap.get(temp) != 0){
			   count2 = tMap.get(temp);
			   count1 = tMap.get(t);
			   
			   
			   if(temp == t){
				   result = (count2*(count2-1))/2;
			   }
			   else{
				   result = count2*count1;
				   tMap.put(t, 0);
				   tMap.put(temp, 0);
			   }
			}
			count += result;
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
