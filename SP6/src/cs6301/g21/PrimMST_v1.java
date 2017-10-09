/* Ver 1.0: Starter code for Prim's MST algorithm */

package cs6301.g21;

import cs6301.g00.Timer;
import cs6301.g21.Graph.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;


public class PrimMST_v1 {

    Graph g;

    public PrimMST_v1(Graph g) {
        this.g = g;
    }

    /**
     * Version 1 for Prim algorithm
     * @param s : Root vertex
     * @return int: weighted MST
     */
    public int prim1(Graph.Vertex s) {
        int wmst = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Graph.getNumEdges(),Graph.edgeComparator);

        Graph.Vertex u, v, addToMST;
        //mark root as seen
        s.setSeen(true);

        //add source edges to the priority queue
        for (Edge e : s.getAdj()) {
            priorityQueue.add(e);
        }

        //pop elements from priority queue
        while (!priorityQueue.isEmpty()) {
            Edge e = priorityQueue.remove();
            u = e.getFrom();
            v = e.getTo();

            //ignore vertices if u and v in MST
            if (u.isSeen() && v.isSeen())
                continue;
            //if u is already in the MST, consider v to be added to MST
            if (u.isSeen()) {
                addToMST = v;

            }
            //if u is not in MST, consider u to be added to MST
            else {
                addToMST = u;
            }

            //add the weight of edge to MST
            wmst += e.getWeight();
            //mark the vertex as seen
            addToMST.setSeen(true);

            //only insert edge to priorityQueue if other end is not seen already
            for (Edge f : addToMST.getAdj()) {
                Graph.Vertex w = f.otherEnd(addToMST);
                if (!w.isSeen())
                    priorityQueue.add(f);
            }
        }

        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in);
        Graph.Vertex s = g.getVertex(1);

        Timer timer = new Timer();
        PrimMST_v1 mst = new PrimMST_v1(g);
        int wmst = mst.prim1(s);
        timer.end();
        System.out.println(wmst);
    }
}