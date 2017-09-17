package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Num {

    private LinkedList<Long> digits;
    private static long BASE = 10;
    private boolean sign = false;//false (not set) +ve number; if true (set) -ve num
    private static boolean flag =  false; //#1
    static Num zero = new Num(0);
    static Num one = new Num(1);
    static Num base = new Num(BASE);

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
                current = Num.product(current, base);
                current = Num.add(current, a);
            }
        }
        digits = current.getDigits();

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

    //trim the String to remove all the zeros
    public static String trimString(String s){

        //take care of all leading zeros
        while ((s.length() > 1) && (s.charAt(0) == '0'))
            s = s.substring(1);

        return s;
    }

    private void trimNum(){
        boolean stop = false;
        int i= 0;
        while(!stop && i<digits.size()){
            if(digits.get(i) == 0){
                digits.remove(i);
            }

        }
    }

    public Iterator iterator(){
        return digits.iterator();
    }

    private void shift(long times){
        while(times > 0){
            digits.addLast((long)0);
            times--;
        }
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

    public void remove(int index){
        digits.remove(index);
    }

    //get digits of the Num
    public LinkedList<Long> getDigits(){
        return this.digits;
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

    //Simple modulus after invoking the divison method
    public static Num mod(Num a, Num b){
        return subtract(a, simpleMul(divide(a, b), b));
    }

    public void printList(){
        System.out.print(BASE + " : ");
        Iterator it = this.iterator();
        while(it.hasNext())
            System.out.print(it.next() + " ");
        if(getSign())
            System.out.println("-");
        else
            System.out.println("+");
    }

    public String toString() {
        Iterator value = this.iterator();
        long result = (long) value.next();
        long first;
        long factor = BASE;
        while(value.hasNext()){
            first = (long) value.next();
            first = first * factor;
            result = result + first;
            factor *= BASE;
        }
        return String.valueOf(result);
    }

//    //copy the value of the number to a new number
//
//    public static Num copyNum(Num a){
//        String value = a.toString();
//        return new Num(value);
//    }

    public int size(){
        return digits.size();
    }

    public static Num[] split(Num n, int position){
        Num[] ans = new Num[2];
        ans[0] = new Num();
        ans[1] = new Num();
        System.out.println(n);
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

    public static Num karatsubaMul(Num a, Num b){
            int len;
//            System.out.print("Multiplying: ");
            a.printList();
            b.printList();
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
            if(len == 0)
                return new Num(0);
//            if(len <= 4) {
//                return simpleMul(a,b);
//            }
            if(len < 4){
                return simpleMul(a,b);
            }
            while(len%2 != 0){
                bigger.addLast(0);
                len++;
            }
            while(smaller.size() < bigger.size()){
                smaller.addLast(0);
            }
//            System.out.print("padded: ");
            bigger.printList();
            smaller.printList();
            int half = len/2; //length of the half of the numbers
            Num[] aSplit, bSplit;
            aSplit = a.split(a,half);
            bSplit = b.split(b,half);
            //aSplit[0] - L ; [1] - R

            Num mulRight = karatsubaMul(aSplit[0], bSplit[0]);
//            System.out.print("Right Term: "); mulRight.printList();
            Num mulLeft = karatsubaMul(aSplit[1], bSplit[1]);
//            System.out.print("Left Term: "); mulLeft.printList();

            Num mulMid = karatsubaMul(add(aSplit[0],aSplit[1]), add(bSplit[0],bSplit[1]));

//        System.out.print("Middle Term: "); mulMid.printList();

//            System.out.print("Middle Term sub Left: "); subtract(mulMid, mulLeft).printList();
//            System.out.print("Middle Term sub Right: "); subtract(mulMid, mulRight).printList();
            mulMid = subtract(subtract(mulMid, mulLeft), mulRight);
//        System.out.print("Middle Term after sub: "); mulMid.printList();
            //mulMid = subtract(mulMid, mulRight);

            mulMid = simpleMul(mulMid, power(new Num(BASE),len/2));
            //shift(mulMid, len/2);
            Num ans = simpleMul(mulLeft, power(new Num(BASE), len));
//            System.out.print("Before Add with mid step: ");ans.printList();
            ans = add(ans, mulMid);
//            ans.printList();
            ans = add(ans, mulRight);
//            System.out.print("Final step: ");ans.printList();
            if(a.getSign() != b.getSign())
                if(ans.getSign() == false) ans.setSign();
            return ans;
   }

    public static Num product(Num a, Num b){
        if(a.size() < 5 && b.size() < 5)
            return simpleMul(a,b);
        return karatsubaMul(a,b);
    }

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

    public void setPositive() { sign = false;}

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

    // taking two Num objects as parameters
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
    
    public static Num unsignedAdd(Num a,Num b){
        Iterator<Long> it1 = a.iterator();
        Iterator<Long> it2 = b.iterator();

        long carry = 0;
        Long temp1 = it1.next();
        Long temp2 = it2.next();

        Num result = new Num();

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

        if(carry>0)
            result.addLast(carry);

        return result;
    }

    public static Num unsignedSubtract(Num a,Num b){
        Iterator<Long> it1 = a.iterator();
        Iterator<Long> it2 = b.iterator();

        boolean carry = false;
        Long temp1 = it1.next();
        Long temp2 = it2.next();

        Num result = new Num();
        String outStr = "";

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

        return result;
    }

    public static Num add(Num a ,Num b){
        boolean signA = a.getSign();
        boolean signB = b.getSign();
        boolean outSign;
        Num tempResult;
        Num a1,b1;

        a.setPositive();
        b.setPositive();

        int comp=a.compareTo(b);

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
        else
        {
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else
                outSign=false;
        }

        if(signA!=signB){
            tempResult = unsignedSubtract(a1,b1);

        }

        else
            tempResult = unsignedAdd(a1,b1);

        if(signA==true)
            a.setNegative();
        else
            a.setPositive();
        if(signB==true)
            b.setNegative();
        else
            b.setPositive();
        return tempResult;
    }

    public static Num subtract(Num a, Num b){

        boolean signA = a.getSign();
        boolean signB = b.getSign();
        Num tempResult;
        boolean outSign;
        Num a1,b1;

        a.setPositive();
        b.setPositive();

        int comp = a.compareTo(b);

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
        else
        {
            a1=a;
            b1=b;
            if(signA!=signB)
                outSign=signA;
            else
                outSign=false;
        }

        if(signA!=signB){
            tempResult = unsignedAdd(a1,b1);

        }

        else{

            tempResult = unsignedSubtract(a1,b1);
        }
        //System.out.println(outSign);
        if(signA==true)
            a.setNegative();
        else
            a.setPositive();
        if(signB==true)
            b.setNegative();
        else
            b.setPositive();
        return tempResult;


    }

    //Get the square root of Num
    public static Num squareRoot(Num a){

        Num low = new Num(1);
        Num high = copyNum(a);
        Num avg;

        //handle edge cases of a=1 and a=0
        if(a.toString().equals("1") || a.toString().equals("0"))
            return high;

        //handle edge cases of a=-ve number where sign==true
        //System.out.println(a.getSign());
        if(a.getSign())
            throw new IllegalArgumentException("Square root of a negative number does not exist");

        //keep the loop going till high is less than low
        //shorten high and low distance by half with each iteration
        while(add(low, new Num("1")).compareTo(high) < 0){

            avg = average(high, low);
            System.out.println(avg);

            Num square = power(avg, 2L);
            int compareResult = square.compareTo(a);

            if(compareResult > 0)
                high = avg;
            else if(compareResult < 0)
                low = avg;
            else
                return avg;

        }

        return low;
    }

    //Manually calculate the average of two numbers
    public static Num average(Num a, Num b){

        Num sum = add(a, b);
        Num answer;

        LinkedList<Long> n = sum.getDigits();
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

        return answer;
    }


    //Compares two numbers and sees which one is greater
//    public static int isGreater(Num a, Num b){
//
//        if(a.digits.size()>b.digits.size())
//            return 1;
//        else if(a.digits.size() == b.digits.size()){
//            if(a.digits.getLast() > b.digits.getLast())
//                return 1;
//            else if(a.digits.getLast() < b.digits.getLast()){
//                return -1;
//            }else{
//                for(int i=0; i<a.digits.size(); i++){
//                    if(a.digits.get(i) > b.digits.get(i))
//                        return 1;
//                    else if(a.digits.get(i) < b.digits.get(i))
//                        return -1;
//                    else
//                        return 0;
//                }
//            }
//        }
//
//        return -1;
//    }

    //Compares two numbers and sees which one is greater
    //testing done
    public int compareTo(Num b){
        //compare negative and positive numbers
        if(this.getSign() != b.getSign())
            if(this.getSign()==true)
                return -1;
            else
                return 1;

        //Remove any leading zeros in a and b
        String s1 = this.toString();
        String s2 = b.toString();
        //take care of all leading zeros
        while ((s1.length() > 1) && (s1.charAt(0) == '0'))
            s1 = s1.substring(1);
        //take care of all leading zeros
        while ((s2.length() > 1) && (s2.charAt(0) == '0'))
            s2 = s2.substring(1);
        //see if the numbers have different sizes
        if(s1.length() > s2.length()){
            if(this.sign)
                return -1;
            else
                return 1;
        }else if(s1.length() < s2.length()){
            if(this.sign)
                return 1;
            else
                return -1;
        }else{
            long tempThis;
            long tempB;
            for(int i=s1.length()-1; i>-1; i--){
                tempThis = this.get(i);
                tempB = b.get(i);
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

    //Driver function to check
    public static void main(String args[]){
        Num n = new Num(-1);
        Num n2 = new Num(-10);
        Num n3 = product(n,n2);
        n3.printList();
    }

}
