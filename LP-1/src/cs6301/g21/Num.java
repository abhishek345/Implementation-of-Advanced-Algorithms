package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

public class Num {

    private LinkedList<Long> digits;

    public Iterator iterator(){
        return digits.iterator();
    }

    public Num(String num){ //only positive numbers yet!
        digits = new LinkedList<>();
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
        while(num != 0){
            digit = num%10;
            digits.addLast(digit);
            num = num/10;
        }
    }

    public Num(){
        digits = new LinkedList<>();
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
        System.out.println(digits);
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
        /*
        4->7->0->5
        9->1->3
         */
        Num[] tempArr = new Num[secondSize];
        int k = 0;
        long mul = 1;
        while(outer.hasNext()){
            long number = (long) outer.next();
            long ans = 0;
            inner = c.iterator();
            tempArr[k] = new Num();
            for(int i=0;i < k;i++)
                tempArr[k].addLast(0);
            while(inner.hasNext()){
                value = number * (long) inner.next() + carry;
                carry = value / 10;
                ans = (value%10)*mul + ans;
                tempArr[k].addLast(value%10);
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

    public static Num add(Num a, Num b){
        return new Num(0);
    }

    public static void main(String args[]){
        Num n = new Num("319.90");
        n.printList();
        Num n2 = new Num(5074);
        n2.printList();
        Num.simpleMul(n,n2);
    }

}
