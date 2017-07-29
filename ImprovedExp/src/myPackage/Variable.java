package myPackage;

public class Variable extends Expression {

	//int value;
	//String name;
//	public Variable(String n){
//		
//		name=n;
//	}
//	public Variable(int v){
//		
//		value=v;
//	}
	@Override
	public String show() {
		
		return null;
	}

	@Override
	public int evaluate(Subs subs) {
	
		return 0;
	}

	@Override
	public String simplify(Subs subs) {
		
		return null;
	}

	@Override
	public int size() {
		int sum=1;		
		return sum;
	}

	@Override
	public int height() {
		int max=0;
		return max;
	}

	@Override
	public int operators() {
		int count=0;
		return count;
	}
//	public static Expression litt(int v){
//		
//
//		return new Variable(v);
//	}
//	public static Expression varr(String n){
//		
//		return new Variable(n);
//		
//	}

}
