import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<T> implements Iterator<T>{

  private class StackObj{
    BST.Entry t;
    int dir;
    StackObj(BST.Entry t, int d){
      this.t = t;
      dir = d;
    }
    
    int getDir(){ return dir; }
    
    BST.Entry node(){ return t; }
  }
  
  Stack<StackObj> stack = new Stack<>();
  
  public TreeIterator(BST.Entry root){
    stack.push(new StackObj(root, 0));
    //System.out.println("push root " + root.element);
    if(root != null)
      travelLeft(root);
  }
  
  void travelLeft(BST.Entry t){
    while(t.left != null){
        t = t.left;
        //System.out.println(" travel push " + t.element);
        stack.push(new StackObj(t, 0));//0 means L child pushed, so push R    
    }
  }
  public boolean hasNext(){
      if(stack.isEmpty())
        return false;
      return true;        
  }
  
  public T next(){
    StackObj t = stack.pop();
    //System.out.println("pop " + t.node().element + " D: " + t.getDir());
    if(t.getDir() == -1){//no child seen
      stack.push(new StackObj(t.node(), 0));
      if(t.node().left != null){
        travelLeft(t.node());
        return (T) stack.pop().node().element;
      }
      else
        t = stack.pop();
    }
    if(t.getDir() == 0){
      if(t.node().right != null)
        stack.push(new StackObj(t.node().right, -1));
      return (T) t.node().element;
    }
    return null;
  }
  
  public void remove() {
       throw new UnsupportedOperationException();
  }
  
  public static void main(String args[]){
    BST<Integer> t = new BST<>();
    t.add(1); t.add(2); t.add(6);
    t.add(4); t.add(3); t.add(5);t.add(7);
    
    for(Integer x: t)
      System.out.println(x);
  }
}
