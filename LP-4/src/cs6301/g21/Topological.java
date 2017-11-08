package cs6301.g21;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Determining the topological ordering of all vertices in a graph.
 *
 *
 * @author Umang Shah, Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/09/13
 * @version 2.0: 2017/10/31 Added counting and generation of all possible topological orderings.
 *
 */
public class Topological {

	static int topNum;
	static LinkedList<XGraph.XVertex> decFinList;
//	static XGraph ge;

	/**
	 * Returns the topological order of a graph by sequentially
	 * removing vertices with no incoming edges and its incident edges
	 *
	 * @param ge : graph whose topological order is determined
	 * @return  : LinkedList<Graph.Vertex> : topological order
	 */
	public static LinkedList<Graph.Vertex> toplogicalOrder1(XGraph ge){

		int topNum= 0;
		LinkedList<Graph.Vertex> queue=new LinkedList<>();
		LinkedList<Graph.Vertex> topList= new LinkedList<>();
//		ge = new XGraph(g);
		Iterator<Graph.Vertex> vertices = ge.iterator();


		while(vertices.hasNext()){
			XGraph.XVertex s= (XGraph.XVertex) vertices.next();
			s.setInDegree(s.revAdj.size());
			if(s.getInDegree()==0){
				queue.add(s);
			}
		}

		while(!queue.isEmpty()){
			XGraph.XVertex u= (XGraph.XVertex) queue.remove();
			u.setTop(++topNum);
			topList.add(u);
			Iterator<Graph.Edge> e= u.iterator();
			while(e.hasNext()){
				XGraph.XVertex v= (XGraph.XVertex) e.next().otherEnd(u);
				int inDegreeVal= v.getInDegree();
				v.setInDegree(inDegreeVal-1);
				if(v.getInDegree()==0){
					queue.add(v);
				}

			}
		}

		if(topNum!=ge.size()){
			return null;
		}
		return topList;

	}

	/**
	 * Returns the topological order of a graph by adding the nodes to
	 * the front of a list in the order of finish time.
	 * Function calls DFS function from DFS class.
	 *
	 * @param ge : graph whose topological order is determined
	 * @return  : LinkedList<Graph.Vertex> : topological order
	 */
	public static LinkedList<XGraph.XVertex> toplogicalOrder2(XGraph ge)throws CyclicGraphException{
//		ge = new XGraph(g);
		Iterator it= ge.iterator();
		decFinList =DFS.DFSCall(it,ge);
		return decFinList;

	}

	public static int allTopological(XGraph ge, boolean onlyCount){
        for(Graph.Vertex u: ge){
            ((XGraph.XVertex) u).setSeen(false);
            ((XGraph.XVertex) u).setInDegree(((XGraph.XVertex) u).xrevAdj.size());
        }
            int count = 0;
	    if(onlyCount)
	        count = findTopological(ge, null);
	    else
		count = findTopological(ge, new ArrayList<>());
	    return count;
	}

	static int findTopological(Graph ge, ArrayList<XGraph.XVertex> ordering){
	    boolean completed = false;
	    int count=0;
	    for(Graph.Vertex u: ge){
            XGraph.XVertex uX = (XGraph.XVertex) u;
            if(uX.getInDegree() == 0 && !uX.getSeen()){
                for (Graph.Edge e: uX){
                    XGraph.XVertex v = (XGraph.XVertex)e.otherEnd(uX);
                    v.setInDegree(v.getInDegree()-1);
                }

		if(ordering != null)
                	ordering.add(uX);
                uX.setSeen(true);
                count += findTopological(ge, ordering);


                uX.setSeen(false);
		if(ordering != null)
                	ordering.remove(ordering.size()-1);
                for (Graph.Edge e: uX){
                    XGraph.XVertex v = (XGraph.XVertex)e.otherEnd(uX);
                    v.setInDegree(v.getInDegree()+1);
                }
                completed = true;
            }
        }

        if(!completed){
	        count++;
		if(ordering != null){
			for(XGraph.XVertex u: ordering){
			    System.out.print(u + " ");
			}
            		System.out.println();
		}
        }
        return count;
    }

	/**
	 * Main function. Reads the graph from a file sent through command
	 * line. Calls both functions to determine the topological order
	 * and displays them
	 *
	 * @param args : command line arguments
	 * @throws FileNotFoundException : Exception if no file is provided as input or no file found
     * @throws CyclicGraphException : Exception if graph is cyclic
	 */
//	public static void main(String[] args)throws FileNotFoundException, CyclicGraphException{
//        DFS.setCycleChecking(true);
//		if(args.length > 0){
//          Scanner sf = new Scanner(new File(args[0]));
//          Graph graph = Graph.readDirectedGraph(sf);
//          try {
//              LinkedList l1= toplogicalOrder1(graph);
//              Iterator i1 = l1.iterator();
//              while (i1.hasNext()) {
//                  System.out.print((Graph.Vertex) i1.next() + " ");
//              }
//              System.out.println();
//          }catch (NullPointerException e){
//              System.out.print("No topological order. ");
//          }
//          try {
//              LinkedList l2 = toplogicalOrder2(graph);
//              Iterator i2 = l2.iterator();
//              while (i2.hasNext()) {
//                  System.out.print((Graph.Vertex) i2.next() + " ");
//              }
//              System.out.println();
//          }catch (CyclicGraphException ce){
//              System.out.print("Cycle in graph");
//          }
//        }
//
//	}
}
