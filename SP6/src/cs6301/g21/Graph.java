/**
 * Class to represent a graph
 *  @author rbk
 *  Ver 1.1: 2017/08/28.  Updated some methods to public.  Added getName() to Vertex
 *  Ver 1.2: 2017/09/08.  Added getVertex() method for GraphAlgorithm.java
 *  Ver 1.3: 2017/09/28.  Added isDirected() and additional Vertex constructor
 *
 */

package cs6301.g21;
import cs6301.g00.ArrayIterator;

import java.util.*;

public class Graph implements Iterable<Graph.Vertex> {
    Vertex[] v; // vertices of graph
    int n; // number of vertices in the graph
    boolean directed;  // true if graph is directed, false otherwise
    static int numEdges; //number of edges on the graph

    //populate edges to get edges array
    public static Comparator<Edge> edgeComparator = new Comparator<Edge>(){

        public int compare(Graph.Edge o1, Graph.Edge o2) {
            if(o1.getWeight() > o2.getWeight())
                return 1;
            else if(o1.getWeight() < o2.getWeight())
                return -1;

            return 0;
        }

    };

    /**
     * Nested class to represent a vertex of a graph
     */
    public static class Vertex implements Iterable<Edge> {
        int name; // name of the vertex
        List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
        boolean seen;

        /**
         * Constructor for the vertex
         *
         * @param n
         *            : int - name of the vertex
         */
        Vertex(int n) {
            seen = false;
            name = n;
            adj = new LinkedList<Edge>();
            revAdj = new LinkedList<Edge>();   /* only for directed graphs */
        }

        Vertex(Vertex u) {
            seen = false;
            name = u.name;
            adj = u.adj;
            revAdj = u.revAdj;
        }

        public List<Edge> getAdj() {
            return adj;
        }

        /**
         * Method to get name of a vertex.
         *
         */
        public int getName() {
            return name;
        }

        public boolean isSeen() {
            return seen;
        }

        public void setSeen(boolean sign){ this.seen = sign; }

        public Iterator<Edge> iterator() { return adj.iterator(); }

        // Helper function for parallel arrays used to store vertex attributes
        public static<T> T getVertex(T[] node, Vertex u) {
            return node[u.name];
        }

        /**
         * Method to get vertex number.  +1 is needed because [0] is vertex 1.
         */
        public String toString() {
            return Integer.toString(name+1);
        }
    }

    /**
     * Nested class that represents an edge of a Graph
     */
    public static class Edge {
        Vertex from; // head vertex
        Vertex to; // tail vertex
        int weight;// weight of edge

        /**
         * Constructor for Edge
         *
         * @param u
         *            : Vertex - Vertex from which edge starts
         * @param v
         *            : Vertex - Vertex on which edge lands
         * @param w
         *            : int - Weight of edge
         */
        Edge(Vertex u, Vertex v, int w) {
            from = u;
            to = v;
            weight = w;

        }

        public Vertex getFrom() {
            return from;
        }

        public Vertex getTo() {
            return to;
        }

        /**
         * Method to find the other end end of an edge, given a vertex reference
         * This method is used for undirected graphs
         *
         * @param u
         *            : Vertex
         * @return
        : Vertex - other end of edge
         */
        public Vertex otherEnd(Vertex u) {
            assert from == u || to == u;
            // if the vertex u is the head of the arc, then return the tail else return the head
            if (from == u) {
                return to;
            } else {
                return from;
            }
        }

        /**
         * Return the string "(x,y)", where edge goes from x to y
         */
        public String toString() {
            return "(" + from + "," + to + ")";
        }

        public String stringWithSpaces() {
            return from + " " + to + " " + weight;
        }

        public int getWeight(){
            return weight;
        }
    }

    /**
     * Constructor for Graph
     *
     * @param n
     *            : int - number of vertices
     */
    public Graph(int n) {
        this.n = n;
        this.v = new Vertex[n];
        this.numEdges = 0;
        this.directed = false;  // default is undirected graph
        // create an array of Vertex objects
        for (int i = 0; i < n; i++)
            v[i] = new Vertex(i);
    }

    public Graph(Graph g) {
        n = g.n;
        v = g.v;
        directed = g.directed;
    }

    /**
     * Find vertex no. n
     * @param n
     *           : int
     */
    public Vertex getVertex(int n) {
        return v[n-1];
    }

    public static int getNumEdges() { return numEdges; }

    /**
     * Method to add an edge to the graph
     *
     * @param from
     *            : int - one end of edge
     * @param to
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    public void addEdge(Vertex from, Vertex to, int weight) {
        Edge e = new Edge(from, to, weight);
        if(this.directed) {
            from.adj.add(e);
            to.revAdj.add(e);
        } else {
            from.adj.add(e);
            to.adj.add(e);
        }
        numEdges++;
    }

    public int size() {
        return n;
    }

    public boolean isDirected() {
        return directed;
    }

    /**
     * Method to create iterator for vertices of graph
     */
    public Iterator<Vertex> iterator() {
        return new ArrayIterator<Vertex>(v);
    }

    // read a directed graph using the Scanner interface
    public static Graph readDirectedGraph(Scanner in) {
        return readGraph(in, true);
    }

    // read an undirected graph using the Scanner interface
    public static Graph readGraph(Scanner in) {
        return readGraph(in, false);
    }

    public static Graph readGraph(Scanner in, boolean directed) {
        // read the graph related parameters
        int n = in.nextInt(); // number of vertices in the graph
        int m = in.nextInt(); // number of edges in the graph

        // create a graph instance
        Graph g = new Graph(n);
        g.directed = directed;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            g.addEdge(g.getVertex(u), g.getVertex(v), w);
        }
        return g;
    }

}