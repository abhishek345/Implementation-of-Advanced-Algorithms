package cs6301.g21;

import java.util.Comparator;
import java.util.HashMap;

public class ValueSort<K, V extends Comparable<V>> implements Comparator<K> {
    HashMap<K, V> counts;
    public ValueSort(HashMap<K, V> counts){
        this.counts = counts;
    }

    @Override
    public int compare(K o, K t1) {
        if(counts.get(o).compareTo(counts.get(t1)) >= 0)
            return -1;
        return 1;
    }
}
