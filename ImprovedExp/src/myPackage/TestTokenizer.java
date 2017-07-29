package myPackage;

import java.util.HashMap;

public class TestTokenizer {
	
	final static String prog1 = "fun(X)=0";
	final static String exp1 = "2.4 +   ( 2 -0)";

	final static String prog2 = "add(X,Y)=(X==0?Y:add(dec(X),inc(Y)))";
	final static String exp2 = "add(inc(0),inc(inc(0)))";

	final static String prog3 = "add(X,Y)=(X==0?Y:add(dec(X),inc(Y)))\n"
			+ "mult(X,Y)=(X==0?0:add(Y, mult(dec(X),Y)))\n";
	final static String exp3 = "mult(inc(inc(inc(0))),inc(inc(0)))";
	
	public static void main(String[] args) {
		
		Tokenizer tz = new MyTokenizer(exp1);
		Object tok;
		while (tz.hasNext()) {
			tok = tz.current();
//			System.out.println(tok instanceof String ? tok : 
//				(tok instanceof Integer?"int:" + tok: "double:" + tok));
			System.out.print(tok);
			tz.next();
		}

		
	}

}
