package cs6301.g21;

import java.util.PriorityQueue;

public class Powers implements Comparable<Powers>{

    long value;
    int a;
    int b;

    /**
     * Powers constructor
     * @param a
     * @param b
     */
    public Powers(int a, int b){
        this.a = a;
        this.b = b;
        this.value = calcPower(a, b);
    }

    /**
     * Returns the value of a^b
     * @return long: value of a^b
     */
    public long getValue() {
        return value;
    }

    /**
     * Calculate the value of a^b
     * @param a : base
     * @param b : exponent
     * @return long: value of a^b
     */
    public long calcPower(int a, int b){

        long result;

        if(b==0l)
            return 1l;

        if(b==1l)
            return a;

        long halfVal=calcPower(a,b/2);

        if((b%2l)==0){
            result= halfVal*halfVal;
        }
        else{
            long subResult = halfVal*halfVal;
            result = subResult*a;
        }

        return result;
    }

    /**
     * Compares the value of two Powers classes
     * @param o : Powers object to be compared to
     * @return int : 0, -1, 1
     */
    @Override
    public int compareTo(Powers o) {
        if(this.getValue() > o.getValue())
            return 1;
        else if(this.getValue() < o.getValue())
            return -1;

        return 0;
    }

    public static void main(String[] args) {

        int n;
        Powers prev = new Powers(2,1);

        long MaxVal = Integer.parseInt(args[0]);

        PriorityQueue<Powers> powers1 = new PriorityQueue<>();

        for (int b=2; b<=Long.MAX_VALUE; b++) {

            //takes care of range to be in [2...n]
            if(Math.pow(2, b) > MaxVal)
                break;

            powers1.add(new Powers(2, b));

        }

        System.out.println("Printing a^b, b>1: ");
        while (!powers1.isEmpty()) {
            Powers powers = powers1.remove();

            //handles duplicates
            if(prev.compareTo(powers)!=0)
                System.out.println(powers.getValue());
            prev = powers;

            // add next perfect power if it doesn't overflow a long
            if (Math.pow(powers.a + 1, powers.b) <= MaxVal)
                powers1.add(new Powers(powers.a + 1, powers.b));
        }

    }



}
