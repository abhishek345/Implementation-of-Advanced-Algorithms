package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckDAG {
    public static boolean isDAG(GraphExtended ge){
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
        Scanner sf = new Scanner(new File("/home/uks/ImplofAlgos/Implementation-of-Advanced-Algorithms/SP3/src/cs6301/g21/cyclicgraph.in"));
        Graph graph = Graph.readDirectedGraph(sf);
        GraphExtended ge = new GraphExtended(graph);
        System.out.println(isDAG(ge));
    }
}
