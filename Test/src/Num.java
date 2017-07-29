import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Eric McCreath - GPLv2
 */

public class Num extends Expression {

    int value;
	
	public Num(int v) {
		value = v;
	}
	
	@Override
	public int evaluate(HashMap<String, Integer> vars, Functions functions) {
		return value;
	}

	@Override
	public String show() {
		return "" + value;
	}
	
	

}
