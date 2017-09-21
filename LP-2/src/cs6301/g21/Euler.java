package cs6301.g21;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;



/**
 * Implementation of graph to check if Eulerian and stitching and printing the subtours
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 */



public class Euler {
    int VERBOSE;
    List<Graph.Edge> tour;
    static GraphExtended2 ge;
    static Graph g;
    // Constructor
    Euler(Graph g, Graph.Vertex start) {
        VERBOSE = 1;
        tour = new LinkedList<>();
        ge = new GraphExtended2(g);
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
	    return true;
    }

    // Find tours starting at vertices with unexplored edges
    void findTours() {
        Iterator it = ge.iterator();
        while(it.hasNext()){
            Graph.Vertex current = (Graph.Vertex)it.next();
            GraphExtended2.GEVertex currentX = ge.getGEVertex(current.getName()+1);
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
            GraphExtended2.GEVertex currentX = ge.getGEVertex(current.getName()+1);
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
        Iterator sticher = ge.iterator();
        boolean started = false;
        while(!started && sticher.hasNext()) {
            Graph.Vertex starter = (Graph.Vertex) sticher.next();
            GraphExtended2.GEVertex start = ge.getGEVertex(starter.getName() + 1);
            if (!start.tourStarted && start.getSubTour() != null) {
                started = true;
                start.tourStarted = true;
                explore(start);
            }
        }
    }

    //checks the unexplored verticed having subtours and stitches them
    void explore(GraphExtended2.GEVertex uX){
        Iterator tourIt = uX.getSubTour().iterator();
        while(tourIt.hasNext()){
            Graph.Edge e = (Graph.Edge) tourIt.next();
            tour.add(e);
            Graph.Vertex v = e.otherEnd(uX.getElement());
            GraphExtended2.GEVertex vX = ge.getGEVertex(v.getName()+1);
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
