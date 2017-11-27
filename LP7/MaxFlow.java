package cs6301.g21;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;
import cs6301.g21.XGraph.XEdge;
import cs6301.g21.XGraph.XVertex;

/**
 * Implementation of Relabel to front and Dinitz algorithm
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/11/18
 *
 */
public class MaxFlow {

	/**
	 * Implements the Relabel to front algorithm
	 * 
	 * @param xg : XGraph provided
	 * @param source :Source vertex
	 * @param target : Target vertex
	 * @param oldcapacity : capacities of edges in Graph 
	 * @param flow : flow in edges of XGraph
	 * @return  max flow calculated by algorithm
	 */
	public int relabelToFront(XGraph xg, Vertex source, Vertex target, HashMap<Edge, Integer> oldcapacity,HashMap<Edge, Integer> flow){
		XVertex s= xg.getVertex(source);
		XVertex t= xg.getVertex(target);
		HashMap<Edge,Integer> capacity = new HashMap<Edge,Integer>();

		initialize(xg, s, flow,oldcapacity, capacity);

		LinkedList<XVertex> L = new LinkedList<XVertex>();
		for(Vertex o : xg){
			XVertex u= xg.getVertex(o);
			if(u!=s && u!=t )
				L.add(u);
		}
		
		boolean done = false;
		while(!done){
			
			Iterator it= L.iterator();
			done=true;
			XVertex u=null;
			while(it.hasNext()){
				u = (XVertex) it.next();
				if(u.getExcess()==0)
					continue;
				int oldHeight= u.getHeight();
				discharge(u, flow, capacity,xg);
				if(u.getHeight()!=oldHeight){
					done=false;
					break;
				}
			}
			if(!done){
				L.remove(u);
				L.addFirst(u);
			}
			
		}
		return t.getExcess();
	}
	
	/**
	 * Sets flow, capacities, excess and height values of each vertex of the XGraph
	 * 
	 * @param xg : XGraph
	 * @param s : Source vertex
	 * @param flow : flow in edges of XGraph
	 * @param oldcapacity : capacities of edges in Graph 
	 * @param capacity :capacities of edges in XGraph 
	 */
	private void initialize(XGraph xg, XVertex s, HashMap<Edge, Integer> flow, HashMap<Edge, Integer> oldcapacity, HashMap<Edge,Integer> capacity) {
		for(Vertex o:xg){
			XVertex u = xg.getVertex(o);
			u.setHeight(0);
			u.setExcess(0);
			Iterator i= u.iterator();
			while(i.hasNext()){
				XEdge e= (XEdge)i.next();
				flow.put(e,0);
				Edge val= xg.getEdge(e);
				capacity.put(e, oldcapacity.get(val));
				e.setResCapacity(oldcapacity.get(val));
			}
		}
		
		s.setHeight(xg.size());
		
		Iterator i = s.iterator();
		while(i.hasNext()){
			XEdge e = (XEdge) i.next();
			Vertex o = e.otherEnd(s);
			XVertex u = xg.getVertex(o);
			flow.put(e,capacity.get(e));
			s.setExcess(s.getExcess()-capacity.get(e));
			u.setExcess(u.getExcess()+capacity.get(e));
		}
		
	}
	
	/**
	 * Discharges the excess flow in the vertex to adjacent edges 
	 * 
	 * @param u : Vertex
	 * @param flow : flow in edges of XGraph
	 * @param capacity :capacities of edges in XGraph
	 * @param xg : XGraph
	 */
	void discharge(XVertex u,HashMap<Edge,Integer> flow, HashMap<Edge,Integer> capacity, XGraph xg){
		while(u.getExcess()>0){
			for(int c=0;c<2;c++){
				Iterator i;
				if(c==0){
				 i = u.iterator();}
				else{
					 i = u.reverseIterator();}
				
				while(i.hasNext()){
					XEdge e =(XEdge) i.next();
					Vertex o= e.otherEnd(u);
					XVertex v= xg.getVertex(o);
					if( inGr(u,e,flow,capacity) && (u.getHeight()==1+v.getHeight())){
						push(u,v,e,flow,capacity);
						if(u.getExcess()==0){
							return;
						}
					}
				}
			}
			relabel(u,xg);
		}
	}
	
	/**
	 * Ralabels the height of the vertex
	 * 
	 * @param u : Vertex
	 * @param xg : XGraph
	 */
	void relabel(XVertex u,XGraph xg){
		int minHeight=Integer.MAX_VALUE;
		boolean done=false;
		Iterator<Edge> i ;
		for(int c=0;c<2;c++){
		if(c==0){
			i= u.iterator();
		}
		
		else{
			i=u.reverseIterator();}
		
		while(i.hasNext()){
			XEdge e =(XEdge) i.next();
			Vertex o= e.otherEnd(u);
			XVertex v = xg.getVertex(o);
			if (minHeight>v.getHeight() && v.getHeight()>=u.getHeight()){
				minHeight=v.getHeight();
				done=true;
			}
				
			}
		}
		if(done)
			u.setHeight(1+ minHeight);
	}
	
	/**
	 * Pushed the excess flow onto provided edge
	 * 
	 * @param u : Vertex
	 * @param v : Other End of Edge Provided
	 * @param e : Edge provided
	 * @param flow : flow in edges of XGraph
	 * @param capacity : capacities of edges in XGraph
	 */
	void push(XVertex u, XVertex v, XEdge e,HashMap<Edge,Integer> flow, HashMap<Edge,Integer> capacity){
		int delta;
		if(e.fromVertex().equals(v))
			delta= u.getExcess();
		else
			delta= min(u.getExcess(), e.getResCapacity());
		if(e.fromVertex().equals(u)){
			flow.put(e, flow.get(e)+delta);
		}
		else{
			flow.put(e, flow.get(e)-delta);
		}
		
		e.setResCapacity(capacity.get(e)-flow.get(e));
		
		u.setExcess(u.getExcess()-delta);
		v.setExcess(v.getExcess()+delta);
	}
	
	/**
	 * Checks if the Edge is in Gf. If so it checks for flow-conservation constrains or it checks if flow is positive
	 * @param u : Vertex
	 * @param e : Edge
	 * @param flow : flow in edges of XGraph
	 * @param capacity :capacities of edges in XGraph
	 * @return boolean value
	 */
	boolean inGr(XVertex u, XEdge e, HashMap<Edge,Integer> flow, HashMap<Edge,Integer> capacity){
		return e.fromVertex().equals(u)? flow.get(e)<capacity.get(e):flow.get(e)>0;
	}
	
	int min(int a, int b){
		if(a<b)
			return a;
		else 
			return b;
	}
	
	//Dinitz Max Flow
	/**
	 * Implements Dinitz max flow algorithm
	 * 
	 * @param xg: XGraph
	 * @param source : Source Vertex
	 * @param target : Target Vertex
	 * @param oldcapacity :capacities of edges in Graph
	 * @param flow :flow in edges of XGraph
	 * @return max flow provided by algorithm
	 */
	public int Dinitz(XGraph xg, Vertex source, Vertex target, HashMap<Edge,Integer> oldcapacity, HashMap<Edge,Integer> flow){
		
		XVertex s= xg.getVertex(source);
		XVertex t= xg.getVertex(target);
		
		HashMap<XEdge, Integer> capacity = new HashMap<XEdge, Integer>();
		int maxFlow=0;
		
		initialize(xg,flow,oldcapacity,capacity);
		
		for(int k=1;k<=xg.size();k++){
		while(true){
			boolean isPath=BreadthFirstSearch.BFS(xg,s,t,k);
			if(isPath){
				List<XVertex> vPath=buildPath(xg,s,t);
						
				LinkedList<XEdge> path = new LinkedList<XEdge>(); 
				
				int minFlow = findMinFlow(xg,vPath,path);
				maxFlow+=minFlow;
				
				updateGraph(xg,path,minFlow, capacity, flow);
			}
			else
				break;
		}
		}
		return maxFlow;
	}

	/**
	 * Sets flow, capacities, residual capacity values of each vertex of the XGraph
	 * 
	 * @param xg : XGraph
	 * @param flow :flow in edges of XGraph
	 * @param oldcapacity :capacities of edges in Graph
	 * @param capacity:capacities of edges in XGraph
	 */
	public void initialize(XGraph xg, HashMap<Edge, Integer> flow, HashMap<Edge, Integer> oldcapacity, HashMap<XEdge,Integer> capacity) {
		//set flow to zero
		
		for(Vertex o:xg){
			XVertex u = xg.getVertex(o);

			Iterator i= u.iterator();
			while(i.hasNext()){
				XEdge e= (XEdge)i.next();
				
				flow.put(e,0);
				Edge val= xg.getEdge(e);
				if(e.fromVertex().getName()==val.fromVertex().getName()){
					
					capacity.put(e, oldcapacity.get(val));
					e.setResCapacity(oldcapacity.get(val));
				}
				else
				{
					capacity.put(e, oldcapacity.get(val));
					e.setResCapacity(0);
				}
			}
		}
				
	}

	/**
	 * Build the path based on parent values assigned by BFS
	 * @param xg :XGraph
	 * @param s : Source Vertex
	 * @param t : Target Vertex
	 * @return path
	 */
	public List<XVertex> buildPath(XGraph xg, XVertex s, XVertex t){
		LinkedList<XVertex> path = new LinkedList<XVertex>();
		
		XVertex cur=t;
		while(!cur.equals(s)){
			path.add(0, cur);
			cur=cur.getParent();
		}
		path.add(0, cur);
		return path;
	}
	
	/**
	 * Finds the minimum capacity in the path
	 * @param xg : XGraph
	 * @param vPath : List of vertices returned by BFS
	 * @param path : Path found by BFS
	 * @return least capacity in the path
	 */
	public int findMinFlow(XGraph xg, List<XVertex> vPath,LinkedList<XEdge> path){
		
		int minFlow=Integer.MAX_VALUE;
		int i=0;
		while(i<vPath.size()-1){
			XVertex u= vPath.get(i);
			Iterator it= u.iterator();
			XVertex v = u;
			while(it.hasNext()){
				XEdge e= (XEdge)it.next();
				Vertex o= e.otherEnd(u);
				v= xg.getVertex(o);
				
				if(v.equals(vPath.get(i+1))){
					path.add(e);
					int f= e.getResCapacity();
					if(f>0 && minFlow>f)
						minFlow=f;
					break;
				}
				
			}
			u=v;
			i++;
		}
		return minFlow;
	}
	
	/**
	 * Update Graph by setting the residual Capacities and flows of the vertices
	 * 
	 * @param xg : XGraph
	 * @param path : Path found by BFS
	 * @param minFlow :minimum capacity in the path
	 * @param capacity : capacities of edges in XGraph
	 * @param flow : flow in edges of XGraph
	 */
	public void updateGraph(XGraph xg, List<XEdge> path, int minFlow, HashMap<XEdge, Integer> capacity,HashMap<Edge, Integer> flow) {

		for(XEdge e:path){
			XEdge opp= xg.oppMap.get(e);
			
			flow.put(e, flow.get(e)+minFlow);
			flow.put(opp, flow.get(opp)-minFlow);
			
			e.setResCapacity(capacity.get(e)-flow.get(e));
			opp.setResCapacity(capacity.get(opp)+flow.get(opp));
			
		}
	}

}