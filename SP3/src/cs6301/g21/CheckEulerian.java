package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CheckEulerian {
	
	//Class used to store information about vertices
	class CCVertex {
		Graph.Vertex element;
		Graph.Vertex parent;
		boolean seen;
		int cno;
		
		
		CCVertex(Graph.Vertex u) {
		    element = u;
		    parent = null;
		    seen = false;
		    cno = -1;
		}
	    }
	    // Algorithm uses a parallel array for storing information about vertices
	    static CCVertex[] ccVertex;
	    Graph g;

	    /**
	     * Constructor of the class
	     * @param g : input graph
	     */
	    public CheckEulerian(Graph g) {
		this.g = g;
		ccVertex = new CCVertex[g.size()];
		for(Graph.Vertex u: g) { ccVertex[u.name] = new CCVertex(u); }
	    }
	
	    
	   /**
	    * dfs is used to get each vertex and check if the vertex is not seen then call dfsVisit with the component number
	    * 
	    * @param g : input graph 
	    * @return : returns the component number
	    */
    static int dfs(Graph g){
    	for(Graph.Vertex u : g){
    		CCVertex ccu = getCCVertex(u);
    		ccu.seen = false;
    		ccu.parent=null;
    	}
    	int cno=0; // Initializing count variable to check for connected components
    	for(Graph.Vertex u : g){
    		if(!seen(u))
    			dfsVisit(u, ++cno);
    	}
    	return cno;
    }
    
    static CCVertex getCCVertex(Graph.Vertex u) {
	return ccVertex[u.name];
    }
    
    //Marks the vertex as seen
    static boolean seen(Graph.Vertex u) {
    	CCVertex ccu = getCCVertex(u);
    	return ccu.seen;
        }
    
    
    /**
     * dfsVisit runs on the vertex sent by dfs to find the deepest path in the graph
     * @param u : vertex u sent by dfs
     * @param cno : component number of corresponding vertex
     */
    static void dfsVisit(Graph.Vertex u, int cno){
    	CCVertex ccu = getCCVertex(u);
    	ccu.seen = true;
    	ccu.cno=cno;
    	for(Graph.Edge e: u.adj){
    		Graph.Vertex v=e.otherEnd(u);
    		if(!seen(v)){
    			CCVertex ccv = getCCVertex(v);
    			ccv.parent=u;
    			dfsVisit(v,cno);
    		}
    	}
    }
    
    
    /**
     * Boolean function to check if the input graph is Eulerian or not
     * @param g : input graph
     * @return : returns true if graph is Eulerian else returns false
     */
    static boolean testEulerian(Graph g){
    	Graph.Vertex v1=null,v2=null;
    	int conn = dfs(g);
    	if(conn > 1){
    		System.out.println("The graph is not connected.");
    		return false;
    	}
    	
    	int odd=0;
    	
    	for(Graph.Vertex u: g){
    		if(u.adj.size()%2!=0){
    			odd++;
    			if(odd==1){
    				v1=u;
    			}
    			else if(odd==2){
    				v2=u;
    			}		
    		}
    	}
    	if(odd==0){
    		System.out.println("Graph is Eulerian.");
    		return true;
    	}
    	else if(odd==2){
    		System.out.println("Graph has an Euler path between vertices\t"+v1+" and "+v2+".");
    		return false;
    	}
    	else{
    		System.out.println("Graph is not Eulerian. It has "+odd+" vertices of odd degree.");
    		return false;
    	}
    
    }
    
    /**
     * Main class reads the graph input file from user and checks whether the given graph is Eulerian or not
     * @param args : command line input
     * @throws FileNotFoundException : Exception if input file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
    	Scanner in;
    	if(args.length>0){
    		File inputFile=new File(args[0]);
    		in = new Scanner(inputFile);
    	}
    	else {
    		in = new Scanner(System.in);
    	}
    	//readGraph method to read the input graph from File or console.
    	Graph g =  Graph.readGraph(in);
    	CheckEulerian ce = new CheckEulerian(g);
    	testEulerian(g);
    }
}
