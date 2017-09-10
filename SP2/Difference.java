package cs6301.g21;

/**
 * Implementation of Difference operator on List
 * @author Abhishek Jagwani, Umang Shah, Vibha  Belavadi, Shreya Vishwanath Rao
 * @version 1.0 : 09/01/2016
 *
 */

import java.util.List;
import java.util.Iterator;

public class Difference {

	/**
	 * Function used to compute Difference of Lists type T
	 * @param l1 : List l1 of type T passed from Main function
	 * @param l2 : List l1 of type T passed from Main function
	 * @param outList : Output list to store the intersection of 2 input lists
	 */	
	
	public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList){
		
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		
		T temp1 = it1.next();
		T temp2 = it2.next();
		T prev = null;
		
		while(temp1!=null && temp2!=null){
			int comp = temp1.compareTo(temp2);
			
			//if element of l1 is smaller than element of l2
			if(comp<0){
				if(prev != temp1)
					outList.add(temp1);
				prev = temp1;
				if(!it1.hasNext())
					break;
				temp1=it1.next();
			}
			
			//if element of l2 is smaller than element of l1
			else if(comp>0){
				if(!it2.hasNext())
					break;
				temp2=it2.next();
			}
			
			//if both the elements of l1 and l2 are equal
			else{
				if(!it1.hasNext() || !it2.hasNext())
					break;
				temp1 = it1.next();
				temp2 = it2.next();
			}	
		}
		temp1 = it1.next();
		
		//if l1 is not empty yet
		while(temp1 != null){			
			if(prev != temp1)
				outList.add(temp1);
			prev = temp1;
			if(!it1.hasNext())
				break;
			temp1=it1.next();
		}
	}
}
