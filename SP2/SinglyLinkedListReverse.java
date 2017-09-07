package cs6301.g21;

import java.util.Stack;


public class SinglyLinkedListReverse<E> {
	
	Node<E> head = null;
	
	Node<E> insertElement(E element){
		if(head == null){
			head = new Node<E>();
			head.element = element;
			return head;
		}
		else{
			Node<E> temp = head;
			head = head.next;
			Node<E> nextNode = insertElement(element);
			head = temp;
			head.next = nextNode;
			return head;
		}
	}
	
	Node<E> reverseListRecursive(Node<E> temp){
		if(temp.next != null){
			Node<E> oldHead = null;
			if(temp == head)
				oldHead = temp;
			Node<E> prevNode = reverseListRecursive(temp.next);
			prevNode.next = temp;
			if(temp == oldHead)
				temp.next = null;
			return temp;
		}
		else{
			head = temp;
			return temp;
		}
	}
	
	void printListReverseRecursive(Node<E> temp){
		if(temp != null){
			printListReverseRecursive(temp.next);
			System.out.println(temp.element);
		}
	}
	
	void reverseListNonRecursive(Node<E> temp){
		Stack<E> stackList = new Stack<E>();
		while(temp != null){
			stackList.push(temp.element);
			temp = temp.next;
		}
		head = null;
		while(!stackList.empty())
			insertElement(stackList.pop());
	}
	
	void printListReverseNonRecursive(Node<E> temp){
		Stack<E> stackList = new Stack<E>();
		while(temp != null){
			stackList.push(temp.element);
			temp = temp.next;
		}
		while(!stackList.empty())
			System.out.println(stackList.pop());
	}
	
	public static void main(String[] args){
		int n = 10;
		if(args.length>0){
			n = Integer.parseInt(args[0]);
		}
		
		SinglyLinkedListReverse<Integer> newList = new SinglyLinkedListReverse<Integer>();
		for(int i=1;i<=n;i++)
			newList.insertElement(i);
		Node<Integer> temp = newList.head;
		newList.reverseListNonRecursive(temp);
		newList.printListReverseNonRecursive(temp);
		newList.printListReverseRecursive(temp);
	}

}

class Node<E>{
	E element = null;
	Node<E> next = null;
	
}
