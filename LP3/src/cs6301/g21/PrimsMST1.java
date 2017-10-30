/* Ver 1.0: Starter code for Prim's MST algorithm */

package cs6301.g21;

import cs6301.g21.Graph.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;


public class PrimMST1{

    Graph g;
    static GraphExtended ge;
    static int count;

    public PrimMST1(Graph g) {
        this.g = g;
        ge = new GraphExtended(g);
        count=0;
    }
    
    public static boolean containsMST(Graph g, Graph.Vertex s){
    	 PrimMST1 mst = new PrimMST1(g);
         int wmst = mst.prim1(s);
         
         if(count==ge.size()-1)
         	return true;
         else
         	return false;
    }

    /**
     * Version 1 for Prim algorithm
     * @param s : Root vertex
     * @return int: weighted MST
     */
    public int prim1(Graph.Vertex s) {
        int wmst = 0;
//        int count=0;
        

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(ge.getNumOfEdges(),ge.edgeComparator);

        Graph.Vertex u, v, addToMST;
        //mark root as seen
        ge.setSeen(s,true);

        //add source edges to the priority queue
        for (Edge e :ge.getAdj(s)) {
            priorityQueue.add(e);
        }

        //pop elements from priority queue
        while (!priorityQueue.isEmpty()) {
            Edge e = priorityQueue.remove();
            u = e.fromVertex();
            v = e.toVertex();

            //ignore vertices if u and v in MST
            if (ge.isSeen(u) && ge.isSeen(v))
                continue;
            //if u is already in the MST, consider v to be added to MST
            if (ge.isSeen(u)) {
                addToMST = v;

            }
            //if u is not in MST, consider u to be added to MST
            else {
                addToMST = u;
            }

            //add the weight of edge to MST
            wmst += e.getWeight();
            count++;
            //mark the vertex as seen
            ge.setSeen(addToMST,true);

            //only insert edge to priorityQueue if other end is not seen already
            for (Edge f : ge.getAdj(addToMST)) {
                Graph.Vertex w = f.otherEnd(addToMST);
                if (!ge.isSeen(w))
                    priorityQueue.add(f);
            }
        }

        return wmst;
    }

//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in;
//
//        if (args.length > 0) {
//            File inputFile = new File(args[0]);
//            in = new Scanner(inputFile);
//        } else {
//            in = new Scanner(System.in);
//        }
//
//        Graph g = Graph.readDirectedGraph(in);
//        Graph.Vertex s = g.getVertex(1);
//
////        Timer timer = new Timer();
////        PrimMST1 mst = new PrimMST1(g);
////        int wmst = mst.prim1(s);
//        
////        timer.end();
//        System.out.println(PrimMST1.containsMST(g,s));
//    }
}
