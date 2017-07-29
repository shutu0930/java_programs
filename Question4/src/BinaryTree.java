import java.util.Map;

/**
 * Created by u5756604 on 6/06/17.
 */
public class BinaryTree extends BST{
    int data;
    BST left, right;
    public BinaryTree(int data){
        this.data = data;
        left = new EmptyBinaryTree();
        right = new EmptyBinaryTree();
    }
    public BinaryTree(int data, BST left, BST right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BST insert(int d){
        if(data == d){
            return this;
        }else if (d < data){
            return new BinaryTree(data,left.insert(d),right);
        }else {
            return new BinaryTree(data,left,right.insert(d));
        }
    }

    public int size(){return 1+left.size() +right.size();}
    @Override
    public int height(){return 1+Math.max(left.height(),right.height());}
    @Override
    public String show(){
        if(left.isEmpty() && right.isEmpty()) return data + "";
        else return data + "(" + left.show() + "," + right.show() +")";
    }
    @Override
    public BST remove(int d) {
        if (data == d) {
            if (left.isEmpty()) return right;
            else if (right.isEmpty()) return left;
            else {
                int b = left.biggest();
                return new BinaryTree(b, left.remove(b), right);
            }
        } else if (d < data) {
            return new BinaryTree(data, left.remove(d), right);
        } else {
            return new BinaryTree(data, left, right.remove(d));
        }
    }
    @Override
    public Integer biggest(){
        if (right.isEmpty()) return data;
        else return right.biggest();
    }
    @Override
    public boolean isEmpty(){
        return false;
    }
    @Override
    public int findCountSum(int targetSum){
        return sum(0, targetSum)+left.sum(0,targetSum)+right.sum(0,targetSum);
    }
    @Override
    public Integer sum(int pre, int targetSum){
        if(left.isEmpty() && right.isEmpty() && (pre + data) == targetSum) return 1;
        else return left.sum(pre + data, targetSum) + right.sum(pre + data, targetSum);
    }
}
