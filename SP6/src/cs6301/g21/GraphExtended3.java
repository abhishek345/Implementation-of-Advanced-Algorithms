package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g21.Graph.Edge;
import cs6301.g21.Graph.Vertex;
import cs6301.g21.GraphExtended2.GEVertex;


/**
 * Implementation of Extended version of Graph
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */
public class GraphExtended3 {
	int n;
	
	/**
	 * Graph Vertex Class
	 *
	 */
	class GEVertex implements Index{
		Graph.Vertex element;
		Graph.Vertex parent;
		boolean seen;
		int dist;
		public int index;
		List<Edge> adj;
		
		/**
		 * Graph Vertex Constructor
		 * @param u : Vertex of Graph
		 */
		GEVertex(Graph.Vertex u){
			this.element= u;
			this.seen= false;
			this.parent= null;
			this.dist= Integer.MAX_VALUE;
			this.index = 0;
			this.adj = new LinkedList<Edge>();
		}	
		
		/*Sets the seen attribute of the variable*/
		public void setSeen(boolean seen){
			this.seen=seen;
		}
		
		/*Sets the parent attribute of the variable*/
		public void setParent(Graph.Vertex p){
			this.parent=p;
		}
		
		/*Sets the distance attribute of the variable*/
		public void setDist(int d){
			this.dist=d;
		}
		
//		/*Sets the index attribute of the variable*/
//		public void setIndex(int n){
//			this.index=n;
//		}
//		
		/*Sets the parent attribute of the variable*/
		public int getIndex() { // To Do
			return this.index;
		}

		/*sets the index attribute of the variable*/
		public void putIndex(int index) { // To Do
			this.index = index;
		}
		
		/*gets the element attribute of the variable*/
		public Graph.Vertex getElement(){
			return element;
		}
		
	}
	
	static Graph g;
	static GEVertex[] geVertex;
	
	/**
	 * Constructor
	 * @param g : Input graph
	 */
	public GraphExtended3(Graph g){
		this.g=g;
		geVertex=new GEVertex[g.size()];
		for(Graph.Vertex u: g){
			geVertex[u.name]=new GEVertex(u);
		}
	}
	
	/*Sets the seen attribute of the variable*/
	public void setSeen(Graph.Vertex u, boolean seen){
		geVertex[u.getName()].setSeen(seen);
	}
	
	/*Sets the parent attribute of the variable*/
	public void setParent(Graph.Vertex u, Graph.Vertex p){
		geVertex[u.getName()].setParent(p);
	}
	
	/*Sets the distance attribute of the variable*/
	public void setDist(Graph.Vertex u, int d){
		geVertex[u.getName()].setDist(d);
	}
	
	/*Sets the index attribute of the variable*/
	public void setIndex(Graph.Vertex u, int n){
		geVertex[u.getName()].putIndex(n);
	}
	
	/*returns the set of vertices of the graph*/
	public Iterator iterator(){
		return g.iterator();
	}
	
	/*returns the number of vertices of the graph*/
	public int size() {
		return g.size();
	    }

	/*returns the GEVertex of the the provided vertex*/
	public GraphExtended3.GEVertex getGEVertex(Vertex tempV) {

		return geVertex[tempV.getName()];
	}
	
}
