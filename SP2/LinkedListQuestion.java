package cs6301.g21;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Implementation of Intersection, Union, and Set difference operators on List
 * @author Abhishek Jagwani, Umang Shah, Vibha  Belavadi, Shreya Vishwanath Rao
 * @version 1.0 : 09/01/2016
 *
 */

public class LinkedListQuestion {
	
	/**
	 * Function used to read input file provided from command line and create two different lists
	 * @param in : Input file of elements scanned to create lists
	 * @param l1 : ArrayList l1 to be created from input file
	 * @param l2 : ArrayList l2 to be created from input file
	 */
	static void createArrayList(Scanner in, ArrayList<Integer> l1, ArrayList<Integer> l2){
		
		int n = in.nextInt();
			
		for(int i = 0; i < n; i++){
			int temp = in.nextInt();
			l1.add(temp);
		}
		
		int m = in.nextInt();
					
		for(int i = 0; i < m; i++){
			int temp = in.nextInt();
			l2.add(temp);
		}
		
	}
	
	/**
	 * Main function used to scan input file of elements and call function to create lists and perform operations on lists
	 * @param args : command line input
	 * @throws FileNotFoundException : Exception if no file is provided as input or no file found
	 */
	
	public static void main(String[] args) throws FileNotFoundException{
	
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		
		Scanner sf = new Scanner(new File("linkedList.txt"));
		createArrayList(sf,l1,l2);
		ArrayList<Integer> outList1 = new ArrayList<Integer>();
		Intersection.intersect(l1,l2,outList1);
		ArrayList<Integer> outList2 = new ArrayList<Integer>();
		Union.union(l1, l2, outList2);
		ArrayList<Integer> outList3 = new ArrayList<Integer>();
		Difference.difference(l1,l2,outList3);
	}
}
