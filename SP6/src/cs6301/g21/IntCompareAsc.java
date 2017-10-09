package cs6301.g21;

import java.util.Comparator;

class IntCompareAsc implements Comparator<Integer> {
    public int compare(Integer x1, Integer x2){
        if(x1 < x2) return 1;
        if(x1 == x2) return 0;
        return -1;
    }
}