package myPackage;

public class MyTokenizer extends Tokenizer {

	private String text;
	private int pos;
	private Object current;
	
	public MyTokenizer(String text) {
		this.text = text;
		pos = 0;
		next();
	}
	
	static final char whitespace[] = { ' ', '\n', '\t' };
	static final char symbol[] = { '(', ')', '=', '*', '+', '-', '/'};
	
	@Override
	boolean hasNext() {
		return current != null;
	}

	@Override
	Object current() {
		return current;
	}

	@Override
	void next() {
		consumewhite();
		if (pos == text.length()) {
			current = null;

		} else if (isin(text.charAt(pos), symbol)) {
			current = "" + text.charAt(pos);
			pos++;

		} else if (Character.isDigit(text.charAt(pos))) {
			int start = pos;
			while (pos < text.length() && Character.isDigit(text.charAt(pos)) )
				pos++;
			if (pos+1 < text.length() && text.charAt(pos) == '.' &&
					Character.isDigit(text.charAt(pos+1))) {
				pos++;
				while (pos < text.length() && Character.isDigit(text.charAt(pos)))
					pos++;
				current = Double.parseDouble(text.substring(start, pos));
			} else {
			    current = Integer.parseInt(text.substring(start, pos));
			}

		} else {
			int start = pos;
			while (pos < text.length() && !isin(text.charAt(pos), symbol)
					&& !isin(text.charAt(pos), whitespace))
				pos++;
			current = text.substring(start, pos);
		}

	}
	
	private void consumewhite() {
		while (pos < text.length() && isin(text.charAt(pos), whitespace))
			pos++;
	}

	private boolean isin(char c, char charlist[]) {
		for (char w : charlist) {
			if (w == c)
				return true;
		}
		return false;
	}
	
	public boolean hasAdd(){
		
		for(int i=0;i<text.length();i++){
			if(text.charAt(i)=='+'){
				return true;
			}
		}
		return false;
		
	}

}
