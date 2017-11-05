package cs6301.g21;

import sun.security.provider.certpath.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    static void initialize(XGraph g, XGraph.XVertex s){
        for(Graph.Vertex u : g){
            XGraph.XVertex uX = (XGraph.XVertex) u;
            uX.setSeen(false);
            uX.setDis(Integer.MAX_VALUE);
            uX.setParent(null);
        }
        s.setDis(0);
    }

    static boolean relax(XGraph.XEdge e){
        XGraph.XVertex u = (XGraph.XVertex) e.from;
        XGraph.XVertex v = (XGraph.XVertex) e.to;
        if(v.getDis() > u.getDis() + e.weight){
            v.setDis(u.getDis() + e.weight);
            v.setParent(u);
            return true;
        }
        return false;
    }

    static void shortestPath(XGraph g, XGraph.XVertex s){
        initialize(g,s);

        IndexedHeap<XGraph.XVertex> pq= new IndexedHeap<>(new XGraph.XVertex[g.size()], new VertexDistance(), 0);
//        PriorityQueue<XGraph.XVertex> pq = new PriorityQueue<>(g.size(),new VertexDistance());
        for(Graph.Vertex u : g) pq.add((XGraph.XVertex) u);

        while(!pq.isEmpty()){
//            XGraph.XVertex u = pq.poll();
            XGraph.XVertex u = pq.remove();
            u.setSeen(true);
            for(Graph.Edge e: u){
                if(relax((XGraph.XEdge)e)){
                    XGraph.XVertex changed = (XGraph.XVertex)e.to;
//                    pq.remove(changed);
//                    pq.add(changed);
                    pq.decreaseKey(changed);
//                    System.out.println(changed + " = " + changed.getDis());
                }
            }
        }
    }

    public static void main(String args[]){
        try {
            Scanner sf1 = new Scanner(new File("/home/uks/Desktop/CS 6301/Implementation-of-Advanced-Algorithms/LP-4/src/cs6301/g21/rewardGraph.in"));
            //Same graph with cycle, second run uses undirected graph
            System.out.print("Graph 1: ");
            Graph graph1 = Graph.readGraph(sf1);
            XGraph ge1 = new XGraph(graph1);
            Dijkstra.shortestPath(ge1, ge1.getVertex(1));
            for(Graph.Vertex v : ge1){
                System.out.println( "d " + v + " : " + ((XGraph.XVertex)v).getDis());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
