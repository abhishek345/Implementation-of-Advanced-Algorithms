package cs6301.g21;

public class InsertionSort {

    /** insertion sort using generics
     *
     * @param arr
     * @param <T>
     */
    static<T extends Comparable<? super T>> void insertionSort(T[] arr){

        T temp;

        for(int i=1; i<arr.length; i++){
            for(int j=i; j>0; j--){
                if(arr[j].compareTo(arr[j-1]) < 0){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param arr   Array to be sorted
     */
    static void insertionSort(int [] arr){

        int temp;

        for(int i=1; i<arr.length; i++){
            for(int j=i; j>0; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }

    }

}
