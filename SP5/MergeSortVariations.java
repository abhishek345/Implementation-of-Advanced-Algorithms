package cs6301.g21;

/**
 * Implementation of best version of Merge Sort on integer arrays
 * @author Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0 : 09/20/2017
 *
 */

public class MergeSortVariations {


    static void mergeSort(int[] arr){
        if(arr==null)
            return;
        int[] temp = arr;
        mergeSort(arr,temp,0,arr.length-1);
    }

    /**
     * Recursive Merge Sort for Integer arrays: The best version for comparison
     * with dual pivot Quick Sort
     *
     * If the array contains more than one element, it splits it in half
     * and recursively calls mergeSort function for either half. Once sorted,
     * it calls the merge function to obtain the complete sorted array
     *
     * @param arr  : int[] : integer array to be sorted
     * @param temp  : int[] : temporary integer array that is used for sorting
     * @param low  : int : smallest index of the section of the array to be sorted
     * @param high : int : biggest index of the section of the array to be sorted
     *
     */
    static void mergeSort(int[] arr, int[] temp,int low, int high){

        if((high-low)<17)
            InsertionSort.insertionSort(arr,low,high);

        else{

            int mid = (low+high)/2;
            mergeSort(temp, arr, low, mid);
            mergeSort(temp, arr, mid+1, high);
            merge(temp, arr, low, mid, high);
        }

    }

    /**
     * Function to merge two sorted arrays
     *
     * Replaces the smallest index in the array with the smallest element
     * of the two sorted sub array.
     * Increments the smallest index values of that sub-array and the array
     * Repeats the above procedure until all elements are sorted.
     *
     * @param arr  : int[] : integer array to be sorted
     * @param temp  : int[] : temporary integer array that is used for sorting
     * @param low  : int : smallest index of the sorted sub-array
     * @param mid  : int : biggest index of the sorted sub-array
     * @param high : int : biggest index of the sorted sub-array
     *
     */
    static void merge(int[] arr, int[] temp, int low, int mid, int high){

        int i = low, j = mid +1;

        for(int k = low;k<=high;k++){
            if((j>high) || ((i<=mid) && (arr[i]<=arr[j])))
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }

    }

}
