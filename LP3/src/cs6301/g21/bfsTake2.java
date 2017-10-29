/** Breadth-first search
 *  originally by @author rbk
 *  Modified to run on XGraph and return edges
 *
 */

package cs6301.g21;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class bfsTake2 {
    public static final int INFINITY = Integer.MAX_VALUE;
    private static XGraph.XVertex src;
    private static XGraph ge;


    // reinitialize allows running bfsTake2 many times, with different sources
    static void reinitialize(XGraph xGraph, XGraph.XVertex newSource) {
        src = newSource;
        ge = xGraph;

        XGraph.XGraphIterator it = (XGraph.XGraphIterator) ge.iterator();
        while (it.hasNext()){
            XGraph.XVertex xcur = (XGraph.XVertex) it.next();
            xcur.seen = false;
            xcur.parent = null;
            xcur.dis = INFINITY;
        }

        ge.getVertex(src).dis = 0;
    }

    /**
     * Perfrms BFS on XGraph ge from given root
     * @param ge : XGraph on which BFS is to be performed
     * @param newSource : root node to start BFS
     * @param zeroWeight : true if need to do BFS on zero weight edges, false otherwise
     * @param numComp : true if component number of the edges need to be checked
     * @return List<XGraph.XEdge> : List of edges in the order or BFS
     */
    public static List<XGraph.XEdge> BFS(XGraph ge, XGraph.XVertex newSource, boolean zeroWeight, boolean numComp) {

        //reinitialize the root node and the vertices of XGraph to be not seen
        reinitialize(ge, newSource);

        Queue<XGraph.XVertex> q = new LinkedList<>();
        List<XGraph.XEdge> bfsEdges = new LinkedList<>();
        q.add(src);

        while(!q.isEmpty()) {
            XGraph.XVertex u = q.remove();
            Iterator<Graph.Edge> edgeIterator = u.iterator();

            XGraph.XEdge x;
            if(!edgeIterator.hasNext())
                continue;
            else {
                x = (XGraph.XEdge) edgeIterator.next();

                //do not consider the edge if zeroWeight is true but not a zeroWeight edge
                if(zeroWeight == true && x.getWeight() != 0){
                    continue;
                }

                //if we have to check number of components, continue
                // incase that check isn't satisfied
                XGraph.XVertex v = (XGraph.XVertex) x.otherEnd(u);
                if(numComp == true && (v.getVCno() != u.getVCno())){
                    continue;
                }

                //only add unseen vertices and the edges
                if (!v.getSeen()) {
                    visit(u, v);
                    q.add(v);
                    bfsEdges.add(x);
                }

            }
        }

        return bfsEdges;
    }

    /**
     * Set the node's seen, distance and parent
     * @param u : To vertex of the edge
     * @param v : From vertex of the edge
     */
    static void visit(XGraph.XVertex u, XGraph.XVertex v) {
        v.setSeen(true);
        v.setParent(u);
        v.dis = u.dis+1;
    }
}