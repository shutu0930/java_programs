package myPackage;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public abstract class Tokenizer {

	abstract boolean hasNext();

	abstract Object current();

	abstract void next();
	abstract boolean hasAdd();

	public void parse(Object o){
		if (current() == null || !current().equals(o))
			//System.out.println("error!");
			throw new Error(); // normally one would use exceptions in such cases, however, 
        // it just makes code simpler as you don't need to deal with the exception 
        // in calling code
		next();
	}

	public boolean currentis(Object o) {
		return (current() != null && current().equals(o));
			
		
	}
}
