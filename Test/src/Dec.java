import java.util.ArrayList;
import java.util.HashMap;


/**
 * Dec - decrement expression
 * 
 * @author Eric McCreath - GPLv2
 */

public class Dec extends Expression {
    Expression exp;
	
    public Dec(Expression exp) {
		this.exp = exp;
	}
	@Override
	public int evaluate(HashMap<String, Integer> vars, Functions functions) {
		int v = exp.evaluate(vars,functions);
		return (v==0?v:v - 1);
	}

	@Override
	public String show() {
		return "dec(" + exp.show() + ")";
	}

}
