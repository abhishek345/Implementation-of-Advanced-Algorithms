package cs6301.g21;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Topological {
	
	static int topNum;
	static LinkedList<Graph.Vertex> decFinList;
	static GraphExtended ge;
	static int time;
	static int cno;
	
	
public static LinkedList<Graph.Vertex> toplogicalOrder1(Graph g){
		
		int topNum= 0;
		LinkedList<Graph.Vertex> queue=new LinkedList<Graph.Vertex>();
		LinkedList<Graph.Vertex> topList= new LinkedList<Graph.Vertex>();
		ge= new GraphExtended(g);
		Iterator<Graph.Vertex> vertices = ge.g.iterator();
		
			
		while(vertices.hasNext()){
			Graph.Vertex s= vertices.next(); 
			ge.setInDegree(s.getName(), s.revAdj.size());
			if(ge.getInDegree(s.getName())==0){
				queue.add(s);
			}
		}
			
		while(!queue.isEmpty()){
			Graph.Vertex u= queue.remove();
			ge.setTop(u.getName(),++topNum);
			topList.add(u);
			Iterator<Graph.Edge> e= u.iterator();
			while(e.hasNext()){
				Graph.Vertex v= e.next().otherEnd(u);
				int vName=v.getName();
				int inDegreeVal= ge.getInDegree(vName);
				ge.setInDegree(vName, inDegreeVal-1);
				if(ge.getInDegree(vName)==0){
					queue.add(v);
				}
				
			}
		}
		
		if(topNum!=g.size()){
			return null;
		}
		return topList;
		
		
}
	
	public static LinkedList<Graph.Vertex> toplogicalOrder2(Graph g) {
		ge = new GraphExtended(g);
		Iterator it= ge.g.iterator();
		DFS(it);
		return decFinList;
		
	}
	
	public static void DFS(Iterator it){
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
				DFSVisit(u);
			}
		}
	}
	
	public static void DFSVisit(Graph.Vertex u){
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
				DFSVisit(v);
			}
		}
		ge.setFin(uName,++time);
		ge.setTop(uName,topNum--);
		decFinList.addFirst(u); //supposed to be addFirst
		
	}

	
	public static void main(String[] args)throws FileNotFoundException, Exception{
        if(args.length > 0){
          Scanner sf = new Scanner(new File(args[0]));
          Graph graph = Graph.readDirectedGraph(sf);
          LinkedList l= toplogicalOrder2(graph);
          Iterator i= l.iterator();
          while(i.hasNext()){
        	  System.out.println((Graph.Vertex)i.next());
          }
          
    }
		
	}
}
