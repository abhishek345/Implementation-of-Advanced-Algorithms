package cs6301.g21;

import java.util.List;
import java.util.Iterator;

public class Union {
	
	public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList){
		
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		
		T temp1 = it1.next();
		T temp2 = it2.next();
		T prev = null;
		
		while(temp1!=null && temp2!=null){
			int comp = temp1.compareTo(temp2);
			
			if(comp<0){
				if(prev != temp1)
					outList.add(temp1);
				prev = temp1;
				if(!it1.hasNext()){
					temp1 = null;
					break;
				}
				temp1=it1.next();
			}
			
			else if(comp>0){
				if(prev != temp2)
					outList.add(temp2);
				prev = temp2;
				if(!it2.hasNext()){
					temp2 = null;
					break;
				}
				temp2=it2.next();
			}
			
			else{
				if(prev != temp1)
					outList.add(temp1);
				prev = temp1;
				if(!it1.hasNext()){
					temp1 = null;
					break;
				}
				if(!it2.hasNext()){
					temp2 = null;
					break;
				}
				temp1=it1.next();
				temp2=it2.next();
			}	
		}
		
		while(temp1 != null){
			if(prev != temp1)
				outList.add(temp1);
			prev = temp1;
			if(!it1.hasNext())
				break;
			temp1=it1.next();
		}
		
		while(temp2 != null){
			if(prev != temp2)
				outList.add(temp2);
			prev = temp2;
			if(!it1.hasNext())
				break;
			temp2=it2.next();
		}
	}
}
