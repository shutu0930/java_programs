import java.util.ArrayList;
import java.util.HashMap;


/**
 * Expressions - This abstract class represent simple expressions.
 * 
 * @author Eric McCreath - GPLv2
 * 
 * Grammar:
 * 
 * <exp> ::= <number> | inc ( <exp> ) | dec ( <exp> ) | ( <exp> == 0 ? <exp> :  <exp> ) | <variable> | <funname> ( <explist> ) | <funname> () 
 * <explist> ::= <exp> | <exp> , <explist> 
 * 
 */


public abstract class Expression { 

	public abstract  int evaluate(HashMap<String,Integer> vars, Functions functions);
	public abstract  String show();
	
	public static Expression parse(Tokenizer tok) throws ParseException {
		Object t = tok.current();
		if (t instanceof Integer) {
			int v = (Integer) t;
			tok.next();
			return new Num(v);
		} else if (t.equals("inc")) {
			tok.next();
			tok.parse("(");
			Expression exp = parse(tok);
			tok.parse(")");
			return new Inc(exp);
		} else if (t.equals("dec")) {
			tok.next();
			tok.parse("(");
			Expression exp = parse(tok);
			tok.parse(")");
			return new Dec(exp);
		} else if (t.equals("(")) {
			tok.next();
			Expression bool = parse(tok);
			tok.parse("=");
			tok.parse("=");
			tok.parse(new Integer(0));
			tok.parse("?");
			Expression exp1 = parse(tok);
			tok.parse(":");
			Expression exp2 = parse(tok);
			tok.parse(")");
			return new Select(bool,exp1,exp2);
		} else if (t instanceof String && Character.isUpperCase(((String) t).charAt(0))) {
			tok.next();
			return new Variable((String) t);
		} else if (t instanceof String) {
			FunctionCall res = new FunctionCall();
			res.expressions = new ArrayList<Expression>();
			res.name = (String) t;
			tok.next();
			tok.parse("(");
			String var;
			while (!")".equals(tok.current())) {
				res.expressions.add(parse(tok));
				if (tok.current().equals(",")) tok.next();
			}
			tok.parse(")");
			return res;
		} else {
			throw new ParseException();
		}
	}
}
