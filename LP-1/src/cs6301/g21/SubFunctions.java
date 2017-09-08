package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

public class SubFunctions {
	
	//Num constructor
	public Num(long num){
        digits = new LinkedList<>();
        long digit;
        if(num==0)			// modification made from here (just included the if statement)
        	digits.add(num);	
        else{
	        while(num != 0){
	            digit = num%10;
	            digits.addLast(digit);
	            num = num/10;
	        }
        }
    }
	
	//Return number to a string in base 10
	public String toString() {
    	Iterator value= iterator();
    	long result=(long) value.next();
    	long first;
    	int factor=10;
    	while(value.hasNext()){
    		first= (long) value.next();
    		first=first * factor;
    		result = result + first;
    		factor*=10;
    	}
	return String.valueOf(result);
    }

	

	 // Use divide and conquer
    static Num power(Num a, long n) {
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
    
    
    
}
