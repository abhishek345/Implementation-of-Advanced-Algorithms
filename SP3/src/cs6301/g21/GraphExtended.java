package cs6301.g21;

public class GraphExtended{
	
	Graph g;
	boolean seen[];
	int dis[];
	int fin[];
	int vCno[];
	int top[];
	int parent[];
	int inDegree[];
	
	
	public GraphExtended(Graph g) {
		this.g= g;
		seen= new boolean[g.size()];
		dis = new int[g.size()];
		fin = new int[g.size()];
		vCno = new int[g.size()];
		top = new int[g.size()];
		parent = new int[g.size()];
		inDegree= new int[g.size()];
		
	}
	
	public void setSeen(int index,boolean value){
		seen[index]=value;
	}
	
	public boolean getSeen(int index){
		return seen[index];
	}
	
	
	public void setDis(int index,int value){
		dis[index]=value;
	}
	
	public int getDis(int index){
		return dis[index];
	}
	
	public void setFin(int index,int value){
		fin[index]=value;
	}
	
	public int getFin(int index){
		return fin[index];
	}
	
	public void setVCno(int index,int value){
		vCno[index]=value;
	}
	
	public int getVCno(int index){
		return vCno[index];
	}
	
	public void setTop(int index,int value){
		top[index]=value;
	}
	
	public int getTop(int index){
		return top[index];
	}
	
	public void setParent(int index,int value){
		parent[index]=value;
	}
	
	public int getParent(int index){
		return parent[index];
	}
	
	public void setInDegree(int index,int value){
		inDegree[index]=value;
	}
	
	public int getInDegree(int index){
		return inDegree[index];
	}
	
	public int size(){
		return g.size();
	}
	
	

}
