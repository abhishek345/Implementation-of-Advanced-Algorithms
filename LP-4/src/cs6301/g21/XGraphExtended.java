package cs6301.g21;

public class XGraphExtended {

	public class XVertexExtended{

		int d[];
		XVertexExtended() {
			d=new int[g.size()+1];
		}
		
		public void setDistance(int k,int dist){
			d[k]=dist;
		}
		public int getDistance(int k){
			return d[k];
		}
	}
	
	XVertexExtended xve[];
	XGraph g;
	
	public XGraphExtended(XGraph g) {
		this.g=g;
		xve= new XVertexExtended[g.size()];
		
//		for(XGraph.XVertex u: g){
//			xve[u.getName()]=new XVertexExtended(u);
//		}
		
		for(int i=0;i<g.size();i++){
			xve[i]= new XVertexExtended();
		}
	}
	
	public XVertexExtended getXVertexExtended(XGraph.XVertex u){
		return xve[u.getName()];
	}
	
	public void setDistance(XGraph.XVertex u, int k, int dist){
		XVertexExtended xu= getXVertexExtended(u);
		xu.setDistance(k, dist);
	}
	
	public int getDistance(XGraph.XVertex u, int k){
		XVertexExtended xu= getXVertexExtended(u);
		return xu.getDistance(k);
	}
}
