/* Adder demo - Eric McCreath 2015 - GPL 
 * This class implements an integer adder using 32 single bit full adders.  
 */



public class Adder3 implements IntAdder {

	
	boolean fulladdervalue(boolean a, boolean b, boolean carry) {
		return (a && !b && !carry) ||  (!a && b && !carry) || (a && b && carry) || (!a && !b && carry);
	}
	
	boolean fulladdercarry(boolean a, boolean b, boolean carry) {
		return (a && b) ||  (a && carry) || (b && carry);
	}
	
	public int add(int a, int b) {
		int res = 0;
		boolean carry = false;
		boolean av, bv, rv, rcarry;
		for (int i=0;i<32;i++) {
			av = ((a>>i) & 1) == 1;
			bv = ((b>>i) & 1) == 1;
			rv = fulladdervalue(av,bv,carry);
			rcarry = fulladdercarry(av,bv,carry);
			if (rv) res |= 1 << i;
			carry = rcarry;
		}
		return res;
	}

}
