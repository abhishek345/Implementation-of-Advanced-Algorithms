package cs6301.g21;


/**Implementation of QuickSort and various versions of partition
 * @author Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0 : 09/24/2017
 *
 */


public class QuickSortQ1 {
	
	/**
	 * Main function used to create an array of size N, shuffle it and sort in
	 * descending order to sort the array in ascending manner
	 * 
	 * @param args : command line argument
	 */
	
	public static void main(String[] args){
		int n = 10000000;
		
		//Random shuffled arrays
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		
		//Descending ordered arrays
		int[] arrDesc1 = new int[n];
		int[] arrDesc2 = new int[n];
	
		Shuffle shuffle = new Shuffle();

        for(int i=1; i<n+1; i++) {
            arr1[i-1] = i;
        }
        
        Timer timer = new Timer();
        shuffle.shuffle(arr1);
        
        for(int i=0;i<n;i++){
        	arr2[i] = arr1[i];
        }
        
        //Sorting the arrays in descending manner
        for(int i=n;i>0;i--){
        	arrDesc1[n-i] = i;
        	arrDesc2[n-i] = i;

        }
          
        //Call to Partition version 1 with randomly shuffled array
        timer.start();
        PartitionVariations.quickSort1(arr1);
        timer.end();
        System.out.println("Time 1 for random: "+timer.toString());

        //Call to Partition version 2 with randomly shuffled array
        timer.start();
        PartitionVariations.quickSort2(arr2);
        timer.end();
        System.out.println("Time 2 for random: "+timer.toString()); 
        
        //Call to Partition version 1 with descending ordered array
        timer.start();
        PartitionVariations.quickSort1(arrDesc1);
        timer.end();
        System.out.println("Time 1 for descending: "+timer.toString());

        //Call to Partition version 2 with descending ordered array
        timer.start();
        PartitionVariations.quickSort2(arrDesc2);
        timer.end();
        System.out.println("Time 2 for descending: "+timer.toString()); 
    }
}
