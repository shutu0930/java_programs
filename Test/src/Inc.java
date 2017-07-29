import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Eric McCreath - GPLv2
 */


public class Inc extends Expression {
    Expression exp;
	
    public Inc(Expression exp) {
		this.exp = exp;
	}
    
	@Override
	public int evaluate(HashMap<String, Integer> vars, Functions functions) {
		return 1 + exp.evaluate(vars,functions);
	}

	@Override
	public String show() {
		return "inc(" + exp.show() + ")";
	}
}
