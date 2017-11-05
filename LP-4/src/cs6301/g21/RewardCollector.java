package cs6301.g21;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RewardCollector {
    static HashMap<Graph.Vertex, Integer> rewards;
    static XGraph graph;
    static XGraph.XVertex source;
    public static int collect(XGraph g, Graph.Vertex s, HashMap<Graph.Vertex, Integer> vertexRewardMap, List<Graph.Vertex> tour){
        graph = g;
        rewards = vertexRewardMap;
        source = (XGraph.XVertex) s;

        Dijkstra.shortestPath(g, source);

        for(Graph.Vertex v : g) {
           // System.out.println( "d " + v + " : " + ((XGraph.XVertex)v).getDis());
            ((XGraph.XVertex) v).setSeen(false);
        }
        int best = maxReward(source, tour, 0, 0);
        tour.add(s);
        Collections.reverse(tour);
        return best;
    }

    static int maxReward(XGraph.XVertex s, List<Graph.Vertex> tour, int d, int r){
//        System.out.println(s + ", " + d + ", " + r);
        if(!s.getSeen()) {
            int curLoc = tour.size();
            int tempReward = r;
            if(d <= s.getDis())
                tempReward += rewards.get(s);
            s.setSeen(true);
            int bestReward = Integer.MIN_VALUE;
            XGraph.XVertex bestChild = null;
            for (Graph.Edge e : s) {
                XGraph.XVertex neighbor = (XGraph.XVertex) e.otherEnd(s);
                int m = maxReward(neighbor, tour, d+e.weight, tempReward);
                if( m > bestReward) {
                    while(tour.size() > curLoc)
                        tour.remove(tour.size()-1);
                    curLoc = tour.size();
                    bestReward = m;
                    bestChild = neighbor;
                }
            }

            if(bestChild != null){
                while(tour.size() > curLoc)
                    tour.remove(tour.size()-1);
                tour.add(bestChild);
            }
            s.setSeen(false);

            return bestReward;
        }
        else if(s.equals(source)){
            return r;
        }
        else {
            return Integer.MIN_VALUE;
        }

    }
}
