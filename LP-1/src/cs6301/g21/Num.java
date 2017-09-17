package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

public class Num {

    private LinkedList<Long> digits;
    private static int BASE = 10;
    private boolean sign = false;//false (not set) +ve number; if true (set) -ve num

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

    public Iterator iterator(){
        return digits.iterator();
    }

    //get the value of the digits at a particular index
    public long get(int i){
        LinkedList<Long> digits = this.getDigits();
        return digits.get(i);
    }

    public void addFirst(long value){
        digits.addFirst(value);
    }

    public void addLast(long value){
        digits.addLast(value);
    }

    //Flip the sign of Num
    public void setSign(){
        if(this.sign)
            this.sign = false;
        else
            this.sign = true;
    }

    //get sign of Num
    public boolean getSign(){
        return this.sign;
    }

    //Long division by binary search. Implement separate method for binary search
    //reference: http://www.techiedelight.com/division-two-numbers-using-binary-search-algorithm/
    //reference: https://en.wikipedia.org/wiki/Division_algorithm
    public static Num divide(Num a, Num b){

        Num zero = new Num(0);
        Num one = new Num(1);
        Num quotient;

        //handle b<0 cases from Wikipedia
        if(b.getSign() == true){
            b.setSign();
            quotient = divide(a, b);
            b.setSign();
            quotient.setSign();
        }

        //handle a<0 case from Wikipedia
        if(a.getSign() == true){
            a.setSign();
            quotient = divide(a, b);
            a.setSign();
            if(simpleMul(quotient, b).compareTo(zero) > 0){
                quotient = add(quotient, one);
            }
            quotient.setSign();
        }

        //throw exception if dividing by zero and return a if divide by 1
        if(b.compareTo(zero) == 0) {
            //handles divide by zero case
            throw new IllegalArgumentException("Argument 'divisor' is 0");

        }else if(b.compareTo(one) == 0)
            //handles divide by 1 case
            return a;

        //return zero if a<b, one if a==b
        if(a.compareTo(b) < 0) {
            //When a < b
            return zero;

        }else if(a.compareTo(b) == 0) {
            //When a == b
            return one;

        }else{
            //When a>b
            Num avg;
            Num high = copyNum(a);
            Num low = new Num(0);

            while(high.compareTo(low) > 0){

                //calculate mid point
                avg = average(high, low);

                Num temp = simpleMul(b, avg);
                int compareResult = a.compareTo(simpleMul(b, temp));
                Num reminder = subtract(a, temp);

                if(b.compareTo(reminder) > 0)
                    return avg;

                //if b*avg < a, update low to avg, if b*avg > a, update high to avg
                //else if b*avg == a, return avg
                if(compareResult > 0)
                    low = avg;
                else if(compareResult < 0)
                    high = avg;
                else
                    return avg;
            }

            return low;

        }
    }

    //Simple modulus after invoking the divison method
    public static Num mod(Num a, Num b){
        return subtract(a, simpleMul(divide(a, b), b));
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

    //copy the value of the number to a new number
    public static Num copyNum(Num a){
        String value = a.toString();
        return new Num(value);
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

    public static Num add(Num a, Num b){
        return new Num(0);
    }

    public static Num pow(Num a, Num b){

        return new Num(0);
    }

    //Get the square root of Num
    //see if separate binary search algo can be written
    //optimize it
    public static Num squareRoot(Num a){

        Num low = new Num(0);
        Num high = copyNum(a);
        Num avg;

        while(high.compareTo(low) > 0){

            avg = average(high, low);
            Num square = power(avg, 2L);
            int compareResult = square.compareTo(high);

            if(compareResult > 0)
                high = avg;
            else if(compareResult < 0)
                low = avg;
            else
                return avg;
        }

        return low;
    }
    
    //Simple division without sign
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

            while(low.compareTo(high) < 0){

                //calculate mid point
                avg = average(high, low);

                //calculate temporary multiplication
                Num temp = simpleMul(b, avg);
                int compareResult = temp.compareTo(a);
                Num reminder = subtract(a, temp);

                //if reminder < divisor, return quotient
                if(b.compareTo(reminder) > 0)
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
    
    //copy the value of the number to a new number
    public static Num copyNum(Num a){
        String value = a.toString();
        Num result = new Num(value);

        //add sign bit as well
        if(a.getSign())
            result.getSign();

        return new Num(value);
    }
    
    //Long division by binary search.
    //reference: http://www.techiedelight.com/division-two-numbers-using-binary-search-algorithm/
    //reference: https://en.wikipedia.org/wiki/Division_algorithm
    public static Num divide(Num a, Num b){

        boolean aSign = a.getSign();
        boolean bSign = b.getSign();

        Num quotient;


        //consider the sign and call function accordingly
        //when a.sign != b.sign
        if(aSign != bSign){
            //case b<0, a>0
            if(bSign){
                b.setSign();
                quotient = divideUnsigned(a, b);
                b.setSign();
                quotient.setSign();
                return quotient;

            }else{
                //handle a<0 case from Wikipedia
                a.setSign();
                quotient = divideUnsigned(a, b);
                a.setSign();
                quotient.setSign();
                //System.out.println(quotient.getSign());
                //getting negative sign here for case quotient = -1, divisor = 10
                //breaking the code here
                System.out.println(karatsubaMul(quotient, b).getSign());

                if(karatsubaMul(quotient, b).compareTo(a) != 0) {
                    quotient = add(quotient, one);

                }

                return quotient;

            }

        }else {
            //case a>0 and b>0
            if(aSign){
                a.setSign();
                b.setSign();
                quotient = divideUnsigned(a, b);
                //System.out.println(quotient.getSign());
                a.setSign();
                b.setSign();

            }else
                //case when a>0 and b>0
                quotient = divideUnsigned(a, b);

        }

        return quotient;
    }

    //Manually calculated the average of two numbers
    public static Num average(Num a, Num b){

        Num sum = add(a, b);
        LinkedList<Long> n = sum.getDigits();
        String avg = null;
        String carry = "0";
        long digit = 0L;
        long temp = 0L;

        for(int i=n.size()-1; i>0; i--) {
            temp = Long.parseLong((carry + n.get(i).toString()));
            digit = temp/2L;
            avg = avg.concat(String.valueOf(digit));

            //defining the carry digit
            if(temp % 2L == 0){
                carry = "0";
            }else{
                carry = "1";
            }
        }

        return new Num(avg);
        //see if trailing zeros needs to be handled.
    }

    //Does subtract function
    public static Num subtract(Num a, Num b){
        return new Num(0);
    }

    //get digits of the Num
    //testing done
    public LinkedList<Long> getDigits(){
        return this.digits;
    }

    //Compares two numbers and sees which one is greater
    //testing done
    public int compareTo(Num b){

        if(this.size()>b.size())
            return 1;
        else if(this.size() == b.size()){
            LinkedList<Long> digitsThis = this.getDigits();
            LinkedList<Long> digitsB = b.getDigits();

            if(digitsThis.getLast() > digitsB.getLast())
                return 1;
            else if(digitsThis.getLast() < digitsB.getLast()){
                return -1;
            }else{
                for(int i=digitsThis.size()-2; i>0; i--){
                    if(digitsThis.get(i) > digitsB.get(i))
                        return 1;
                    else if(digitsThis.get(i) < digitsB.get(i))
                        return -1;
                    else
                        return 0;
                }
            }
        }

        return -1;
    }



    //Driver function to check
    public static void main(String args[]){
        Num n = new Num("-5074");
        Num n2 = new Num(5074);

        LinkedList<Long> d = n2.getDigits();
        //iterate through linked List to get digits
        //for(Long l : d)
        //    System.out.println(l);

        System.out.println(n2.getSign());


    }

}
