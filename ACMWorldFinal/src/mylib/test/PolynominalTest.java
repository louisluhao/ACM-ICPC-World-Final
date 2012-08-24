package mylib.test;

import static org.junit.Assert.*;

import mylib.PolyTerm;
import mylib.Polynominal;

import org.junit.Test;

public class PolynominalTest {
	
	@Test
	public void testAdd() {
		Polynominal poly = new Polynominal();
		poly.add(new PolyTerm(2, 1));
		poly.add(new PolyTerm(1, 1));
		PolyTerm ans = new PolyTerm(3, 1);
		assertEquals(ans, poly.getPolyTermByExponent(1));

	}

	@Test
	public void testGetValue() {
		Polynominal poly = new Polynominal();
		poly.add(new PolyTerm(2, 1));
		poly.add(new PolyTerm(3, 0));
		poly.add(new PolyTerm(1, 1));
		
		assertEquals(Double.doubleToLongBits(6),
				Double.doubleToLongBits(poly.getValue(1)));
		assertEquals(Double.doubleToLongBits(9),
				Double.doubleToLongBits(poly.getValue(2)));
	}
	
	@Test
	public void testIntegral() {
		Polynominal poly = new Polynominal();
		poly.add(new PolyTerm(3, 1));
		poly.add(new PolyTerm(3, 0));
		
		Polynominal ansPoly = new Polynominal();
		ansPoly.add(new PolyTerm(3, 1));
		ansPoly.add(new PolyTerm(1.5, 2));
	}
	
	@Test
	public void testMultiple() {
		Polynominal poly = new Polynominal();
		poly.add(new PolyTerm(1, 1));
		poly.add(new PolyTerm(-1, 0));
		
		Polynominal ansPoly = new Polynominal();
		ansPoly.add(new PolyTerm(1, 2));
		ansPoly.add(new PolyTerm(-2, 1));
		ansPoly.add(new PolyTerm(1, 0));
		
		assertEquals(ansPoly, Polynominal.multiple(poly, poly));
	}

}
