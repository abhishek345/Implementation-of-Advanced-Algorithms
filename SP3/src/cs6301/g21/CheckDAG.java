package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Determining if a graph has cycles
 *
 * @author Umang Shah, Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/09/13
 *
 */
public class CheckDAG {

    public static boolean isDAG(GraphExtended ge){
        DFS.setCycleChecking(true);
        try{
            if(ge.g.directed) {
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

    public static void main(String args[])throws FileNotFoundException {
        Scanner sf1 = new Scanner(new File("/home/uks/ImplofAlgos/Implementation-of-Advanced-Algorithms/SP3/src/cs6301/g21/cyclicgraph.in"));
        Scanner sf2 = new Scanner(new File("/home/uks/ImplofAlgos/Implementation-of-Advanced-Algorithms/SP3/src/cs6301/g21/cyclicgraph.in"));
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
