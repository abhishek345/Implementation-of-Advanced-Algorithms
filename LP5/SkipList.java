// Change this to your group number
package cs6301.g21;

import java.util.ArrayList;
import cs6301.g00.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Skip List Implementation
 * 
 * @author Shreya Vishwanath Rao, Umang Shah, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/10/30
 *
 * @param <T> : Type of element stored in each node of the skip list
 */

public class SkipList<T extends Comparable<? super T>> {
	
	/*
	 * Class of the node that is stored in the Skip List
	 */
	class SkipListEntry<T extends Comparable<? super T>>{
		T element;
		SkipListEntry next[];
		
		/**
		 * Comstructor
		 * 
		 * @param x : Element to be stored in the node
		 * @param lev : Size of the next array
		 */
		public SkipListEntry(T x, int lev){
			element=x;
			next=new SkipListEntry[lev];
		}
	}
	
	int maxLevel; //Maximum level of next array in any node 
	int size; //number of elements
	SkipListEntry head,tail;
	
    /**
     * Constructor of SkipList Class
     */
    public SkipList() {
    	size=0;
    	maxLevel=1;
    	head= new SkipListEntry(null,32);
    	tail= new SkipListEntry(null,32);
    	for(int i=0;i<32;i++)
    		head.next[i]=tail;
    }
    
    /**
     * Finds the position of elements that are smaller than x, in every level of the list
     *  
     * @param x : value used for comparison
     * @return : Array of SkipListEntry nodes for each level in the list
     */
    public SkipListEntry[] find(T x){
    	SkipListEntry[] prev= new SkipListEntry[maxLevel];
    	
    	for(int i=0;i<maxLevel;i++){
    		SkipListEntry p=head;

    		int j=0;
    		while(p.next[i].element!=null &&
    				p.next[i].element.compareTo(x)<0){
    			p=p.next[i];
    		}
    		prev[i]=p;
    	}
    	
    	return prev;
    }
     
//    public int chooseLevel(int maxLevel){
//    	int i=1;
//    	Random r= new Random();
//    	while(i<maxLevel){
//    		Boolean b= r.nextBoolean();
//    		if(b){
//    			i++;
////    			maxLevel++;
////    			rebuild();
//    		}
//    		else
//    			break;
//    	}
//    	return i;
//    }
    
    /**
     * Randomly chooses a level for the element to be added
     * 
     * @param level : current maximum level
     * @return : randomly chosen level
     */
    public int chooseLevel(int level){
    	int mask=(1<<level)-1;
    	Random r= new Random();
    	int rand = r.nextInt()&mask;
    	int lev= Integer.numberOfTrailingZeros(rand);
    	if(lev>level){
    		maxLevel++;
    		return maxLevel;
    	}
    	else
    		return lev+1;
    }

    /**
     * Adds an element to the list. If the elements exists, it replaces it.
     * Otherwise it adds the new node to the list 
     *  
     * @param x : The element to be added to the list
     * @return : true if the element doesn't already exist, false otherwise
     */
    public boolean add(T x) {
    	SkipListEntry[] prev = find(x);
    	if(prev[0].next[0].element!=null &&prev[0].next[0].element.compareTo(x)==0){
    		prev[0].next[0].element=x;
    		return false;
    	}
    	else{
    		int lev= chooseLevel(maxLevel);

    		SkipListEntry n= new SkipListEntry(x,lev); //sir's notes choose level
    		prev=find(x);
    		for(int i=0;i<lev;i++){
    			n.next[i]=prev[i].next[i];
    			prev[i].next[i]=n;
    		}
    		size++;
    		return true;
    	}
    }


    /**
     * Gets the Node whose element value is smaller than or equal to the
     * the value of x
     * 
     * @param x : Value used for comparison
     * @return : SkipListEntry whose element value is smaller or equal to the value of x
     */
    public SkipListEntry getElement(T x){
    	int lev= maxLevel-1;
    	SkipListEntry p= head;
    	while(lev>=0 && p.next[lev].element!=null ){
    		while(p.next[lev].element!=null && p.next[lev].element.compareTo(x)<=0)
    		{
    			p=p.next[lev];
    		}
    		lev--;
    	}
    	return p;
    }

    /**
     * Finds the smallest element that is greater or equal to x
     * 
     * @param x : the value used for comparison
     * @return : the ceiling value of x
     */
    public T ceiling(T x) {
    	SkipListEntry p=getElement(x);
    	
   		return (T) p.next[0].element;
    }

    /**
     * Checks if the list contains x
     * 
     * @param x : the value used for comparison
     * @return : true if the list contains x, false otherwise
     */
    public boolean contains(T x) {
    	SkipListEntry p=getElement(x);
    	
    	return (p.element.compareTo(x)==0);
    }

    /**
     * Finds the first element of list
     * 
     * @return : first element in the list
     */
    public T first() {
    	SkipListEntry val= head.next[0];
    	if(val.element == null)
    		return null;
    	
    	return (T)val.element;
    }

    /**
     * Finds the largest element that is less than or equal to x
     * 
     * @param x : the value used for comparison
     * @return : the floor value of x
     */
    public T floor(T x) {
    	SkipListEntry p=getElement(x);
   		return (T) p.element;
    }

    /**
     * Finds element at index n of list.  First element is at index 0.
     * 
     * @param n : index value
     * @return :Element at index n, null if n>size
     */
    public T get(int n) {
    	if(n>=size)
    		return null;
    	SkipListEntry p = head;
    	for(int i=0;i<=n;i++){
    		p=p.next[0];
    	}
	return (T)p.element;
    }
    
    

    /**
     * Checks if the list is empty
     * 
     * @return : true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
    	if(size==0)
    		return true;
	return false;
    }

    /**
     * Iterates through the elements of list in sorted order
     * 
     * @return :Iterator containing elements of list in sorted order
     */
    public Iterator<T> iterator() {
    	ArrayList<T> list = new ArrayList<T>();
    	SkipListEntry p = head;
    	while(p.next[0].element!=null){
    		list.add((T) p.next[0].element);
    		p=p.next[0];
    	}
	return list.iterator();
    }

    /**
     * Finds the last element of list
     * 
     * @return : last element of the list
     */
    public T last() {
    
    if(size==0)
    	return null;
    
    int lev= maxLevel-1;
	SkipListEntry p= head;
	while(lev>=0 && p.next[lev].element!=null ){
		while(p.next[lev].element!=null)
		{
			p=p.next[lev];
		}
		lev--;
	}
	return (T)p.element;
    
    }

    /**
     * Reorganize the elements of the list into a perfect skip list
     */
    public void rebuild() {
    	maxLevel=(int)Math.ceil(Math.log(size()+1)/Math.log(2));
    	rebuild(head, 0,size-1,maxLevel,tail);
    }
    
    /**
     * Helper function. Used to reorganize the elements of the list into a perfect skip list.
     * Finds the middle element between "low" and "high" and creates a new node with next array of
     * "level" length. It then goes ahead to call rebuild on the left and right portions of the list,
     * about the current middle node.
     * 
     * @param head : first node
     * @param low : lower index
     * @param high : higher index
     * @param level : level of next
     * @param tail : last node
     */
    public void rebuild(SkipListEntry head, int low,int high, int level, SkipListEntry tail){
    	if(high==low)
    		level=1;
    	if(high<0)
    		return;
    	if(head.next[0].equals(tail))
    		return;
    	int mid;
    	if((high-low+1)%2==0)
    		mid=(int) Math.floor((double)(high+low+1)/2.0);
    	else
    		mid=(int) Math.floor((double)(high+low)/2.0);
    	int pos=Math.abs(mid-low);
    	
    	SkipListEntry p= head;
    	
    	for(int i=0;i<pos;i++){
    		p=p.next[0];
    	}
    	
    	SkipListEntry prev = p;
   		p=p.next[0];
    	
    	SkipListEntry curr = new SkipListEntry(p.element,level);
    	head.next[level-1]=curr;
    	curr.next[level-1]=tail;
    	for(int i=level-2;i>=1;i--){
    		head.next[i]=curr;
    		curr.next[i]=tail;
    	}
    	prev.next[0]=curr;
    	curr.next[0]=p.next[0];

    	if(level>1)
    		level=level-1;

    	high=high-pos-1;
    	
    	rebuild(head,0,pos-1,level,curr);
    	rebuild(curr,0,high,level,tail);
    }

    
    /**
     * Removes x from list.  Removed element is returned. Returns null if x not in list
     * 
     * @param x : element to be removed
     * @return : element that is removed if it exists in the list, null otherwise
     */
    public T remove(T x) {
    	SkipListEntry[] prev= find(x);
    	SkipListEntry n= prev[0].next[0];
    	if(n.element.compareTo(x)!=0)
    		return null;
    	else{
    		for(int i=0;i<maxLevel;i++){
    			if(prev[i].next[i]==n)
    				prev[i].next[i]=n.next[i];
    			else
    				break;
    		}
    		size--;
    		return (T)n.element;
    	}
    }

    /**
     * Return the number of elements in the list
     * @return : integer value that is number of elements in the list
     */
    public int size() {
    	return size;
    }
    
    /**
     * Main Function.
     * @param args : Command Line arguments
     */
    public static void main(String args[]){
    	SkipList s=new SkipList();
    	
    	System.out.println("Is list empty?:"+ s.isEmpty());
    	
    	s.add(6);
    	s.add(5);
    	s.add(10);
    	s.add(8);
    	s.add(3);
    	s.add(2);
    	s.add(30);
    	s.add(15);
    	
    	i = s.iterator();
    	while(i.hasNext()){
    		System.out.println(i.next());
    	}
    	System.out.println();
    	System.out.println("Size : "+s.size());
    	System.out.println("Maxlevel : "+s.maxLevel);
    	System.out.println("Is list empty?:"+ s.isEmpty());
    	s.display();
    	s.remove(6);
    	System.out.println("Removing...");
    	s.display();
    	s.rebuild();
    	
    	s.display();
    	
    	System.out.println("Ceiling of 5 is "+ s.ceiling(5));
    	System.out.println("Floor of 14 is "+ s.floor(14));
    	System.out.println("Contains 5 : " +s.contains(5));
    	System.out.println("First "+s.first());
    	System.out.println("Last: "+ s.last());
    	
    	
    }
    
    /**
     * Display the elements of the list along each level.
     * Starts from maxLevel
     */
    public void display(){
    	
    	System.out.println("\nDisplay!");
    	for(int i=maxLevel-1;i>=0;i--){

    		SkipListEntry p=head;
    		System.out.print(p.element+"->");
    		
    		while(p.next[i].element!=null){
    			System.out.print(p.next[i].element+"->");
    			p=p.next[i];
    		}
    		
    		System.out.println(p.next[i].element);
    	}
    	
    }
    
}
