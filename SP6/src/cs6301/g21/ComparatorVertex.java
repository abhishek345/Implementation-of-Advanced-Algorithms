package cs6301.g21;

import java.util.Comparator;

/**
 * Implementation of Vertex Comparator
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0:08/29/2017
 * @since 1.0
 */
public class ComparatorVertex {
	
	public static class VertexComparator implements Comparator<GraphExtended3.GEVertex> {
		public int compare(GraphExtended3.GEVertex u, GraphExtended3.GEVertex v) {
			return u.dist - v.dist;
		}
	}

	
	

}
