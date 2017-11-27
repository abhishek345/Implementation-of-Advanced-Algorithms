package cs6301.g21;

public class TestClass {


    static Long L(int n){
        return new Long(n);
    }

    static void printLongArr(Long[] arr){
        for(Long l: arr){
            System.out.print(l +" ");
        }
        System.out.println();
    }

    public static void main(String args[]){
        MDS db = new MDS();

        //add item, descr
        db.add(new Long(1), (new Long[]{L(10), L(20), L(40), L(99)}));
        db.add(new Long(2), (new Long[]{L(5), L(10), L(12), L(14)}));
        db.add(new Long(3), (new Long[]{L(21), L(40), L(50), L(99)}));
        db.add(new Long(4), (new Long[]{L(60), L(70), L(75), L(99)}));
        db.add(new Long(4), (new Long[]{L(65)}));
        db.add(new Long(5), (new Long[]{L(40), L(60), L(80), L(99)}));

        //Test: description(id)
        Long[] descr = db.description(L(4));
        printLongArr(descr);


        //Test: findItem()
        Long[] larr = db.findItem((new Long[]{L(40), L(80), L(99)}));
        //1: 1, 3:2, 4:1, 5:3
        printLongArr(larr);

        //Test: add(supplier, reputation)
        db.add(L(21), 3.0f);
        db.add(L(22), 4.5f);
        db.add(L(23), 4.5f);
        db.add(L(24), 4.0f);
        //test: add sup, pair
        MDS.Pair p1[] = new MDS.Pair[3];
        MDS.Pair p2[] = new MDS.Pair[1];
        MDS.Pair p3[] = new MDS.Pair[2];

        p1[0] = new MDS.Pair(3, 10);
        p1[1] = new MDS.Pair(1, 5);
        p1[2] = new MDS.Pair(5, 10);

        p2[0] = new MDS.Pair(4, 15);
//        p2[1] = new MDS.Pair(5, 20);

        p3[0] = new MDS.Pair(4, 15);
        p3[1] = new MDS.Pair(5, 15);
        //1: 21, 2: -, 3:21, 4:23,24, 5:21, 24
        db.add(L(21), p1);
        db.add(L(23), p2);
        db.add(L(24), p3);

        //test: findSupplier(id)
        Long[] sups = db.findSupplier(L(5));
        printLongArr(sups);


        //test: findSupplier(id, minRep)
        sups = db.findSupplier(L(5), 3.4f);
        printLongArr(sups);

        sups = db.purge(4.4f);
        printLongArr(sups);
    }
}
