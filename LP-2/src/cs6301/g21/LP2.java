package cs6301.g21;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Implementation of graph to check if Eulerian and print the Euler tour
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 */


public class LP2 {
    static int VERBOSE = 1;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            return;
//            in = new Scanner(System.in);
        }
	int start = 1;
        if(args.length > 1) {
	    start = Integer.parseInt(args[1]);
	}
	if(args.length > 2) {
            VERBOSE = Integer.parseInt(args[2]);
        }
        Graph g = Graph.readDirectedGraph(in);
	Graph.Vertex startVertex = g.getVertex(start);

        Timer timer = new Timer();
	Euler euler = new Euler(g, startVertex);
	euler.setVerbose(VERBOSE);

	boolean eulerian = euler.isEulerian();
	if(!eulerian) {
	    return;
	}
	List<Graph.Edge> tour = euler.findEulerTour();
        timer.end();

        if(VERBOSE > 0) {
	    System.out.println("Output:\n_________________________");
        System.out.println(tour.size());
	    for(Graph.Edge e: tour) {
                System.out.print(e);
            }
	    System.out.println();
	    System.out.println("_________________________");
        }
        System.out.println(timer);
    }
}
