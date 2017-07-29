package myPackage;

public class LitExpression extends Expression {

	int value;
	public LitExpression(int v){
		
		value=v;
	}
//	public static LitExpression lit(int v){
//		
//		
//		return new LitExpression(v);
//	}

	public int size(){
		int sum=1;		
		return sum;
		
	}
	
	public int height(){
		int max=0;
		
		return max;
		
		
	}
	
	public int operators(){
		int count=0;
		
		return count;
		
		
	}
	
	public String simplify(Subs subs) {
	
		return value+"";
	}
	public static Expression lit(int v){
	

		return new LitExpression(v);
	}
	
	
	@Override
	public String show() {
		
		return ""+value;
	}

	@Override
	public int evaluate(Subs subs) {
		
		return value;
	}

}
