package myPackage;

public class VarExp extends Variable {
	String name;
	public VarExp(String n){
		name=n;
	}
//	public static Expression var(String n){
//	
//	return new VarExp(n);
//	}
	@Override
	public String show() {
		
		return name;
	}
	@Override
	public int evaluate(Subs subs) {
		
		return subs.get(name);
	}
	@Override
	public String simplify(Subs subs) {
		
		return name;
	}
	
}
