import java.security.PublicKey;

/**
 * Created by u5756604 on 6/06/17.
 */
public class EmptyBinaryTree extends BST {
    static private EmptyBinaryTree emptytree = new EmptyBinaryTree();
    public static EmptyBinaryTree constructEmptyBinaryTree(){return emptytree;}
    @Override
    public int size(){
        return 0;
    }
    @Override
    public BST insert(int data){
        return new BinaryTree(data);
    }
    @Override
    public int height(){
        return -1;
    }
    @Override
    public String show(){
        return "";
    }
    @Override
    public boolean isEmpty(){
        return true;
    }
    @Override
    public BST remove(int data){
        return this;
    }
    @Override
    public Integer biggest(){
        return null;
    }
    @Override
    public int findCountSum(int targetSum){
        return 0;
    }
    @Override
    public Integer sum(int pre,int targetSum){
        return 0;
    }
}
