/* Adder demo - Eric McCreath 2015 - GPL  */

public class AdderTest {

	public static void main(String[] args) {
		IntAdder adder1 = new Adder1();
		IntAdder adder2 = new Adder2();
		IntAdder adder3 = new Adder3();
		IntAdder adder4 = new Adder4();
		System.out.println("4 + 7 = " + adder1.add(4,7));
		System.out.println("4 + 7 = " + adder2.add(4,7));
		System.out.println("4 + 7 = " + adder3.add(4,7));
		System.out.println("4 + 7 = " + adder4.add(4,7));
	}
}
