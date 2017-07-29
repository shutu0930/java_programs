package myPackage;

public class AddExpression extends Expression {

	Expression left, right;
	public AddExpression(Expression l, Expression r){
		left=l;
		right=r;
	}
	
	public int size(){
		int sum=1;
		
		sum+=left.size();
		sum+=right.size();	
		return sum;
		
	}
	
	public int height(){
		int max=0;
		max=Math.max(right.height()+1, left.height()+1);
		//max=Math.max(max, right.height()+1);
		return max;
		
		
	}
	
	public int operators(){
		int count=left.operators()+right.operators()+1;
		
		
		return count;
		
		
	}
	
	public static Expression add(Expression l, Expression r){
		
		return new AddExpression(l,r);
		
	}
	
//	public static AddExpression add(Expression l, Expression r){
//		
//		
//		return new AddExpression(l,r);
//	}
	@Override
	public String show() {
		
		return "("+ left.show()+"+"+right.show()+")";
	}

	public int evaluate(Subs subs) {
		
		return left.evaluate(subs)+right.evaluate(subs);
	}
	
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
