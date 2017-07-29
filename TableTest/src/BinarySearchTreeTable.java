import java.awt.Color;

/*
 * BinarySearchTreeTable - this implements the Table using a Binary search tree. 
 * 
 */
public class BinarySearchTreeTable implements Table {

	
	// add fields
	BinaryNode root;
	public BinarySearchTreeTable() {
		// constructor code
		root = new BinaryNode();
	}
	
	@Override
	public void insert(int key, String value) {
		// add your code for insert
	}

	@Override
	public String lookup(int key) {
		// add your code for lookup
		return null;
	}

	@Override
	public Color color() {
		return Color.red;
	}

	@Override
	public String name() {
		return "BinarySearchTree";
	}

}
