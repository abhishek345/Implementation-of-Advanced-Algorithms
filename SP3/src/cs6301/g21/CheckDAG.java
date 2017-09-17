package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Determining if a graph is a Directed Acyclic Graph
 *
 * @author Umang Shah, Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/09/13
 *
 */
public class CheckDAG {
    /**
     * Performs DFS on a graph and if any cycles exist it reports an exception
     *
     * @param ge : Graph extended object containing the graph
     * @return   : boolean : True if graph is a DAG else False
     */
    public static boolean isDAG(GraphExtended ge){
        DFS.setCycleChecking(true);
        try{
            if(ge.isDirected()) {
                DFS.DFSCall(ge);
                return true;
            }
            else{
                System.out.println("Graph is not directed");
                return false;
            }
        }catch(CyclicGraphException e){
            System.out.println("Graph has Cycles");
            return false;
        }
    }
    /**
     * Main function. Reads the graph from a file sent through command
     * line. Calls isDag function and displays the result.
     *
     * @param args : command line arguments
     */
    public static void main(String args[])throws FileNotFoundException {

        Scanner sf1 = new Scanner(new File(args[0]));
        Scanner sf2 = new Scanner(new File(args[0]));
        //Same graph with cycle, second run uses undirected graph
        System.out.print("Graph 1: ");
        Graph graph1 = Graph.readDirectedGraph(sf1);
        GraphExtended ge1 = new GraphExtended(graph1);
        System.out.println(isDAG(ge1));

        System.out.print("Graph 2: ");
        Graph graph2 = Graph.readGraph(sf2);
        GraphExtended ge2 = new GraphExtended(graph2);
        System.out.println(isDAG(ge2));
    }
}
