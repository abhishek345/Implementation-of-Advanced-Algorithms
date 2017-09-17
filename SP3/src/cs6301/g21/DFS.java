package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;

/**
  *
  * Depth First Search Implementation
  *
  * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
  * @version 1.0: 2017/09/13
  *
  */

public class DFS {
    private static int time = 0;
    private static int topNum = 0;
    private static boolean checkCycles = true;
    private static LinkedList<Graph.Vertex> decFinList;

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
    public static LinkedList<Graph.Vertex> DFSCall(Iterator it, GraphExtended ge)throws CyclicGraphException{
        return DFSGraph(it, ge);
    }

    /**
     * Depth First Search Higher level Function Call
     *
     * @param ge : Graph extended object containing the graph
     * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
     */
    public static LinkedList<Graph.Vertex> DFSCall(GraphExtended ge)throws CyclicGraphException{
        return DFSGraph(ge.iterator(), ge);
    }

    /**
      * Depth First Search Function Call
      *
      * @param it : order in which DFS must be implemented
      * @param ge : Graph extended object containing the graph
      * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
      */
    public static LinkedList<Graph.Vertex> DFSGraph(Iterator it, GraphExtended ge)throws CyclicGraphException{
        topNum=ge.size();
        time = 0;
        ge.cno = 0;
        decFinList= new LinkedList<>();

        for(int i=0;i<ge.size();i++){
            Graph.Vertex temp = ge.getVertex(i);
            ge.setSeen(temp, false);
            ge.setParent(temp, null);
        }
        while(it.hasNext()){
            Graph.Vertex u= (Graph.Vertex)it.next();
            if(!ge.getSeen(u)){
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
    public static void DFSVisit(GraphExtended ge, Graph.Vertex u)throws CyclicGraphException{
        ge.setSeen(u,true);
        ge.setDis(u,++time);
        ge.setVCno(u,ge.cno);

        Iterator adjEdges = u.adj.iterator();

        while(adjEdges.hasNext()){
            Graph.Edge e = (Graph.Edge)adjEdges.next();
            Graph.Vertex v = e.otherEnd(u);
            if(!ge.getSeen(v)){
                ge.setParent(v,u);
                DFSVisit(ge, v);
            }
            else if(checkCycles && (ge.isDirected() && ge.getSeen(v))){
                Graph.Vertex a = ge.getParent(u);
                while(a != null){
                    if(a.getName() == v.getName())
                        throw new CyclicGraphException("Cycle Found");
                    a = ge.getParent(a);
                }
            }
        }
        ge.setFin(u,++time);
        ge.setTop(u,topNum--);
        decFinList.addFirst(u); //supposed to be addFirst
    }

}
