package cs6301.g21;

/**
 * Implementation of Intersection operator on List
 * @author Abhishek Jagwani, Umang Shah, Vibha  Belavadi, Shreya Vishwanath Rao
 * @version 1.0 : 09/01/2016
 *
 */

import java.util.Iterator;
import java.util.List;

public class Intersection {
	
	/**
	 * Function used to compute intersection of Lists type T
	 * @param l1 : List l1 of type T passed from Main function
	 * @param l2 : List l1 of type T passed from Main function
	 * @param outList : Output list to store the intersection of 2 input lists
	 */
	public static<T extends Comparable<? super T>>
    void intersect(List<T> l1, List<T> l2, List<T> outList) {
		
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		
		T temp1 = it1.next();
		T temp2 = it2.next();
		
		while(temp1!=null && temp2!=null){
			int comp = temp1.compareTo(temp2);
			
			//if element of l1 is smaller than element of l2
			if(comp<0){
				if(!it1.hasNext())
					return;
				temp1=it1.next();
			}
			
			//if element of l2 is smaller than element of l1
			else if(comp>0){
				
				if(!it2.hasNext())
					return;
				temp2=it2.next();
			}
			
			//if both the elements of l1 and l2 are equal
			else{
				outList.add(temp1);
				if(!it1.hasNext() || !it2.hasNext())
					return;
				temp1 = it1.next();
				temp2 = it2.next();
			}	
			
		}
		
	}
}
