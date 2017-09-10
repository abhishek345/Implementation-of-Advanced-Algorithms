package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Num {

    private LinkedList<Long> digits;
    private static int BASE = 10;
    private boolean sign = false;//false (not set) +ve number; if true (set) -ve num
    private static boolean flag =  false;

    public Num(String num){
        digits = new LinkedList<>();
        if(num.charAt(0) == '-'){
            sign = true;
            num = num.substring(1);
        }
        int decimalPoint = num.indexOf(".");
        //If a decimal point is present, truncate at the decimal point
        if(decimalPoint > -1){
            num = num.substring(0,decimalPoint);
        }
        for(int i = 0;i < num.length(); i++){
            Character c = num.charAt(i);
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("String should only contain numbers");
            }
            else{
                digits.addFirst(Long.parseLong(c.toString()));
            }
        }
    }

    public Num(long num){
        digits = new LinkedList<>();
        long digit;
        if(num==0)
            digits.add(num);
        else {
            if(num < 0){
                sign = true;
                num = -1*num;
            }
            while (num != 0) {
                digit = num % BASE;
                digits.addLast(digit);
                num = num / BASE;
            }
        }
    }

    public Num(){
        digits = new LinkedList<>();
    }

    public boolean isNegative() { return sign; }

    public void setNegative() { sign = true; }
    
    public void setPositive() { sign = false;}

    public Iterator iterator(){
        return digits.iterator();
    }

    public long get(int i){
        return digits.get(i);
    }

    public void addFirst(long value){
        digits.addFirst(value);
    }

    public void addLast(long value){
        digits.addLast(value);
    }

    public void printList(){
        System.out.print(BASE + " : ");
        Iterator it = this.iterator();
        while(it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
    }

    public String toString() {
        Iterator value = this.iterator();
        long result = (long) value.next();
        long first;
        int factor = BASE;
        while(value.hasNext()){
            first = (long) value.next();
            first = first * factor;
            result = result + first;
            factor *= BASE;
        }
        return String.valueOf(result);
    }

//    private class Result{
//        int value;
//        int carry;
//
//        public Result(int value, int carry){
//
//        }
//    }
//
//    private static Result getResult(int op1, int op2, int opr){
//        Result r;
//
//    }

    public int size(){
        return digits.size();
    }

//    public static Num karatsubaMul(Num a, Num b){
//        //Needs split and subtract function
//        int len;
//        if(a.size() < b.size())
//            len = a.size();
//        else
//            len = b.size();
//        if(len == 0)
//            return new Num(0);
//        if(len == 1)
//            return simpleMul(a,b);
//
//        int half = len/2; //length of the half of the numbers
//
//        Num[] aSplit, bSplit;
//        aSplit = a.split(a,half);
//        bSplit = b.split(b,half);
//        //aSplit[0] - L ; [1] - R
//
//        Num mulLeft = simpleMul(aSplit[0], bSplit[0]);
//        Num mulRight = simpleMul(aSplit[1], bSplit[1]);
//        Num mulMid = simpleMul(add(aSplit[0],aSplit[1]), add(bSplit[0],bSplit[1]));
//
//        mulMid = sub(mulMid, mulLeft);
//        mulMid = sub(mulMid, mulRight);
//        mulMid = simpleMul(mulMid, power(new Num(BASE),len/2));
//        Num ans = simpleMul(mulLeft, power(new Num(BASE), len));
//        ans = add(ans, mulMid);
//        ans = add(ans, mulRight);
//
//        return ans;
//    }

    public static Num simpleMul(Num a, Num b){
        //need to add -ve no consideration
        long value = 0;
        long carry = 0;
        int secondSize = 0;
        Iterator outer;
        Num c;
        Iterator inner;
        if(a.size() < b.size()){
             outer = a.iterator();
             c = b;
             secondSize = a.size();
        }
        else{
            outer = b.iterator();
            c = a;
            secondSize = b.size();
        }

        Num[] tempArr = new Num[secondSize];
        int k = 0;
        while(outer.hasNext()){
            long number = (long) outer.next();
            inner = c.iterator();
            tempArr[k] = new Num();
            for(int i=0;i < k;i++)
                tempArr[k].addLast(0);
            while(inner.hasNext()){
                value = number * (long) inner.next() + carry;
                carry = value / BASE;
                tempArr[k].addLast(value%BASE);
            }
            if(carry != 0) tempArr[k].addLast(carry);
            tempArr[k++].printList();
            carry = 0;
        }
        if(secondSize == 1)
            return tempArr[0];
        else {
            Num holder = Num.add(tempArr[0], tempArr[1]);
            if(secondSize == 2)
                return holder;
            else{
                int idx = 2;
                while(idx < secondSize){
                    holder = Num.add(holder,tempArr[idx++]);
                }
            }
            return holder;
        }

    }

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

    
    
 //Get the square root of Num
public static Num squareRoot(Num a){

    Num left = new Num(0);
    Num right = a;

    while(isGreater(right, left) > 0){

        Num middle = getMid(right, left);
        Num square = power(middle, 2L);
        if(isGreater(square, a) > 0)
            right = middle;
        else if(isGreater(square, a) < 0)
            left = middle;
        else
            return middle;
    }

    return left;
}

/*public static Num pow(Num a, long b){

    return new Num(0);
}*/

//Compares two numbers and sees which one is greater
public static int isGreater(Num a, Num b){

    if(a.digits.size()>b.digits.size())
        return 1;
    else if(a.digits.size() == b.digits.size()){
        if(a.digits.getLast() > b.digits.getLast())
            return 1;
        else if(a.digits.getLast() < b.digits.getLast()){
            return -1;
        }else{
            for(int i=0; i<a.digits.size(); i++){
                if(a.digits.get(i) > b.digits.get(i))
                    return 1;
                else if(a.digits.get(i) < b.digits.get(i))
                    return -1;
                else
                    return 0;
            }
        }
    }

    return -1;
}

//Get the average of two Num
public static Num getMid(Num a, Num b){

    return divide(add(a,b), new Num(2));
}

public static Num add(Num a ,Num b){
	Num temp = new Num();
	Num tempA = a;
	Num tempB = b;
	Num tempResult;
	LinkedList<Long> result = new LinkedList<Long>();
	String outStr = "";

   	Num a1 = compare(a,b);

	if(a.isNegative()!=b.isNegative()){
		if(a1.isNegative())
			flag = true;
		
		tempA.setPositive();
		tempB.setPositive();
		tempResult = subtract(tempA,tempB);
		if(a1.equals(a))
			if(a1.isNegative())
				tempA.setNegative();
			else
				tempB.setNegative();
		else
			if(a1.isNegative())
				tempA.setNegative();
			else
				tempB.setNegative();
		return tempResult;
	}
	else if(a.isNegative()==false && b.isNegative()==false)
		temp.setPositive();
	else
		temp.setNegative();
	
	Iterator<Long> it1;
	Iterator<Long> it2;
		
	if(a1.equals(a)){
		it1 = a.digits.iterator();
		it2 = b.digits.iterator();
	}
	else{
		it1 = b.digits.iterator();
		it2 = a.digits.iterator();    		
	}
	
	long carry = 0;
	Long temp1 = it1.next();
	Long temp2 = it2.next();
			
	while(temp1!=null && temp2!=null){
		Long sum = temp1 + temp2 + carry;
		result.addLast(sum%10);
		carry = sum/10;
		
		try{
			temp1 = it1.next();
		}
		catch(NoSuchElementException e){
			temp1 = null;
		}
			
		try{
			temp2 = it2.next();
		}
		catch(NoSuchElementException e){
			temp2 = null;
		}
	}
	
	while(temp1!=null){
		Long sum = temp1 + carry;
		result.addLast(sum%10);
		carry = sum/10;
	
		try{
			temp1 = it1.next();
		}
		catch(NoSuchElementException e){
			temp1 = null;
		}
	}
	
	while(temp2!=null){
		Long sum = temp2 + carry;
		result.addLast(sum%10);
		carry = sum/10;
	
		try{
			temp2 = it2.next();
		}
		catch(NoSuchElementException e){
			temp2 = null;
		}
	}
	
	if(carry>0)
		result.addLast(carry);
		

	
	for(int i = result.size()-1;i>=0;i--)
		outStr += result.get(i);
	
	System.out.println(outStr);
	if(flag==true){
		temp.setPositive();
		System.out.println(temp.sign);
		flag = false;
	}
	else
		System.out.println(temp.sign);
	temp.setPositive();
	return new Num(outStr);
}

public static Num subtract(Num a, Num b){
	
	Num temp = new Num();
	Num tempA = a;
	Num tempB = b;
	Num tempResult;
	Num a1 = compare(a,b);
	if(a.isNegative()!=b.isNegative()){
		if(a1.isNegative())
			flag = true;
		tempA.setPositive();
		tempB.setPositive();
		tempResult =  add(tempA,tempB);
		if(flag == true)
			a1.setNegative();
		if(a1.equals(a))
			if(a1.isNegative())
				tempA.setNegative();
			else
				tempB.setNegative();
		else
			if(a1.isNegative())
				tempA.setNegative();
			else
				tempB.setNegative();
		return tempResult;
	}
	
   	
	Iterator<Long> it1;
	Iterator<Long> it2;
	
if(a1.equals(a)){
	it1 = a.digits.iterator();
	it2 = b.digits.iterator();
	if(a.isNegative()==false)
		temp.setPositive();
	else
		temp.setNegative();
}
else{
	it1 = b.digits.iterator();
	it2 = a.digits.iterator();
	if(b.isNegative()==false)
		temp.setPositive();
	else
		temp.setNegative();
}

	boolean carry = false;
	Long temp1 = it1.next();
	Long temp2 = it2.next();
	
	LinkedList<Long> result = new LinkedList<Long>();
	while(temp1!=null && temp2!=null){
		if(carry){
			if(temp1 == 0)
				temp1 = (long)9;
			else{
				temp1 -= 1;
				carry = false;
			}
		}
		if(temp1<temp2){
			temp1 += 10;
			carry = true;
		}
		long difference = temp1 - temp2;
		result.addLast(difference%10);
		
		try{
			temp1 = it1.next();
		}
		catch(NoSuchElementException e){
			temp1 = null;
		}
		
		try{
			temp2 = it2.next();
		}
		catch(NoSuchElementException e){
			temp2 = null;
		}
	}
	
	
	while(temp1 != null){
		if(carry){
			if(temp1 == 0)
				temp1 = (long)9;
			else{
				temp1 -= 1;
				carry = false;
			}
		}
		result.addLast(temp1);
		try{
			temp1 = it1.next();
		}
		catch(NoSuchElementException e){
			temp1 = null;
		}
	}
	
	String outStr = "";
	
	for(int i=result.size()-1;i>=0;i--)
		outStr += result.get(i);
	
	System.out.println(outStr);
	if(flag == true){
		temp.setNegative();
		System.out.println(temp.sign);
		flag = false;
	}
	else
		System.out.println(temp.sign);
	temp.setPositive();
	return new Num(outStr);
	
}

public static Num compare(Num a ,Num b){
	
	Num temp = new Num();
	
	if(a.size()>b.size())
		temp = a;
	else if(b.size()>a.size()){
		temp = b;
	}
	else{
		for(int i=a.size()-1;i>=0;i--){
			if(a.get(i)>b.get(i))
				temp = a;
			else
				temp = b;					
		}
	}
	return temp;
}


    public static void main(String args[]){
        Num n = new Num("-319.90");
        n.printList();
        Num n2 = new Num(5074);
        Num.simpleMul(n,n2);
        System.out.println(n);
        System.out.println(n.isNegative());
    }

}
