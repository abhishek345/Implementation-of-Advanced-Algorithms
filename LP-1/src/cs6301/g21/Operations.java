package cs6301.g21;

public class Operations {

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
    }}
