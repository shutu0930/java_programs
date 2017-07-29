/**
 * AvlBinaryTreeDemo - some code to demo the use of the avl binary sorted tree.
 * 
 * @author Eric McCreath - GPLv2
 */

public class AvlBinaryTreeDemo {

	public static void main(String[] args) {
		BinaryTree bt = new AvlEmptyBinaryTree();
		System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
				+ bt.treeshow());
		for (int i = 0; i < 16; i++) {
			bt = bt.insert(i);
			System.out.println("insert : " + i);
			System.out.println(
					 bt.treeshow());
		}
		
		
		bt = new AvlEmptyBinaryTree();
		System.out.println("size : " + bt.size());

		System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
				+ bt.treeshow());
		System.out.println("insert 7 : ");
		bt = bt.insert(7);
		System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
				+ bt.treeshow());
		System.out.println("insert 9 : ");
		bt = bt.insert(9);
		System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
				+ bt.treeshow());

		System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
				+ bt.treeshow());

		for (int i = 0; i < 25; i++) {
			bt = bt.insert(30 - i);
			System.out.println("avlBalanced " + bt.avlBalanced() + "\n"
					+ bt.treeshow());
		}
		
		               // below code from Shao who spotted a bug in my implementation.
		               // it appears to have duplicated the final 4 down both sides of the tree
		               // fixed by changing a left to a right in rotateLR
		                 BinaryTree bt2 = new AvlEmptyBinaryTree();
		               System.out.println("insert: 5");
		               bt2 = bt2.insert(5);
		               System.out.println(bt2.treeshow());
		               System.out.println("insert: 2");
		               bt2 = bt2.insert(2);
		               System.out.println(bt2.treeshow());
		               System.out.println("insert: 6");
		               bt2 = bt2.insert(6);
		               System.out.println(bt2.treeshow());
		               System.out.println("insert: 1");
		               bt2 = bt2.insert(1);
		               System.out.println(bt2.treeshow());
		               System.out.println("insert: 3");
		               bt2 = bt2.insert(3);
		               System.out.println(bt2.treeshow());
		               System.out.println("insert: 4");
		               bt2 = bt2.insert(4);
		               System.out.println(bt2.treeshow());
		               
		               
		            // below code based on Li who spotted a bug in my implementation.
		            // something to do with balance, although, it currently looks okay
		                        bt2 = new AvlEmptyBinaryTree();
		                       System.out.println("insert: 5 3, 13, 8");
		                       bt2 = bt2.insert(5);
		                       bt2 = bt2.insert(3);
		                       bt2 = bt2.insert(13);
		                       bt2 = bt2.insert(8);
		                              
		                       System.out.println(bt2.treeshow());
		                       System.out.println("insert: 7");
		                       bt2 = bt2.insert(7);
		                       System.out.println(bt2.treeshow());
		               

		
	}

}
