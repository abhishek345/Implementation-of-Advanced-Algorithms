package cs6301.g21;
import cs6301.g00.Graph;
import cs6301.g00.Graph.*;
import cs6301.g21.XGraph.XEdge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Wrapper funtions to call Relabel To Front  and Dinitz algorithm
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/11/18
 */

public class Flow {
	Graph g;
	Vertex s;
	Vertex t;
	HashMap<Edge,Integer> capacity;
	HashMap<Edge, Integer> flow;
	XGraph xg;
	
	HashSet<Vertex> S,T;

	/**
	 * Constructor of flow
	 * 
	 * @param g : Graph provided
	 * @param s : Source vertex 
	 * @param t : Target vertex
	 * @param capacity : Capacities of the edges
	 */
    public Flow(Graph g, Vertex s, Vertex t, HashMap<Edge, Integer> capacity) {
    	this.g=g;
    	this.s=s;
    	this.t=t;
    	this.capacity=capacity;
    	flow = new HashMap<Edge,Integer>();
    }

    /**
     * Return max flow found by Dinitz's algorithm
     * @return max flow of the graph
     */
    public int dinitzMaxFlow() {
    	MaxFlow mf= new MaxFlow();
    	XGraph.setDinitz(true);
    	xg= new XGraph(this.g);
    	int maxFlow=mf.Dinitz(xg,this.s,this.t,this.capacity,this.flow);
    	findMinST();
	return maxFlow;
    }

    /**
     * Return max flow found by relabelToFront algorithm
     * @return max flow of the graph
     */
    public int relabelToFront() {
    	
    	MaxFlow mf= new MaxFlow();
    	XGraph.setDinitz(false);
    	xg= new XGraph(this.g);
    	int maxFlow = mf.relabelToFront(xg,this.s,this.t,this.capacity,this.flow);
    	findMinST();
    	
    	return maxFlow;
    }

    /**
     * Flow going through edge e
     * @param e : Edge whose flow has to be returned
     * @return : flow of edge e
     */
    public int flow(Edge e) {
    	XEdge xe= (XEdge) this.xg.getXEdge(e);
    	return this.flow.getOrDefault(xe,0);
    }

    /**
     * Capacity of edge e
     * @param e :Edge whose capacity has to be returned
     * @return :capacity of edge e
     */
    public int capacity(Edge e) {
    	return capacity.getOrDefault(e, 0);
    }
    
    /**
     * Determines the min cut on S and T side
     */
    public void findMinST(){
    	S= new HashSet<Vertex>();
    	T= new HashSet<Vertex>();
    	S.add(this.s);
    	T.add(this.t);
    	for(Vertex u: g){
    		for(Edge e: u){
    			Vertex v= e.otherEnd(u);
    			
    			if(S.contains(u)){
    				if(flow(e)==capacity(e) && !T.contains(v))
    					T.add(v);
    				else if(!T.contains(v) && !S.contains(v))
    					S.add(v);
    			}
    				
    			if(T.contains(u)){
    				if(flow(e)==capacity(e) && !T.contains(v))
    					S.add(v);
    				else if(!S.contains(v) && !T.contains(v))
    					T.add(v);
    			}
    		}
    	}
    }

    /* After maxflow has been computed, this method can be called to
       get the "S"-side of the min-cut found by the algorithm
    */
    public Set<Vertex> minCutS() {
    	return S;
    }

    /* After maxflow has been computed, this method can be called to
       get the "T"-side of the min-cut found by the algorithm
    */
    public Set<Vertex> minCutT() {
	return T;
    }
}