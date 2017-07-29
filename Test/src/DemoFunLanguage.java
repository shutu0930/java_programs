import java.util.HashMap;

/**
 * DemoGunLanguage - this just tests the simple language out.
 * 
 * @author Eric McCreath - GPLv2
 */



public class DemoFunLanguage {

	final static String prog1 = "fun(X)=0";
	final static String exp1 = "fun(    inc(33.0 ) ) ";

	final static String prog2 = "add(X,Y)=(X==0?Y:add(dec(X),inc(Y)))";
	final static String exp2 = "add(inc(0),inc(inc(0)))";

	final static String prog3 = "add(X,Y)=(X==0?Y:add(dec(X),inc(Y)))\n"
			+ "mult(X,Y)=(X==0?0:add(Y, mult(dec(X),Y)))\n";
	final static String exp3 = "mult(inc(inc(inc(0))),inc(inc(0)))";

	public static void main(String[] args) {
		Tokenizer tz = new MySimpleTokenizer(exp1);
		Object tok;
		while (tz.hasNext()) {
			tok = tz.current();
			System.out.println(tok instanceof String ? tok : 
				(tok instanceof Integer?"int:" + tok: "double:" + tok));
			tz.next();
		}

		try {
			Functions fun = Functions.parse(new MySimpleTokenizer(prog3));
			System.out.println(fun.show());
			Expression e = Expression.parse(new MySimpleTokenizer(exp3));
			System.out.println(e.evaluate(new HashMap<String, Integer>(), fun));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
