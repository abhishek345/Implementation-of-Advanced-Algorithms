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

    public static void DFSGraph(GraphExtended ge)throws CyclicGraphException{
        topNum=ge.size();
        decFinList= new LinkedList<>();

        Iterator it = ge.g.iterator();
        for(int i=0;i<ge.size();i++) {
            ge.setSeen(i, false);
            ge.setParent(i, -1);
        }
        while(it.hasNext()){
            Graph.Vertex u= (Graph.Vertex)it.next();
            if(!ge.getSeen(u.getName())){
                cno++;
                DFSVisit(ge, u);
            }
        }
    }

    public static void DFSVisit(GraphExtended ge, Graph.Vertex u)throws CyclicGraphException{
        int uName= u.getName();
        ge.setSeen(uName,true);
        ge.setDis(uName,++time);
        ge.setVCno(uName,cno);

        Iterator adjEdges = u.adj.iterator();

        while(adjEdges.hasNext()){
            Graph.Edge e = (Graph.Edge)adjEdges.next();
            Graph.Vertex v = e.otherEnd(u);
            if(!ge.getSeen(v.getName())){
                ge.setParent(v.getName(),uName);
                DFSVisit(ge, v);
            }
            else if(checkCycles && (ge.isDir() && ge.getSeen(v.getName()))){

                int a = ge.getParent(u.getName());
                if(ge.getVCno(uName) == ge.getVCno(v.getName()))
                    throw new CyclicGraphException("Cycle ");
                while(a != -1){
                    a = ge.getParent(a);
                    if(a == v.getName())
                        throw new CyclicGraphException("Cycle Found");
                }
                System.out.print("no cycle found");
            }
        }
        ge.setFin(uName,++time);
        ge.setTop(uName,topNum--);
        decFinList.addFirst(u); //supposed to be addFirst
    }

    public static boolean isDAG(GraphExtended ge){
        try{
            if(ge.isDir()) {
                DFSGraph(ge);
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
