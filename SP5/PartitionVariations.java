package cs6301.g21;

import java.util.Random;

/**Implementation of Various versions of Partitions
 * @author Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0 : 09/24/2017
 *
 */

public class PartitionVariations {
	
	/**
	 * Function used to call quickSort1 which passes input array, lower index, higher index
	 * @param arr : input array
	 */
	static void quickSort1(int[] arr){
		//edge cases
		if(arr==null)
			return;
		
		quickSort1(arr,0,arr.length-1);
	}
	
	/**
	 * Function used to compute Partition version 1 and recursively call itself
	 * @param arr : input array
	 * @param low : lower index of input array
	 * @param high : higher index of input array
	 */
	static void quickSort1(int[] arr, int low, int high){
		if(low<high){
			int q = partition1(arr,low,high);
			quickSort1(arr,low,q-1);
			quickSort1(arr,q+1,high);
		}
	}
	
	/**
	 * Implementation of Partition version 1 
	 * @param arr : input array
	 * @param low : lower index of input array
	 * @param high : higher index of input array
	 * @return : partition index
	 */
	static int partition1(int[] arr, int low, int high){
		Random random = new Random();
		int i = random.nextInt(high-low) + low;
		swap(arr,i,high);
		int pivot = arr[high];
		i = low-1;
		for(int j = low; j<high; j++){
			if(arr[j]<=pivot){
				i++;
				swap(arr,i,j);
			}
		}
		swap(arr,i+1,high);
		return i+1;
	}

	
	/**
	 * Function used to call quickSort2 which passes input array, lower index, higher index
	 * @param arr : input array
	 */	
	static void quickSort2(int[] arr){
		if(arr==null)
			return;
		quickSort1(arr,0,arr.length-1);
	}
	
	/**
	 * Function used to compute Partition version 2 and recursively call itself
	 * @param arr : input array
	 * @param low : lower index of input array
	 * @param high : higher index of input array
	 */	
	static void quickSort2(int[] arr, int low, int high){
		if(low<high){
			int q = partition2(arr,low,high);
			quickSort2(arr,low,q-1);
			quickSort2(arr,q,high);
		}
	}
	
	
	/**
	 * Implementation of Partition version 1 
	 * @param arr : input array
	 * @param low : lower index of input array
	 * @param high : higher index of input array
	 * @return : partition index
	 */
	static int partition2(int[] arr, int low, int high){
		Random random = new Random();
		int pivot = random.nextInt(high-low) + low;
		int i = low - 1;
		int j = high + 1;
		while(true){
			do{
				i++;
			}while(arr[i]<pivot);
			
			do{
				j--;
			}while(arr[j]>pivot);
			
			if(i>=j)
				return j;
			
			swap(arr,i,j);
			i++;
			j--;
		}
	}
	
	
	/**
	 * Function used to exchange values at given indexes
	 * @param arr : input array
	 * @param left : left index to be exchanged
	 * @param right : right index to be exchanged
	 */
	static void swap(int[] arr,int left, int right){
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}