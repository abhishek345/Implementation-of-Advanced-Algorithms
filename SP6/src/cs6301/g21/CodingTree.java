package cs6301.g21;

/**
 * Stores the coding tree.
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0: 2017/10/07
 */
public class CodingTree {
	
	/**
	 * Inner class that represents the nodes of the tree.
	 */
	public class Node{
		String value;
		double freq;
		Node left;
		Node right;

		/**
		 * Constructor of the inner class.
		 */
		Node(){
			this.value=null;
			this.freq=0;
			this.left=null;
			this.right=null;
		}
		
		/**
		 * Parameterized constructor of the inner class.
		 * 
		 * @param frequency : frequency of the string
		 * @param left : left child of the tree
		 * @param right : right child of the tree
		 */
		Node(double frequency,Node left, Node right){
			this.value=null;
			this.freq=frequency;
			this.left=left;
			this.right= right;
		}
		
		/**
		 * Parameterized constructor of the inner class.
		 * 
		 * @param value : String value of the node
		 * @param frequency : frequency of the string
		 */
		Node(String value, double frequency){
			this.value= value;
			this.freq= frequency;
			left=null;
			right=null;
		}

		/*returns the string value of the node*/
		public String getValue() {
			return value;
		}

		/*sets of the string value of the node*/
		public void setValue(String value) {
			this.value = value;
		}

		/*returns the frequency of the string value of the node*/
		public double getFreq() {
			return freq;
		}

		/*sets the frequency of the string value of the node*/
		public void setFreq(double freq) {
			this.freq = freq;
		}

		/*returns the left child of the node*/
		public Node getLeft() {
			return left;
		}

		/*gets the left child of the node*/
		public void setLeft(Node left) {
			this.left = left;
		}

		/*gets the right child of the node*/
		public Node getRight() {
			return right;
		}

		/*sets the right child of the node*/
		public void setRight(Node right) {
			this.right = right;
		}
	}

	//root node of the tree
	Node root;
	
	/**
	 * Constructor of the outer tree
	 */
	public CodingTree(){
		this.root= null;
	}
	
	/**
	 * Parameterized constructor of the outer tree
	 */
	public CodingTree(Node r){
		this.root= r;
	}
	
	/*Sets the root of the tree*/	
	public void setRoot(Node r){
		this.root= r;
	}
	
	/*Returns the root of the tree*/
	public Node getRoot(){
		return root;
	}
	
	/**
	 * Sets the root of the tree
	 * 
	 * @param value : string value of the root
	 * @param freq : frequency of the string value
	 */
	public void setRoot(String value, double freq){
		Node n= new Node(value,freq);
		root= n;
		
	}
	
	/**
	 * Creates a new tree with the calling tree and provided as its children
	 * 
	 * @param tree : provided tree
	 * @return : root of a tree whose children are calling tree and provided tree
	 */
	public CodingTree add(CodingTree tree){
		if(tree==null){
			return tree;
		}
		CodingTree newTree = new CodingTree();
		Node newRoot;
		double freq= root.getFreq()+tree.getFreq();
		if(root.getFreq()<=tree.getFreq()){
			newRoot=new Node(freq,root,tree.getRoot());
			newTree.setRoot(newRoot);
		}
		else{
			newRoot=new Node(freq,tree.getRoot(),root);
			newTree.setRoot(newRoot);
		}
		
		return newTree;
	}

	/*return the frequency of the root node*/
	public double getFreq(){
		return root.getFreq();
	}
	
	/*Displays the tree*/
	public void display(){
		if(root.getValue()!=null)
			System.out.println(root.getValue() + " 0");
		else
			display(root, "");
		
	}
	
	/**
	 * Displays the tree.
	 * 
	 * @param root : current node being accessed
	 * @param prefix : prefix value
	 */
	public void display(Node root, String prefix){
		if(root.getValue()!=null){
			System.out.println(root.getValue() + " "+ prefix);
			return;
		}
		
		if(root.getLeft()!=null){
			display(root.getLeft(), prefix+"0");
		}
		
		if(root.getRight()!=null){
			display(root.getRight(), prefix+"1");
		}
		
	}
}
