/* Adder demo - Eric McCreath 2015 - GPL 
 * This class implements an integer adder using a recursive approach which is derived
 * from the "definition" of addition. 
 */



public class Adder2 implements IntAdder {

	@Override
	public int add(int a, int b) {
		if (a == 0) return b;
		else return add(a-1,b+1);
	}

}
