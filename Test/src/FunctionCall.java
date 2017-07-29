import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Eric McCreath - GPLv2
 */


public class FunctionCall extends Expression {

	String name;
	ArrayList<Expression> expressions;
	
	@Override
	public int evaluate(HashMap<String, Integer> vars, Functions functions) {
		Function fun = functions.find(name);
		HashMap<String,Integer> callmap = new HashMap<String,Integer>();
		for (int i=0;i<fun.variables.size();i++) {
			callmap.put(fun.variables.get(i), expressions.get(i).evaluate(vars, functions));
		}
		return fun.exp.evaluate(callmap,functions);
	}

	@Override
	public String show() {
		String res = "";
		for(int i=0;i<expressions.size();i++) {
			res += expressions.get(i).show() + (i<expressions.size()-1?",":"");
		}
		return name + "(" + res + ")";
	}

}
