package cs6301.g21;

import java.util.List;
import java.util.Stack;

public class DirectedMST {
    XGraph dirGraph;
    XGraph.XVertex root;
    Stack<XGraph.XVertex> expanded;

    public DirectedMST(Graph.Vertex start, Graph g){
        //create XGraph from g
        dirGraph = new XGraph(g);
        root = dirGraph.getVertex(start);
    }

    public int construct(List edgeList){
        //make zero edge graph
        boolean stillPossibleToFindMST = true; // dummy
        while(stillPossibleToFindMST) {
            if (!zeroTree(edgeList)) {
                //findSCC() - this should mark component nos
                //using comp nos, shrink()
                shrink();
                return 0;
            }
            //else found mst, end.
            else {
                expand();
                //calc weight from edgeList and return
                //optional: restore Graph to original state , reverse all actions
                return 1;
            }
        }
        return -1;
    }

    public boolean zeroTree(List edgeList){
        //for each vertex in dirGraph
        //go through all incoming edges (revEdge)
        //pick min weight
        //set XVertex.setDecrement(min weight) - this will subtract weight from the edges
        //disable non zero edges

        //try bfs/dfs from root use only zero edges
        //if it has all edges, it is mst
        //restore weights - XVertex.resetDecrement()
        //fill edges into edgeList also calc weight
        //
        return true;
        //else false
    }

    public void shrink(){
        //for vertices having same comp no do:
        //create super node super1;
            //super1 = XVertex.add(comp number) - should do the foll:
                //create a vertex
                //add to hash map as <xvertex, list<xvertex>> this will be used for restoring
                //find min edge from incoming vertices, add it as new edges to super1.
                //disable all other edges
        //stack.push(super1);
    }

    public void expand(){
        while(expanded.isEmpty()){
            //expand 1 by 1 from stack
            //pop super node from stack
            //resetDecrement()
            //get vertices contained within it from hash map
            //remove all edges associated to it by assigning null
            //remove the edge (assign null)

        }
    }
}
