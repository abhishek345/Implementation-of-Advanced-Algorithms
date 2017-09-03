/**
 *  Get the diameter (longest path) in a Tree.
 *  @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Rao
 *  Ver 1.1: 2017/09/01.  
 *
 */

package cs6301.g21;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class TreeDiameter {
    /**
		 * function finds diameter of a tree
		 * 
		 * Return a longest path in g.  Algorithm is correct only if g is a tree.
		 * perform 1st bfs from arbitrary node, and then 2nd bfs from farthest node,
		 * this gives the longest path in the tree.
		 *
		 * @param g: Graph : the tree whose diameter we need to find
		 * 
		 */
    static LinkedList<Graph.Vertex> diameter (Graph g) {
        //1st BFS to find farthest node
        LinkedList<Graph.Vertex> start = BreadthFirstSearch.bfs(g, g.getVertex(1));
        //2nd BFS starting from farthest node
        LinkedList<Graph.Vertex> end = BreadthFirstSearch.bfs(g, start.get(start.size()-1));
        return end;
    }
    
    public static void main(String[] args)throws FileNotFoundException{
        if(args.length > 0){
          Scanner sf = new Scanner(new File(args[0]));
          Graph graph = Graph.readGraph(sf);
          System.out.println("Longest Path is: " + TreeDiameter.diameter(graph));
        }
        else
          System.out.println("usage: java cs6301.g21.TreeDiameter <path to graph file>");
    }
}
