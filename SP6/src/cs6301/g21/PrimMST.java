package cs6301.g21;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import cs6301.g21.GraphExtended3.GEVertex;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Implementation of Prim's algorithm using Indexed heaps
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */

public class PrimMST {
    static final int Infinity = Integer.MAX_VALUE;
    static GraphExtended3 ge;
   
    /**
     * Constructor of the class PrimMST
     * @param g : input graph
     */
    public PrimMST(Graph g) {
    	ge = new GraphExtended3(g);
    }

    public int prim1(Graph.Vertex s) {
        int wmst = 0;

        // SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:

        return wmst;
    }

    
    /**
     * Implementation of the Prim's algorithm version 2 using Indexed heaps
     * @param s : Starting Vertex of the graph to implement Prim's algorithm
     * @return : weight of the minimum spanning tree
     */
    public int prim2(Graph.Vertex s) {
        int wmst = 0;

        GraphExtended3.GEVertex[] arrayOfVertices = new GraphExtended3.GEVertex[ge.size() +1];
        Comparator<GraphExtended3.GEVertex> comparator = (Comparator<GEVertex>) new ComparatorVertex.VertexComparator();
		int i=0;
        Iterator it = ge.iterator();
		while(it.hasNext())
		{
			Graph.Vertex u = (Graph.Vertex)it.next();
			ge.setSeen(u,false);
			ge.setParent(u,null);
			ge.setDist(u,Infinity);
			ge.setIndex(u,i);
			arrayOfVertices[i++] = ge.getGEVertex(u);	
		}
		
		ge.setDist(s,0);
		
		IndexedHeap<GraphExtended3.GEVertex> indexedHeaps = new IndexedHeap<GraphExtended3.GEVertex>(arrayOfVertices, comparator, ge.size()-1);
        
		while(indexedHeaps.size > 0){
			GraphExtended3.GEVertex otherVertex = indexedHeaps.remove();
			otherVertex.setSeen(true);
			wmst += otherVertex.dist;
			for(Graph.Edge e : otherVertex.adj){
				Graph.Vertex tempV = e.otherEnd(otherVertex.getElement());
				GraphExtended3.GEVertex v= ge.getGEVertex(tempV);
				if(!v.seen && e.weight < v.dist){
					v.dist = e.weight;
					v.parent = otherVertex.getElement();
					indexedHeaps.percolateUp(v.getIndex());
					
				}
			}
		}
		
        return wmst;
    }

    /**
     * Main Function
     * @param args : command line Input
     * @throws FileNotFoundException : In case of file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

	Graph g = Graph.readGraph(in);
        Graph.Vertex s = g.getVertex(1);

	Timer timer = new Timer();
	PrimMST mst = new PrimMST(g);
	int wmst = mst.prim2(s);
	timer.end();
        System.out.println(wmst);
    }
}
