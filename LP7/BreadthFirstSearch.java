
package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs6301.g00.Graph;
import cs6301.g21.XGraph.XEdge;
import cs6301.g21.XGraph.XVertex;

/**
 * Breadth First Search Implementation
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/11/18
 *
 */

public class BreadthFirstSearch {
    static int VERBOSE = 0;
    public static final int INFINITY = Integer.MAX_VALUE;
    private static XVertex src;
    private static XGraph ge;
    private static boolean isPath=false;


    /**
     * Reinitialize allows running BFS many times, with different sources
     * @param xg : XGraph
     * @param s :Source Vertex
     */
    static void reinitialize(XGraph xg, XVertex s) {
        src = s;
        ge = xg;

        Iterator it = ge.iterator();
        while (it.hasNext()){
            XVertex xcur = (XVertex) it.next();
            xcur.setSeen(false);
            xcur.setParent(null);
            xcur.setDis(INFINITY);
        }

        src.setDis(0);
        isPath=false;
    }

    /**
     * Performs BFS on XGraph ge from given root
     * @param ge : XGraph on which BFS is to be performed
     * @param newSource : root node to start BFS
     * @param zeroWeight : true if need to do BFS on zero weight edges, false otherwise
     * @param numComp : true if component number of the edges need to be checked
     * @return List<XGraph.XEdge> : List of edges in the order or BFS
     */
    public static boolean BFS(XGraph ge, XVertex s, XVertex t, int k) {

        //reinitialize the root node and the vertices of XGraph to be not seen
        reinitialize(ge, s);

        LinkedList<XVertex> q = new LinkedList<>();
        List<XEdge> bfsEdges = new LinkedList<>();
        q.add(src);

        while(!q.isEmpty()) {
            XVertex u = q.remove();
            Iterator<Graph.Edge> edgeIterator = u.iterator();

            XEdge x;
            while(edgeIterator.hasNext()){
                x = (XEdge) edgeIterator.next();
                if(x.resCapacity==0)
                	continue;

                Graph.Vertex o = (Graph.Vertex) x.otherEnd(u);
                XVertex v= ge.getVertex(o);

                if (!v.getSeen()) {
                	v.setSeen(true);
                    v.setParent(u);
                    q.add(v);
                    bfsEdges.add(x);
                    
                    if(v.equals(t)){
                    	isPath=true;
                    }
                    
                }
            }
            if(k==0)
            	return isPath;
            k--;

        }
        return isPath;
    }

}