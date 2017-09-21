package cs6301.g21;

import java.util.List;

import java.util.Iterator;

import java.util.LinkedList;

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

    // To do: function to find an Euler tour
    public List<Graph.Edge> findEulerTour() {
	findTours();
	if(VERBOSE > 9) { printTours(); }
	stitchTours();
	return tour;
    }

    /* To do: test if the graph is Eulerian.
     * If the graph is not Eulerian, it prints the message:
     * "Graph is not Eulerian" and one reason why, such as
     * "inDegree = 5, outDegree = 3 at Vertex 37" or
     * "Graph is not strongly connected"
     */
    boolean isEulerian() {
//	System.out.println("Graph is not Eulerian");
//	System.out.println("Reason: Graph is not strongly connected");
	//previously it was false
	return true;
    }

    // Find tours starting at vertices with unexplored edges
    void findTours() {
        Iterator it = ge.iterator();

        while(it.hasNext()){
            Graph.Vertex current = (Graph.Vertex)it.next();
//            System.out.println(current.getName());
            GraphExtended2.GEVertex currentX = ge.getGEVertex(current.getName()+1);
            if(currentX.hasNext()){
                findTours(currentX.getElement(), currentX.getSubTour());
            }
        }
//    	GraphExtended2.GEVertex start= ge.getGEVertex(1);
//    	findTours(start.getElement(), start.getSubTour());
//    	Iterator vertices = ge.iterator();
//    	while(vertices.hasNext()){
//    		Graph.Vertex v= (Graph.Vertex)vertices.next();
//    		if(ge.hasNext(v)){
//    			GraphExtended2.GEVertex geV= ge.getGEVertex(v.getName()+1);
//    	    	findTours(geV.getElement(), geV.getSubTour());
//    		}
//    	}
    }
    
	void findTours(Graph.Vertex u, List<Graph.Edge> subTour){
		Graph.Vertex temp =u;
		while(ge.hasNext(u)){
			Graph.Edge e = ge.next(u);
//			tour.add(e);
			subTour.add(e);
			u = e.otherEnd(u);
//			if(u==temp)
//				break;
		}
		if(u!=temp){
			System.out.println("Not Eulerian");
			System.exit(0);
		}
	}

    /* Print tours found by findTours() using following format:
     * Start vertex of tour: list of edges with no separators
     * Example: lp2-in1.txt, with start vertex 3, following tours may be found.
     * 3: (3,1)(1,2)(2,3)(3,4)(4,5)(5,6)(6,3)
     * 4: (4,7)(7,8)(8,4)
     * 5: (5,7)(7,9)(9,5)
     *
     * Just use System.out.print(u) and System.out.print(e)
     */
    void printTours() {
    }

    // Stitch tours into a single tour using the algorithm discussed in class
    void stitchTours() {
        Iterator sticher = ge.iterator();
        boolean started = false;
        while(!started && sticher.hasNext()) {
            Graph.Vertex starter = (Graph.Vertex) sticher.next();
            GraphExtended2.GEVertex start = ge.getGEVertex(starter.getName() + 1);
            if (start.getSubTour() != null && start.tourStarted == false) {
                started = true;
                start.tourStarted = true;
                explore(start.getElement());
            }
        }
    }
        
    void explore(Graph.Vertex u){
        GraphExtended2.GEVertex uX= ge.getGEVertex(u.getName()+1);
        if(uX.getSubTour() != null){
            Iterator tourIt = uX.getSubTour().iterator();
            while(tourIt.hasNext()){
                Graph.Edge e = (Graph.Edge) tourIt.next();
                tour.add(e);
                Graph.Vertex v = e.otherEnd(u);
                GraphExtended2.GEVertex vX = ge.getGEVertex(v.getName()+1);
                if(vX.getSubTour() != null && vX.tourStarted==false){
                    vX.tourStarted = true;
                    explore(v);
                    vX.setSubTour(null);
                }
            }
        }
//    	Graph.Vertex temp;
//    	temp = u;
//    	GraphExtended2.GEVertex geV= ge.getGEVertex(temp.getName()+1);
//    	for(Graph.Edge e : geV.getSubTour()){
//    		tour.add(e);
//    		temp = e.otherEnd(temp);
//    		GraphExtended2.GEVertex geV1= ge.getGEVertex(temp.getName()+1);
//    		if(geV1.getSubTour()!=null){
//    			explore(temp);
//                geV1.setSubTour(null);
//    		}
//    	}
//    	geV.setSubTour(null);
    }

    void setVerbose(int v) {
	VERBOSE = v;
    }
}
