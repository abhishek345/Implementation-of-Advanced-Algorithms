package cs6301.g21;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Determining the topological ordering of all vertices in a graph using
 * two techniques.
 *
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
 * @version 1.0: 2017/09/13
 *
 */
public class Topological {

	static int topNum;
	static LinkedList<Graph.Vertex> decFinList;
	static GraphExtended ge;

	/**
	 * Returns the topological order of a graph by sequentially
	 * removing vertices with no incoming edges and its incident edges
	 *
	 * @param g : graph whose topological order is determined
	 * @return  : LinkedList<Graph.Vertex> : topological order
	 */
	public static LinkedList<Graph.Vertex> toplogicalOrder1(Graph g){

		int topNum= 0;
		LinkedList<Graph.Vertex> queue=new LinkedList<Graph.Vertex>();
		LinkedList<Graph.Vertex> topList= new LinkedList<Graph.Vertex>();
		ge = new GraphExtended(g);
		Iterator<Graph.Vertex> vertices = ge.iterator();


		while(vertices.hasNext()){
			Graph.Vertex s= vertices.next();
			ge.setInDegree(s, s.revAdj.size());
			if(ge.getInDegree(s)==0){
				queue.add(s);
			}
		}

		while(!queue.isEmpty()){
			Graph.Vertex u= queue.remove();
			ge.setTop(u,++topNum);
			topList.add(u);
			Iterator<Graph.Edge> e= u.iterator();
			while(e.hasNext()){
				Graph.Vertex v= e.next().otherEnd(u);
				int inDegreeVal= ge.getInDegree(v);
				ge.setInDegree(v, inDegreeVal-1);
				if(ge.getInDegree(v)==0){
					queue.add(v);
				}

			}
		}

		if(topNum!=g.size()){
			return null;
		}
		return topList;

	}

	/**
	 * Returns the topological order of a graph by adding the nodes to
	 * the front of a list in the order of finish time.
	 * Function calls DFS function from DFS class.
	 *
	 * @param g : graph whose topological order is determined
	 * @return  : LinkedList<Graph.Vertex> : topological order
	 */
	public static LinkedList<Graph.Vertex> toplogicalOrder2(Graph g)throws CyclicGraphException{
		ge = new GraphExtended(g);
		Iterator it= ge.iterator();
		decFinList =DFS.DFSCall(it,ge);
		return decFinList;

	}

	/**
	 * Main function. Reads the graph from a file sent through command
	 * line. Calls both functions to determine the topological order
	 * and displays them
	 *
	 * @param args : command line arguments
	 * @throws FileNotFoundException :Exception if no file is provided as input or no file found
	 */
	public static void main(String[] args)throws FileNotFoundException, CyclicGraphException{
        DFS.setCycleChecking(true);
		if(args.length > 0){
          Scanner sf = new Scanner(new File(args[0]));
          Graph graph = Graph.readDirectedGraph(sf);
          LinkedList l1= toplogicalOrder1(graph);
          Iterator i1= l1.iterator();
          while(i1.hasNext()){
        	  System.out.print((Graph.Vertex)i1.next() + " ");
          }
          System.out.println();

          LinkedList l2= toplogicalOrder2(graph);
          Iterator i2= l2.iterator();
          while(i2.hasNext()){
        	  System.out.print((Graph.Vertex)i2.next() + " ");
          }
          System.out.println();
    }

	}
}
