package cs6301.g21;

import java.util.ArrayList;

public class Q6 {

    static ArrayList<Integer> arrayList = new ArrayList<>();


    //int[] pos = 0,1,2,3,4,5 ,6 ,7 ,8 ,9 ,10,11,12,13
    //int[] arr = 1,2,5,8,9,10,12,13,15,20,21,23,25,30,31,32,33,34,35,36
    //15 numbers, find 15 missing numbers
    //answer: 3,4,6,7,11,14,16,17,18,19
    /**
     * Preconditions: arr[0..n-1] is sorted, n distinct integers from 1 to k+n
     * Returns k missing numbers in the sequence
     * @param arr : array to be searched for k missing elements
     * @param k : number of elements that are missing, in this case: 14
     * @return ArrayList<Integers> : arraylist containing the missing elements
     */
    public static ArrayList<Integer> findKNum(int[] arr, int k){

        int low = 0;
        int high = arr.length-1;
        int num;
        int diff;

        //checking for the end of the array case
        if(arr[high] < arr.length){
            diff = high-k;
            for(int i=1; i< diff; i++){
                num = arr[high]+i;
                arrayList.add(num);
            }
        }

        findKNum(arr, low, high);

        return arrayList;

    }

    /**
     * Recursive version of findKNum
     * @param arr : array to be investigated
     * @param low :
     * @param high
     */
    public static void findKNum(int[] arr, int low, int high){

        int avg = (low+high)/2;
        int diff;
        int num;

        if((low+1)==high)
            return;

        //handles case where
        if(low > high)
            return;

        //change low or high to avg depending on where in array it is
        //print missing elements in between
        if(arr[avg] > (avg+1)){
            if(avg > 0)
                diff = arr[avg]-arr[avg-1];
            else
                diff = arr[avg]-1;

            for(int i=1; i<diff; i++){
                num = arr[avg]-i;
                arrayList.add(num);
            }
        }

        findKNum(arr, low, avg);
        findKNum(arr, avg, high);
    }

    public static void main(String[] args) {

        int[] arr2 = new int[9];

        arr2[0] = new Integer(2);
        arr2[1] = new Integer(3);
        arr2[2] = new Integer(5);
        arr2[3] = new Integer(7);
        arr2[4] = new Integer(11);
        arr2[5] = new Integer(13);
        arr2[6] = new Integer(17);
        arr2[7] = new Integer(19);
        arr2[8] = new Integer(23);
        //in arr2, 12 elements missing: 1,4,6,8,9,10,12,14,15,16,18,20,21,22

        ArrayList<Integer> result = findKNum(arr2, 14);

        for(Integer i : result)
            System.out.println(i);
    }
}
