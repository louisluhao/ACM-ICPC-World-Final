package wf2012.d;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int caseNum = 1;

	private static Map<Integer, String> fstring = null;
	private static int FSTRINGMAX;
	private static Map<Integer, BigDecimal> fnum = null;
	private static int FNUMMAX;

	int n;
	String p;

	public Main(int n, String p) {
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
			if (newString.length() > 100000)
				break;
			fstring.put(n, newString);
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

	public static void main(String[] args) {
		try {
			while (sc.hasNextInt()) {
				Main currentCase = new Main(sc.nextInt(), sc.next());
				caseNum++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
