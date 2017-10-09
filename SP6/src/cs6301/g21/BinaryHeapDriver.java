package cs6301.g21;

import java.util.Random;

public class BinaryHeapDriver {

    public static void main(String args[]){
        Integer[] arr = new Integer[10];
        IntCompare desc = new IntCompare();
        IntCompareAsc asc = new IntCompareAsc();
        Random r = new Random();
        for(int i=0;i < 10; i++){
            arr[i] = new Integer(r.nextInt(100));
        }

        System.out.println(" Ascending order : ");
        BinaryHeap.heapSort(arr, asc);
        for(Integer x : arr)
            System.out.print(x+", ");
        System.out.println();

        System.out.println(" Descending order : ");
        BinaryHeap.heapSort(arr, desc);
        for(Integer x : arr)
            System.out.print(x+", ");
        System.out.println();
    }

}
