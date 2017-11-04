package cs6301.g21;

import java.util.PriorityQueue;

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
        PriorityQueue<XGraph.XVertex> pq = new PriorityQueue<>(g.size(),new VertexDistance());
        for(Graph.Vertex u : g) pq.add((XGraph.XVertex) u);

        while(!pq.isEmpty()){
            XGraph.XVertex u = pq.poll();
            u.setSeen(true);
            for(Graph.Edge e: u){
                relax((XGraph.XEdge)e);
            }
        }
    }
}
