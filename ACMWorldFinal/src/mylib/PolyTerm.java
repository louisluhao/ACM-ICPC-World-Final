package mylib;

import static java.lang.Math.pow;

public class PolyTerm {
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
		return new PolyTerm(this.coefficient * other.coefficient,
				this.exponent + other.exponent);
	}

	public double getValue(double x) {
		return this.coefficient * pow(x, exponent);
	}
	
	public static boolean isSameExponent(PolyTerm a, PolyTerm b){
		return a.exponent == b.exponent;
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