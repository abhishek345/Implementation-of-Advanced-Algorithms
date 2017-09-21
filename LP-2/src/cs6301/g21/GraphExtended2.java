package cs6301.g21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphExtended2 {
	
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
		
		
		
		public void setIteratorSet(boolean value){
			iteratorSet=value;
		}
		
		public boolean getIteratorSet(){
			return iteratorSet;
		}
		 
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
		
		
		public Graph.Edge next(){
			if(hasNext()){
				return (Graph.Edge)outList.next(); 
			}
			return null;
		}
		
		public void setElement(Graph.Vertex u){
			this.element=u;
		}
		public Graph.Vertex getElement(){
			return element;
		}
		
		public List<Graph.Edge> getSubTour(){
			return subTour;
		}
		
		public void setSubTour(List<Graph.Edge> T){
			subTour = T;
		}
	}
	
	GEVertex geVertex[];
	Graph g;
	
	public GraphExtended2(Graph g){
		this.g=g;
		geVertex=new GEVertex[g.size()];
		for(Graph.Vertex u: g){
			geVertex[u.name]=new GEVertex(u);
		}
	}
	
	public boolean hasNext(Graph.Vertex u){
		return geVertex[u.getName()].hasNext();
	}
	
	public Graph.Edge next(Graph.Vertex u){
		return geVertex[u.getName()].next();
	}

	public Graph.Vertex getVertex(int index){
		return geVertex[index].element;
	}
	
	public GEVertex getGEVertex(int index){
		return geVertex[index-1];
	}
	
	public Iterator iterator(){
		return g.iterator();
	}
}
