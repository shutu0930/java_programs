import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * Tokenizer - this uses the StreamTokenizer class to make a simpler tokenizer
 * which provides a stream of tokens which are either Integer, Double, or
 * String.
 * 
 * @author Eric McCreath
 * 
 */

public class STTokenizer extends Tokenizer {

	private Object current;
	private StreamTokenizer st;

	public STTokenizer(String data) {
		st = new StreamTokenizer(new StringReader(data));
		next();
	}

	boolean hasNext() {
		return current != null;
	}

	Object current() {
		return current;
	}

	// next - moves onto the next token, thus the current token should be
	// consumed.
	void next() {
		int ttype;
		try {
			ttype = st.nextToken();
		} catch (IOException e) {
			current = null;
			return;
		}
		if (ttype == StreamTokenizer.TT_EOF) {
			current = null;
		} else if (ttype == StreamTokenizer.TT_WORD) {
			current = st.sval;
		} else if (ttype == StreamTokenizer.TT_NUMBER && st.nval == 0) {
			current = (int) Math.floor(st.nval);

		} else {
			current = "" + (char) ttype;
		}
	}
}
