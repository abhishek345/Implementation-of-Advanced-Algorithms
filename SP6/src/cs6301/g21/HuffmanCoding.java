package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs6301.g21.CodingTree.Node;

/**
 * Implementing Huffman Coding Algorithm to generate codes 
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
 * @version 1.0 : 2017/10/06
 *
 */

public class HuffmanCoding {
	
	/**
	 * Generates Huffman Codes for the given elements and their frequencies
	 * 
	 * @param in : Scanner that reads the contents of the file containing
	 * 				the elements and its frequencies
	 * @return : final Coding Tree
	 */
	public static CodingTree findHuffmanCoding(Scanner in){
//		int n = 0;
//		if(in.hasNext()){
//			n=Integer.parseInt(in.nextLine());
//		}
		PriorityQueue<CodingTree> pq = new PriorityQueue<CodingTree>(new minFrequency());
		
		//read all the elements and its frequencies into a priority queue
		while(in.hasNext()){
			String str= in.nextLine();
			String[] words= str.split(" ");
			double freq= Double.parseDouble(words[1]);
			CodingTree tree= new CodingTree();
			tree.setRoot(words[0],freq);
			pq.add(tree);
		}
		
		while(pq.size()>1){
			CodingTree tree1= pq.remove();
			CodingTree tree2=pq.remove();
		
			CodingTree tree3= tree1.add(tree2);
			pq.add(tree3);
		}
		
		return pq.remove();
	}


	/**
	 * Class function used to compare elements in the Priority Queue 
	 *
	 */
	static class minFrequency implements Comparator<CodingTree>{

		/**
		 * Overridden compare function
		 */
		@Override
		public int compare(CodingTree c1, CodingTree c2) {
			return Double.compare(c1.getFreq(),c2.getFreq());
		}
		
	}
	
	/**
	 * Main  function. Reads string and its frequencies from a file and generates
	 * Huffman codes for it.
	 *  
	 * @param args : Command line argument
	 * @throws FileNotFoundException : Exception thrown when the file is not found or could not be opened
	 */
	public static void main(String[] args) throws FileNotFoundException{
		String filename= null;
		if(args.length>0)
			filename= args[0];
		
		Scanner in= new Scanner(new File(filename));
		
		CodingTree root= findHuffmanCoding(in);
		
		root.display();
	}
}
