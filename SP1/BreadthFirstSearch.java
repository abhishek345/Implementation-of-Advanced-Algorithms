/**
 *  Performs BFS on a given graph and returns path to farthest node.
 *  @author Umang Shah, Vibha Belavadi, Abhishek Jagwani, Shreya Rao
 *  Ver 1.0: 2017/08/28.  
 *
 */
 
package cs6301.g21;

import java.util.LinkedList;

public class BreadthFirstSearch {

    class DiameterVertex{
        // Currently 2 parallel arrays - visited and parent are used
        // this can be replaced by extending the Vertex class similar to CCVertex used by prof in CC graph example
        // the new variables for visited and parent can be then replaced in the BFS program
    }

    static LinkedList<Graph.Vertex> bfs(Graph g, Graph.Vertex v){
        Graph.Vertex farthestNode = v;
        //queue for BFS
        LinkedList<Graph.Vertex> queue = new LinkedList<>();
        //array to mark visited vertices
        boolean[] visited = new boolean[g.size()];
        //array to store parent in BFS path
        Graph.Vertex[] parent = new Graph.Vertex[g.size()];
        //initalize to default values
        for(int i=0;i < parent.length; i++)
            parent[i] = null;
        for(int i=0;i < visited.length;i++){
            visited[i] = false;
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
