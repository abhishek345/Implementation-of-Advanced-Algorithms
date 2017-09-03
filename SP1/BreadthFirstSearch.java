/**
 *  Performs BFS on a given graph and returns path to farthest node.
 *  @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Rao
 *  Ver 1.1: 2017/09/01.  
 *
 */
 
package cs6301.g21;

import java.util.LinkedList;

public class BreadthFirstSearch {
    /**
		 * BreadthFirstSearch for Graph Class
		 * 
		 * Standard BFS using queues for the Graph class, 
		 * starting from the vertex specified, returns the
		 * path to the farthest node in the graph.
		 *
		 * @param g: Graph : the graph on which BFS is performed
		 * @param v: Graph.Vertex : the vertex to start BFS from
		 * 
		 */
    static LinkedList<Graph.Vertex> bfs(Graph g, Graph.Vertex v){
        Graph.Vertex farthestNode = v;
        //queue for BFS
        LinkedList<Graph.Vertex> queue = new LinkedList<>();
        //array to mark visited vertices
        boolean[] visited = new boolean[g.size()];
        //array to store parent in BFS path
        Graph.Vertex[] parent = new Graph.Vertex[g.size()];
        //initalize to default values
        for(int i=0;i < visited.length;i++){
            visited[i] = false;
            parent[i] = null;
        }
        
        //Add starting vertex to the queue
        queue.add(v);
        visited[v.getName()] = true;
        while(queue.size() > 0){
            //get first node from queue
            Graph.Vertex current_node = queue.pop();
            farthestNode = current_node;
            //for each node adj which is not visited add to queue
            for(Graph.Edge u : current_node.adj){
                if(!visited[u.otherEnd(current_node).getName()]){
                    queue.add(u.otherEnd(current_node));
                    parent[u.otherEnd(current_node).getName()] = current_node;
                    visited[u.otherEnd(current_node).getName()] = true;
                }
            }
        }

        LinkedList<Graph.Vertex> bfsList = new LinkedList<>();

        while(farthestNode != null){
            bfsList.addFirst(farthestNode);
            farthestNode = parent[farthestNode.getName()];
        }
        return bfsList;
    }
}
