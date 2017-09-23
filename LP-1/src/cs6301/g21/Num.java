package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Num {

    private LinkedList<Long> digits;
    private static long BASE = 1073741824;//2^30
    private boolean sign = false;//false (not set) +ve number; if true (set) -ve num

    static Num zero = new Num(0);
    static Num one = new Num(1);
    static Num base = new Num(BASE);
    static Num ten = new Num(10);

    /**
     * Create a Num of base BASE from a String
     * @param num: the number in base 10
     */
    public Num(String num){
        digits = new LinkedList<>();
        Num current = new Num(0);
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
                long digit = Long.parseLong(c.toString());
                Num a = new Num(digit);
                current = Num.product(current, ten);
                current = Num.unsignedAdd(current, a);
            }
        }
        digits = current.getDigits();

    }

    /**
     * Create a Num of base BASE from a long
     * @param num: the number in base 10
     */
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

    /**
     * Create an empty Num Object
     */
    public Num(){
        digits = new LinkedList<>();
    }

    /**
     * Change the base being used by Num class
     * @param newbase: the new base
     */
    public static void changeBase(long newbase){
        BASE = newbase;
    }

    /**
     * Print the internal representation of a list
     */
    public void printList(){
        System.out.print(BASE + " : ");
        Iterator it = this.iterator();
        while(it.hasNext())
            System.out.print(it.next() + " ");
    }

    /**
     * String representation of Num
     * @return String: the String of number in base 10
     */
    public String toString() {
        this.trimNum();
        Num n = makeDec(this);
        StringBuilder s = new StringBuilder();
        if(getSign())
            s.append("-");
        for(int i=n.size()-1; i>=0;i--){
            s.append(n.get(i));
        }
        return s.toString();
    }

    /**
     * Create a Num of base 10 from a Num base BASE
     * @param n: the number in base BASE
     * @return Num: the number in base 10
     */
    public static Num makeDec(Num n){
        long oldbase = BASE;
        changeBase(10);
        Num factor = new Num(oldbase);
        Num current = new Num(0);
        for(int i=n.size()-1; i>=0;i--){
//            current.printList();
            long digit = n.get(i);
            Num a = new Num(digit);
            current = Num.product(current, factor);
//            current.printList();
            current = Num.unsignedAdd(current, a);
        }
        changeBase(oldbase);
        return current;
    }

    /**
     * Trim the leading zeros
     */
    private void trimNum(){
        if(this.size() > 1) {
            boolean stop = false;
            int i = digits.size() - 1;
            while (!stop && i >= 0) {
                if (digits.get(i) == 0) {
                    digits.remove(i);
                    i--;
                } else
                    stop = true;
            }
        }
    }

    /**
     * Get an iterator for Num
     */
    public Iterator iterator(){
        return digits.iterator();
    }

    /**
     * Left Shift operation on Num
     * @param times: the no of times shift should be applied
     */
    private void shift(long times){
        while(times > 0){
            digits.addFirst((long)0);
            times--;
        }
    }

    /**
     * Get the value of the digits at a particular index
     * @return long: the digit that was stored as long
     */
    public long get(int i){
        return digits.get(i);
    }

    /**
     * Add a value at the head of LinkedList of digits
     * @param value: the long to be added
     */
    public void addFirst(long value){
        digits.addFirst(value);
    }

    /**
     * Add a value at the tail of LinkedList of digits
     * @param value: the long to be added
     */
    public void addLast(long value){
        digits.addLast(value);
    }

    /**
     * Create a Num of base BASE from a String
     * @param index: the number in base 10
     */
    public void remove(int index){
        digits.remove(index);
    }

    /**
     * Gives the size of the Num instance
     * @return int: size of the Num
     */
    public int size(){
        return digits.size();
    }

    /**
     * Get the digits of the Num
     * @return LinkedList: Internal LinkedList of Num
     */
    private LinkedList<Long> getDigits(){
        return this.digits;
    }

    /**
     * Sets sign of Num to a given sign
     * @param b : boolean sign to be assigned
     */
    public void setSign(boolean b){
        this.sign = b;
    }

    /**
     * Flip the sign of a Num
     */
    public void setSign(){
        this.sign = !this.sign;
    }

    /**
     * Get sign of Num
     * @return boolean : false if Num is positive, true otherwise
     */
    public boolean getSign(){
        return this.sign;
    }

    /**
     * Simple division without sign
     * @param a : Dividend
     * @param b : Divisor
     * @return Num : Returns Quotient
     */
    public static Num divideUnsigned(Num a, Num b){

        //throw exception if dividing by zero and return a if divide by 1
        if(b.compareTo(zero) == 0) {
            //handles divide by zero case
            throw new IllegalArgumentException("Argument 'divisor' is 0");

        }else if(b.compareTo(one) == 0) {
            //handles divide by 1 case
            return copyNum(a);
        }

        //return zero if a<b, one if a==b
        int compareR = a.compareTo(b);

        if(compareR < 0) {
            return zero;

        } else if(compareR == 0) {
            return one;

        }else{
            //When a>b
            Num avg;
            Num high = copyNum(a);
            Num low = new Num(0);
            Num reminder;

            while(low.compareTo(high) < 0){

                //calculate mid point
                avg = average(high, low);

                //calculate temporary multiplication
                Num temp = product(b, avg);
                int compareResult = temp.compareTo(a);
                reminder = subtract(a, temp);

                //if reminder < divisor, return quotient
                if(b.compareTo(reminder) > 0 && reminder.compareTo(zero) > 0)
                    return avg;

                //if b*avg < a, update low to avg, if b*avg > a, update high to avg
                //else if b*avg == a, return avg
                if(compareResult < 0)
                    low = avg;
                else if(compareResult > 0)
                    high = avg;
                else
                    return avg;
            }

            return low;

        }

    }

    /**
     * Copy the value of the number to a new number
     * @param a : Number to be copied
     * @return Num: Result is the copied number
     */
    public static Num copyNum(Num a){
        String value = a.toString();
        Num result = new Num(value);

        //add sign bit as well
        if(a.getSign())
            result.getSign();

        return new Num(value);
    }


    /**
     * Abstraction for handling sign and perform unsigned division
     * @param a : Dividend
     * @param b : Divisor
     * @return Num : Quotient
     */
    public static Num divide(Num a, Num b){

        boolean aSign = a.getSign();
        boolean bSign = b.getSign();

        Num quotient;

        //consider the sign and call function accordingly
        //when a.sign != b.sign
        if(aSign != bSign) {
            //case b<0, a>0
            if (bSign) {
                b.setSign();
            } else {
                a.setSign();
            }

            quotient = divideUnsigned(a, b);
            b.setSign(bSign);
            a.setSign(aSign);
            quotient.setSign(true);
            quotient.trimNum();
            return quotient;

        }else {
            //case a>0 and b>0
            if(aSign){
                a.setSign();
                b.setSign();
                quotient = divideUnsigned(a, b);
                a.setSign(aSign);
                b.setSign(bSign);

            }else
                //case when a>0 and b>0
                quotient = divideUnsigned(a, b);

        }

        quotient.trimNum();
        return quotient;
    }

    /**
     * Simple modulus after invoking the division method
     * @param a : Dividend
     * @param b : Divisor
     * @return Num: Remainder
     */
    public static Num mod(Num a, Num b){
        if(a.compareTo(zero) < 0 || b.compareTo(zero) < 0)
            throw new IllegalArgumentException("Both the dividend and the divisor should be positive");

        return subtract(a, product(divide(a, b), b));
    }

    /**
     * Split a Num at certain position and return the two parts
     *
     * @param n : Original Num to split
     * @param position : position to split
     * @return Num[] : having first part at index 0 and second part at index 1
     */
    public static Num[] split(Num n, int position){
        Num[] ans = new Num[2];
        ans[0] = new Num();
        ans[1] = new Num();
        for(int i=0;i < n.size();i++){
            if(i < position){
                ans[0].addLast(n.get(i));
            }
            else{
                ans[1].addLast(n.get(i));
            }
        }
        return ans;
    }

    /**
     * Signed multiplication of two numbers
     * @param a : Operand 1
     * @param b : Operand 2
     * @return Num: Product
     */
    public static Num product(Num a, Num b){
        Num answer;
        if(a.size() < 5 && b.size() < 5)
            answer = simpleMul(a,b);
        else
            answer = karatsubaMul(a,b);
        if(a.getSign() != b.getSign())
            if(answer.getSign() != true) answer.setSign();
        answer.trimNum();
        return answer;
    }

    /**
     * Unsigned multiplication of two numbers using Divide and Conquer technique
     * @param a : Operand 1
     * @param b : Operand 2
     * @return Num: Product
     */
    public static Num karatsubaMul(Num a, Num b){
            int len;

            Num bigger, smaller;
            if(a.size() >= b.size()){
                bigger = a;
                smaller = b;
                len = a.size();
            }
            else {
                bigger = b;
                smaller = a;
                len = b.size();
            }
            if(len == 0)//Empty Num or zero
                return new Num(0);

            if(len < 5){
                return simpleMul(a,b);
            }
            //padding for odd length
            while(len%2 != 0){
                bigger.addLast(0);
                len++;
            }
            //padding smaller num
            while(smaller.size() < bigger.size()){
                smaller.addLast(0);
            }

            int half = len/2; //length of the half of the numbers
            Num[] aSplit, bSplit;
            aSplit = a.split(a,half);
            bSplit = b.split(b,half);

            Num mulRight = karatsubaMul(aSplit[0], bSplit[0]);
            Num mulLeft = karatsubaMul(aSplit[1], bSplit[1]);

            Num mulMid = karatsubaMul(unsignedAdd(aSplit[0],aSplit[1]), unsignedAdd(bSplit[0],bSplit[1]));

            mulMid = unsignedSubtract(unsignedSubtract(mulMid, mulLeft), mulRight);
            mulMid.shift(len/2);
            mulLeft.shift(len);
            Num ans = mulLeft;
            ans = unsignedAdd(ans, mulMid);
            ans = unsignedAdd(ans, mulRight);

            return ans;
   }
    /**
     * Unsigned multiplication of two numbers using iterative method
     * @param a : Operand 1
     * @param b : Operand 2
     * @return Num: Product
     */
    public static Num simpleMul(Num a, Num b){
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
            if(carry != 0)
                tempArr[k].addLast(carry);
            carry = 0;
            k++;
        }
        if(secondSize == 1)
            return tempArr[0];
        else {
            Num holder = Num.unsignedAdd(tempArr[0], tempArr[1]);
            if(secondSize == 2)
                return holder;
            else{
                int idx = 2;
                while(idx < secondSize){
                    holder = Num.unsignedAdd(holder,tempArr[idx++]);
                }
            }
            return holder;
        }

    }

    /**
     * Calculates 'a' to the power n, exponent value
     *
     * @param a : base Num object
     * @param n : exponential value
     * @return Num object that is the result of a^n
     */
    static Num power(Num a, long n) {
        Num result;
        if(n==0)
            return new Num(1);
        if(n==1)
            return a;

        Num halfVal=power(a,n/2);
        if((n%2)==0){
            result= product(halfVal,halfVal);
        }
        else{
            Num subResult = product(halfVal, halfVal);
            result = product(subResult,a);
        }
        return result;
    }

    /**
     * Calculates 'a' to the power n, exponent value. Both parameter are Num objects
     *
     * @param a : base value
     * @param b : exponent value
     * @return Num object that is the result of a^b
     */
    public static Num power(Num a, Num b){
        Iterator value= b.iterator();
        Num result= new Num(1);
        if(value.hasNext()){
            result= power(a,(long)value.next());
        }
        long factor = BASE;
        while(value.hasNext()){
            Num subResult1= power(a, (long)value.next());
            Num subResult2= power(subResult1, factor);
            result= product(result,subResult2);
        }
        return result;
    }
    
    /**
     * Function used to perform of addition of long integers 
     * @param a : First number to be added of data type Num
     * @param b : Second number to be added of data type Num
     * @return : returns the addition of 2 numbers as a Num object
     */
    public static Num add(Num a ,Num b){
        boolean signA = a.getSign();
        boolean signB = b.getSign();
        boolean outSign;
        Num tempResult;
       
        //Variables used to store the initial values with sign of a & b
        Num a1,b1;

        //Set sign positive to compare numbers without sign and pass on the unsigned addition or unsigned subtraction
        a.setSign(false);
        b.setSign(false);

        int comp=a.compareTo(b);

        //if a<b
        if(comp<0){
            a1=b;
            b1=a;
            if(signA!=signB)
                outSign=signB;
            else{
                if(signA)
                    outSign=true;
                else
                    outSign=false;
            }
        }
        //if a>b
        else if(comp>0){
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else{
                if(signA)
                    outSign=true;
                else
                    outSign=false;
            }
        }
        //if a==b
        else
        {
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else
                outSign=false;
        }

        //if both numbers have opposite sign, call unsigned subtraction
        if(signA!=signB){
            tempResult = unsignedSubtract(a1,b1);

        }
        //if both numbers have same sign, call unsigned addition
        else
            tempResult = unsignedAdd(a1,b1);

        a.setSign(signA);
        b.setSign(signB);
        
        //set output sign to the final result Num object
        tempResult.setSign(outSign);

        return tempResult;
    }

    /**
     * Function used to perform of addition of long integers irrespective of the sign
     * @param a : First number to be added of data type Num
     * @param b : Second number to be added of data type Num
     * @return : returns the addition of 2 numbers as a Num object
     */
    public static Num unsignedAdd(Num a,Num b){
        
    	//Edge Cases
    	if(a.size() < 1)return b;
        else if(b.size() < 1)return a;

        Iterator<Long> it1 = a.iterator();
        Iterator<Long> it2 = b.iterator();

        long carry = 0;
        Long temp1 = it1.next();
        Long temp2 = it2.next();

        Num result = new Num();
        
        //Loop to compute addition of 2 numbers till both the numbers have valid digits
        while(temp1!=null && temp2!=null){
            Long sum = temp1 + temp2 + carry;
            result.addLast(sum%BASE);
            carry = sum/BASE;

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

        //Loop to add up the digits of Number 1 if Number 2 gets empty
        while(temp1!=null){
            Long sum = temp1 + carry;
            result.addLast(sum%BASE);
            carry = sum/BASE;

            try{
                temp1 = it1.next();
            }
            catch(NoSuchElementException e){
                temp1 = null;
            }
        }

        //Loop to add up Number 2 if Number 1 gets empty
        while(temp2!=null){
            Long sum = temp2 + carry;
            result.addLast(sum%BASE);
            carry = sum/BASE;

            try{
                temp2 = it2.next();
            }
            catch(NoSuchElementException e){
                temp2 = null;
            }
        }

        //Check if carry still exists after addition
        if(carry>0)
            result.addLast(carry);

        return result;
    }
    
    /**
     * Function used to perform of subtraction of long integers
     * @param a : First number to be Subtracted of data type Num
     * @param b : Second number to be subtracted from first number of data type Num
     * @return : returns the subtraction of 2 numbers as a Num object
     */
    public static Num subtract(Num a, Num b){

        boolean signA = a.getSign();
        boolean signB = b.getSign();
        Num tempResult;
        boolean outSign;
       
        //Variables used to store the original values and signs of a & b
        Num a1,b1;

        //Set initial sign to positive and compare both numbers to further pass into unsigned addition or unsigned subtraction
        a.setSign(false);
        b.setSign(false);

        int comp = a.compareTo(b);
        
        //if a<b
        if(comp<0){
            a1=b;
            b1=a;
            if(signA!=signB)
                outSign=signA;
            else{
                if(signA)
                    outSign=false;
                else
                    outSign=true;
            }
        }
        //if a>b
        else if(comp>0){
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else{
                if(signA)
                    outSign=true;
                else
                    outSign=false;
            }
        }
        //if a==b
        else
        {
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else
                outSign=false;
        }

        //if both numbers are having opposite sign, call unsigned addition
        if(signA!=signB){
            tempResult = unsignedAdd(a1,b1);

        }
        //if both numbers are having same sign, call unsigned subtraction
        else{

            tempResult = unsignedSubtract(a1,b1);
        }
        
        a.setSign(signA);
        b.setSign(signB);
        //Set final output sign to the final result Num object
        tempResult.setSign(outSign);

        return tempResult;


    }
    
    /**
     * Function used to perform of Subtraction of long integers irrespective of the sign
     * @param a : First number to be subtracted of data type Num
     * @param b : Second number to be subtracted from first number of data type Num
     * @return : returns the Subtraction of 2 numbers as a Num object
     */
    public static Num unsignedSubtract(Num a,Num b){
        
    	//Edge cases
    	if(a.size() < 1)return b;
        else if(b.size() < 1)return a;

        Iterator<Long> it1 = a.iterator();
        Iterator<Long> it2 = b.iterator();

        boolean carry = false;
        Long temp1 = it1.next();
        Long temp2 = it2.next();

        Num result = new Num();

        //Loop to calculate subtraction when both the numbers are having valid digits
        while(temp1!=null && temp2!=null){
            if(carry){
                if(temp1 == 0)
                    temp1 = BASE-1;
                else{
                    temp1 -= 1;
                    carry = false;
                }
            }
            if(temp1<temp2){
                temp1 += BASE;
                carry = true;
            }
            long difference = temp1 - temp2;
            result.addLast(difference%BASE);

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

        //Loop for Number 1 to be added when Number 2 gets empty
        while(temp1 != null){
            if(carry){
                if(temp1 == 0)
                    temp1 = BASE-1;
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

        return result;
    }

    /**
     * Get the square root of Num
     * @param a : Num whose square root is to be found
     * @return Num: Square Root of a
     */
    public static Num squareRoot(Num a){

        Num low = new Num(1);
        Num high = copyNum(a);
        Num avg;

        //handle edge cases of a=1 and a=0
        if(a.compareTo(one) == 0 || a.compareTo(zero) == 0)
            return high;

        //handle edge cases of a=-ve number where sign==true
        //System.out.println(a.getSign());
        if(a.getSign())
            throw new IllegalArgumentException("Square root of a negative number does not exist");

        //keep the loop going till high is less than low
        //shorten high and low distance by half with each iteration
        while(add(low, new Num("1")).compareTo(high) < 0){

            avg = average(high, low);
//            System.out.println(avg);

            Num square = power(avg, 2L);
            int compareResult = square.compareTo(a);

            if(compareResult > 0)
                high = avg;
            else if(compareResult < 0)
                low = avg;
            else {
                avg.trimNum();
                return avg;
            }

        }
        low.trimNum();
        return low;
    }

    /**
     * Manually calculate the average of two numbers
     * @param a : Number whose average is to be calculated
     * @param b : Number whose average is to be calculated
     * @return Num: Average of the two numbers
     */
    public static Num average(Num a, Num b){

        Num sum = add(a, b);
        Num answer;

        LinkedList<Long> n = makeDec(sum).getDigits();
        String avg = "";
        String carry = "";
        long digit = 0L;
        long temp = 0L;

        for(int i=n.size()-1; i>=0; i--) {

            //concatenate the carry digit to the current digit for / by 2
            temp = Long.parseLong((carry + n.get(i).toString()));
            digit = temp/2L;
            avg = avg + String.valueOf(digit);

            //defining the carry digit
            if(temp % 2L == 0){
                carry = "0";
            }else{
                carry = "1";
            }

        }

        //Assign the bit here if different from sum
        answer = new Num(avg);
        if(answer.getSign() != sum.getSign())
            answer.setSign();

        answer.trimNum();
        return answer;
    }

    /**
     * Compares two numbers and sees which one is greater
     * @param b : Number to be compared with
     * @return int: 1 if current object > b, -1 if current object < b, 0 if both are equal
     */
    public int compareTo(Num b){
        //compare negative and positive numbers
        if(this.getSign() != b.getSign())
            if(this.getSign()==true)
                return -1;
            else
                return 1;

        //Remove any leading zeros in a and b using trimNum()
        this.trimNum();
        b.trimNum();

        //see if the numbers have different sizes
        if(this.size() > b.size()){
            if(this.sign)
                return -1;
            else
                return 1;
        }else if(this.size() < b.size()){
            if(this.sign)
                return 1;
            else
                return -1;
        }else{
            long tempThis;
            long tempB;
            for(int i=this.size()-1; i>=0; i--){
                tempThis = this.get(i);
                //System.out.println(tempThis);
                tempB = b.get(i);
                //System.out.println(tempB);
                if(tempThis > tempB){
                    if(this.getSign())
                        return -1;
                    else
                        return 1;
                }
                else if(tempThis < tempB)
                    if(this.getSign())
                        return 1;
                    else
                        return -1;
                else
                    continue;
            }
        }
        return 0;
    }

}
