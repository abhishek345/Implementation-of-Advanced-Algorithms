package cs6301.g21;

import java.util.Iterator;
import java.util.Scanner;

import cs6301.g21.Graph.Edge;
import cs6301.g21.Graph.Vertex;
import cs6301.g21.XGraph.XEdge;
import cs6301.g21.XGraph.XVertex;

import java.io.File;
import java.io.FileNotFoundException;

public class BellmanFord {
	
	
	private static final int INFINITY = Integer.MAX_VALUE;

	public static boolean BellmanFord(XGraph g, Vertex sourceNode){
		System.out.println("Inside BellmanFord, Source is "+sourceNode.getName());
		boolean noChange = false;
		XGraphExtended ge= new XGraphExtended(g);
		Iterator<Vertex> it = g.iterator();
		
		System.out.println("For k = 0" );
		
		while(it.hasNext()){
			XGraph.XVertex s= (XGraph.XVertex) it.next();
			ge.setDistance(s, 0, INFINITY);
			s.parent = null;
			System.out.println(1+s.getName()+ " "+ ge.getDistance(s, 0));
		}
		XGraph.XVertex source = g.getVertex(sourceNode);
		ge.setDistance(source, 0, 0);
		System.out.println(1+source.getName()+ " "+ ge.getDistance(source, 0));
		System.out.println("Initialized");
		
		for(int k=1;k<=g.size();k++){
			System.out.println("For k ="+k );
			noChange = true;
			it = g.iterator();
			
	 		while(it.hasNext()){
				XGraph.XVertex u= (XGraph.XVertex) it.next();
				int val=ge.getDistance(u, k-1);
				ge.setDistance(u, k, val); //u.d[k]=u.d[k-1];
				System.out.println(1+u.getName()+ " "+ ge.getDistance(u, k));
				
				Iterator<Edge> itEdge= u.revIterator();
				while(itEdge.hasNext()){
					XEdge e= (XEdge)itEdge.next();
					XVertex p= (XVertex) e.otherEnd(u); //Little confusion. check again
					
					if(ge.getDistance(u,k)>ge.getDistance(p, k-1) + e.getWeight()){
						int newDist=ge.getDistance(p, k-1) + e.getWeight();
						ge.setDistance(u, k, newDist);
						u.setParent(p);
						noChange=false;
					}
					System.out.println(1+u.getName()+ " "+ ge.getDistance(u, k));
				}
				System.out.println();
			}
	 		if(noChange){
	 			it = g.iterator();
	 			while(it.hasNext()){
					XGraph.XVertex u= (XGraph.XVertex) it.next();
					u.setDis(ge.getDistance(u, k));
	 			}
	 			return true;
	 		}
		}
		return false;
	}
	
	public static int shortestPath(XGraph g, Vertex sourceNode, Vertex target){
		XVertex s= g.getVertex(sourceNode);
		XVertex t= g.getVertex(target);
		System.out.println("in shortest path, source: "+s.getName()+ ", target: "+ t.getName());
		if(BellmanFord(g,s)){
			return 5;
		}
		else{
			return INFINITY;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		java.util.Scanner in = new java.util.Scanner(new File(args[0]));
		Graph g = Graph.readDirectedGraph(in);

		int source = in.nextInt();
		int target = in.nextInt();
		
		XGraph ge = new XGraph(g);
		Vertex s= g.getVertex(source);
		Vertex t= g.getVertex(target);
    	System.out.println(BellmanFord.shortestPath(ge,s,t));
	    }

}
