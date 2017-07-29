package myPackage;

public class LitExp extends Variable {
	int value;
	public LitExp(int v){
		value=v;
	}
//	public static Expression litt(int v){
//	
//	return new LitExp(v);
//	}
	@Override
	public String show() {
		
		return value+"";
	}
	@Override
	public int evaluate(Subs subs) {
		
		return value;
	}
	@Override
	public String simplify(Subs subs) {
		
		return value+"";
	}
	
}
