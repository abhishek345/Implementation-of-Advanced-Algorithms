package cs6301.g21;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class LinkedListQuestion {

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
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		
		Scanner sf = new Scanner(new File("linkedList.txt"));
		createArrayList(sf,l1,l2);
	//	System.out.println(l1);
	//	System.out.println(l2);
		ArrayList<Integer> outList1 = new ArrayList<Integer>();
		Intersection.intersect(l1,l2,outList1);
	//	System.out.println(outList1);
		ArrayList<Integer> outList2 = new ArrayList<Integer>();
		Union.union(l1, l2, outList2);
	//	System.out.println(outList2);
		ArrayList<Integer> outList3 = new ArrayList<Integer>();
		Difference.difference(l1,l2,outList3);
	//	System.out.println(outList3);
	}
}
