package cs6301.g21;

import java.util.Comparator;

public class VertexDistance  implements Comparator<XGraph.XVertex>{

    @Override
    public int compare(XGraph.XVertex o1, XGraph.XVertex o2) {
        if(o1.getDis() == o2.getDis()) return 0;
        else if(o1.getDis() < o2.getDis()) return -1;
        return 1;
    }
}
