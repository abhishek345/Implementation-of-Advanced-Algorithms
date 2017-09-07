package cs6301.g21;

import java.util.LinkedList;

public class Num {

    private LinkedList<Long> digits;

    public Num(String num){ //only positive numbers yet!
        digits = new LinkedList<>();
        int decimalPoint = num.indexOf(".");
        //If a decimal point is present, truncate at the decimal point
        if(num.indexOf(".") > -1){
            num = num.substring(0,decimalPoint);
        }
        for(int i = 0;i < num.length(); i++){
            Character c = num.charAt(i);
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("String should only contain numbers");
            }
            else{
                digits.addFirst(Integer.parseInt(c.toString()));
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
//    private Result getResult(int op1, int op2, int opr){
//        Result r;
//
//    }
    public static void main(String args[]){
        Num n = new Num("156.90");
        n.printList();
        long num1 = Long.MAX_VALUE;
        System.out.println(num1);
        
        Num n2 = new Num(num1);

        n2.printList();
    }

}
