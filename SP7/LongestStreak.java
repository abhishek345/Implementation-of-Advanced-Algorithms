package cs6301.g21;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Implementation of length of the longest streak of consecutive integers
 * @author  Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0:10/15/2017
 * @since 1.0
 */

public class LongestStreak {

	static int LScountCurr = 1;
	static int LScountFinal = 1;
	/**
	 * find out length of the longest streak of consecutive integers
	 * @param A : input array 
	 * @return : length of the longest streak
	 */
	static int longestStreak(int[] A){
			TreeSet<Integer> tSet = new TreeSet<>();
			int prev = 0, curr = 0;
			
			for(int t : A)
				tSet.add(t);
			
			Iterator<Integer> it = tSet.iterator();
			
			if(it.hasNext())
				prev = it.next();
			
			while(it.hasNext()){
				curr = it.next();
				if(prev  == curr -1)
					LScountCurr++;
				else
					LScountCurr = 1;
				prev = curr;
				
				if(LScountCurr > LScountFinal)
					LScountFinal = LScountCurr;
			}
			return LScountFinal;
	}
	
	/**
	 * Main function
	 * @param args : command line input
	 */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n = in.nextInt();
		int a[] = new int[n];
			
		System.out.println("Enter the values of array");
		for(int i=0 ;i<n; i++){
			a[i] = in.nextInt();
		}
		
		System.out.println("Longest Streak in the Array is: " + longestStreak(a));
	}
}
