package cs6301.g21;

public class InsertionSort {

    //insertion sort using generics

    /**This method provides insertion sort for Integer Array
     *
     * @param arr   Integer Array to be sorted
     * @param <T>   Generic type T
     */
    static<T extends Comparable<? super T>> void nSquareSort(T[] arr){

        T tmp;

        for(int i=1; i<arr.length; i++){

            tmp = arr[i];
            int j;

            for(j=i-1; j>-1; j--){

                if(tmp.compareTo(arr[j]) < 0) {
                    arr[j+1] = arr[j];
                }else
                    break;
            }

            arr[j+1] = tmp;

        }
    }

    /** This method provides simple insertion sort for int array
     *
     * @param arr   int array to be sorted
     */
    static void nSquareSort(int[] arr){

        int tmp;

        for(int i=1; i<arr.length; i++){

            tmp = arr[i];
            int j;

            for(j=i-1; j>-1; j--){

                if(tmp < arr[j]){
                    arr[j+1] = arr[j];
                }else
                    break;

            }

            arr[j+1] = tmp;

        }

    }



}
