import java.util.Random;

public class DualPivotPartition{
  
  public static void DualPivotQS(T[] A, int p, int r){
    if(p > r) return;
    int x[] = Partition(A, p, r);
    int k,i,j;
    k = x[0]; i = x[1]; j = x[2];
    DualPivotQS(A,p+1,k-1);
    DualPivotQS(A,j+1,r-1);
    if(A[k-1] != A[j+1])
      DualPivotQS(A, j, i-1);
  }
  
  public static int[] Partition(T[] A, int p, int r){
    Random r = new Random(7);
    int x1,x2;
    x1 = p + r.nextInt(A.length-1);
    x2 = p + r.nextInt(A.length-1);
    int k,i,j;
    k = p+1; i=p+1; j=r-1;
    swap(A,x1,p);
    swap(A,x2,r);
    while(i < j){
      if(A[i].compareTo(A[p]) >= 0 && A[i].compareTo(A[r]) <= 0)
        i++;
      else if(A[i].compareTo(A[p]) < 0){
        swap(A, i, k);
        i++;
      }
      else if(A[j].compareTo(A[r]) > 0)
        j--;
      else if(A[i].compareTo(A[r]) > 0 && A[j].compareTo(A[p]) < 0){
        circleSwap(A, k, i, j);
        k++; i++; j--;
      }
      else if(A[i].compareTo(A[r]) > 0 && A[j].compareTo(A[p]) >=0 && A[j].compareTo(A[r]) <= 0){
        swap(A, i, j);
        i++;
        j--;
      }
    }
    swap(A, p, k-1);
    swap(A, j+1, r);
    int[] ans = {k , i, j};
    return ans;
    
  }
  
  static void swap(T[] A,int p1, int p2){
    if(p1 != p2){
      T temp = A[p1];
      A[p1] = A[p2];
      A[p2] = temp;
    }
  }
  
  static void circleSwap(T[] A, int x, int y, int z){
    T temp = A[y];
    A[y] = x;
    A[x] = z;
    A[z] = temp;
  }

}
