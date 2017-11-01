package cs6301.g21;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs6301.g21.Graph.Edge;
import cs6301.g21.Graph.Vertex;

/**
 * Determining the number of Strongly Connected Components in a graph
 *
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
 * @version 1.0: 2017/09/13
 *
 */

public class StronglyConnectedComponent {

	//static XGraph ge;

	/**
	 *
	 * Finds the finish order time of the graph using DFS. Further,
	 * reverses all the edges of the graph and then performs DFS
	 * on the new graph in the finish order computed earlier. The number
	 * of components is calculated in the latter implementation.
	 *
	 * @param g : graph whose Strongly Connected Component are determined
	 * @return  : int :the number of components
	 */
	static int stronglyConnectedComponents(XGraph g)throws CyclicGraphException {
		DFS.setCycleChecking(false);
		LinkedList<XGraph.XVertex> decFinList1 = Topological.toplogicalOrder2(g);

//		Graph gT = reverseGraph(g);
		g.reverseXGraph();
		Iterator V= decFinList1.iterator();

		LinkedList<Vertex> finOrder = new LinkedList<Vertex>();
		while(V.hasNext()){
			int vName=((Vertex)V.next()).getName();
			finOrder.add(g.getVertex(vName+1)); //changed gT to g
		}

		findComponents(g,finOrder); //changed gT to g
		g.reverseXGraph();

		return g.cno;
	}

	/**
	 *
	 * Reverses all the edges of the graph.
	 *
	 * @param g : graph whose edges have to be reverses
	 * @return  : Graph :reversed graph
	 */
//	static Graph reverseGraph(Graph g){
//		Graph gT= new Graph(g.size());
//		gT.directed=true;
//		
//		Iterator<Vertex> vertices= g.iterator();
//		
//		while(vertices.hasNext()){
//			Vertex v = (Vertex)vertices.next();
//			
//			int vName = v.getName();
//			gT.getVertex(vName+1).adj=v.revAdj;
//			gT.getVertex(vName+1).revAdj=v.adj;
//		}
//		return gT;
//	}

	/**
	 *
	 * Calls DFS, sending the finish order as a parameter
	 *
	 * @param ge : reversed graph
	 * @param decFinList : finish order
	 */
	static void findComponents(XGraph ge,List decFinList)throws CyclicGraphException{
//		ge = new XGraph(gt);
		Iterator it= decFinList.iterator();

		DFS.DFSCall(it,ge);
	}

	/**
	 * Main function. Reads the graph from a file sent through command
	 * line. Calls the function to calculate the number of Strongly Connected
	 * Components.
	 *
	 * @param args : command line arguments
	 * @throws FileNotFoundException :Exception if no file is provided as input or no file found
	 */
	public static void main(String[] args)throws FileNotFoundException,CyclicGraphException{

		if(args.length > 0){
			Scanner sf = new Scanner(new File(args[0]));
			Graph graph = Graph.readDirectedGraph(sf);
			XGraph x= new XGraph(graph);
			int count= stronglyConnectedComponents(x);
			System.out.println("Number of Components="+count);
		}

	}

}
