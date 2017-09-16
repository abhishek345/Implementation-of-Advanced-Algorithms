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
	
	static int time;
	static int topNum;
	static LinkedList<Graph.Vertex> decFinList;

	/**
	 * Depth First Search implementation
	 * 
	 * @param it : order in which DFS must be implemented 
	 * @param ge : Graph extended object containing the graph
	 * @return   : LinkedList<Graph.Vertex> : decreasing finish order time of the vertices in the graph
	 */
	public static LinkedList<Graph.Vertex> DFS(Iterator it,GraphExtended ge){
		topNum=ge.size();
		time=0;
		ge.cno=0;
		decFinList= new LinkedList<Graph.Vertex>();

		for(int i=0;i<ge.size();i++)
			ge.setSeen(ge.getVertex(i),false);
		while(it.hasNext()){
			Graph.Vertex u= (Graph.Vertex)it.next();
			if(!ge.getSeen(u)){
				ge.cno++;
				DFSVisit(u,ge);
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
	public static void DFSVisit(Graph.Vertex u, GraphExtended ge){
		ge.setSeen(u,true);
		ge.setDis(u,++time);
		ge.setVCno(u,ge.cno);
		
		Iterator adjEdges = u.adj.iterator();
		
		while(adjEdges.hasNext()){
			Graph.Edge e= (Graph.Edge)adjEdges.next();
			Graph.Vertex v= e.otherEnd(u);
			if(!ge.getSeen(v)){
				ge.setParent(v,u);
				DFSVisit(v,ge);
			}
		}
		ge.setFin(u,++time);
		ge.setTop(u,topNum--);
		decFinList.addFirst(u);
		
	}
}
