package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

public class SubFunctions {
	
	
	//Return number to a string in base 10
	public String toString() {
    	
		Iterator value = this.iterator();
		StringBuilder result = new StringBuilder(String.valueOf(value.next()));
		long first;
		while(value.hasNext()){
		    first = (long) value.next();
		    result.reverse();
		    result.append(String.valueOf(first));
		    result.reverse();
		}
		if(this.isNegative()){
			result.reverse();
		    result.append("-");
		    result.reverse();
			return result.toString();
		}
		else{
			return result.toString();
		}
	 }

   static Num power(Num a, long n) {
//    	if(a==null)
//    		return null;
        Num result;
        if(n==0)
            return new Num(1);
        if(n==1)
            return a;

        Num halfVal=power(a,n/2);
        if((n%2)==0){
            result= simpleMul(halfVal,halfVal);
        }
        else{
            Num subResult = simpleMul(halfVal, halfVal);
            result = simpleMul(subResult,a);
        }
        return result;
    }
	

    // taking two Num objects as parameters
      public static Num power(Num a, Num b){
//    	if(a==null || b==null)
//    		return  null;
    	Iterator value= b.iterator();
    	Num result= new Num(1);
    	if(value.hasNext()){
    		result= power(a,(long)value.next());
    	}
    	int factor = BASE;
    	while(value.hasNext()){
    		Num subResult1= power(a, (long)value.next());
    		Num subResult2= power(subResult1, factor);
    		result= simpleMul(result,subResult2);
    	}
    	return result;
    }	
}
