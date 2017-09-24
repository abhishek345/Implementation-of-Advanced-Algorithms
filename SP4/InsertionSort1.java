package cs6301.g21;

public class InsertionSort1 {

	static void insertionSort(int[] arr, int low, int high){

        int tmp;

        for(int i=low; i<=high; i++){

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
