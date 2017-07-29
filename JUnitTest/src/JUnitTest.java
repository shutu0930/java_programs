import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class JUnitTest {

	static int identity(int x) {
		if (20 <= x && x <= 30) {//20<x<30
			x /= 2;
		}
		if (5 <= x && x <= 15) {//5<x<15
			x *= 2;
		}
		return x;
	}
	
	@Test
	public void testIdentity() {
	//branch coverage
	 assertEquals(25, identity(25));
	 assertEquals(35, identity(35));
	 
	 assertEquals(10, identity(10));
	 assertEquals(18, identity(18));
	
	 //path coverage
	
	 assertEquals(20, identity(20));//TF
	 assertEquals(10, identity(10));//TT
	 
	 
	 assertEquals(80, identity(80));
	 assertEquals(5, identity(5));
	 
	
	 
	}

	
	
}
