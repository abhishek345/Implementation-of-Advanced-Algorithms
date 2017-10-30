package cs6301.g21;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g21.Graph.Edge;
import cs6301.g21.Graph.Vertex;
//import cs6301.g21.GraphExtended.GEVertex;


/**
 * Implementation of Extended version of Graph
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */
public class GraphExtended {
	int n;
	
	/**
	 * Graph Vertex Class
	 *
	 */
	class GEVertex{
		Graph.Vertex element;
		Graph.Vertex parent;
		boolean seen;
		int dist;
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
			this.adj = u.adj;
		}	
		
		/*gets the adjacent edges of the variable*/
		public List<Edge> getAdj(){
			return this.adj;
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
		
		/*Gets the seen attribute of the variable*/
		public boolean isSeen(){
			return this.seen;
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
	public GraphExtended(Graph g){
		this.g=g;
		geVertex=new GEVertex[g.size()];
		for(Graph.Vertex u: g){
			geVertex[u.name]=new GEVertex(u);
		}
	}
	
	public int getNumOfEdges(){
		return g.m;
	}
	
	//populate edges to get edges array
    public static Comparator<Edge> edgeComparator = new Comparator<Edge>(){

        public int compare(Graph.Edge o1, Graph.Edge o2) {
            if(o1.getWeight() > o2.getWeight())
                return 1;
            else if(o1.getWeight() < o2.getWeight())
                return -1;

            return 0;
        }

    };
	
	/*Sets the seen attribute of the variable*/
	public void setSeen(Graph.Vertex u, boolean seen){
		geVertex[u.getName()].setSeen(seen);
	}
	
	/*Gets the seen attribute of the variable*/
	public boolean isSeen(Graph.Vertex u){
		return geVertex[u.getName()].isSeen();
	}
	
	/*Sets the parent attribute of the variable*/
	public void setParent(Graph.Vertex u, Graph.Vertex p){
		geVertex[u.getName()].setParent(p);
	}
	
	/*Sets the distance attribute of the variable*/
	public void setDist(Graph.Vertex u, int d){
		geVertex[u.getName()].setDist(d);
	}
	
	/*Gets the list of edges out of the variable*/
	public List<Edge> getAdj(Graph.Vertex u){
		return geVertex[u.getName()].getAdj();
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
	public GraphExtended.GEVertex getGEVertex(Vertex tempV) {

		return geVertex[tempV.getName()];
	}
	
}
