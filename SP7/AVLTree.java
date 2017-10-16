
/** Starter code for AVL Tree
 */


import java.util.Comparator;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
    static class Entry<T> extends BST.Entry<T> {
        int height;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            height = 0;
        }
    }

    AVLTree() {
	super();
    }
    /*
    int height(Entry<T> u){
      if(u == null) return -1;
      int lh = height(u.left);
      int rh = height(u.right);
      return 1 + max(lh, rh);
    }
    
    int traversal(Entry<T> u, int d){
      if(u != null){
        int lh = traversal(u.left, d+1);
        int rh = traversal(u.right, d+1);
        height = 1 + max(lh ,rh);
        return height;
      }
      else return -1;
    }*/
    
    int max(int a, int b){
      return a > b ? a : b;
    }
    
    public boolean add(T x){
      super.add(x);
      //balance the tree
      if(stack != null){
        BST.Entry<T> t = stack.peek();  
        System.out.println(t.element);
      }
      return true;
    }
    
    public static void main(String args[]){
      AVLTree<Integer> t = new AVLTree<>();
      t.add(1); t.add(2); t.add(6);
      t.add(4); t.add(3); t.add(5);t.add(7);
      
    //  for(Integer x: t)
      //  System.out.println(x);
    }
}

