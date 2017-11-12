package cs6301.g21;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Reward Collection Problem.
 *
 *
 * @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Vishwanath Rao
 * @version 1.0: 2017/11/05 
 *
 */
public class RewardCollector {
    static HashMap<Graph.Vertex, Integer> rewards;
    static XGraph graph;
    static XGraph.XVertex source;
    
    /**
	 * Public interface which sets up the problem
	 *
	 * @param g : graph where reward collection will happen
     * @param s : start vertex
     * @param vertexRewardMap : map with reward value of each vertex
     * @param tour : the best reward tour
	 * @return  : int : maximum possible reward
	 */
    public static int collect(XGraph g, Graph.Vertex s, HashMap<Graph.Vertex, Integer> vertexRewardMap, List<Graph.Vertex> tour){
        graph = g;
        rewards = vertexRewardMap;
        source = (XGraph.XVertex) s;

        Dijkstra.shortestPath(g, source);

        for(Graph.Vertex v : g) {
            ((XGraph.XVertex) v).setSeen(false);
        }
        int best = maxReward(source, tour, 0, 0);
        tour.add(s);
        Collections.reverse(tour);
        return best;
    }
    
    /**
	 * Recursive function which calculates max reward based on max reward of adjacent nodes 
	 * Like backtracking + enumeration
     *
     * @param s : vertex to visit
     * @param tour : the best reward tour
     * @param d : distance traveled so far
     * @param r : reward collected so far
	 * @return  : int : maximum possible reward for tour from this node
	 */
    static int maxReward(XGraph.XVertex s, List<Graph.Vertex> tour, int d, int r){
        if(!s.getSeen()) { //unexplored vertex
            int sPos = tour.size();
            int curLoc = tour.size();
            int tempReward = r;
            
            if(d <= s.getDis()) //reached this node using shortest path, so collect reward
                tempReward += rewards.get(s);
            s.setSeen(true);
            int bestReward = Integer.MIN_VALUE;
            XGraph.XVertex bestChild = null;
            for (Graph.Edge e : s) {
                XGraph.XVertex neighbor = (XGraph.XVertex) e.otherEnd(s);
                //recursive call to adjacent vertices
                int m = maxReward(neighbor, tour, d+e.weight, tempReward);
                if( m > bestReward) {
                    //remove everythig betw sPos and curLoc before moving ahead, as this is a path which we no longer use
                    int itemsToRemove = curLoc - sPos;
                    while (itemsToRemove > 0){
                        tour.remove(sPos);
                        itemsToRemove--;
                    }
                    curLoc = tour.size();
                    bestReward = m;
                    bestChild = neighbor;
                }else{
                    //current path is rejected so remove it
                    while(tour.size() > curLoc)
                        tour.remove(tour.size()-1);
                }
            }

            if(bestChild != null){
                //precaution against unwanted vertices in path
                while(tour.size() > curLoc)
                    tour.remove(tour.size()-1);
                
                tour.add(bestChild);
            }
            s.setSeen(false);

            return bestReward;
        }
        else if(s.equals(source)){//tour complete
            return r;
        }
        else {//no path to finish the tour from this vertex
            return Integer.MIN_VALUE;
        }

    }
}
