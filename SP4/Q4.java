package cs6301.g21;

public class Q4 {

    // Preconditions: arr[0..n-1] is sorted, and arr[0] <= x < arr[n-1].
    // Returns index i such that arr[i] <= x < arr[i+1].
    public static<T extends Comparable<? super T>> int binarySearch(T[] arr, T x){

        int low = 0;
        int high = arr.length-1;
        int avg;

        int answer = binarySearch(arr, low, high, x);

        return answer;
    }

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
