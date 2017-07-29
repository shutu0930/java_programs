import java.util.ArrayList;

/**
 * 
 * @author Eric McCreath - GPLv2
 */



public class Functions extends ArrayList<Function> {
	
	public Function find(String name) {
		for(Function f : this) {
			if (f.name.equals(name)) return f;
		}
		return null;
	}

	static Functions parse(Tokenizer tok) throws ParseException {
		Functions res = new Functions();
		while (tok.hasNext()) {
			res.add(Function.parse(tok));
		}
		return res;
	}
	
	String show() {
	   String res = "";
	   for (Function f : this) {
		   res += f.show();
	   }
	   return res;
	}
	
}
