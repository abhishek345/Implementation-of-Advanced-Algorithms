package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS {
    private static int time = 0;
    private static int cno = 0;
    private static int topNum = 0;
    private static boolean checkCycles = true;
    private static LinkedList<Graph.Vertex> decFinList;


    public DFS(GraphExtended ge){
        topNum = ge.size();
    }

    public static LinkedList<Graph.Vertex> DFSCall(Iterator it, GraphExtended ge)throws CyclicGraphException{
        return DFSGraph(it, ge);
    }

    public static LinkedList<Graph.Vertex> DFSCall(GraphExtended ge)throws CyclicGraphException{
        return DFSGraph(ge.iterator(), ge);
    }

    public static LinkedList<Graph.Vertex> DFSGraph(Iterator it, GraphExtended ge)throws CyclicGraphException{
        topNum=ge.size();
        decFinList= new LinkedList<>();

        while(it.hasNext()) {
            Graph.Vertex temp = (Graph.Vertex) it.next();
            ge.setSeen(temp, false);
            ge.setParent(temp, null);
        }
        while(it.hasNext()){
            Graph.Vertex u= (Graph.Vertex)it.next();
            if(!ge.getSeen(u)){
                cno++;
                DFSVisit(ge, u);
            }
        }
    }

    public static void DFSVisit(GraphExtended ge, Graph.Vertex u)throws CyclicGraphException{
        int uName= u.getName();
        ge.setSeen(u,true);
        ge.setDis(u,++time);
        ge.setVCno(u,cno);

        Iterator adjEdges = u.adj.iterator();

        while(adjEdges.hasNext()){
            Graph.Edge e = (Graph.Edge)adjEdges.next();
            Graph.Vertex v = e.otherEnd(u);
            if(!ge.getSeen(v)){
                ge.setParent(v,u);
                DFSVisit(ge, v);
            }
            else if(checkCycles && (ge.g.directed && ge.getSeen(v))){
                Graph.Vertex a = ge.getParent(u);
                while(a != null){
                    a = ge.getParent(a);
                    if(a.getName() == v.getName())
                        throw new CyclicGraphException("Cycle Found");
                }
                System.out.print("no cycle found");
            }
        }
        ge.setFin(u,++time);
        ge.setTop(u,topNum--);
        decFinList.addFirst(u); //supposed to be addFirst
    }

    public static boolean isDAG(GraphExtended ge){
        try{
            if(ge.g.directed) {
                DFSCall(ge);
                return true;
            }
            else{
                System.out.println("Graph is not directed");
                return false;
            }
        }catch(CyclicGraphException e){
            return false;
        }
    }

    public static void main(String args[])throws FileNotFoundException{
        Scanner sf = new Scanner(new File("/home/uks/ImplofAlgos/Implementation-of-Advanced-Algorithms/SP3/src/cs6301/g21/cyclicgraph.in"));
        Graph graph = Graph.readDirectedGraph(sf);
        GraphExtended ge = new GraphExtended(graph);
        System.out.println(isDAG(ge));
    }
}
