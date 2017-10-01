package cs6301.g21;

import java.util.Random;

public class DualPivotPartition{
  static Random rd;

  public static void swap(int[] A, int x, int y){
    int tmp = A[x];
    A[x] = A[y];
    A[y] = tmp;
  }

  public static void circleSwap(int[] A,  int x, int y, int z){
    int tmp = A[y];
    A[y] = A[x];
    A[x] = A[z];
    A[z] = tmp;
  }

  public static void quickSort(int[] A, int p,int r){
    System.out.println("qs: " + p + " , "+r);


    if(p >= r) return;
    if(r-p < 2){
      InsertionSort.nSquareSort(A,p,r);
      return;
    }
    rd = new Random();
    int ans[] = partition(A, p, r);
    int x1 = ans[0];
    int x2 = ans[1];
    int i = ans[2];
    int j = ans[3];
    int k = ans[4];
    // System.out.println("x1 x2 i j k : ");
    // for(int x: ans)
    //   System.out.print(" > " +x);
    // System.out.println();
    quickSort(A, p, k-1);
    quickSort(A, j+1, r);
    // if(x1 != x2){
    quickSort(A, k+1, j-1);
    // }
    // System.out.println("printing array: ");
    // for(int a: A)
    //   System.out.println(a);
  }

  public static int[] partition(int[] A, int p, int r){
    int p1,p2;
    p1 = rd.nextInt(r-1) + p;
    p2 = rd.nextInt(r-1) + p;
    if(A[p1] > A[p2]){
      int tmp = p1;
      p1 = p2;
      p2 = tmp;
    }
    System.out.println(" pivots = " + p1 + " , " + p2);
    swap(A, p1, p);
    swap(A, p2, r);
    int x1 = A[p];
    int x2 = A[r];
    int i = p+1; int k = p+1; int j = r-1;
    while(i <= j){
      if(A[i] < x1){
        swap(A, i , k); k++;
      }
      else if(A[i] >= x2){
        while(A[j] > x2 && i < j) j--;
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

    swap(A, p, k);
    swap(A, r, j);
    int ans[] = {x1, x2, i, j, k};
    return ans;
  }

  public static void main(String[] args) {
    int arr[] = {6, 5, 7, 3, 1, 8, 2, 9};
    quickSort(arr, 0, arr.length-1);
    //swap(arr, 0, 3);
    System.out.println("printing array: ");
    for(int a: arr)
      System.out.println(a);
  }
}
