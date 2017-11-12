package cs6301.g21;

import java.util.Iterator;

/**
 * @author Vibha Belavadi, Umang Shah, Abhishek Jagwani, Shreya Vishwanath Rao
 * @version 1.0: 2017/11/05 
 */
public class ConstrainedShortestPath {

    /**
     * Performs a modified Bellman Ford Take 1 to give shortest path of atmost k edges
     * @param g : XGraph
     * @param start : the source vertex
     * @param end : the destination vertex
     * @param k : the maximum number of edges allowed
     * @return
     */
    public static int getPaths(XGraph g, XGraph.XVertex start, Graph.Vertex end, int k){

        //get a distance array
        boolean noChange = false;
        int numVert = g.n;
        int [][] vertDist = new int[numVert][k+1];

        //initialize all the distances
        for(int i=0; i<numVert; i++){
            vertDist[i][0] = Integer.MAX_VALUE;
            for(int j=1; j<k+1; j++)
                vertDist[i][j] = 0;
        }

        //set parents to be null
        for(Graph.Vertex u : g){
            ((XGraph.XVertex) u).setParent(null);
        }

        //initialise distance for source to be zero:
        vertDist[start.name][0] = 0;

        //go through as many iterations as number of vertices required
        for(int i=1; i<=k; i++){
            noChange = true;

            for(Graph.Vertex u : g){
                vertDist[u.name][i] = vertDist[u.name][i-1];
                Iterator<Graph.Edge> edgeIterator = ((XGraph.XVertex) u).revIterator();

                while (edgeIterator.hasNext()){
                    Graph.Edge e = edgeIterator.next();
                    Graph.Vertex p = e.from;

                    if(vertDist[p.name][i-1] != Integer.MAX_VALUE && vertDist[u.name][i] > (vertDist[p.name][i-1] + e.getWeight())){
                        vertDist[u.name][i] = (vertDist[p.name][i-1] + e.getWeight());
                        ((XGraph.XVertex) u).parent = (XGraph.XVertex) p;
                        noChange = false;
                    }

                }
            }

            //if no change update the distance
            if(noChange){
                for(Graph.Vertex u : g){
                    ((XGraph.XVertex) u).dis = vertDist[u.name][i];
                }
                return vertDist[end.name][i];
            }
        }

        return vertDist[end.name][k];
    }

}
