
package cs6301.g21;

import java.util.Comparator;
import cs6301.g21.BST.Entry;

/**
 * Implementation of Red Black Trees that extends BST
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Umang Shah, Vibha Belavadi
 * @version 1.0 : 2017/10/18 
 *
 */
public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
    /*
     * Node of the tree
     */
	static class Entry<T> extends BST.Entry<T> {
        boolean isRed;
        Entry<T> parent;
        
        /**
         * Constructor of the class
         * 
         * @param x : element in the node
         * @param left : left child
         * @param right : right child
         */
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            isRed = true;
            parent=null;
            
        }
        
        /*return the element stored in the node*/
        public T getElement() {
            return this.element;
        }
        
        /*sets the isRed variable, true: Node is red; false: Node is black*/
        public void setIsRed(boolean value){
        	isRed=value;
        }
        
        /*returns the value in isRed variable, true: Node is red; false: Node is black*/
        public boolean getIsRed(){
        	return isRed;
        }
        
        /*Sets the parent of the node*/
        public void setParent(Entry<T> p){
        	parent=p;
        }
        
        /*returns the parent of the node*/
        public Entry<T> getParent(){
        	return parent;
        }

        /*Sets the right child of the node*/
		public Entry<T> getRightChild() {
			return (Entry<T>) right;
		}
		
		/*returns the right child of the node*/
		public void setRightChild(Entry<T> right) {
			this.right=right;
		}
		
		/*Sets the left child of the node*/
		public Entry<T> getLeftChild() {
			return (Entry<T>) left;
		}
		
		/*returns the left child of the node*/
		public void setLeftChild(Entry<T> right) {
			this.left=right;
		}
		
		/*Checks if the node is  the root of the tree*/
		public boolean isRoot(){
			if (this.getParent()==null)
				return true;
			else
				return false;
		}
		
    }

	/*Constrctor of the class*/
    RedBlackTree() {
	super();
    }
    
    /**
     * Returns the root of the tree 
     * @return root of the tree
     */
    public Entry<T> getRoot(){
		return (Entry<T>) root;
	}
    
    /**
     *sets the root of the tree 
     * @param r: node to be set as root
     */
    public void setRoot(Entry<T> r){
    	root=r;
    }
    

    /**
     * Compares the contents of two nodes
     * 
     * @param arg0 : first node
     * @param arg1 : second node
     * @return : 0 if both are equal, -1 is first<second , 1 if first>second
     */
    public int compare(Entry<T> arg0, Entry<T> arg1){
    	return arg0.getElement().compareTo(arg1.getElement());
    }
    
    /**
     * Checks for Double Left Rotation
     * 
     * @param t: current node
     * @param p_t : parent node
     * @param g_t : grandparent node
     * @return : true, if Double Left Rotation exists, false, otherwise
     */
    public boolean isLL(Entry<T> t, Entry<T> p_t, Entry<T> g_t){
    	if(p_t!=null && g_t!=null){
	    	if(compare(p_t,g_t)<0 && compare(t,p_t)<0)
	    		return true;
	    	else
	    		return false;
    	}
    	else
    		return false;
    }
    
    /**
     * Checks for Double Right Rotation
     * 
     * @param t: current node
     * @param p_t : parent node
     * @param g_t : grandparent node
     * @return : true, if Double Right Rotation exists, false, otherwise
     */
    public boolean isRR(Entry<T> t, Entry<T> p_t, Entry<T> g_t){
    	if(p_t!=null && g_t!=null){
	    	if(compare(p_t,g_t)>0 && compare(t,p_t)>0)
	    		return true;
	    	else
	    		return false;
    	}
    	else
    		return false;
    }
    
    /**
     * Checks for Left Right Rotation
     * 
     * @param t: current node
     * @param p_t : parent node
     * @param g_t : grandparent node
     * @return : true, if Left Right Rotation exists, false, otherwise
     */
    public boolean isLR(Entry<T> t, Entry<T> p_t, Entry<T> g_t){
    	if(p_t!=null && g_t!=null){
	    	if(compare(p_t,g_t)<0 && compare(t,p_t)>0)
	    		return true;
	    	else
	    		return false;
    	}
    	else
    		return false;
    }
    
    /**
     * Checks for Right Left Rotation
     * 
     * @param t: current node
     * @param p_t : parent node
     * @param g_t : grandparent node
     * @return : true, if Right Left Rotation exists, false, otherwise
     */
    public boolean isRL(Entry<T> t, Entry<T> p_t, Entry<T> g_t){
    	if(p_t!=null && g_t!=null){
	    	if(compare(p_t,g_t)>0 && compare(t,p_t)<0)
	    		return true;
	    	else
	    		return false;
    	}
    	else
    		 return false;
    }
    
    /**
     * Performs Right Rotation
     * 
     * @param node : node along which rotation must occur
     * @return : node present at the location where rotation occured
     */
    public Entry<T> rotateR(Entry<T> node){
    	Entry<T> n1 =  node.getLeftChild();
    	node.setLeftChild(n1.getRightChild());
        n1.setRightChild(node);
        
        if(!node.isRoot()){
        	if(node.getParent().getLeftChild()==node)
        		node.getParent().setLeftChild(n1);
        	else
        		node.getParent().setRightChild(n1);
        }
        
        n1.setParent(node.getParent());
        node.setParent(n1);
        if(node.getLeftChild()!=null)
        	node.getLeftChild().setParent(node);
        if(node.equals(getRoot())){
        	setRoot(n1);
        }
        return n1;
    }
    
    /**
     * Performs Left Rotation
     * 
     * @param node : node along which rotation must occur
     * @return : node present at the location where rotation occured
     */
    public Entry<T> rotateL(Entry<T> node){
    	Entry<T> n1 =  node.getRightChild();
    	node.setRightChild(n1.getLeftChild());
        n1.setLeftChild(node);
        
        if(!node.isRoot()){
        	if(node.getParent().getLeftChild()==node)
        		node.getParent().setLeftChild(n1);
        	else
        		node.getParent().setRightChild(n1);
        }
        
        n1.setParent(node.getParent());
        node.setParent(n1);
        if(node.getRightChild()!=null)
        node.getRightChild().setParent(node);
        
        if(node.equals(getRoot())){
            setRoot(n1);
        }
        return n1;
    }
    
    /**
     * Balances the tree after addition of new node
     * @param t : node from where repair must be performed
     */
    public void repair(Entry<T> t){
    	Entry<T> p_t= t.getParent();
		Entry<T> g_t,u_t;
		boolean uncle=false;
    	//t is root.
    	if(p_t==null){
    		//root is colored black by add
    		return;
    	}
    	while(t.getIsRed()){
    		p_t= t.getParent();
    		g_t=null;
    		if(p_t!=null)
    			g_t=p_t.getParent();
    		if(p_t==null || g_t==null){
    			return;
    		}
    		if(compare(p_t,g_t)<0){
    				u_t=g_t.getRightChild();
    		}
    		else{
    				u_t=g_t.getLeftChild();
    		}
    		//case 0
    		if(t.isRoot() || p_t.isRoot() || !p_t.getIsRed()){
    			return;
    		}
    		
    		//case1
    		if(u_t!=null && u_t.getIsRed()){
    			p_t.setIsRed(false);
    			u_t.setIsRed(false);
    			g_t.setIsRed(true);
    			t=g_t;
    			continue;
    		}
    		
    		//case2
    		if(u_t==null)
    			uncle=true;
    		if((uncle || !u_t.getIsRed()) && isLL(t,p_t,g_t)){
    			rotateR(g_t);
    			p_t.setIsRed(false);
    			g_t.setIsRed(true);
    			return;
    		}
    		else if((uncle || !u_t.getIsRed()) && isRR(t,p_t,g_t)){
    			rotateL(g_t);
    			p_t.setIsRed(false);
    			g_t.setIsRed(true);
    			if(g_t==getRoot())
    				root=p_t;
    			return;
    		}
    		//case 3
    		else if((uncle|| !u_t.getIsRed()) && isLR(t,p_t,g_t)){
    			rotateL(p_t);
    			rotateR(g_t);
    			p_t.setIsRed(false);
    			g_t.setIsRed(true);
    			return;
    		}
    		else if((uncle|| !u_t.getIsRed()) && isRL(t,p_t,g_t)){
    			rotateR(p_t);
    			rotateL(g_t);
    			p_t.setIsRed(false);
    			g_t.setIsRed(true);
    			return;
    		}
    	}
    }
    
    
    /**
     * Add a new Node to the Red Black Tree
     * @param x :value to be added 
     */
    public boolean add(T x){
        Entry<T> newElem = new Entry<>(x, null, null);
        
        /*return false since duplicate value has been added to the tree*/
        if(!super.addNode(newElem))
        	return false;

        if(stack!=null && !stack.isEmpty()){
           	RedBlackTree.Entry<T> p_t= (Entry<T>)stack.pop();
            	
       	if(p_t== null)
       		newElem.setParent(getRoot());
       	else{
          	if(compare(newElem,p_t)<0)
           		newElem.setParent(p_t.getLeftChild());
         	else 
           		newElem.setParent(p_t.getRightChild());
          }
            	
          p_t=newElem.getParent();
            	
          if(p_t.getElement().compareTo(x)!=0)
         	newElem.setParent(p_t);
          }
        	
        	repair(newElem);
        	getRoot().setIsRed(false);
        	return true;
        
    }

    /**
     * Fixes the black node count after removal of node 
     * @param t : node from where fixing must be performed
     */
    public void fix(Entry<T> t){
    	Entry<T> p_t,s_t,s_t_l,s_t_r,rc;
    	while(!t.isRoot()){
    		
    		//case1:
    		if(t.getIsRed()){
    			t.setIsRed(false);
    			return;
    		}
    		
    		//case 2
    		p_t=t.getParent();
    		s_t = p_t.getLeftChild()==t?p_t.getRightChild():p_t.getLeftChild();
    		
    		if(s_t!=null){
    			s_t_l=s_t.getLeftChild();
    			s_t_r=s_t.getRightChild();
	    		
	    		if(!s_t.getIsRed() && (s_t_l==null || !s_t_l.getIsRed()) && (s_t_r==null || !s_t_r.getIsRed())){
	    			s_t.setIsRed(true);
	    			t=p_t;
	    		}
    		
	    		//case 3
	    		if(s_t_l!=null && s_t_l.getIsRed()){
	    			rc=s_t_l;
	    		}
	    		else if(s_t_r!=null && s_t_r.getIsRed()){
	    			rc=s_t_r;
	    		}
	    		else
	    			rc=null;
	    		if(!s_t.getIsRed() && rc!=null && isRR(rc, s_t, p_t)){
	    			rotateL(p_t);
	    			exchangeColors(p_t,s_t);
	    			rc.setIsRed(false);
	    			return;
	    		}
	    		else if(!s_t.getIsRed() && rc!=null && isLL(rc, s_t, p_t)){
	    			rotateR(p_t);
	    			exchangeColors(p_t,s_t);
	    			rc.setIsRed(false);
	    			return;
	    		}
	    		else if(!s_t.getIsRed() && rc!=null && isRL(rc, s_t, p_t)){
	    			rotateR(s_t);
	    			exchangeColors(rc,s_t);
	    			
	    			rotateL(p_t);
	    			exchangeColors(p_t,s_t);
	    			rc.setIsRed(false);
	    			return;
	    		}
	    		else if(!s_t.getIsRed() && rc!=null && isLR(rc, s_t, p_t)){
	    			rotateL(s_t);
	    			exchangeColors(rc,s_t);

	    			rotateR(p_t);
	    			exchangeColors(p_t,s_t);
	    			rc.setIsRed(false);
	    			return;
	    		}
	    		
	    		else if(s_t.getIsRed()){
	    			if(p_t.getLeftChild()==s_t)
	    				rotateR(p_t);
	    			else 
	    				rotateL(p_t);
	    			exchangeColors(p_t,s_t);
	    			continue;
	    		}
    		
    		}
    	}
    }
    
    /**
     * Swapping colors of the provided nodes
     * @param x : node 1
     * @param y : node 2
     */
    void exchangeColors(Entry<T> x, Entry<T> y){
    	boolean temp= x.getIsRed();
    	x.setIsRed(y.getIsRed());
    	y.setIsRed(temp);
    }
    
    /**
     * Remove a node from the Red Black Tree
     * @param x : value of the node to be removed
     */
    public T remove(T x){
    	
    	Entry<T> t= (Entry<T>) find(x);
    	Entry<T> minRight=t;
    	if(t.getLeftChild()!=null && t.getRightChild()!=null)
    		minRight = (Entry<T>) find(t.getRightChild(),t.getElement());
    	T result = super.remove(x);
    	
    	if(result==null)
    		return null;
    	if(minRight.getIsRed())
    		return result;
    	else{
    		Entry<T> c = (Entry<T>) find(minRight.getElement());
    		fix(c);
    		return result;
    	}
    	
    }
    
    
//    public static void preOrder(Entry<Integer> entry){
//    	if(entry!=null){
//    	
//    	System.out.println(entry.getElement());
//    	preOrder(entry.getLeftChild());
//    	preOrder(entry.getRightChild());
//    	}
//    }
    
    public static void main(String[] args){
    	RedBlackTree<Integer> t= new RedBlackTree<Integer>();
    	
    	t.add(20);
        t.add(30);
        t.add(50);
        t.add(10);
        t.add(25);

       for(int i:t)
    	   System.out.println(i);   
        
        System.out.println("\n");
        
        t.remove(10);
        for(int i:t)
     	   System.out.println(i); 
    }
}
