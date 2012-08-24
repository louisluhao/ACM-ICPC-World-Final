package mylib;

import static java.lang.Math.pow;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polynominal {
	
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
