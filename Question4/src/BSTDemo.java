/**
 * COMP 2100/2500/6442 Exam 2016
 * Question 3
 * Author Name: 
 * UID: 
 *
 * BSTDemo 
 */

/* Add your solution here */
public class BSTDemo {


    public static void main(String[] args) {
        BST bt = new BinaryTree(5, new BinaryTree(3), new BinaryTree(7));
        bt = bt.insert(-2);
        bt = bt.insert(4);
        System.out.println(bt.show());
        System.out.println(bt.findCountSum(12));
        System.out.println(bt.findCountSum(7));
        System.out.println(bt.findCountSum(-1));
        System.out.println(bt.findCountSum(1));
        System.out.println(bt.findCountSum(6));

    }


}
