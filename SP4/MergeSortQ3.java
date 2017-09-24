package cs6301.g21;

/**
 * Implementation of Variations of Merge Sort on integer arrays 
 * @author Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0 : 09/20/2017
 *
 */

public class MergeSortQ3 {
	
	/**
	 * Main function which calls various versions of merge sort
	 * @param args : command line input
	 */

	public static void main(String[] args){
		int n = 10000;
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		int[] arr3 = new int[n];
		int[] arr4 = new int[n];
	
		Timer timer = new Timer();
		Shuffle shuffle = new Shuffle();

        for(int i=1; i<n+1; i++) {
            arr1[i-1] = i;
        }
        
        shuffle.shuffle(arr1);
        
        for(int i=0;i<n;i++){
        	arr2[i] = arr1[i];
        	arr3[i] = arr1[i];
        	arr4[i] = arr1[i];
        }        
      	
         timer.start();
         MergeSortVariations.mergeSort1(arr1);
         timer.end();
         System.out.println("Time for Merge Sort Variation 1 : "+timer.toString());
         
         timer.start();
         MergeSortVariations.mergeSort2(arr2);
         timer.end();
         System.out.println("Time for Merge Sort Variation 2 : "+timer.toString());
         
         timer.start();
         MergeSortVariations.mergeSort3(arr3);
         timer.end();
         System.out.println("Time for Merge Sort Variation 3 : "+timer.toString());
        
         timer.start();
         MergeSortVariations.mergeSort4(arr4);
         timer.end();
         System.out.println("Time for Merge Sort Variation 4 : "+timer.toString());
	}
}
