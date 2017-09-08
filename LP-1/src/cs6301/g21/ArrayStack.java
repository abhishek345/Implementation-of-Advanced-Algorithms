package cs6301.g21;

public class ArrayStack {
    private int arr[];
    private int top;

    public ArrayStack(){
        //default constructor, creates stack of size 10
        arr = new int[10];
        top = -1;
    }

    public ArrayStack(int capacity){
        arr = new int[capacity];
        top = -1;
    }

    public void push(int obj){
        if(top < arr.length-1){
            top++;
            arr[top] = obj;
        }
        else{
            throw new StackOverflowError("Stack is Full, cannot perform push.");
        }
    }

    public int pop(){
        if(top >= 0){
            return arr[top--];
        }
        else
            return Integer.MIN_VALUE;
    }

    public static void main(String args[]){
        ArrayStack stak = new ArrayStack(5);
        System.out.println(stak.pop());
        stak.push(1);
        stak.push(2);
        stak.push(3);
        stak.push(4);
        stak.push(5);

        System.out.println(stak.pop());

    }
}
