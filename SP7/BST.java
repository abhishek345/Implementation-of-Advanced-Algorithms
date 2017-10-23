import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	    this.left = left;
	    this.right = right;
        }
    }
    Stack<Entry<T>> stack;
    Entry<T> root;
    int size;

    public BST() {
	root = null;
	size = 0;
    }

	Entry<T> peekstack(){
        if(stack != null && !stack.isEmpty())
    	    return stack.peek();
        return null;
	}
    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
    	Entry<T> node = find(root,x);
	return node == null ? false : true;
    }
    
    Entry<T> find(T x){
      //System.out.println("stack cretaed");
    	stack = new Stack<Entry<T>>();
    	stack.push(null);
	stack.push(root);
    	return find(root, x);
    }
    
    Entry<T> find(Entry<T> t, T x){
    	if(t == null || t.element == x)
    		return t;
    	while(true){
    		int comp = x.compareTo(t.element);
    		if(comp < 0){
    			if(t.left == null)
    				break;
    			else{
    				stack.push(t);
    				t = t.left;
    			}
    		}
    		else if(comp == 0)
    			break;
    		else{
    			if(t.right == null)
    				break;
    			else{
    				stack.push(t);
    				t = t.right;
    			}
    		}
    	}
    	return t;
    }
    
    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
    	Entry<T> node = find(root,x);
    	return (T) (node == null ? null : x.equals(node.element));
    }

    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x){
        Entry<T> newEle = new Entry<>(x, null, null);
        return addNode(newEle);
    }

    public boolean addNode(Entry<T> x) {
    	if(root == null){
    		root = x;
    		size = 1;
    		return true;
    	}
    	Entry<T> t = find(x.element);
    	int comp = x.element.compareTo(t.element);
    	if(comp == 0){
    		t.element = x.element;
    		return false;
    	}
    	else if(comp < 0)
    		t.left = x;
    	else
    		t.right = x;
    	
    	size++;
    	
    	return true;
    }



    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
    	T result;
    	Entry<T> minRight;
    	if(root == null)
    		return null;
    	Entry<T> t = find(x);
    	if(t.element != x)
    		return null;
    	result = t.element;
    	if(t.left == null || t.right == null)
    		bypass(t);
    	else{
    		stack.push(t);
    		minRight = find(t.right,t.element);
    		t.element = minRight.element;
    		bypass(minRight);
    	}
    	size--;
    	return result;
    }
    
    public void bypass(Entry<T> t){
    	Entry<T> pt,c;
    	pt = stack.peek();
    	c = t.left == null ? t.right : t.left;
    	if(pt == null)
    		root = c;
    	else if(pt.left == t)
    		pt.left = c;
    	else
    		pt.right = c;
    }

    /** TO DO: Iterate elements in sorted order of keys
     */
    public Iterator<T> iterator() {
      if(root == null)
      	return null;
      else
        return new TreeIterator<T>(root);
    }
		

    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }           
        }
    }


    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	/* write code to place elements in array here */
	inOrder(root, arr, 0);
	return arr;
    }
    
    int inOrder(Entry<T> node, Comparable[] arr, int index){
    	if(node != null){
    		index = inOrder(node.left, arr, index);
    		arr[index++] = node.element;
    		index = inOrder(node.right, arr, index);
    	}
    	return index;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }

}
