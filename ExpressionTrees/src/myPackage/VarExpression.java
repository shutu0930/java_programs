package myPackage;

public class VarExpression extends Expression {

	String name;
	public VarExpression(String n){
		
		name=n;
	}
	
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

	public static Expression var(String n){
		
		return new VarExpression(n);
		
	}
	
	@Override
	public String show() {
		
		return name;
	}
	
	public String simplify(Subs subs) {
		
		return name;
	}


	public int evaluate(Subs subs) {
		
		return subs.get(name);
	}

}
