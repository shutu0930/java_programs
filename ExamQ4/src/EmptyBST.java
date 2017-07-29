import java.util.Stack;

public class EmptyBST extends BST {
	int paths=0;

	@Override
	public BST insert(int d) {
		
		return new NonEmptyBST(d);
	}

	@Override
	public int size() {
		
		return 0;
	}

	@Override
	public int height() {
		
		return -1;
	}

	@Override
	public String show() {
		
		return "";
	}

	@Override
	public boolean isEmpty() {
		
		return true;
	}

	@Override
	public BST remove(int d) {
		
		return this;
	}

	@Override
	public Integer biggest() {
		
		return null;
	}

	@Override
	public int findCountSum(int targetSum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSum(int d) {
		// TODO Auto-generated method stub
		return 0;
	}





	@Override
	public void bft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findPath(int targetSum, Stack<Integer> path, int paths) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public void dft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getData() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BST getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BST getRight() {
		// TODO Auto-generated method stub
		return null;
	}

}
