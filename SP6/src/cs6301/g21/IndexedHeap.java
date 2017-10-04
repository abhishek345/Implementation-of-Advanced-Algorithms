package cs6301.g21;

import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {


    IndexedHeap(T[] q, Comparator<T> comp, int n) {
        super(q, comp, n);
    }

    void decreaseKey(T x) {
        percolateUp(x.getIndex());
    }

    void buildHeap() {
        super.buildHeap();
        updateIndices();

    }

    void percolateUp(int i) {
        super.percolateUp(i);
        updateIndices();
    }

    void percolateDown(int i) {
        super.percolateDown(i);
        updateIndices();
    }

    //update indices after any operation
    void updateIndices(){
        
        for (int j = 0; j <= size; j++) {
            T temp = pq[j];
            temp.putIndex(j);
        }
    }

}
