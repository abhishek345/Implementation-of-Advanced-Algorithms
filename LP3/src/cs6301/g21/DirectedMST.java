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
        supernodeMap = new HashMap<>();
        expanded = new Stack<>();
        boolean stillPossibleToFindMST = true; // dummy
        while(stillPossibleToFindMST) {
            System.out.println("Find tree ");
            int weight = zeroTree(edgeList);
            if (weight < 0) {
                System.out.println("Need shrinking ");
                int comp = 0;
                try {
                    comp = StronglyConnectedComponent.stronglyConnectedComponents(dirGraph);
                    // there will be "comp" components and they will be marked
                }
                catch (CyclicGraphException ce){
                    //exception will never be generated as cycle checking is disabled
                }
                //findSCC() - this should mark component nos
                System.out.println(comp + " - no of components ");
                shrink(comp);//using comp nos, shrink()
                //return 0;
            }
            //else found mst, end.
            else {
                System.out.println("Weight not -1");
                /*
                expand();
                //calc weight from edgeList and return
                int weight = 0;
                for(Object xe: edgeList){
                    weight += ((XGraph.XEdge) xe).getWeight();
                }*/
                return weight;
                //optional: if not already done restore Graph to original state , reverse all actions
            }
        }
        return -1;
    }

    public int zeroTree(List<XGraph.XEdge> edgeList){

        int w = 0;

        //minWeight is used to get minWeight for each vertex
        int minWeight = Integer.MAX_VALUE;

        //iterate through each vertex in dirGraph
        XGraph.XGraphIterator it = (XGraph.XGraphIterator) dirGraph.iterator();
        while (it.hasNext()){

            //get the current vertex
            XGraph.XVertex xcur = (XGraph.XVertex) it.next();
            Iterator<Graph.Edge> edgeIterator = xcur.revIterator();

            //if no incoming edge exists, move on to next vertex
//            if(xcur.revAdj.size() == 0)
//                continue;
            XGraph.XEdge x;
            if(!edgeIterator.hasNext())
                continue;
            //pick min weight
            else {
                x = (XGraph.XEdge) edgeIterator.next();
                minWeight = x.weight;
            }
            while (edgeIterator.hasNext()){
                x = (XGraph.XEdge) edgeIterator.next();
                if(x.weight < minWeight)
                    minWeight = x.weight;
            }

            //subtract minWeight from the edges for each vertex
            xcur.setDecrement(minWeight);

            //reset minWeight for next vertex
            minWeight = Integer.MAX_VALUE;
        }


        //try bfs/dfs from root use only zero edges
        //if it has traversed all vertices, it is mst
        LinkedList<XGraph.XVertex> xVertices = null;
        try {
            xVertices = DFS.DFSCall(dirGraph);
        } catch (CyclicGraphException e) {
            //wont be thrown
        }
        XGraph.XGraphIterator it1 = (XGraph.XGraphIterator) dirGraph.iterator();
        while (it1.hasNext()){
            XGraph.XVertex xcur = (XGraph.XVertex) it1.next();
            if(xcur.seen == false)
                return -1;
        }
        System.out.println("Found MST ");

        //restore weights
        for(XGraph.XVertex v : xVertices){
            System.out.println("v: " + v);
            //fill edges into edgeList also calc weight
            Iterator<Graph.Edge> edgeIterator = v.revIterator();
            while (edgeIterator.hasNext()){

                XGraph.XEdge xEdge = (XGraph.XEdge) edgeIterator.next();
                if(xEdge.weight == 0) {
                    edgeList.add(xEdge);
                }
            }
            w = w + v.weightDecrease;
            v.resetDecrement();
        }
        System.out.println(edgeList.size());
        //return weight
        return w;
    }


    /**
     *
     * @param C no of components that will be present in current graph
     */
    public void shrink(int C){
        System.out.println("Now shrinking ");
        //hold the vertices within each component
        ArrayList<List<XGraph.XVertex>> componentVertices = new ArrayList<>();
        //init to empty components
        for(int i=0;i <= C; i++){
            componentVertices.add(new ArrayList<>());
        }
        //get vertex iterator, start at some u
        for(Graph.Vertex u: dirGraph){
            //store vertices in respective list.
            XGraph.XVertex uX = (XGraph.XVertex) u;
            //add to corresp component list, where index i = component i
            componentVertices.get(uX.getVCno()).add(uX);
        }
        //find and record edges to other components
        ArrayList<List<XGraph.XEdge>> minEdges = new ArrayList<>();
        //go through vertices in each component
        for(List vertexList: componentVertices){
            //minEdges.add(new ArrayList<>());
            //outgoing min edge to all reachable components
            HashMap<Integer, XGraph.XEdge> temp = new HashMap<>();
            //go through edges of each vertex
            for(Object uX: vertexList){
                Iterator eItr = ((XGraph.XVertex)uX).iterator();
                while(eItr.hasNext()){
                    XGraph.XEdge e = ((XGraph.XEdge) eItr.next());
                    //comp no of To vertex
                    int cnum = ((XGraph.XVertex)e.to).getVCno();
                    int wt = e.weight;
                    XGraph.XEdge minE = temp.get(cnum);
                    //track and update min outgoing edge to each Cj from current comp Ci
                    if(minE == null){
                        temp.put(cnum, e);
                    }else{
                        if(wt < minE.weight){
                            temp.put(cnum, e);
                        }
                    }

                }
                ((XGraph.XVertex) uX).disable();
            }
            //minEdges.add(new ArrayList<>());
            ArrayList<XGraph.XEdge> tempList = new ArrayList<>();
            for(Map.Entry<Integer, XGraph.XEdge> m: temp.entrySet()){
                //disable these edges
                m.getValue().disabled = true;
                tempList.add(m.getValue());
            }
            minEdges.add(tempList);
            //disable original edges, only newly added edges betw supernode are active now
        }
        //create super node super1;
        List<XGraph.XVertex> newNodes = dirGraph.createComponents(minEdges);
        //super1 = XVertex.add(comp number) - should do the foll:
        //create a vertex
        //add to hash map as <xvertex, list<xvertex>> this will be used for restoring
        //find min edge from incoming vertices, add it as new edges to super1.
        //min edges will be recorded during component iteration, keep just these edges, disable the rest
        //create new edges between every Ci, Cj which has an edge from prev step
        for(int i=0;i <= C;i++){
            //array list so it is ok to use for loop
            supernodeMap.put(newNodes.get(i), componentVertices.get(i));
            //stack.push(super1);
            expanded.push(newNodes.get(i));
        }

    }

    public void expand(){
        //test variables for expand

        //expand 1 by 1 from stack
        while(!expanded.isEmpty()){
            //pop super node from stack
            XGraph.XVertex supernode = expanded.pop();
            //get vertices contained within it from hash map
            List<XGraph.XVertex> vList = supernodeMap.get(supernode);
            //remove all edges associated to super node by assigning null

            //go through each vertex and enable edges

            //remove the super node (assign null)
        }
    }
}
