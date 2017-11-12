package cs6301.g21;

import java.util.Iterator;
import java.util.Scanner;

import cs6301.g21.Graph.Edge;
import cs6301.g21.Graph.Vertex;
import cs6301.g21.XGraph.XEdge;
import cs6301.g21.XGraph.XVertex;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Determining the Shortes Paths of all vertices in a graph.
 *
 *
 * @author Umang Shah, Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/09/13
 *
 */

public class BellmanFord {
	
	
	private static final int INFINITY = Integer.MAX_VALUE;

	/**Counts the number of shortest paths in the graph
	 * 
	 * @param g : Input Graph G
	 * @param s : Source Vertex
	 * @param end : Target Vertex
	 * @return : number of Shortest Paths
	 */
	public static int getPaths(XGraph g, Vertex s, Graph.Vertex end){

        //get a distance array
        boolean noChange = false;
        int numVert = g.n;
        int [][] vertDist = new int[numVert][g.m];
        int[] parentCount= new int[numVert];

        //initialize all the distances
        for(int i=0; i<numVert; i++){
            vertDist[i][0] = Integer.MAX_VALUE;
            parentCount[i]=0;
            for(int j=1; j<g.m; j++)
                vertDist[i][j] = 0;
        }

        //set parents to be null
        for(Graph.Vertex u : g){
            ((XGraph.XVertex) u).setParent(null);
        }

        //initialise distance for source to be zero:
        vertDist[s.name][0] = 0;
        parentCount[s.name]=1;

        //go through as many iterations as number of vertices required
        for(int i=1; i<=g.n; i++){
            noChange = true;

            
            for(int k=0; k<numVert; k++){
            	parentCount[k]=0;
            }
            parentCount[s.name]=1;
            
            
            for(Graph.Vertex u : g){
                vertDist[u.name][i] = vertDist[u.name][i-1];
                Iterator<Graph.Edge> edgeIterator = ((XGraph.XVertex) u).revIterator();

                while (edgeIterator.hasNext()){
                    Graph.Edge e = edgeIterator.next();
                    Graph.Vertex p = e.from;

                    if(vertDist[p.name][i-1] != Integer.MAX_VALUE && vertDist[u.name][i] >= (vertDist[p.name][i-1] + e.getWeight())){
                    	if(vertDist[u.name][i] > (vertDist[p.name][i-1] + e.getWeight())){
	                        vertDist[u.name][i] = (vertDist[p.name][i-1] + e.getWeight());
	                        ((XGraph.XVertex) u).parent = (XGraph.XVertex) p;
	                        noChange = false;
                    	}
                    		if(vertDist[u.name][i] > (vertDist[p.name][i-1] + e.getWeight()))
                    			parentCount[u.name]=parentCount[p.name];
                    		else
                    			parentCount[u.name]+=parentCount[p.name];
                    	
                    	
                    }
                }
            }

            //if no change update the distance
            if(noChange){
                for(Graph.Vertex u : g){
                    ((XGraph.XVertex) u).dis = vertDist[u.name][i];
                }
                return parentCount[end.name];
            }
        }

        return vertDist[end.name][g.m-1];
    }


}
