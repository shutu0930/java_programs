
/* Adder demo - Eric McCreath 2015 - GPL 
 * This class implements an integer adder by transforming the numbers 
 * into an exponential space and then using "multiplication", the result of
 * which is transformed back via natural log into an integer. 
 */



public class Adder4 implements IntAdder {

	public int add(int a, int b) {
		double ra = a;
		double rb = b;
		double ea = Math.exp(ra);
		double eb = Math.exp(rb);
		double eres = ea * eb;
		double rres = Math.log(eres);
		return (int) Math.round(rres);
	}

}
