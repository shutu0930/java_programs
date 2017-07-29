package myPackage;

import java.util.HashMap;


public abstract class Expression {
	abstract public String show();
	abstract public int evaluate(Subs subs);
	abstract public String simplify(Subs subs);
	abstract public int size();
	abstract public int height();
	abstract public int operators();
	
	
	

	
	

}
