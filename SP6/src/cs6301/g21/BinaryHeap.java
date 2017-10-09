// Ver 1.0:  Starter code for bounded size Binary Heap implementation

package cs6301.g21;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    T[] pq;
    Comparator<T> comp;
    int size=0;
    /** Build a priority queue with a given array q, using q[0..n-1].
     *  It is not necessary that n == q.length.
     *  Extra space available can be used to add new elements.
     */
    public BinaryHeap(T[] q, Comparator<T> comp, int n) {
        pq = q;
        this.comp = comp;
        size = n;
        buildHeap();
    }

    int parent(int i){
        return (i-1)/2;
    }

    public void insert(T x) {
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x) { /* TO DO. Throw exception if q is full. */
        if(pq.length == size)
            throw new ArrayIndexOutOfBoundsException("Heap is full");
        else{
            pq[size] = x;
            percolateUp(size);
            size++;
        }
    }

    public T remove() { /* TO DO. Throw exception if q is empty. */
        if(size == 0)
            throw new NoSuchElementException("Heap is empty");
        else{
            T minEle = pq[0];
            pq[0] = pq[--size];
            percolateDown(0);
            return minEle;
        }
    }

    public T peek() { /* TO DO. Throw exception if q is empty. */
        if(size == 0)
            throw new NoSuchElementException("Heap is empty");
        else
            return pq[0];

    }

    public void replace(T x) {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing. 
	   This operation is used in finding largest k elements in a stream.
	 */
	    if(comp.compare(x,pq[0]) < 0){

        }
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { /* to be implemented */
        T x = pq[i];
        while(i > 0 && comp.compare(x,pq[parent(i)]) < 0){
            pq[i] = pq[parent(i)];
            i = parent(i);
        }
        pq[i] = x;
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { /* to be implemented */
        T x = pq[i];
        int c = 2*i + 1;
        while(c <= size-1){
            if(c < size - 1 && comp.compare(pq[c],pq[c+1]) > 0)
                c++;
            if(comp.compare(x,pq[c]) <= 0)
                return;
            pq[i] = pq[c];
            i = c;
            c = 2*i + 1;
        }
        pq[i] = x;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
        if(size > 0){
            for(int i = ((size+1)/2)-1; i >= 0; i-- ){
                percolateDown(i);
            }
        }
    }

    /* sort array A[].
       Sorted order depends on comparator used to build heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    public static<T> void heapSort(T[] A, Comparator<T> comp) {
        BinaryHeap<T> bheap = new BinaryHeap<>(A, comp, A.length);
        
        for(int i = A.length-1; i>=0; i--){
            A[i] = bheap.remove();
        }
    }
}
