package cs6301.g21;

public class Q4 {

    /**
     * Preconditions: arr[0..n-1] is sorted, and arr[0] <= x < arr[n-1].
     * Returns index i such that arr[i] <= x < arr[i+1].
     * @param arr : Sorted array
     * @param x : element to be investigated
     * @param <T> : Type of the array/element
     * @return int : index of the array arr that has the result
     */
    public static<T extends Comparable<? super T>> int binarySearch(T[] arr, T x){

        int low = 0;
        int high = arr.length-1;
        
        int answer = binarySearch(arr, low, high, x);

        return answer;
    }

    /**
     * Recursive version of binary search
     * @param arr : Sorted array with
     * @param low : lower index for binary search
     * @param high : high index for binary search
     * @param x : Element to be compared
     * @param <T> : Type of array/element
     * @return int: index of the array arr that has the result
     */
    public static<T extends Comparable<? super T>> int binarySearch(T[] arr, int low, int high, T x){

        int avg = (low+high)/2;

        //return -1
        if(low > high)
            return -1;

        //case where low and high differ by 1 element
        if((low+1)==high)
            return low;

        //change low or high to avg depending on where in array it is
        if(arr[avg].compareTo(x) < 0){
            return binarySearch(arr, avg, high, x);

        }else if(arr[avg].compareTo(x) > 0){
            return binarySearch(arr, low, avg, x);

        }else
            return avg;

    }

}
