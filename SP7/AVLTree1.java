package cs6301.g21;

/**
 *
 * @param <T>
 */

public class AVLTree1<T extends Comparable<? super T>> extends BST<T> {

    static class Entry<T> extends BST.Entry<T> {
        int height;

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



    int setHeight(BST.Entry<T> node){
        if(node==null)
            return -1;

        int lh = setHeight(node.left);
        int rh = setHeight(node.right);

        return 1+max(lh, rh);
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
    //Does rebalancing only once: Need to fix it
    public boolean addNode(T x)
    {
        AVLTree1.Entry<T> newEle = new Entry<T>(x, null, null);
        AVLTree1.Entry<T> curr = newEle;

        //return false if updated duplicate node
        if(!super.addNode(newEle))
            return false;

        //return true if stack has only root
        if(stack == null || stack.peek() ==null) {
            return true;

        }else{

            while(!stack.isEmpty()){

                //get the parent of the node
                AVLTree1.Entry<T> node = (Entry<T>) stack.pop();

                //update the height of the new parent
                node = rebalace(curr.element, node);
                setHeight(node);

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
        return rotateWithRightChild( node );
    }

    //check if the AVL tree needs to be rebalanced
    private AVLTree1.Entry<T> rebalace(T val, AVLTree1.Entry<T> parent){

        AVLTree1.Entry<T> lchild = (Entry<T>) parent.left;
        AVLTree1.Entry<T> rchild = (Entry<T>) parent.right;

        int lHeight = getHeight(lchild);
        int rHeight = getHeight(rchild);

        //check for balance conditions
        if((lHeight - rHeight) == 2){
            if(val.compareTo(lchild.getElement()) < 0)
                parent = rotateWithLeftChild(parent);
            else
                parent = doubleWithLeftChild(parent);

        }else if((rHeight - lHeight) == 2){
            if(val.compareTo(rchild.getElement()) > 0)
                parent = rotateWithRightChild(parent);
            else
                parent = doubleWithRightChild(parent);

        }

        return parent;
    }

    //need to modify this a little
    public T remove(T x){

        //call the BST remove method
        T result = super.remove(x);
        if(result == null)
            return null;

        AVLTree1.Entry<T> curr = null;

        if(stack == null || stack.peek() ==null) {
            return null;

        }else{
            while(!stack.isEmpty()){

                //get the parent of the element that has been deleted
                AVLTree1.Entry<T> node = (Entry<T>) stack.pop();

                //check the balancing condition:
                node = rebalace(curr.element, node);

                //update the height of element
                setHeight(node);

                //update the child node
                curr = node;
            }
        }

        return result;
    }




}
