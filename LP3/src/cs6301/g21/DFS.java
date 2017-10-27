package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

/**
  *
  * Depth First Search Implementation
  *
  * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
  * @version 1.0: 2017/09/13
  * @version 2.0: 2017/10/23 Uses XGraph instead of GraphExtended
  *
  */

public class DFS {
    private static int time = 0;
    private static int topNum = 0;
    private static boolean checkCycles = true;
    private static LinkedList<XGraph.XVertex> decFinList;

    public static void setCycleChecking(boolean b){
        checkCycles = b;
    }

    /**
     * Depth First Search Higher level Function Call
     *
     * @param it : order in which DFS must be implemented
     * @param ge : Graph extended object containing the graph
     * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
     */
    public static LinkedList<XGraph.XVertex> DFSCall(Iterator it, XGraph ge)throws CyclicGraphException{
        return DFSGraph(it, ge);
    }

    /**
     * Depth First Search Higher level Function Call
     *
     * @param ge : Graph extended object containing the graph
     * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
     */
    public static LinkedList<XGraph.XVertex> DFSCall(XGraph ge)throws CyclicGraphException{
        return DFSGraph(ge.iterator(), ge);
    }

    /**
      * Depth First Search Function Call
      *
      * @param it : order in which DFS must be implemented
      * @param ge : Graph extended object containing the graph
      * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
      */
    public static LinkedList<XGraph.XVertex> DFSGraph(Iterator it, XGraph ge)throws CyclicGraphException{
        topNum=ge.size();
        time = 0;
        ge.cno = 0;
        decFinList= new LinkedList<>();

        for(int i=0;i<ge.size();i++){
            XGraph.XVertex temp = (XGraph.XVertex) ge.getVertex(i+1);
            temp.setSeen(false);
            temp.setParent(null);
        }
        while(it.hasNext()){
            XGraph.XVertex u= (XGraph.XVertex)it.next();
            if(!u.getSeen()){
                ge.cno++;
                DFSVisit(ge, u);
            }
        }
        return decFinList;
    }

    /**
     * Traverses through all the adjacent unvisited vertices of the vertex
	 * sent to the function.
     * Stores the topological number, discovery and finish time of the vertex.
     * It add the vertex to the finish order list once all its neighbors
     * have been visited.
     *
     * @param u : the vertex whose neighbors have to be traversed through
	 * @param ge :GraphExtended object containing the graph and other details of the vertex
	 */
    public static void DFSVisit(XGraph ge, XGraph.XVertex u)throws CyclicGraphException{
        u.setSeen(true);
        u.setDis(++time);
        u.setVCno(ge.cno);

        Iterator adjEdges = u.iterator();

        while(adjEdges.hasNext()){
            XGraph.XEdge e = (XGraph.XEdge)adjEdges.next();
            XGraph.XVertex v = (XGraph.XVertex) e.otherEnd(u);
            if(!v.getSeen()){
                v.setParent(u);
                DFSVisit(ge, v);
            }
            else if(checkCycles && (ge.isDirected() && v.getSeen())){
                XGraph.XVertex a = u.getParent();
                while(a != null){
                    if(a.getName() == v.getName())
                        throw new CyclicGraphException("Cycle Found");
                    a = a.getParent();
                }
            }
        }
        u.setFin(++time);
        u.setTop(topNum--);
        decFinList.addFirst(u); //supposed to be addFirst
    }

}
