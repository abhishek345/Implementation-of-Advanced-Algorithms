package cs6301.g21;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of graph to check if Eulerian and stitching and printing the subtours
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/09/20
 * @version 1:1: 2017/09/21
 */

public class Euler {
    int VERBOSE;
    List<Graph.Edge> tour;
    static GraphExtended ge;
    static Graph g;
    Graph.Vertex s1;
    // Constructor
    Euler(Graph g, Graph.Vertex start) {
        VERBOSE = 1;
//        g=g;
        tour = new LinkedList<>();
        ge = new GraphExtended(g);
        s1=start;
    }

    //function to find an Euler tour
    public List<Graph.Edge> findEulerTour() {
        findTours();
        if(VERBOSE > 9) { printTours(); }
        stitchTours();
        return tour;
    }

    /* 
     * Checks if the graph is Eulerian
     */
    boolean isEulerian() {
    	CheckEulerian ce = new CheckEulerian(ge.g);
    	boolean result = CheckEulerian.testEulerian(ge.g);
	    return result;
//    	return true;
    }

    // Find tours starting at vertices with unexplored edges
    void findTours() {
    	GraphExtended.GEVertex Start = ge.getGEVertex(s1.getName()+1);
    	findTours(s1,Start.getSubTour());
        Iterator it = ge.iterator();
        while(it.hasNext()){
            Graph.Vertex current = (Graph.Vertex)it.next();
            GraphExtended.GEVertex currentX = ge.getGEVertex(current.getName()+1);
            if(currentX.hasNext()){
                findTours(currentX.getElement(), currentX.getSubTour());
            }
        }
    }
    
    // Find sub tours and adds the edges into respective sub tours
	void findTours(Graph.Vertex u, List<Graph.Edge> subTour){
		Graph.Vertex temp =u;
		while(ge.hasNext(u)){
			Graph.Edge e = ge.next(u);
			subTour.add(e);
			u = e.otherEnd(u);
		}
		if(u!=temp){
			System.out.println("Not Eulerian");
			System.exit(0);
		}
	}

    // Print tours found by findTours()
    void printTours() {
        Iterator it = ge.iterator();
        while(it.hasNext()){
            Graph.Vertex current = (Graph.Vertex)it.next();
            GraphExtended.GEVertex currentX = ge.getGEVertex(current.getName()+1);
            if(currentX.getSubTour()!=null){
            	Iterator tourIt = currentX.getSubTour().iterator();
                if(tourIt.hasNext()){
                	System.out.println();
                	System.out.print(current);
                	System.out.print(": ");
                }
            	//System.out.println();
            	while(tourIt.hasNext()){
            		Graph.Edge e = (Graph.Edge) tourIt.next();
            		System.out.print(e);
            	}	
            }
    	}
        System.out.println();

    }

    // Stitch tours into a single tour using the algorithm discussed in class
    void stitchTours() {
    	GraphExtended.GEVertex Start = ge.getGEVertex(s1.getName()+1);
    	Start.tourStarted=true;
    	explore(Start);
        Iterator sticher = ge.iterator();
        boolean started = false;
        while(!started && sticher.hasNext()) {
            Graph.Vertex starter = (Graph.Vertex) sticher.next();
            GraphExtended.GEVertex start = ge.getGEVertex(starter.getName() + 1);
            if (!start.tourStarted && start.getSubTour() != null) {
                started = true;
                start.tourStarted = true;
                explore(start);
            }
        }
    }

    //checks the unexplored verticed having subtours and stitches them
    void explore(GraphExtended.GEVertex uX){
        Iterator tourIt = uX.getSubTour().iterator();
        while(tourIt.hasNext()){
            Graph.Edge e = (Graph.Edge) tourIt.next();
            tour.add(e);
            Graph.Vertex v = e.otherEnd(uX.getElement());
            uX = ge.getGEVertex(v.getName()+1);
            GraphExtended.GEVertex vX = ge.getGEVertex(v.getName()+1);
            if(vX.getSubTour() != null && !vX.tourStarted){
                vX.tourStarted = true;
                explore(vX);
            }
        }
    }

    void setVerbose(int v) {
	    VERBOSE = v;
    }
}
