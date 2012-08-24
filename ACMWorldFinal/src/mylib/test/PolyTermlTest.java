package mylib.test;

import static org.junit.Assert.*;

import mylib.PolyTerm;

import org.junit.Before;
import org.junit.Test;

public class PolyTermlTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPolyTermGetValue() {
		PolyTerm test = new PolyTerm(2, 2);
		assertEquals(Double.doubleToLongBits(8),
				Double.doubleToLongBits(test.getValue(2)));
		assertEquals(Double.doubleToLongBits(2),
				Double.doubleToLongBits(test.getValue(1)));
		PolyTerm test2 = new PolyTerm(3, 0);
		assertEquals(Double.doubleToLongBits(3),
				Double.doubleToLongBits(test2.getValue(1)));
		assertEquals(Double.doubleToLongBits(3),
				Double.doubleToLongBits(test2.getValue(100)));
	}

	@Test
	public void testPolyTermMultiple() {
		PolyTerm a = new PolyTerm(2, 3);
		PolyTerm b = new PolyTerm(3, 4);
		PolyTerm ans = new PolyTerm(6, 7);
		assertEquals(ans, a.multiple(b));
		assertEquals(ans, b.multiple(a));
	}

	@Test
	public void testPolyTermAdd() {
		PolyTerm a = new PolyTerm(2, 3);
		PolyTerm b = new PolyTerm(3, 3);
		PolyTerm ans = new PolyTerm(5, 3);
		assertEquals(ans, a.add(b));
		assertEquals(ans, b.add(a));

		a = new PolyTerm(2, 3);
		b = new PolyTerm(3, 4);
		assertEquals(null, a.add(b));
		assertEquals(null, b.add(a));
	}
	
	@Test
	public void testPolyTermSub() {
		PolyTerm a = new PolyTerm(2, 3);
		PolyTerm b = new PolyTerm(3, 3);
		PolyTerm ans1 = new PolyTerm(-1, 3);
		PolyTerm ans2 = new PolyTerm(1, 3);
		assertEquals(ans1, a.sub(b));
		assertEquals(ans2, b.sub(a));

		a = new PolyTerm(2, 3);
		b = new PolyTerm(3, 4);
		assertEquals(null, a.sub(b));
		assertEquals(null, b.sub(a));
	}
	
	@Test
	public void testPolyIntegral() {
		PolyTerm a = new PolyTerm(2, 1);
		PolyTerm ans = new PolyTerm(1, 2);
		assertEquals(ans, a.Integral());
	}
	
	

}
