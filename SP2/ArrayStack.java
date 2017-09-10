/**
 * Implementation of a Stack using ArrayList.
 * @author Abhishek Jagwani, Umang Shah, Vibha  Belavadi, Shreya Vishwanath Rao
 * @version 1.0 : 09/05/2016
 *
 */
 
package cs6301.g21;

import java.util.ArrayList;

public class ArrayStack<T> {
    private ArrayList<T> arr;
    //use of ArrayList to support generic types
    private int top;
    private int MAX = 10;
    
    /**
	 * default constructor, creates stack of size 10
	 * 
	 * 
	 */
    public ArrayStack(){
        
        arr = new ArrayList<>(MAX);
        top = -1;
    }
    
    /**
	 * Create a stack of specified size
	 * @param args : max capacity of stack
	 */
    public ArrayStack(int capacity){
        MAX = capacity;
        arr = new ArrayList<>(MAX);
        top = -1;
    }
    
    /**
	 * Push an element onto the stack, if stack is full throws out of bounds exception
	 * @param args : the element to be pushed
	 */
    public void push(T ele){
        if(top < MAX-1){
            top++;
            arr.add(ele);
        }
        else{
            throw new IndexOutOfBoundsException("Stack is Full, cannot push.");
        }
    }
  
    /**
	 * Pop, remove and return an element from the top of stack. throws out of bounds exception if stack is empty
	 * 
	 */
    public T pop(){
        if(top >= 0){
            T ele = arr.remove(top--);
            return ele;
        }
        else
            throw new IndexOutOfBoundsException("Stack is Empty, cannot pop.");
    }
    
    /**
	 * main function to create stack and call its various methods
	 * @param args : command line input
	 * @throws IndexOutOfBoundsException : Exception if stack operations are performed on invalid (overflow or
	 * underflow) states
	 */
    public static void main(String args[])throws IndexOutOfBoundsException{
        ArrayStack<Integer> stak = new ArrayStack<>(5);
        //System.out.println(stak.pop()); // causes exception
        stak.push(1);
        stak.push(2);
        stak.push(3);
        stak.push(4);
        stak.push(5);
        //stak.push(8); //causes exception
        System.out.println(stak.pop());
        stak.push(6);
        System.out.println(stak.pop());
    }
}

