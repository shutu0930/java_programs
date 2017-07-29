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
//		BST bt = new NonEmptyBST(5, new NonEmptyBST(3), new NonEmptyBST(13,new NonEmptyBST(8), new NonEmptyBST(14)) );
//		System.out.println("size : " + bt.size());
//		
//		System.out.println(bt.treeshow());
//		System.out.println("insert 7 : ");
//		bt = bt.insert(7);
//		System.out.println(bt.treeshow());
//		System.out.println("insert 9 : ");
//		bt = bt.insert(9);
//		System.out.println(bt.treeshow());
//		System.out.println("remove 13 : ");
//		bt = bt.remove(13);
//		System.out.println(bt.treeshow());
		
//		BST bt=new NonEmptyBST(5);
//		bt=bt.insert(7);
//		bt=bt.insert(2);
//		bt=bt.insert(20);
//		
//		System.out.println(bt.show()+"size:"+bt.size());
//		bt=bt.remove(5);
//		System.out.println(bt.show()+"size:"+bt.size());
		
		
		
		BST bt = new NonEmptyBST(5);
		bt = bt.insert(3);
		bt = bt.insert(7);
		bt = bt.insert(-2);
		bt = bt.insert(4);
		bt = bt.insert(10);
		bt = bt.remove(10);
		System.out.println("size: " + bt.size());
		System.out.println("height: " + bt.height());
		System.out.println(bt.show());
		//System.out.println(bt.getSum(5));
		//System.out.println(bt.findCountSum(7));
		bt.bft();
		//bt.dft();
	}

}
