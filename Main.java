package cs6301.g21;

import java.util.ArrayList;

/** Main class for simple merge sort
 *  @author vxb
 */

public class Main {

    /**
     *
     * @param args Default parameter
     */
    public static void main(String[] args) {
	// write your code here

        int n = 1000000;
        int[] arr = new int[n];
        int[] arr1 = new int[1000000];
        int[] arr2 = new int[2000000];
        int[] arr3 = new int[4000000];
        int[] arr4 = new int[8000000];
        int[] arr5 = new int[16000000];
        int[] tmp = new int[1000000];

        Integer[] arrG = new Integer[n];
        Integer[] arrG1 = new Integer[1000000];
        Integer[] arrG2 = new Integer[2000000];
        Integer[] arrG3 = new Integer[4000000];
        Integer[] arrG4 = new Integer[8000000];
        Integer[] arrG5 = new Integer[16000000];
        Integer[] tmpG = new Integer[n];


        Timer timer = new Timer();
        Shuffle shuffle = new Shuffle();

        for(int i=0; i<n; i++) {
            arr[i] = i;
            arrG[i] = i;
        }

        shuffle.shuffle(arr);

        /*//Timer for merge sort for int array
        timer.start();

        MergeSort.mergeSort(arr,tmp);

        timer.end();
        System.out.println("Time for simple mergeSort is: "+timer.toString());
        */

        //Timer for insertion sort for int array
        timer.start();

        InsertionSort.insertionSort(arr);

        timer.end();
        System.out.println("Time for simple insertionSort is: "+timer.toString());

        timer.start();

        InsertionSort.insertionSort(arrG);

        timer.end();
        System.out.println("The time for Insertion Sort using Generics is: "+timer.toString());

    }
}
