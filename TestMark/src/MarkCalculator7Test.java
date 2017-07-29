import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class MarkCalculator7Test {

	MarkCalculator mc = new MarkCalculator2();
	
	@Test
	public void test() {
		try {
			assertTrue(mc.calculateMark(10, 0, 0, 0, true).equals(new MarkGrade(0,Grade.N)));
		} catch (ComponentOutOfRangeException e) {
			fail("they sat the exam");
		}
	}
}
