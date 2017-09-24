package cs6301.g21;

/**
 * Implementation of Variations of Merge Sort on integer arrays 
 * @author Abhishek Jagwani, Shreya Vishwanath Rao, Umang Shah, Vibha Belavadi
 * @version 1.0 : 09/20/2017
 *
 */

public class MergeSortVariations {
	
	/**
	 * Merge Sort for Integer arrays described in textbooks
	 * 
	 * Calls another method called mergeSort1, sending the received integer
	 * array, starting and ending index of the integer
	 * array
	 * 
	 * @param : int[] : arr integer array to be sorted
	 * 
	 */
	static void mergeSort1(int[] arr){
		if(arr==null)
			return;
		mergeSort1(arr,0,arr.length-1);
	}
	
	/**
	 * Recursive Merge Sort for Integer arrays
	 * 
	 * If the array contains more than one element, it splits it in half
	 * and recursively calls mergeSort1 function for either half. Once sorted,
	 * it calls the merge1 function to obtain the complete sorted array
	 * 
	 * @param arr  : int[] : integer array to be sorted
	 * @param low  : int : smallest index of the section of the array to be sorted
	 * @param high : int : biggest index of the section of the array to be sorted 
	 * 
	 */
	static void mergeSort1(int[] arr, int low, int high){
		
		if(low<high){
			int mid = (low+high)/2;
			mergeSort1(arr, low, mid);
			mergeSort1(arr, mid+1,high);
			merge1(arr,low,mid,high);
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
	 * @param low  : int : smallest index of the sorted sub-array
	 * @param mid  : int : biggest index of the sorted sub-array
	 * @param high : int : biggest index of the sorted sub-array
	 * 
	 */
	static void merge1(int[] arr, int low, int mid, int high){
		
		int inf = Integer.MAX_VALUE;
		int[] L = new int[mid-low+2];
		int[] R = new int[high-mid+1];
		
		int Ltemp = 0, Rtemp=0;
		for(int pos=low;pos<=mid;pos++){
			L[Ltemp++] = arr[pos];
		}

		
		for(int pos=mid+1;pos<=high;pos++){
			R[Rtemp++] = arr[pos];
		}
		
		L[Ltemp] = inf;
		R[Rtemp] = inf;
		
		int i = 0, j = 0,k = 0;
		
		for(k=low;k<=high;k++){
			if(L[i]<=R[j])
				arr[k] = L[i++];
			else
				arr[k] = R[j++];
		}
		
	}
	
	
	/**
	 * Merge Sort for Integer arrays by keeping one temp array passed as parameter
	 * 
	 * Calls another method called mergeSort2, sending the received integer
	 * array, starting and ending index of the integer
	 * array
	 * 
	 * @param : int[] : arr integer array to be sorted
	 * 
	 */
	static void mergeSort2(int[] arr){
		if(arr==null)
			return;
		mergeSort2(arr,0,arr.length-1);
	}
	
	/**
	 * Recursive Merge Sort for Integer arrays
	 * 
	 * If the array contains more than one element, it splits it in half
	 * and recursively calls mergeSort2 function for either half. Once sorted,
	 * it calls the merge2 function to obtain the complete sorted array
	 * 
	 * @param arr  : int[] : integer array to be sorted
	 * @param low  : int : smallest index of the section of the array to be sorted
	 * @param high : int : biggest index of the section of the array to be sorted 
	 * 
	 */
	static void mergeSort2(int[] arr, int low, int high){
		
		if(low<high){
			int[] temp = new int[arr.length];
			int mid = (low+high)/2;
			mergeSort2(arr, low, mid);
			mergeSort2(arr,  mid+1,high);
			merge2(arr,temp,low,mid,high);
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
	 * @param tmp  : int[] : temporary integer array that is used for sorting
	 * @param low  : int : smallest index of the sorted sub-array
	 * @param mid  : int : biggest index of the sorted sub-array
	 * @param high : int : biggest index of the sorted sub-array
	 * 
	 */
	static void merge2(int[] arr, int[] temp, int low, int mid, int high){
				
		for(int pos=low;pos<=high;pos++){
			temp[pos] = arr[pos];
		}

		int i = low, j = mid+1;
		
		for(int k = low;k<=high;k++){
			if((j>high) || ((i<=mid) && (temp[i]<=temp[j])))
				arr[k] = temp[i++];
			else
				arr[k] = temp[j++];
		}
		
	}
	
	
	/**
	 * Merge Sort for Integer arrays with use of Insertion sort when array size is less than threshold
	 * 
	 * Calls another method called mergeSort3, sending the received integer
	 * array, starting and ending index of the integer
	 * array
	 * 
	 * @param : int[] : arr integer array to be sorted
	 * 
	 */
	static void mergeSort3(int[] arr){
		if(arr==null)
			return;
		mergeSort3(arr,0,arr.length-1);
	}
	
	/**
	 * Recursive Merge Sort for Integer arrays
	 * 
	 * If the array contains more than one element, it splits it in half
	 * and recursively calls mergeSort3 function for either half. Once sorted,
	 * it calls the merge3 function to obtain the complete sorted array
	 * 
	 * @param arr  : int[] : integer array to be sorted
	 * @param tmp  : int[] : temporary integer array that is used for sorting
	 * @param high : int : biggest index of the section of the array to be sorted 
	 * 
	 */
	static void mergeSort3(int[] arr, int low, int high){
		
		if((high-low)<17)
			InsertionSort1.insertionSort(arr,low,high);
		
		else{
			int[] temp = new int[arr.length];
			int mid = (low+high)/2;
			mergeSort3(arr, low, mid);
			mergeSort3(arr, mid+1,high);
			merge3(arr,temp,low,mid,high);
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
	 * @param tmp  : int[] : temporary integer array that is used for sorting
	 * @param low  : int : smallest index of the sorted sub-array
	 * @param mid  : int : biggest index of the sorted sub-array
	 * @param high : int : biggest index of the sorted sub-array
	 * 
	 */
	static void merge3(int[] arr, int[] temp, int low, int mid, int high){
		
		for(int pos=low;pos<=high;pos++){
			temp[pos] = arr[pos];
		}
		
		int i = low, j = mid +1;
		
		for(int k = low;k<=high;k++){
			if((j>high) || ((i<=mid) && (temp[i]<=temp[j])))
				arr[k] = temp[i++];
			else
				arr[k] = temp[j++];
		}
		
	}
	
	
	/**
	 * Merge Sort for Integer arrays with use of insertion sort and avoiding copying to temp array
	 * 
	 * Calls another method called mergeSort4, sending the received integer
	 * array, starting and ending index of the integer
	 * array
	 * 
	 * @param : int[] : arr integer array to be sorted
	 * 
	 */
	static void mergeSort4(int[] arr){
		if(arr==null)
			return;
		int[] temp = arr;
		mergeSort4(arr,temp,0,arr.length-1);
	}
	
	/**
	 * Recursive Merge Sort for Integer arrays
	 * 
	 * If the array contains more than one element, it splits it in half
	 * and recursively calls mergeSort4 function for either half. Once sorted,
	 * it calls the merge4 function to obtain the complete sorted array
	 * 
	 * @param arr  : int[] : integer array to be sorted
	 * @param tmp  : int[] : temporary integer array that is used for sorting
	 * @param low  : int : smallest index of the section of the array to be sorted
	 * @param high : int : biggest index of the section of the array to be sorted 
	 * 
	 */
	static void mergeSort4(int[] arr, int[] temp,int low, int high){
		
		if((high-low)<17)
			InsertionSort1.insertionSort(arr,low,high);
		
		else{
			
			int mid = (low+high)/2;
			mergeSort4(temp, arr, low, mid);
			mergeSort4(temp, arr, mid+1, high);
			merge4(temp, arr, low, mid, high);
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
	 * @param tmp  : int[] : temporary integer array that is used for sorting
	 * @param low  : int : smallest index of the sorted sub-array
	 * @param mid  : int : biggest index of the sorted sub-array
	 * @param high : int : biggest index of the sorted sub-array
	 * 
	 */
	static void merge4(int[] arr, int[] temp, int low, int mid, int high){
	
		int i = low, j = mid +1;
		
		for(int k = low;k<=high;k++){
			if((j>high) || ((i<=mid) && (arr[i]<=arr[j])))
				temp[k] = arr[i++];
			else
				temp[k] = arr[j++];
		}
		
	}
	
}
