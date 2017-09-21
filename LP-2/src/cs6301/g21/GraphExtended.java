package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that uses Graph class and creates parallel array of vertices to
 * store additional information of the vertices 
 * 
 * @author Abhishek Jagwani, Umang Shah, Shreya Vishwanath Rao, Vibha Belavadi
 * @version 1.0: 2017/09/20
 * 
 */
public class GraphExtended {

	/**
	 * Used to store additional information about a vertex*/
	class GEVertex{
		Graph.Vertex element;
		Iterator outList;
		boolean iteratorSet;
		List<Graph.Edge> subTour;
		public boolean tourStarted = false;

		GEVertex(Graph.Vertex u){
			element=u;
			iteratorSet=false;
			subTour= new LinkedList<Graph.Edge>();
			//subTour.add(element);
		}
		

		/*sets the value of iteratorSet variable*/
		public void setIteratorSet(boolean value){
			iteratorSet=value;
		}
		
		/*returns the iteratorSet value*/
		public boolean getIteratorSet(){
			return iteratorSet;
		}
		 
		
		/**
		 * checks if outList contains any more edges in it
		 * 
		 * @return true if outList has edges, false otherwise
		 * 
		 */
		public boolean hasNext(){
			if(outList!=null)
				return outList.hasNext();
			else{
				if(!iteratorSet){
					outList=element.iterator();
					setIteratorSet(true);
					return true;
				}
			}
			return false;
		}
		
		/**
		 * returns the next edge in the iterator
		 * 
		 * @return next edge if there hasNext() returns true,
		 * 			null if false
		 */
		public Graph.Edge next(){
			if(hasNext()){
				return (Graph.Edge)outList.next(); 
			}
			return null;
		}
		
		/*sets the value of element variable*/
		public void setElement(Graph.Vertex u){
			this.element=u;
		}
		
		/*gets the element value */
		public Graph.Vertex getElement(){
			return element;
		}
		
		/*gets the subTour of the vertex*/
		public List<Graph.Edge> getSubTour(){
			return subTour;
		}
		
		/*sets the subtour of the vertex*/
		public void setSubTour(List<Graph.Edge> T){
			subTour = T;
		}
	}
	
	/*Algorithm uses a parallel array for storing information about vertices */
	GEVertex geVertex[];
	Graph g;
	
	/*constructor of the class*/
	public GraphExtended(Graph g){
		this.g=g;
		geVertex=new GEVertex[g.size()];
		for(Graph.Vertex u: g){
			geVertex[u.name]=new GEVertex(u);
		}
	}
	
	/*checks if u's iterator has more edges*/
	public boolean hasNext(Graph.Vertex u){
		return geVertex[u.getName()].hasNext();
	}
	
	/*returns the next edge in u's iterator*/
	public Graph.Edge next(Graph.Vertex u){
		return geVertex[u.getName()].next();
	}

	/*rteurns the corresponding Graph vertex of index*/
	public Graph.Vertex getVertex(int index){
		return geVertex[index].element;
	}
	
	/*rteurns the corresponding geVertex of index*/
	public GEVertex getGEVertex(int index){
		return geVertex[index-1];
	}
	
	/*returns the iterator of the graph, a list of all vertices*/
	public Iterator iterator(){
		return g.iterator();
	}
}