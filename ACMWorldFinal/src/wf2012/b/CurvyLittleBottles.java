package wf2012.b;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurvyLittleBottles {
	
	private static Map<Integer, Double> factorialChache = new HashMap<Integer, Double>(); 
	
	public static double combination(int n, int m){
		if(m == 0) return 1;
		return factorial(n) / factorial(m);
	}
	
	private static double factorial (int x){
		if(factorialChache.get(x) != null){
			return factorialChache.get(x);
		}
		double result = 1;
		for(int i = 2; i <= x ; i++){
			result = result * i;
		}
		factorialChache.put(x, result);
		return result;
	}
	
	private static List<Double> getEmptyCoefficients(int power){
		List<Double> coefficients = new ArrayList<Double>();
		for(int i = 0; i < power; i++){
			coefficients.add(0.0);
		}
		return coefficients;
	}
	
	
	
	public static void main(String[] args){
		
	}
}
