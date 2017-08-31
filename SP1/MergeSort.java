package cs6301.g21;


/**
 * Implementation of Merge Sort on integer and generic arrays
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */

public class MergeSort {
	
		/**
		 * Merge Sort for Integer arrays
		 * 
		 * Calls another method called mergeSort, sending the received integer
		 * array, temporary array, starting and ending index of the integer
		 * array
		 * 
		 * @param : int[] : arr integer array to be sorted
		 * @param : int[] : tmp temporary integer array that is used for sorting
		 * 
		 */
		static void mergeSort(int[] arr,int[] tmp){
			if(arr==null)
				return;
			integerMergeSort(arr,tmp,0,arr.length-1);
		}
		
		
		/**
		 * Recursive Merge Sort for Integer arrays
		 * 
		 * If the array contains more than one element, it splits it in half
		 * and recursively calls mergeSort function for either half. Once sorted,
		 * it calls the merge function to obtain the complete sorted array
		 * 
		 * @param arr  : int[] : integer array to be sorted
		 * @param tmp  : int[] : temporary integer array that is used for sorting
		 * @param low  : int : smallest index of the section of the array to be sorted
		 * @param high : int : biggest index of the section of the array to be sorted 
		 * 
		 */
		static void integerMergeSort(int[] arr, int[] temp, int low, int high){
			if(low<high){
				int mid=(low+high)/2;
				
				integerMergeSort(arr,temp,low,mid);
				integerMergeSort(arr,temp,mid+1,high);
				integerMerge(arr,temp,low,mid,high);
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
		static void integerMerge(int[] arr, int[] temp, int low, int mid, int high){
			int index1,index2,mergeIndex;
			for(int pos=low;pos<=high;pos++){
				temp[pos]=arr[pos];
			}
			
			index1=low;
			index2=mid+1;
			mergeIndex=low; 
			while(index1<=mid && index2<=high){
				if(arr[index1]<=arr[index2]){
					temp[mergeIndex]=arr[index1];
					index1++;
				}
				else{
					temp[mergeIndex]=arr[index2];
					index2++;
				}
				mergeIndex++;
			}
			
			for(int pos=index1;pos<=mid;pos++){
				temp[mergeIndex]=arr[pos];
				mergeIndex++;
			}
			
			for(int pos=low;pos<=high;pos++){
				arr[pos]=temp[pos];
			}
		}

		/**
		 * Merge Sort for Generic arrays
		 * 
		 * Calls another method called mergeSort, sending the received array,
		 * temporary array, starting and ending index of the array
		 * 
		 * @param : T[] : arr array to be sorted
		 * @param : T[] : tmp temporary array that is used for sorting
		 * 
		 */
		static <T extends Comparable<? super T>> void mergeSort(T[] arr,T[] tmp){
			if(arr==null)
				return;
			genericMergeSort(arr,tmp,0,arr.length-1);
		}
		
		/**
		 * Recursive Merge Sort for Generic arrays
		 * 
		 * If the array contains more than one element, it splits it in half
		 * and recursively calls mergeSort function for either half. Once sorted,
		 * it calls the merge function to obtain the complete sorted array
		 * 
		 * @param arr  : T[] : array to be sorted
		 * @param tmp  : T[] : temporary array that is used for sorting
		 * @param low  : T : smallest index of the section of the array to be sorted
		 * @param high : T : biggest index of the section of the array to be sorted 
		 * 
		 */
		static <T extends Comparable<? super T>> void genericMergeSort(T[] arr, T[] temp, int low, int high){
			if(low<high){
				int mid=(low+high)/2;
				
				genericMergeSort(arr,temp,low,mid);
				genericMergeSort(arr,temp,mid+1,high);
				genericMerge(arr,temp,low,mid,high);
			}
		}
		
		/**
		 * Function to merge two sorted arrays
		 * 
		 * [doubtful]
		 * Copies the section of the array to be sorted into the temporary array.
		 * Uses it to replace the ordering of the elements in the array.
		 *
		 * Replaces the smallest index in the array with the smallest element
		 * of the two sorted sub array. Comparison is done using the
		 * compare to function defined in the parent class of type of array.
		 * Increments the smallest index values of that sub-array and the array
		 * Repeats the above procedure until all elements are sorted.
		 * 
		 * @param arr  : T[] : array to be sorted
		 * @param tmp  : T[] : temporary array that is used for sorting
		 * @param low  : T : smallest index of the sorted sub-array
		 * @param mid  : T : biggest index of the sorted sub-array
		 * @param high : T : biggest index of the sorted sub-array
		 * 
		 */
		static <T extends Comparable<? super T>> void genericMerge(T[] arr, T[] temp, int low, int mid, int high){
			for(int pos=low;pos<=high;pos++){
				temp[pos]=arr[pos];
			}
			
			int index1=low;
			int index2=mid+1;
			int mergeIndex=low; 
			while(index1<=mid && index2<=high){
				int cmp=arr[index1].compareTo(arr[index2]);
				if(cmp<=0){
					temp[mergeIndex]=arr[index1];
					index1++;
				}
				else{
					temp[mergeIndex]=arr[index2];
					index2++;
				}
				mergeIndex++;
			}
			
			for(int pos=index1;pos<=mid;pos++){
				temp[mergeIndex]=arr[pos];
				mergeIndex++;
			}
			
			for(int pos=low;pos<=high;pos++){
				arr[pos]=temp[pos];
			}
			
		}

}
