package cs6301.g21;

public class GraphExtended {

    class ExtendedVertex{
        Graph.Vertex element;
        boolean seen;
        int cno;
        ExtendedVertex(Graph.Vertex u) {
            element = u;
            seen = false;
            cno = -1;
        }
    }

    ExtendedVertex[] xVertex;
    Graph g;

    public GraphExtended(Graph g) {
        this.g = g;
        xVertex= new ExtendedVertex[g.size()];
        for(Graph.Vertex u: g) { xVertex[u.name] = new ExtendedVertex(u); }
    }


}
