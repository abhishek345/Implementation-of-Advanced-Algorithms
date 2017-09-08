package cs6301.g21;

import java.util.Iterator;
import java.util.List;

public class Intersection {

	public static<T extends Comparable<? super T>>
    void intersect(List<T> l1, List<T> l2, List<T> outList) {
		
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		
		T temp1 = it1.next();
		T temp2 = it2.next();
		
		while(temp1!=null && temp2!=null){
			int comp = temp1.compareTo(temp2);
			
			if(comp<0){
				if(!it1.hasNext())
					return;
				temp1=it1.next();
			}
			
			else if(comp>0){
				
				if(!it2.hasNext())
					return;
				temp2=it2.next();
			}
			
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
