package wf2012.b;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurvyLittleBottles {
	
	public static void main(String[] args){
		Polynominal case1 = new Polynominal();
		case1.add(new PolyTerm(4, 0));
		case1.add(new PolyTerm(-0.25, 1));
		Polynominal circleArea = new Polynominal();
		circleArea = Polynominal.multiple(case1, case1);
		circleArea.integral();
		System.out.println(circleArea.getValue(0.515)*PI);
		
		Polynominal case2 = new Polynominal();
		case2.add(new PolyTerm(1.7841241161782, 0));
		Polynominal circleArea2 = new Polynominal();
		circleArea2 = Polynominal.multiple(case2, case2);
		circleArea2.integral();
		System.out.println(circleArea2.getValue(10)*PI - circleArea2.getValue(-5)*PI);		
	}
	
	
	
	
	//polynominal and polyterm


public static class Polynominal {
	
	Map<Double, PolyTerm> polyMap = new HashMap<Double, PolyTerm>();
	
	
	public void add(PolyTerm p){
		if(polyMap.containsKey(p.exponent)){
			polyMap.put(p.exponent, polyMap.get(p.exponent).add(p));
		}
		else{
			polyMap.put(p.exponent, p);
		}
	}
	
	public PolyTerm getPolyTermByExponent(double exp){
		return polyMap.get(exp);
	}
	
	public Collection<PolyTerm> getPolyTerms(){
		return polyMap.values();
	}
	
	public double getValue(double x){
		double ans = 0;
		for(PolyTerm term : polyMap.values()){
			ans += term.getValue(x);
		}
		return ans;
	}
	
//	public Polynominal toPower(int power){
//		Polynominal rePoly = new Polynominal();
//	}
	
	public static Polynominal multiple(Polynominal a, Polynominal b){
		Polynominal repoly = new Polynominal();
		for(PolyTerm aterm : a.getPolyTerms())
			for(PolyTerm bterm : b.getPolyTerms()){
				repoly.add(aterm.multiple(bterm));
			}
		return repoly;
	}
	
	public void integral(){
		Map<Double, PolyTerm> old = new HashMap<Double, PolyTerm>(polyMap);
		polyMap.clear();
		for(PolyTerm poly : old.values()){
			this.add(poly.Integral());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((polyMap == null) ? 0 : polyMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynominal other = (Polynominal) obj;
		if (polyMap == null) {
			if (other.polyMap != null)
				return false;
		} else if (!polyMap.equals(other.polyMap))
			return false;
		return true;
	}
	
	
}
	
public static class PolyTerm {
	double coefficient;
	double exponent;

	public PolyTerm(double coefficient, double exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	public PolyTerm add(PolyTerm other) {
		if (other.exponent != this.exponent) {
			return null;
		} else {
			return new PolyTerm(this.coefficient + other.coefficient,
					this.exponent);
		}
	}

	public PolyTerm sub(PolyTerm other) {
		if (other.exponent != this.exponent) {
			return null;
		} else {
			return new PolyTerm(this.coefficient - other.coefficient,
					this.exponent);
		}
	}

	public PolyTerm multiple(PolyTerm other) {
		return new PolyTerm(this.coefficient * other.coefficient, this.exponent
				+ other.exponent);
	}

	public double getValue(double x) {
		return this.coefficient * pow(x, exponent);
	}

	public static boolean isSameExponent(PolyTerm a, PolyTerm b) {
		return a.exponent == b.exponent;
	}

	public PolyTerm Integral() {
		return new PolyTerm(this.coefficient / (this.exponent + 1),
				exponent + 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(exponent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PolyTerm other = (PolyTerm) obj;
		if (Double.doubleToLongBits(coefficient) != Double
				.doubleToLongBits(other.coefficient))
			return false;
		if (Double.doubleToLongBits(exponent) != Double
				.doubleToLongBits(other.exponent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PolyTerm [coefficient=" + coefficient + ", exponent="
				+ exponent + "]";
	}

}
}
