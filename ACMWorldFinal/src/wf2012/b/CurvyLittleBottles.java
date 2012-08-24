package wf2012.b;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurvyLittleBottles {
	static Scanner sc = new Scanner(System.in);
	static int caseNum = 1;

	int n;
	double xlow;
	double xhigh;
	int inc;

	Polynominal inPoly = new Polynominal();
	Polynominal ansPoly;

	double fullVolumn;

	DecimalFormat decimalFormat = new DecimalFormat("0.00");

	public CurvyLittleBottles() {
		init();
		printCaseNum();
		integral();
		printFullVolumn();
		calculateAndPrintMarking();
	}

	private void init() {
		n = sc.nextInt();
		for (int i = 0; i <= n; i++) {
			inPoly.add(new PolyTerm(sc.nextDouble(), i));
		}
		xlow = sc.nextDouble();
		xhigh = sc.nextDouble();
		inc = sc.nextInt();
	}

	private void printCaseNum() {
		System.out.print("Case " + caseNum + ": ");
	}

	private void integral() {
		ansPoly = Polynominal.multiple(inPoly, inPoly);
		ansPoly.integral();
	}

	private void printFullVolumn() {
		this.fullVolumn = getVolumn(xlow, xhigh);
		System.out.println(decimalFormat.format(fullVolumn));
	}

	private double getVolumn(double xlow, double xhigh) {
		if (xlow >= xhigh)
			throw new IllegalArgumentException();
		return PI * ansPoly.getValue(xhigh - xlow);
	}

	private void calculateAndPrintMarking() {
		if (inc > fullVolumn) {
			System.out.println("insufficient volume");
			return;
		}

		// startCalculate;
		int markNum = 1;
		double x = xlow;
		while (inc * markNum <= fullVolumn && markNum <= 8) {
			x = getNextMarking(markNum, x);
			System.out.print(decimalFormat.format(x - xlow));
			if (markNum < 8) {
				System.out.print(" ");
			}
			markNum++;
		}
		System.out.println();
	}

	private double getNextMarking(int markNum, double x) {
		x += 0.001;
		while (getVolumn(xlow, x) < markNum * inc) {
			x += 0.001;
		}
		return x;
	}

	public static void main(String[] args) {
		try{
			while (sc.hasNextInt()) {
				CurvyLittleBottles currentCase = new CurvyLittleBottles();
				caseNum++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	// polynominal and polyterm

	public static class Polynominal {

		Map<Double, PolyTerm> polyMap = new HashMap<Double, PolyTerm>();

		public void add(PolyTerm p) {
			if (polyMap.containsKey(p.exponent)) {
				polyMap.put(p.exponent, polyMap.get(p.exponent).add(p));
			} else {
				polyMap.put(p.exponent, p);
			}
		}

		public PolyTerm getPolyTermByExponent(double exp) {
			return polyMap.get(exp);
		}

		public Collection<PolyTerm> getPolyTerms() {
			return polyMap.values();
		}

		public double getValue(double x) {
			double ans = 0;
			for (PolyTerm term : polyMap.values()) {
				ans += term.getValue(x);
			}
			return ans;
		}

		// public Polynominal toPower(int power){
		// Polynominal rePoly = new Polynominal();
		// }

		public static Polynominal multiple(Polynominal a, Polynominal b) {
			Polynominal repoly = new Polynominal();
			for (PolyTerm aterm : a.getPolyTerms())
				for (PolyTerm bterm : b.getPolyTerms()) {
					repoly.add(aterm.multiple(bterm));
				}
			return repoly;
		}

		public void integral() {
			Map<Double, PolyTerm> old = new HashMap<Double, PolyTerm>(polyMap);
			polyMap.clear();
			for (PolyTerm poly : old.values()) {
				this.add(poly.Integral());
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((polyMap == null) ? 0 : polyMap.hashCode());
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
			return new PolyTerm(this.coefficient * other.coefficient,
					this.exponent + other.exponent);
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
