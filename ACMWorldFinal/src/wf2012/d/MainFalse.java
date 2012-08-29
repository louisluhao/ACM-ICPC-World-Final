package wf2012.d;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * DP
 *  f(n) = f(n-1) + f(n-2) + (nth string)
 */
public class MainFalse {
	static Scanner sc = new Scanner(System.in);
	static int caseNum = 1;

	private static Map<Integer, String> fstring = null;
	private static int FSTRINGMAX;
	private static Map<Integer, BigDecimal> fnum = null;
	private static int FNUMMAX;

	int n;
	String p;

	public MainFalse(int n, String p) {
		if (fstring == null) {
			init();
		}

		this.n = n;
		this.p = p;

		boolean isFind = false;
		for (int i = 0; i <= FSTRINGMAX && i <= n; i++) {
			if (fstring.get(i).contains(p)) {
				isFind = true;
				computeAndPrint(i);
				break;
			}
		}
		if (!isFind) {
			System.out.println(0);
		}
	}

	private void computeAndPrint(int occurNum) {
		System.out.println(fnum.get(n - occurNum));
	}

	private void init() {
		// init fstring
		fstring = new HashMap<Integer, String>();
		fstring.put(0, "0");
		fstring.put(1, "1");
		int n = 2;

		while (true) {
			String newString = fstring.get(n - 1) + fstring.get(n - 2);
			if (n > 100)
				break;
			fstring.put(n, newString);
			System.out.println(n);
			n++;
		}
		FSTRINGMAX = n - 1;

		// init fnum
		long start = System.nanoTime();

		fnum = new HashMap<Integer, BigDecimal>();
		fnum.put(0, new BigDecimal(0));
		fnum.put(1, new BigDecimal(1));
		n = 2;
		BigDecimal finalNum = (new BigDecimal(2)).pow(63);
		while (true) {
			BigDecimal newNum = fnum.get(n - 1).add(fnum.get(n - 2));
			if (newNum.compareTo(finalNum) > 0)
				break;
			fnum.put(n, newNum);
			n++;
		}
		FNUMMAX = n - 1;
	}

	static class FString{
		public String firstM;
		String lastM;

		public FString(String firstM, String lastM){
			this.firstM = firstM;
			this.lastM = lastM;
		}
	}

	public static void main(String[] args) {
		try {
			while (sc.hasNextInt()) {
				MainFalse currentCase = new MainFalse(sc.nextInt(), sc.next());
				caseNum++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}