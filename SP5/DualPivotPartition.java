package cs6301.g21;

/**Implementation of Quicksort with Dual Pivot Parition
 * @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Vishwanath Rao
 * @version 1.0 : 09/30/2017
 *
 */

public class DualPivotPartition{
  /*
  public static void main(String[] args) {
    // int arr[] = {6, 5, 7, 3, 1, 8, 2, 9};
    // dualPivotQS(arr, 0, arr.length-1);
    // System.out.println("printing array: ");
    // for(int a: arr)
    //   System.out.println(a);
    // NEEDS A DRIVER
  }*/

  /** swaps value at 2 indices in an array
   *
   * @param A Integer Array
   * @param x index 1
   * @param y index 2
   */
  public static void swap(int[] A, int x, int y){
    int tmp = A[x];
    A[x] = A[y];
    A[y] = tmp;
  }

  /** sort an array using quicksort with dual pivot partition
   *  using insertion sort for size below 18
   *  reference : DualPivotQuicksort by Yaroslavskiy
   *  http://codeblab.com/wp-content/uploads/2009/09/DualPivotQuicksort.pdf
   *
   * @param A Integer Array
   * @param p start index
   * @param r end index
   */
  public static void dualPivotQS(int[] A, int p, int r){
    if(p >= r) return;
    if(r-p < 18){
      InsertionSort.nSquareSort(A, p, r);
      return;
    }
    if(A[p] > A[r])
      swap(A,p,r);
    int x1 = A[p]; int x2 = A[r];
    int i = p+1; int k = p+1; int j = r-1;

    while(i <= j){
      if(A[i] < x1){
        swap(A, i , k); k++;
      }
      else if(A[i] > x2){
        while(i < j && A[j] > x2) j--;
        swap(A, i, j);
        j--;
        if(A[i] < x1){
          swap(A, i , k); k++;
        }
      }
      i++;
    }
    k--;
    j++;
    swap(A, p, k); swap(A, r, j);

    dualPivotQS(A, p, k-1);
    if(x1 != x2) dualPivotQS(A, k+1, j-1);
    dualPivotQS(A, j+1, r);
  }


}
