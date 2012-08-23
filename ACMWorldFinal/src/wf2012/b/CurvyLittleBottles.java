package wf2012.b;

import static java.lang.Math.PI;

import java.util.HashMap;
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
	
	public static void main(String[] args){
		System.out.println(combination(2, 1));
	}
}
