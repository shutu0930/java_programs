
public class BinaryTreeTable implements StrTable {

	private class BinaryTreeTableNode {
		String key, value;
		BinaryTreeTableNode left, right;
		
		public BinaryTreeTableNode(String id, String name) {
			key = id;
			value = name;
			left = null;
			right = null;
		}

		public String lookup(String id) {
			if (id.equals(key)) return value;
			else if (id.compareTo(key) < 0) return (left == null ? null : left.lookup(id));
			else return (right == null ? null : right.lookup(id));
		}

		public void insert(String id, String name) {
			if (id.equals(key)) {
				value = name;
			} else if (id.compareTo(key) < 0) {
				if (left == null)  left =   new  BinaryTreeTableNode(id,name); else left.insert(id,name);
			} else  {
				if (right == null) right =  new BinaryTreeTableNode(id,name); else right.insert(id,name);
			}
		}
	}
	
	BinaryTreeTableNode root;
	
    public BinaryTreeTable() {
	   root = null;
    }
	
	@Override
	public String lookup(String id) {
		if (root != null) return root.lookup(id);
		return null;
	}

	@Override
	public void insert(String id, String name) {
		if (root == null) {
			root = new  BinaryTreeTableNode(id,name);
		} else {
			root.insert(id, name);
		}
	}
}
