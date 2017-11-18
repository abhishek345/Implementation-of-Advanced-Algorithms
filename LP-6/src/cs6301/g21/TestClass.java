package cs6301.g21;

public class TestClass {


    static Long L(int n){
        return new Long(n);
    }
    public static void main(String args[]){
        MDS db = new MDS();


        db.add(new Long(1), (new Long[]{L(10), L(20), L(40), L(99)}));
        db.add(new Long(2), (new Long[]{L(5), L(10), L(12), L(14)}));
        db.add(new Long(3), (new Long[]{L(21), L(40), L(50), L(99)}));
        db.add(new Long(4), (new Long[]{L(60), L(70), L(75), L(99)}));
        db.add(new Long(5), (new Long[]{L(40), L(60), L(80), L(99)}));

        Long[] larr = db.findItem((new Long[]{L(40), L(80), L(99)}));
        //1: 1, 3:2, 4:1, 5:3
        for(Long l: larr){
            System.out.print(l +" ");
        }


    }
}
