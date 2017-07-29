package myPackage;

public class Sum extends Operator {

	public Sum(Expression l, Expression r) {
		super(l, r);
		
	}
//	public static Expression sum(Expression l, Expression r){
//		
//		return new Sum(l,r);
//		
//	}
	
	@Override
	public String show() {
		
		return "("+ left.show()+"+"+right.show()+")";
	}
	@Override
	public int evaluate(Subs subs) {
		
		
		return left.evaluate(subs)+right.evaluate(subs);
	}
	@Override
	public String simplify(Subs subs) {
		
		
		String resl="";
		String resr="";
		String res="";
		if(!left.simplify(subs).contains("x")){
			
			resl=left.evaluate(subs)+"";
			
			
		}else{
			resl=left.simplify(subs);
			
		}
		if(!right.simplify(subs).contains("x")){
			
			resr=right.evaluate(subs)+"";
			
			
		}else{
			resr=right.simplify(subs);
			
		}
		
		if(!resl.contains("x")&&!resr.contains("x")){
			
			res=left.evaluate(subs)+right.evaluate(subs)+"";
		}else{
			
			res="("+resl+"+"+resr+")";
		}
		
		
		return res;
		
		
		
		}

}
