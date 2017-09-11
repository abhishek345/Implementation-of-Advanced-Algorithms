package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

public class SubFunctions {
	
	
	//Return number to a string in base 10
	public String toString() {
        long result=convertToLong(this);
        if(this.isNegative()){
        	return "-" + String.valueOf(result);
        }
        else{
        	return String.valueOf(result);
        }
        
    }

    
    public static long convertToLong(Num b){
    	Iterator value = b.iterator();
        long result = (long) value.next();
        long first;
        int factor = BASE;
        while(value.hasNext()){
            first = (long) value.next();
            first = first * factor;
            result = result + first;
            factor *= BASE;
        }
        return result;
    }

	

    // taking two Num objects as parameters
     public static Num power(Num a, Num b){

     	long exponent=convertToLong(b);
    	Num result=power(a,exponent);
        return result;
    }
	
}
