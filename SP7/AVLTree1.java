package cs6301.g21;

/**
 *
 * @param <T>
 */

public class AVLTree1<T extends Comparable<? super T>> extends BST<T> {

    static class Entry<T> extends BST.Entry<T> {
        int height;
        Entry<T> parent;

        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            height = 0;
            parent = null;
        }

        public T getElement() {
            return this.element;
        }
    }

    AVLTree1(){
        super();
    }

    /**
     * Function to check if the AVL Tree is empty
     * @return boolean: True if the AVL Tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return  root==null;
    }

    /**
     * Get height of a given node
     * @param node : AVL Tree node whose height is to be calculated
     * @return int : Height of the AVL Tree node
     */
    public int getHeight(AVLTree1.Entry<T> node){
        return node.height;
    }

    /**
     * Update the height of the nodes
     * @param node
     * @return
     */
    int setHeight(AVLTree1.Entry<T> node){
        if(node==null)
            return -1;

        int lh = -1;
        int rh = -1;

        if(node.left != null)
            lh = setHeight((Entry<T>) node.left);
        if(node.right != null)
            rh = setHeight((Entry<T>) node.right);

        node.height = 1+max(lh, rh);

        //System.out.println("Height of the tree is: "+height);
        return node.height;
    }

    /**
     * Helper function to find the maximum of the two heights
     * @param n1
     * @param n2
     * @return
     */
    private int max(int n1, int n2){
        return  n1 > n2 ? n1 : n2;
    }

    /**
     * Adds element to the AVL Tree
     * @param x
     * @return
     */
    public boolean add(T x){
        AVLTree1.Entry<T> curr = new Entry<T>(x, null, null);
        return addNode(curr);
    }

    /**
     * Function to insert data recursively
     * @param curr
     * @return
     */
    public boolean addNode(AVLTree1.Entry<T> curr)
    {

        //return false if updated duplicate node
        if(!super.addNode(curr))
            return false;

        //return true if stack has only root
        if(stack == null){
            return true;

        }else{

            while(!stack.isEmpty()){

                //get the parent of the node
                AVLTree1.Entry<T> node = (Entry<T>) stack.pop();
                if(node == null)
                    break;

                //set height of the parent
                setHeight(node);

                //update the height of the new parent
                node = rebalance(curr.getElement(), node);

                //set height
                setHeight(node);

                //parent updation: won't work
                /*AVLTree1.Entry<T> par = (Entry<T>) stack.peek();
                if(par != null){
                    if(par.left.getElement().compareTo(node.getElement()) > 0)
                        par.right = node;
                    else
                        par.left = node;
                }*/


            }

        }
        return true;

    }

    /**
     * Rotate binary tree node with left child
     * @param node
     * @return
     */
    private AVLTree1.Entry<T> rotateWithLeftChild(AVLTree1.Entry<T> node)
    {
        int flag = 0;
        AVLTree1.Entry<T> n1;

        if(node.equals(root)) {
            flag = -1;
        }

        n1 = (Entry<T>) node.left;
        node.left = n1.right;
        n1.right = node;

        setHeight(n1);

        if(flag == -1) {
            root = n1;
            root.parent = null;
        }
        return n1;
    }

    /**
     * Rotate binary tree node with right child
     * @param node
     * @return
     */
    private AVLTree1.Entry<T> rotateWithRightChild(AVLTree1.Entry<T> node)
    {
        int flag = 0;
        AVLTree1.Entry<T> n1;
        AVLTree1.Entry<T> par = node.parent;

        if(node.equals(root)) {
            flag = -1;
        }

        n1 = (Entry<T>) node.right;
        if(n1.left != null)
            n1.left.parent = node;
        node.right = n1.left;
        n1.left = node;
        node.parent = n1;

        //update the parent
        if(par != null){
            if(par.getElement().compareTo(n1.getElement()) > 0) {
                n1.parent = par;
                par.left = n1;
            }else {
                n1.parent = par;
                par.right = n1;
            }
        }

        setHeight(n1);

        if(flag == -1) {
            root = n1;
            root.parent = null;
        }
        return n1;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child
     * @param node
     * @return
     */
    private AVLTree1.Entry<T> doubleWithLeftChild(AVLTree1.Entry<T> node)
    {
        node.left = rotateWithRightChild((Entry<T>) node.left);
        return rotateWithLeftChild(node);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child
     * @param node
     * @return
     */
    private AVLTree1.Entry<T> doubleWithRightChild(AVLTree1.Entry<T> node)
    {
        node.right = rotateWithLeftChild((Entry<T>) node.right);
        return rotateWithRightChild(node);
    }

    /**
     * Check if the AVL tree needs to be rebalanced
     * @param val
     * @param parent
     * @return
     */
    //Check this function, need rotation in it's child
    private AVLTree1.Entry<T> rebalance(T val, AVLTree1.Entry<T> parent){

        AVLTree1.Entry<T> lchild = (Entry<T>) parent.left;
        AVLTree1.Entry<T> rchild = (Entry<T>) parent.right;

        int diff = 0;

        //Find the difference in heights between left child and right child
        if(lchild == null && rchild == null)
            return parent;

        if(lchild == null && rchild != null){
            diff = - getHeight(rchild) - 1;

        }else if(rchild == null && lchild != null){
            diff = getHeight(lchild) + 1;

        }else if(lchild != null && rchild !=null){
            diff = getHeight(lchild) - getHeight(rchild);
        }

        //check for balance conditions
        if(Math.abs(diff) == 1 | Math.abs(diff) == 0){
            return parent;

        }
        else if(diff == 2){
            if(val.compareTo(lchild.getElement()) < 0) {
                parent = rotateWithLeftChild(parent);

            }else {
                parent = doubleWithLeftChild(parent);

            }
        }else if(diff == -2){
            if(val.compareTo(rchild.getElement()) > 0) {
                parent = rotateWithRightChild(parent);

            }else {
                parent = doubleWithRightChild(parent);

            }
        }

        return parent;
    }

    public T remove(T x){

        //call the BST remove method
        T result = super.remove(x);
        if(result == null)
            return null;

        AVLTree1.Entry<T> curr = new AVLTree1.Entry<T>(x, null, null);

        if(stack.peek() == null) {
            return null;

        }else{
            while(!stack.isEmpty()){

                //get the parent of the element that has been deleted
                BST.Entry<T> node = stack.pop();

                //check the balancing condition:
                node = rebalance(curr.getElement(), (Entry<T>) node);

                //update the height of element
                setHeight((Entry<T>) node);

                //update the child node
                curr = (Entry<T>) node;
            }
        }

        return result;
    }

    public static void main(String args[]){
        AVLTree1<Integer> t = new AVLTree1<>();

        //Populate the AVL Tree
        /*Random rand = new Random();
        for(int i=0; i<1000; i++){
            int val = rand.nextInt(2000) + 1;
            t.add(val);
        }*/

        t.add(1);
        t.add(2);
        t.add(3);
        t.add(4);
        t.add(5);
        t.add(6);
        t.add(8);
        t.add(9);
        t.add(7);
        t.add(10);

        for(Integer x: t)
            System.out.println(x);

    }


}
