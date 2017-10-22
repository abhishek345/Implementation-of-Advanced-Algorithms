package cs6301.g21;

/**
 *
 * @param <T>
 */

public class AVLTree1<T extends Comparable<? super T>> extends BST<T> {

    static class Entry<T> extends BST.Entry<T> {
        int height;
        int parent;

        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            height = 0;

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

    /* Function to insert data recursively */
    public boolean addNode(T x)
    {
        AVLTree1.Entry<T> curr = new Entry<T>(x, null, null);

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
                setHeight(node);

                //decrement the size of the stack
                size--;

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
        AVLTree1.Entry<T> n1 = (Entry<T>) node.left;
        node.left = n1.right;
        n1.right = node;
        setHeight(node);
        setHeight(n1);
        return n1;
    }

    /**
     * Rotate binary tree node with right child
     * @param node
     * @return
     */
    private AVLTree1.Entry<T> rotateWithRightChild(AVLTree1.Entry<T> node)
    {
        AVLTree1.Entry<T> n1 = (Entry) node.right;
        node.right = n1.left;
        n1.left = node;
        if(node.equals(root)){
            root = n1;
        }

        setHeight(node);
        setHeight(n1);
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
                node = rebalance(curr.getElement(), ( Entry<T>) node);

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

        t.addNode(1);
        t.addNode(2);
        t.addNode(3);
        t.addNode(4);
        t.addNode(5);
        t.addNode(6);
        t.addNode(8);
        t.addNode(9);
        t.addNode(7);
        t.addNode(10);

        for(Integer x: t)
            System.out.println(x);

    }


}
