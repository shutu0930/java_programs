import java.util.Stack;

/**
 * COMP 2100/2500/6442 Exam 2016
 * Question 3
 * BST - this defines a simple binary search tree for storing a set of integers.
 */
 
public abstract class BST {
 
    public abstract BST insert(int d); // add an element to the tree, this returns a new/modified tree
    public abstract int size(); // the number of elements in the tree
    public abstract int height(); // the height of the tree
    public abstract String show(); // Print the tree structure
    public abstract boolean isEmpty(); // check if the tree is empty
    public abstract BST remove(int d); // remove an element from the tree, this returns a new/modifited tree
    public abstract Integer biggest();
    public abstract int findCountSum(int targetSum);
    public abstract int getSum(int d);
    public abstract int findPath(int targetSum, Stack<Integer> path,int paths);
    public abstract void dft();
    public abstract void bft();
    public abstract int getData();
    public abstract BST getLeft();
    public abstract BST getRight();
    
    /* Add findCountSum function declaration here */ 
}
