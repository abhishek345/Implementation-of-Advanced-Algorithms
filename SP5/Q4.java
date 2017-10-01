package cs6301.g21;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

/**
 * Comparision of Dual Pivot Quick Sort with best version Merge Sort
 *
 * @author Vibha Belavadi, Umang Shah, Abhishek Jagwani, Shreya Vishwanath Rao, 
 * @version 1.0 : 10/1/2017
 */

public class Q4 {


    /**
     * Main function which compares of Dual Pivot Quick Sort with Merge Sort
     *
     * @param args : command line input
     */

    public static void main(String[] args) {
        int n = 512000000;
        int [] array = new int[n];

        Timer timer = new Timer();
        Shuffle shuffle = new Shuffle();

        for (int i = 1; i < n + 1; i++) {
            array[i - 1] = i;
        }

        shuffle.shuffle(array);

        System.out.println("The value of n is: "+n);

        timer.start();
        MergeSortVariations.mergeSort(array);
        timer.end();
        System.out.println("Time for best version of Merge Sort : " + timer.toString());

        /*timer.start();
        DualPivotPartition.dualPivotQS(array, 0, array.length - 1);
        timer.end();
        System.out.println("Time for Quick Sort Dual Pivot : " + timer.toString());*/
    }

}
