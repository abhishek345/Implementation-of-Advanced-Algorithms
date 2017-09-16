package cs6301.g21;

import java.util.Iterator;

import cs6301.g21.Graph.Vertex;

/**
 * Extended version of the Graph class. This class contains the graph
 * and other details of the vertices in the class. 
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
 * @version 1.0: 2017/09/13
 *
 */

public class GraphExtended{
	
	Graph g;
	boolean seen[];
	int dis[];
	int fin[];
	int vCno[];
	int top[];
	int parent[];
	int inDegree[];
	static int cno;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param g : graph that is being extended
	 */
	public GraphExtended(Graph g) {
		this.g= g;
		seen= new boolean[g.size()];
		dis = new int[g.size()];
		fin = new int[g.size()];
		vCno = new int[g.size()];
		top = new int[g.size()];
		parent = new int[g.size()];
		inDegree= new int[g.size()];
		
	}
	
	/*sets the boolean value of the seen parameter of the vertex*/
	public void setSeen(int index,boolean value){
		seen[index]=value;
	}
	
	/*return the boolean value of seen parameter of a vertex*/
	public boolean getSeen(int index){
		return seen[index];
	}
	
	/*sets the discovery time of a vertex*/
	public void setDis(int index,int value){
		dis[index]=value;
	}
	
	/*returns the discovery time of a vertex*/
	public int getDis(int index){
		return dis[index];
	}
	
	/*sets the finish time of a vertex*/
	public void setFin(int index,int value){
		fin[index]=value;
	}
	
	/*returns the finish time of a vertex*/
	public int getFin(int index){
		return fin[index];
	}
	
	/*sets the component number of a vertex*/
	public void setVCno(int index,int value){
		vCno[index]=value;
	}
	
	/*returns the component number of a vertex*/
	public int getVCno(int index){
		return vCno[index];
	}
	
	/*sets the topological number of a vertex*/
	public void setTop(int index,int value){
		top[index]=value;
	}
	
	/*returns the topological number of a vertex*/
	public int getTop(int index){
		return top[index];
	}
	
	/*sets the parent of a vertex*/
	public void setParent(int index,int value){
		parent[index]=value;
	}
	
	/*returns the parent of a vertex*/
	public int getParent(int index){
		return parent[index];
	}
	
	/*sets the in degree of a vertex*/
	public void setInDegree(int index,int value){
		inDegree[index]=value;
	}
	
	/*returns the in degree of a vertex*/
	public int getInDegree(int index){
		return inDegree[index];
	}
	
	/*returns the number of vertices in the graph*/
	public int size(){
		return g.size();
	}
	
	/*returns the iterator for vertices of the graph*/
	public Iterator<Vertex> iterator() {
		return g.iterator();
	}
	 
	/*returns the vertex of the provided index*/
	public Graph.Vertex getVertex(int index){
		return g.getVertex(index);
	}
	 
}
