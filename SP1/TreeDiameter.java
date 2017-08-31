/**
 *  Get the diameter (longest path) in a Tree.
 *  @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Rao
 *  Ver 1.0: 2017/08/28.  
 *
 */

package cs6301.g21;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class TreeDiameter {
    // Return a longest path in g.  Algorithm is correct only if g is a tree.
    static LinkedList<Graph.Vertex> diameter (Graph g) {
        //1st BFS to find farthest node
        LinkedList<Graph.Vertex> start = BreadthFirstSearch.bfs(g, g.getVertex(1));
        //2nd BFS starting from farthest node
        LinkedList<Graph.Vertex> end = BreadthFirstSearch.bfs(g, start.get(start.size()-1));
        return new LinkedList<>();
    }
    
    public static void main(String[] args)throws FileNotFoundException{

        Scanner sf = new Scanner(new File("graph.in"));
        Graph graph = Graph.readGraph(sf);
        System.out.println(TreeDiameter.diameter(graph));
    }
}
