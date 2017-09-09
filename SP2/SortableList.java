package cs6301.g21;

import java.util.Random;
import cs6301.g00;

/**
 * 
 * @author Shreya Vishwanath Rao, Umang Shah, Abhishek Jagwani, Vibha Belavadi
 * @version 1.0: 2017/09/05
 * @since 1.0
 * 
 * Implementation of an class that extends a SinglyLinkedList class and sorting the contents of its object using MergeSort
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {


	/**
	 * Class constructor. It calls the parent class (SinglyLinkedList)
	 */
	public SortableList(){
		super();
	}
	
	/**
	 * Merges two sorted linked lists. One is the list that calls this function
	 * and the other is the one sent as a parameter
	 * 
	 * @param otherlist : SortableList<T>: the second list object used to merge with the calling object 
	 */
	void merge(SortableList<T> otherlist){
		
		/*head and tail of first list*/
		Entry<T> head1= this.head.next; 
		Entry<T> tail1=this.tail;
		
		/*head and tail of second list*/
		Entry<T> head2= otherlist.head.next;
		Entry<T> tail2=otherlist.tail;
		
		/*head of the final merged list*/
		Entry<T> index= this.head;
		
		while(head1!=tail1.next && head2!=null){
			int cmp=head1.element.compareTo(head2.element);
			if(cmp<=0){
				index.next=head1;
				head1=head1.next;
				index=index.next;
				
			}
			else{
				index.next=head2;
				head2=head2.next;
				index=index.next;
				
			}
		}
		
		if(head2==null){
			tail2.next=head1;
			tail=tail1;
		}
		else{
			tail1.next=head2;
			tail=tail2;
		}
		
	}
	
	/**
	 * Recursive Merge Sort on the object calling the function.
	 * 
	 * The function splits the List to two SortableLists and calls the 
	 * mergeSort function on either lists. Once both lists are sorted,
	 * they are merged
	 * 
	 */
	void mergeSort(){
		
		if(size>1){
			int middle=size/2;
			
			Entry<T> tempTail=this.head.next;
			
			/*move tempTail to the middle element*/
			while(middle>1){
				tempTail=tempTail.next;
				middle--;
			}
			Entry<T> tempHead=tempTail.next;
			
			/*Left side of the list*/
			SortableList<T> leftList= new SortableList<T>();
			leftList.size=size/2;
			leftList.head.next=this.head.next;
			leftList.tail=tempTail;
			leftList.tail.next=null;
			
			leftList.mergeSort();
			
			/*Right side of the list*/
			SortableList<T> rightList = new SortableList<T>();
			rightList.size=size/2;
			rightList.head.next=tempHead;
			rightList.tail=this.tail;
			rightList.tail.next=null;

			rightList.mergeSort();
			
			leftList.merge(rightList);
			this.head.next=leftList.head.next;
			this.tail=leftList.tail;
			
		}
		
	}

	/**
	 * Merge Sort function to sort a SortableList object
	 * 
	 * @param list : SortableList<T> : list to be sorted 
	 * 	 
	 */
	public static <T extends Comparable<? super T>> void mergeSort(SortableList<T> list){
		list.mergeSort();
	}
	
	/**
	 * Main function. Creates a SortableList object and sorts it 
	 * using Merge Sort 
	 * 
	 * @param args :String[] : command line arguments
	 */
	public static void main(String[] args){
		int n=8;
		Random r= new Random();
		
		if(args.length>0){
			n=Integer.parseInt(args[0]);
		}
		
		SortableList<Integer> sl= new SortableList<Integer>();
		
		for(int i=1;i<=n;i++){
			sl.add(new Integer(r.nextInt(n)));
		}
		sl.printList();
		
		sl.mergeSort();
		sl.printList();
	}
}
