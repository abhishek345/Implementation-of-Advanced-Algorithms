package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class StronglyConnectedComponent {
	
	static GraphExtended ge;
	static int topNum;
	static int time;
	static int cno;
	static LinkedList decFinList;

	static int stronglyConnectedComponents(Graph g) {
	
		LinkedList<Graph.Vertex> decFinList1 = finishTimeOrder(g);
		
		Graph gT = reverseGraph(g);
		
		findComponents(gT,decFinList1);
		
		return cno;
	}
	
	public static LinkedList<Graph.Vertex> finishTimeOrder(Graph g) {
		ge = new GraphExtended(g);
		Iterator it= ge.g.iterator();
		DFSOrder1(it);
		return decFinList;
		
	}
	
	public static void DFSOrder1(Iterator it){
		topNum=ge.size();
		time=0;
		cno=0;
		decFinList= new LinkedList<Graph.Vertex>();

		for(int i=0;i<ge.size();i++)
			ge.setSeen(i,false);
		while(it.hasNext()){
			Graph.Vertex u= (Graph.Vertex)it.next();
			if(!ge.getSeen(u.getName())){
				cno++;
				DFSVisitOrder1(u);
			}
		}
	}
	
	public static void DFSVisitOrder1(Graph.Vertex u){
		int uName= u.getName();
		ge.setSeen(uName,true);
		ge.setDis(uName,++time);
		ge.setVCno(uName,cno);
		
		Iterator adjEdges = u.adj.iterator();
		
		while(adjEdges.hasNext()){
			Graph.Edge e= (Graph.Edge)adjEdges.next();
			Graph.Vertex v= e.otherEnd(u);
			if(!ge.getSeen(v.getName())){
				ge.setParent(v.getName(),uName);
				DFSVisitOrder1(v);
			}
		}
		ge.setFin(uName,++time);
		ge.setTop(uName,topNum--);
		decFinList.addFirst(u); 
		
	}

	
	static Graph reverseGraph(Graph g){
		Graph gT= new Graph(g.size());
		gT.directed=true;
		
		Iterator<Graph.Vertex> vertices= g.iterator();
		
		while(vertices.hasNext()){
			Graph.Vertex v = (Graph.Vertex)vertices.next();
			
			int vName = v.getName();
			gT.v[vName].adj=v.revAdj;
			gT.v[vName].revAdj=v.adj;
			
		}
		return gT;
	}
	
	
	
	
	static void findComponents(Graph gt,LinkedList<Graph.Vertex> decFinList){
		ge = new GraphExtended(gt);
		Iterator it= decFinList.iterator();
		Iterator it1= decFinList.iterator();
		
		DFSOrder2(it);
	}
	
	public static void DFSOrder2(Iterator it){
		topNum=ge.size();
		time=0;
		cno=0;

		for(int i=0;i<ge.size();i++)
			ge.setSeen(i,false);
		while(it.hasNext()){
			Graph.Vertex u= (Graph.Vertex)it.next();
			if(!ge.getSeen(u.getName())){
				cno++;
				DFSVisitOrder2(u);
			}
		}
	}
	
	public static void DFSVisitOrder2(Graph.Vertex u){
		int uName= u.getName();
		ge.setSeen(uName,true);
		ge.setDis(uName,++time);
		ge.setVCno(uName,cno);
		
		Iterator adjEdges = u.revAdj.iterator();
		
		while(adjEdges.hasNext()){
			Graph.Edge e= (Graph.Edge)adjEdges.next();
			Graph.Vertex v= e.otherEnd(u);
			if(!ge.getSeen(v.getName())){
				ge.setParent(v.getName(),uName);
				DFSVisitOrder2(v);
			}
		}
		ge.setFin(uName,++time);
		ge.setTop(uName,topNum--);
		
	}
	

	public static void main(String[] args)throws FileNotFoundException, Exception{
        if(args.length > 0){
          Scanner sf = new Scanner(new File(args[0]));
          Graph graph = Graph.readDirectedGraph(sf);
          int count= stronglyConnectedComponents(graph);
          System.out.println("Number of Components="+count);
        }
		
	}
	
}
