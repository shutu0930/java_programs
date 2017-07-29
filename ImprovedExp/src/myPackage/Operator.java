package myPackage;

public class Operator extends Expression {

	Expression left, right;
	public Operator(Expression l, Expression r){
		left=l;
		right=r;
	}
	@Override
	public String show() {
			
		return "";
	}
	@Override
	public int evaluate(Subs subs) {
		
		return 0;
	}
	@Override	
	public String simplify(Subs subs) {

			return "";
		}
	
	@Override
	public int size() {
		int sum=1;
		
		sum+=left.size();
		sum+=right.size();	
		return sum;
	}

	@Override
	public int height() {
		int max=0;
		max=Math.max(right.height()+1, left.height()+1);
		
		return max;
	}

	@Override
	public int operators() {
		int count=left.operators()+right.operators()+1;
		return count;
	}

}
