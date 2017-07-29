package myPackage;

import java.util.HashMap;


public abstract class Expression {
	
	abstract public String show();
	abstract public int evaluate(Subs subs);
	abstract public String simplify(Subs subs);
	abstract public int size();
	abstract public int height();
	abstract public int operators();
	
	
	static public boolean isoccur(int flag){
		if(flag==1)return true;
		else return false;
	}
	/*	
	 * <exp> ::= <term>| <term> + <exp>|<term> - <exp> 
	 * <term> ::= <factor>| <factor> * <term>| <factor> / <term> 
	 * <factor> ::= <lit> | <var> | "(" <exp> ")"
	 * <lit> ::= <digit> | <nonZero><lit>
	 * <digit> ::= "0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"
	 * <nonZero> ::= "1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"
	 * <var> ::= "x"
	 */
	
	static public Expression parseExp(Tokenizer tok) {
		Expression term=parseTerm(tok);
		boolean flag=tok.hasAdd();
		//Expression term=parseExp(tok);
		if(tok.currentis("+")){
			tok.next();
			Expression exp=parseExp(tok);
			return new Sum(term, exp);
			
		}else if(tok.currentis("-")){
			
			if(flag==false){
				tok.next();
				Expression exp=parseExp(tok);
				return new Minus(term, exp);
				
			}else{
				tok.next();
				Expression exp=parseExp(tok);
				Expression exp2=times(litt(-1),exp);
				return new Sum(term, exp2);
				
			}
			
		}
		else{
			return term;
		}
		
	}
	private static Expression parseTerm(Tokenizer tok){
		Expression fact=parseFactor(tok);
		if(tok.currentis("*")){
			tok.next();
			Expression term =parseTerm(tok);
			//System.out.println(term.show());
			return new Times(fact,term);
			
		}else if(tok.currentis("/")){
			tok.next();
			Expression term =parseTerm(tok);
			//System.out.println(term.show());
			return new Divide(fact,term);
		}
		else{
			return fact;
		}
		
	}
	
	private static Expression parseFactor(Tokenizer tok){
		
		if(tok.current() instanceof Integer){
			Expression exp= new LitExp((Integer)tok.current());
			tok.next();
			return exp;
			
		}else if(tok.currentis("(")){
			tok.next();
			Expression exp=parseExp(tok);
			tok.parse(")");
			//System.out.println(exp.show());
			return exp;	
		}
		else{
			Expression exp=new VarExp((String)tok.current());
			tok.next();
			return exp;
		}
		
	}

//	public static Functions parseFunctions(Tokenizer tok) {
//		
//		return null;
//	}
	
	
	
	public static Expression litt(int v){
		
		return new LitExp(v);
	}
	public static Expression sum(Expression l, Expression r){
		
		return new Sum(l,r);
		
	}

	public static Expression times(Expression l, Expression r){
		
		return new Times(l,r);
		
	}
	public static Expression minus(Expression l, Expression r){
		
		return new Minus(l,r);
		
	}
	public static Expression divide(Expression l, Expression r){
		
		return new Divide(l,r);
		
	}

	public static Expression var(String n){
		
		return new VarExp(n);
	}
    //public static Expression evaluate(HashMap<String, Integer> map,String variable){ return new Evaluate(map,variable);}
	
	
	

}
