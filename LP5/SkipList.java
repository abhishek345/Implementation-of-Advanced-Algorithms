// Change this to your group number
package cs6301.g21;

import java.util.ArrayList;
//import cs6301.g00.*;
import java.util.Iterator;
import java.util.Random;

import cs6301.g21.SkipList.SkipListEntry;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {
	
//	final Integer InfinityPos= Integer.MAX_VALUE;
//	final Integer InfinityNeg= Integer.MIN_VALUE;
	class SkipListEntry<T extends Comparable<? super T>>{
		T element;
		SkipListEntry next[];
		
		public SkipListEntry(T x, int lev){
			element=x;
			next=new SkipListEntry[lev];
		}
	}
	
	int maxLevel;
	int size;
	SkipListEntry head,tail;
	
    // Constructor
    public SkipList() {
    	size=0;
    	maxLevel=1;
    	head= new SkipListEntry(null,32);//InfinityNeg
    	tail= new SkipListEntry(null,32);//InfinityPos
    	for(int i=0;i<32;i++)
    		head.next[i]=tail;
    	
//    	System.out.println("SkipList constructor: checking head->next");
//    	for(int i=0;i<32;i++){
//    		System.out.println(i+ " " + head.next[i].element);
//    	}
//    	System.out.println();
    }
    
    public SkipListEntry[] find(T x){
    	SkipListEntry[] prev= new SkipListEntry[maxLevel];
    	SkipListEntry p=head;
    	
    	for(int i=0;i<maxLevel;i++){
//    		System.out.println(maxLevel+ " i="+i);
//    		try{
//    		System.out.println(p.next[0].element.toString());
    		int j=0;
    		while(p.next[i].element!=null &&
    				p.next[i].element.compareTo(x)<0){
//    			System.out.println("While loop " + j++);
    			p=p.next[i];
    		}
    		prev[i]=p;
//    		}
//    		catch(NullPointerException e){
//    			return prev;//or return null
//        	}
    	}
    	
    	return prev;
    }
    
//  According to printed notes  
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
    
//    According to sir's notes
    public int chooseLevel(int level){
    	int mask=(1<<level)-1;
    	Random r= new Random();
    	int rand = r.nextInt()&mask;
//    	System.out.println(rand +" "+ Integer.numberOfTrailingZeros(rand));
    	int lev= Integer.numberOfTrailingZeros(rand);
    	if(lev>level){
    		maxLevel++;
//    		rebuild();
    		return maxLevel;
    	}
    	else
    		return lev+1;
    }

    // Add x to list. If x already exists, replace it. Returns true if new node is added to list
    public boolean add(T x) {
//    	System.out.println("\nAdding element : "+x);
    	SkipListEntry[] prev = find(x);
    	if(prev[0].next[0].element!=null &&prev[0].next[0].element.compareTo(x)==0){
    		prev[0].next[0].element=x;
    		return false;
    	}
    	else{
    		int lev= chooseLevel(maxLevel);
    		//
//    		if(lev>maxLevel)
//    		prev=find(x);
    		//
//    		SkipListEntry n= new SkipListEntry(x,lev+1); //printed notes chooselevel
    		SkipListEntry n= new SkipListEntry(x,lev); //sir's notes choose level
//    		System.out.println("Value at position before adding node is " + prev[0].element + "; Level of "+ x + " is "+ lev+ "; MaxLevel is "+maxLevel);
    		prev=find(x);
    		for(int i=0;i<lev;i++){
    			System.out.println("i =" +i);
//    			System.out.println("For " + x + ", In add, level = "+ i+ "\n prev[i].next[i] = " + prev[i].next[i].element );
//    			System.out.println(i);
    			n.next[i]=prev[i].next[i];
    			prev[i].next[i]=n;
    			
//    			System.out.println(prev[i].element+"->"+prev[i].next[i].element+"->"+prev[i].next[i].next[i].element);
    		}
    		size++;
    		return true;
    	}
    }

    public SkipListEntry getElement(T x){
    	int lev= maxLevel-1;
    	SkipListEntry p= head;
    	while(lev>=0 && p.next[lev].element!=null ){
//    		System.out.println("Level: "+lev);
    		while(p.next[lev].element!=null && p.next[lev].element.compareTo(x)<=0)
    		{
    			p=p.next[lev];
//    			System.out.println("At : "+ p.element);
    		}
    		lev--;
    	}
    	return p;
    }
    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
    	SkipListEntry p=getElement(x);
    	
   		return (T) p.next[0].element;
    }

    // Does list contain x?
    public boolean contains(T x) {
    	SkipListEntry p=getElement(x);
    	
    	return (p.element.compareTo(x)==0);
    }


    // Return first element of list
    public T first() {
    	T val=(T) head.next[0].element;
    	if(head.next[0].element == null)
    		return null;
    	
    	return val;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
    	SkipListEntry p=getElement(x);
   		return (T) p.element;
    }

//    So not sure. damn sure it can make use of the skip list properties (MaxLev=log(n))
    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
    	if(n>=size)
    		return null;
    	SkipListEntry p = head;
    	for(int i=0;i<=n;i++){
    		p=p.next[0];
    	}
	return (T)p.element;
    }
    
    

    // Is the list empty?
    public boolean isEmpty() {
    	if(size==0)
    		return true;
	return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
    	ArrayList<T> list = new ArrayList<T>();
    	SkipListEntry p = head;
    	while(p.next[0].element!=null){
    		list.add((T) p.next[0].element);
    		p=p.next[0];
    	}
	return list.iterator();
    }

    // Return last element of list
    public T last() {
    
    if(size==0)
    	return null;
    SkipListEntry[] p = find(null);//Last element
    return (T) p[0].element;
    }

    // Reorganize the elements of the list into a perfect skip list
    public void rebuild() {
//    	int pos=(int)Math.ceil(size/2);
//    	rebuild(head, pos,maxLevel,tail);
    	maxLevel=(int)Math.ceil(Math.log(size()+1)/Math.log(2));
    	rebuild(head, 0,size-1,maxLevel,tail);
    }
    
    public void rebuild(SkipListEntry head, int low,int high, int level, SkipListEntry tail){
    	if(high==low)
    		level=1;
    	if(high<0)
    		return;
    	if(head.next[0].equals(tail))
    		return;
//    	if(level==0)
//    		return;
    	int mid;
    	if((high-low+1)%2==0)
    		mid=(int) Math.floor((double)(high+low+1)/2.0);
    	else
    		mid=(int) Math.floor((double)(high+low)/2.0);
//    	if(mid>(high-low))
//    		mid--;
    	int pos=Math.abs(mid-low);
    	
//    	System.out.println("Low: "+low+" High:"+ high+" pos:"+pos);
//    	if((pos==high || pos==low))
//    		return;
    	
    	SkipListEntry p= head;
    	
    	for(int i=0;i<pos;i++){
    		p=p.next[0];
    	}
    	
    	SkipListEntry prev = p;
   		p=p.next[0];
    	
//    	System.out.println("Rebuild!");
//    	System.out.println(head.element+ " "+ p.element+" "+tail.element + " "+pos+ " "+ level+ " Prev: "+ prev.element + " p.next[0]: "+ p.next[0].element);
    	
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

    
//    public void rebuild(SkipListEntry head, int pos, int level, SkipListEntry tail){
//    	if(pos==0)
//    		return;
//    	
//    	SkipListEntry p= head;
//    	
//    	for(int i=0;i<pos-1;i++){
//    		p=p.next[0];
//    	}
//    	
//    	SkipListEntry prev = p;
//    	p=p.next[0];
//    	
//    	SkipListEntry curr = new SkipListEntry(p.element,level);
//    	head.next[level-1]=curr;
//    	curr.next[level-1]=tail;
//    	for(int i=level-1;i>=1;i--){
//    		head.next[i]=curr;
//    		curr.next[i]=tail;
//    	}
//    	prev.next[0]=curr;
//    	curr.next[0]=p.next[0];
//    	pos=(int)Math.ceil(pos/2);
//    	rebuild(head,pos,level-1,curr);
//    	rebuild(curr,pos,level-1,tail);
//    }

    
    /*
     * Assuming size=8, maxlevel=log(8)=3
     * Rebuild(head, 8/2,maxLevel,tail)
     * 
     * Rebuild(head, pos, level, tail)
     * 		if(pos=0)
     * 			return
     * 		p=head;
     * 		for i<-0 to pos-2
     * 			p=p.next[0];
     * 			
     *		prev=p;
     *		//next=p.next[0].next[0];
     *		p=p.next[0]
     *		curr=new skipListEntry(p.element,level);
     *		head.next[level]=curr;
     *		curr.next[level]=tail;
     *		for i<-level-1 down to 1
     *			head.next[i]=curr;
     *			curr.next[i]=tail;
     *		prev.next[0]=curr;
     *		curr.next[0]=p.next[0];
     *		
     *		rebuild(head,pos/2,level-1,curr)
     *		rebuild(curr,pos/2,level-1,tail)
     *		
     * 		
     *
     */
    // Remove x from list.  Removed element is returned. Return null if x not in list
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

    // Return the number of elements in the list
    public int size() {
    	return size;
    }
    
    public static void main(String args[]){
    	SkipList s=new SkipList();
    	s.add(15);
    	s.add(6);
    	s.add(5);
    	s.add(10);
    	s.add(8);
    	
    	Iterator i = s.iterator();
    	while(i.hasNext()){
    		System.out.println(i.next());
    	}
    	System.out.println();
    	
    	s.add(3);
    	s.add(2);
    	s.add(1);
    	
    	i = s.iterator();
    	while(i.hasNext()){
    		System.out.println(i.next());
    	}
    	System.out.println();
    	System.out.println("Size : "+s.size());
    	System.out.println("Maxlevel : "+s.maxLevel);
    	s.display();
    	s.rebuild();
    	
    	s.display();
    	
//    	System.out.println("Ceiling of 5 is "+ s.ceiling(5));
    	System.out.println("Floor of 14 is "+ s.floor(14));
    	System.out.println(s.contains(5));
    	
    	
    }
    
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
