package cs6301.g21;

import java.util.*;

public class DirectedMST {
    XGraph dirGraph;
    XGraph.XVertex root;
    Stack<XGraph.XVertex> expanded;
    HashMap<XGraph.XVertex, List<XGraph.XVertex>> supernodeMap;
    /**
     * Constructor
     * @param start node which will be root of mst
     * @param g instance of Graph class, will be used to make XGraph
     */
    public DirectedMST(Graph.Vertex start, Graph g){
        //create XGraph from g
        dirGraph = new XGraph(g);
        root = dirGraph.getVertex(start);
    }

    /**
     * construct a mst from the graph
     * @param edgeList edges forming the mst
     * @return weight of mst
     */
    public int construct(List edgeList){
        boolean stillPossibleToFindMST = true; // dummy
        while(stillPossibleToFindMST) {
            if (zeroTree(edgeList) < 0) {
                int comp = 0;
                try {
                    comp = StronglyConnectedComponent.stronglyConnectedComponents(dirGraph);
                    // there will be "comp" components and they will be marked
                }
                catch (CyclicGraphException ce){
                    //exception will never be generated as cycle checking is disabled
                }
                //findSCC() - this should mark component nos
                shrink(comp);//using comp nos, shrink()
                return 0;
            }
            //else found mst, end.
            else {
                expand();
                //calc weight from edgeList and return
                int weight = 0;
                for(Object xe: edgeList){
                    weight += ((XGraph.XEdge) xe).getWeight();
                }
                return weight;
                //optional: if not already done restore Graph to original state , reverse all actions
            }
        }
        return -1;
    }

    public int zeroTree(List edgeList){
        //for each vertex in dirGraph
        //go through all incoming edges (revEdge)
        //pick min weight
        //set XVertex.setDecrement(min weight) - this will subtract weight from the edges
        //disable non zero edges

        //try bfs/dfs from root use only zero edges
        //if it has all edges, it is mst
        //restore weights - XVertex.resetDecrement()
        //fill edges into edgeList also calc weight
        //return weight
        return -1;
    }

    /**
     *
     * @param C no of components that will be present in current graph
     */
    public void shrink(int C){
        ArrayList<List<XGraph.XVertex>> componentVertices = new ArrayList<>();
        for(int i=0;i < C; i++){
            componentVertices.add(new ArrayList<>());
        }
        //get vertex iterator, start at some u
        for(Graph.Vertex u: dirGraph){
            //store vertices in respective list.
            XGraph.XVertex uX = (XGraph.XVertex) u;
            componentVertices.get(uX.getVCno()).add(uX);
        }
        //find and record edges to other components
        ArrayList<List<XGraph.XEdge>> minEdges = new ArrayList<>();
        for(List vertexList: componentVertices){
            minEdges.add(new ArrayList<>());
            for(Object uX: vertexList){
                Iterator eItr = ((XGraph.XVertex)uX).iterator();
                while(eItr.hasNext()){

                }
            }
        }
        List<XGraph.XVertex> newNodes = XGraph.createComponents(minEdges);
        for(int i=0;i < C;i++){
            //array list so it is ok to use for loop
            supernodeMap.put(newNodes.get(i), componentVertices.get(i));
            expanded.push(newNodes.get(i));
        }
        //create super node super1;
            //super1 = XVertex.add(comp number) - should do the foll:
                //create a vertex
                //add to hash map as <xvertex, list<xvertex>> this will be used for restoring
                //find min edge from incoming vertices, add it as new edges to super1.
                    //min edges will be recorded during component iteration, keep just these edges, disable the rest
                    //create new edges between every Ci, Cj which has an edge from prev step
                    //disable original edges, only newly added edges betw supernode are active now

                //disable all other edges
        //stack.push(super1);
    }

    public void expand(){
        //test variables for expand
        while(expanded.isEmpty()){
            //expand 1 by 1 from stack
            //pop super node from stack
            //get vertices contained within it from hash map
            //go through each vertex and enable edges
            //remove all edges associated to super node by assigning null
            //remove the super node (assign null)
        }
    }
}
