package cs6301.g21;

import java.awt.Component;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import cs6301.g21.Graph.Vertex;

public class DfsImplementation {

	static int topNum;
	static int time;
	static int cno;
	static boolean seen[];
	static LinkedList<Graph.Vertex> decFinList;
	static int dis[];
	static int fin[];
	static int vCno[];
	static int top[];
	static int parent[];

	
	public static LinkedList<Graph.Vertex> toplogicalOrder1(Graph g) throws Exception{
		
		int topNum= 0;
		LinkedList<Graph.Vertex> queue=new LinkedList<Graph.Vertex>();
		LinkedList<Graph.Vertex> topList= new LinkedList<Graph.Vertex>();
		Iterator<Graph.Vertex> vertices = g.iterator();
		int inDegree[]= new int[g.size()];
		int top[]= new int[g.size()];
			
		while(vertices.hasNext()){
			Graph.Vertex s= vertices.next(); 
			inDegree[s.getName()]= s.revAdj.size();
			if(inDegree[s.getName()]==0){
				queue.add(s);
			}
		}
			
		while(!queue.isEmpty()){
			Graph.Vertex u= queue.remove();
			top[u.getName()]=++topNum;
			topList.add(u);
			Iterator<Graph.Edge> e= u.iterator();
			while(e.hasNext()){
				Graph.Vertex v= e.next().otherEnd(u);
				inDegree[v.getName()]--;
				if(inDegree[v.getName()]==0){
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
		Iterator it= g.iterator();
		DFS(it,g);
		return decFinList;
		
	}
	
	public static void DFS(Iterator it, Graph g){
		topNum=g.size();
		time=0;
		cno=0;
		decFinList= new LinkedList<Graph.Vertex>();
		seen= new boolean[g.size()];
		dis = new int[g.size()];
		fin = new int[g.size()];
		vCno = new int[g.size()];
		top = new int[g.size()];
		parent = new int[g.size()];

		for(int i=0;i<g.size();i++)
			seen[i]=false;
		while(it.hasNext()){
			Graph.Vertex u= (Graph.Vertex)it.next();
			if(!seen[u.getName()]){
				cno++;
				DFSVisit(g,u);
			}
		}
	}
	
	public static void DFSVisit(Graph g, Graph.Vertex u){
		int uName= u.getName();
		seen[uName]=true;
		dis[uName]=++time;
		vCno[uName]=cno;
		
		Iterator adjEdges = u.adj.iterator();
		
		while(adjEdges.hasNext()){
			Graph.Edge e= (Graph.Edge)adjEdges.next();
			Graph.Vertex v= e.otherEnd(u);
			if(!seen[v.getName()]){
				parent[v.getName()]=uName;
				DFSVisit(g, v);
			}
		}
		fin[uName]=++time;
		top[uName]=topNum--;
		decFinList.addFirst(u);
		
	}

	
	public static void main(String[] args)throws FileNotFoundException, Exception{
        if(args.length > 0){
          Scanner sf = new Scanner(new File(args[0]));
          Graph graph = Graph.readDirectedGraph(sf);
          LinkedList l= toplogicalOrder1(graph);
          Iterator i= l.iterator();
          while(i.hasNext()){
        	  System.out.println((Graph.Vertex)i.next());
          }
          
    }
		
	}
}
