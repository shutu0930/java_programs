import java.util.LinkedList;
import java.util.Stack;

public class NonEmptyBST extends BST {

	public BST left,right;
	int data;
	

	int sum=0;
	NonEmptyBST(int d){
		this.data=d;
		left=new EmptyBST();
		right=new EmptyBST();
	}
	NonEmptyBST(int d, BST l,BST r){
		this.data=d;
		left=l;
		right=r;
	}
	
	@Override
	public BST insert(int d) {
		
		if(d==data){
			return this;
		}else if(d<data){//left
			
			return new NonEmptyBST(data,left.insert(d),right);
			
		}else{//right
			
			return new NonEmptyBST(data,left, right.insert(d));
			
		}
	}

	@Override
	public int size() {
		
		return left.size()+1+right.size();
	}

	@Override
	public int height() {
		int max=0;
		max=Math.max(left.height()+1, right.height()+1);
		return max;
	}

	@Override
	public String show() {
		
		return left.show()+" "+this.data+" "+right.show();
	}

	@Override
	public boolean isEmpty() {
		
		return false;
	}

	@Override
	public BST remove(int d) {
		if(d==data){
			if(this.left.isEmpty()) return right;
			else if(this.right.isEmpty()) return left;
			else{
				int lb=left.biggest();
				return new NonEmptyBST(lb,left.remove(lb),right);
			}
			
		}else if(d<data){//left
			return new NonEmptyBST(data, left.remove(d),right);
			
		}else{
			return new NonEmptyBST(data, left,right.remove(d));
			
		}
		
	}
	@Override
	public Integer biggest() {
		if(!this.right.isEmpty()){
			return right.biggest();
		}else{
			return this.data;
		}

	}
	
	public int getSum(int d){
		if(d==data){
			return data+left.getSum(d)+right.getSum(d);
		}else if(d < data){
			return left.getSum(d);
			
		}else{
			return right.getSum(d);
		}
	}
	@Override
	public int findCountSum(int targetSum) {
//		if(targetSum==data){
//			paths++;
//			left.findPath(targetSum);
//		}else if(targetSum<data){
//			return left.findCountSum(targetSum);
//		}else{
//			
//			
//		}
//		return paths;
		Stack<Integer> path=new Stack<Integer>();
		int paths=0;
		//path.push(0);
		//if(this.isEmpty()) return 0;  
        //Stack<Integer> stack = new Stack<Integer>();  
		paths=findPath(targetSum,path,paths);  
		
        return paths;
	}
	@Override
	public int findPath(int targetSum, Stack<Integer> path,int paths) {
		if(!this.isEmpty()){
			sum+=this.data;
			path.add(this.data);

			
			if(sum<targetSum){
				paths+=left.findPath(targetSum-sum,path,paths);
				paths+=right.findPath(targetSum-sum,path,paths);
				//System.out.println("hahhah");
				//path.pop();
			}
			else if(sum==targetSum){
				//for(Integer i:path)
				//if(left.isEmpty() && right.isEmpty())
				paths++;
				for(int i :path)  
                    System.out.print(i+","); 
				System.out.println(); 
				
				
				
			}else{
				paths+=left.findPath(targetSum-sum,path,paths);
			}
			path.pop();
			
		}
		return paths;
		
		
		
	}
	@Override
	public void dft() {
		System.out.println(this.data);
		left.bft();
		right.bft();
		
//		for(int i :path)  
//            System.out.print(i+","); 
	}
	@Override
	public void bft() {
		LinkedList<BST> queue = new LinkedList<BST>();
		queue.add(this);
		while(queue.size()>0){
			BST temp= queue.removeFirst();
			System.out.println(temp.getData());
			if(!temp.getLeft().isEmpty()){
				queue.add(temp.getLeft());
			}
			if(!temp.getRight().isEmpty()){
			queue.add(temp.getRight());
			}
		}
		
	}
	@Override
	public int getData() {
		
		return this.data;
	}
	@Override
	public BST getLeft() {
		
		return this.left;
	}
	@Override
	public BST getRight() {
		
		return this.right;
	}

}
