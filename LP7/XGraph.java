package cs6301.g21;

import java.util.*;
import cs6301.g00.*;
import cs6301.g00.Graph.Vertex;
import cs6301.g21.XGraph.XVertex;

/**
 * Extended version of Graph
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/11/18
 *
 */

public class XGraph extends Graph {

	/**
	 * Vertex class of XGraph
	 */
    public static class XVertex extends Vertex{

    	int excess;
    	int height;
    	List<XEdge> xadj, xrevAdj;
    	
    	boolean seen;
    	int dis;
        XVertex parent;

        /**
         * Constructor of XVertex
         * @param u : Vertex of Graph
         */
        XVertex(Vertex u) {
            super(u);
            xadj = new LinkedList<>();
            xrevAdj = new LinkedList<>();
        }
        
        /*Sets the seen attribute*/
        void setSeen(boolean b){ seen = b; }
        
        /*Gets the seen attribute*/
        boolean getSeen(){ return seen; }

        /*Sets the parent attribute*/
        void setParent(XVertex p){ parent = p; }
        
        /*Gets the parent attribute*/
        XVertex getParent(){ return parent; }

        /*Sets the distance attribute*/
        void setDis(int b){ dis = b; }
        
        /*Gets the distance attribute*/
        int getDis(){ return dis; }
        
        /*Gets the excess attribute*/
        public int getExcess() {
			return excess;
		}

        /*Sets the excess attribute*/
		public void setExcess(int excess) {
			this.excess = excess;
		}

		/*Gets the height attribute*/
		public int getHeight() {
			return height;
		}

		/*Sets the height attribute*/
		public void setHeight(int height) {
			this.height = height;
		}
		
		/*Iterator to access adjacent edges of vertex */
		@Override
        public Iterator<Edge> iterator() { return new XVertexIterator(this,true); }
		
		/*Iterator to access reverse adjacent edges of vertex */
		@Override
		public Iterator<Edge> reverseIterator() { return new XVertexIterator(this,false); }
        
		/*Class extending Iterator interface*/
        class XVertexIterator implements Iterator<Edge>{
            XEdge cur;
            Iterator<XEdge> it;
            boolean ready;

            /*Constructor of class*/
            XVertexIterator(XVertex u,boolean flag) {
            	if(flag)
            		this.it = u.xadj.iterator();
            	else
            		this.it = u.xrevAdj.iterator();
	            ready = false;
            
            }
            
            /*Overridden hasNext()*/
            public boolean hasNext() {
	            if(ready) { return true; }
	            if(!it.hasNext()) { return false; }
	            cur = it.next();
	            boolean check=false;
	            if(dinitz)
	            	check=(cur.resCapacity==0);
	            while(check && it.hasNext()) {
	                cur = it.next();
	                check=(cur.resCapacity==0);
	            }
	            
	            ready = true;
	            if(dinitz)
	            	return !(cur.resCapacity==0);
	            else
	            	return true;
            }

            /*Over ridden next()*/
            public XEdge next() {
	            if(!ready) {
	                if(!hasNext()) {
	                throw new java.util.NoSuchElementException();
	                }
	            }
	            ready = false;
	            return cur;
            }

            public void remove() {
            	throw new java.lang.UnsupportedOperationException();
            }
        }
    }

    static class XEdge extends Edge {
	int resCapacity;

	XEdge(XVertex from, XVertex to, int weight) {
	    super(from, to, weight);
	    resCapacity=1;
	    
	}
	
	public void setResCapacity(int weight){
		this.resCapacity=weight;
	}
	
	public int getResCapacity(){
		return resCapacity;
	}
	
    }

    XVertex[] xv; // vertices of graph
    HashMap<XEdge,Edge> map;
    HashMap<Edge,XEdge> revMap;
    HashMap<XEdge,XEdge> oppMap;
    static boolean dinitz;

    public XGraph(Graph g) {
    	super(g);
        xv = new XVertex[g.size()];  // Extra space is allocated in array for nodes to be added later
            for(Vertex u: g) {
                xv[u.getName()] = new XVertex(u);
            }
            
        map= new HashMap<XEdge,Edge>();
        revMap= new HashMap<Edge,XEdge>();
        oppMap= new HashMap<XEdge,XEdge>();
        // Make copy of edges
        if(!dinitz){
        for(Vertex u: g) {
            for(Edge e: u) {
            Vertex v = e.otherEnd(u);
            XVertex x1 = getVertex(u);
            XVertex x2 = getVertex(v);
            XEdge xedge = new XEdge(x1, x2, e.getWeight());
            map.put(xedge,e);
            revMap.put(e,xedge);
            x1.xadj.add(xedge);
            x2.xrevAdj.add(xedge);
            }
        }
        }
        else{
        	for(Vertex u: g) {
                for(Edge e: u) {
                Vertex v = e.otherEnd(u);
                XVertex x1 = getVertex(u);
                XVertex x2 = getVertex(v);
                XEdge xedge = new XEdge(x1, x2, e.getWeight());
                
                XEdge xrevedge = new XEdge(x2, x1, 0);
                x1.xadj.add(xedge);
                x2.xadj.add(xrevedge);
                x2.xrevAdj.add(xedge);
                x1.xrevAdj.add(xrevedge);
                
                map.put(xedge,e);
                map.put(xrevedge, e);
                revMap.put(e,xedge);
                oppMap.put(xedge, xrevedge);
                }
            }
        }
        	
    }
    
    public static void setDinitz(boolean val){
    	dinitz=val;
    }
    public Edge getEdge(XEdge xe){
    	return map.get(xe);
    }
    
    public Edge getXEdge(Edge e){
    	return revMap.get(e);
    }

    public Edge getOppXEdge(XEdge e){
    	return oppMap.get(e);
    }

    public Iterator<Vertex> iterator() {
    	return new ArrayIterator<Vertex>(xv);
    }
    
    @Override
    public XVertex getVertex(int n) {
        return xv[n-1];
    }

    XVertex getVertex(Vertex u) {
	return Vertex.getVertex(xv, u);
    }

    /** Method to reverse the edges of a graph.  Applicable to directed graphs only. */
    public void reverseXGraph() {
        	if(isDirected()) {
            		Iterator i = iterator();
            	    while(i.hasNext()) {
                	    	XVertex u= (XVertex) i.next();
                	    	List<XEdge> tmp = u.xadj;
                	    	u.xadj = u.xrevAdj;
                	    	u.xrevAdj = tmp;
                	    }
           	}
    }
}