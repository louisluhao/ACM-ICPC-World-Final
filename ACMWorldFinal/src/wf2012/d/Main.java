package wf2012.d;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author (Louis)Hao Lu
 * 
 */
public class Main {
	static Scanner sc = new Scanner(System.in);
	static long caseNum = 1;

	static Map<Integer, String> pCache = null;
	static int pCacheSize;

	String firstP;
	String lastPodd;
	String lastPeven;

	int n;
	String p;
	BigDecimal f[] = new BigDecimal[101];

	BigDecimal evenNumber;
	BigDecimal oddNumber;

	public Main(int n, String p) {
		if (pCache == null)
			init();
		for (int i = 0; i <= 100; i++)
			f[i] = new BigDecimal(0);

		this.n = n;
		this.p = p;

		int x = getX();

		for (int i = x + 2; i <= n; i++) {
			BigDecimal addNum = i % 2 == 0 ? evenNumber : oddNumber;
			if (i - 2 < 0 && i != 1) {
				f[i] = f[i - 1].add(addNum);
			} else if (i == 1) {
				f[1] = new BigDecimal(0);
			} else {
				f[i] = f[i - 1].add(f[i - 2].add(addNum));
			}
		}
		System.out.println("Case " + caseNum + ": " + f[n]);
	}

	private void getEven() {
		evenNumber = new BigDecimal(getContainStringNumberExclusive(lastPodd
				+ firstP, p));
	}

	private void getOdd() {
		oddNumber = new BigDecimal(getContainStringNumberExclusive(lastPeven
				+ firstP, p));
	}

	/*
	 * init cache
	 */
	private void init() {
		pCache = new HashMap<Integer, String>();
		pCache.put(0, "0");
		pCache.put(1, "1");
		int n = 2;
		while (true) {
			String newString = pCache.get(n - 1) + pCache.get(n - 2);
			if (newString.length() >= 100000) {
				pCache.put(n, newString);
				n++;
				newString = pCache.get(n - 1) + pCache.get(n - 2);
				pCache.put(n, newString);
				n++;
				break;
			}
			pCache.put(n, newString);
			n++;
		}
		pCacheSize = n - 1;
	}

	/*
	 * get first fstring that length >= p.length
	 */
	protected int getX() {
		for (int i = 0; i <= pCacheSize; i++) {
			if (p.equals("1"))
				i = 1;
			String currentString = pCache.get(i);
			if (currentString.length() >= p.length()) {
				// get firstp
				firstP = currentString.substring(0, p.length());

				int length = currentString.length();
				String nextString = i - 1 >= 0 ? currentString
						+ pCache.get(i - 1) : "1";
				int nextLength = nextString.length();
				// get lastp
				if (i % 2 == 0) {
					lastPeven = currentString.substring(length - p.length());
					lastPodd = nextString.substring(nextLength - p.length());
				} else {
					lastPodd = currentString.substring(length - p.length());
					lastPeven = nextString.substring(nextLength - p.length());
				}
				getEven();
				getOdd();
				f[i] = new BigDecimal(getContainStringNumber(currentString, p));
				f[i + 1] = new BigDecimal(getContainStringNumber(nextString, p));
				return i;
			}// if
		}
		return 100;
	}

	protected static int getContainStringNumber(String mother, String son) {
		int ml = mother.length();
		int sl = son.length();
		if (ml == sl)
			return mother.contains(son) ? 1 : 0;
		int count = 0;
		for (int i = 0; i <= ml - sl; i++) {
			if ((mother.substring(i, i + sl)).contains(son)) {
				count++;
			}
		}
		return count;
	}

	protected static long getContainStringNumberExclusive(String mother,
			String son) {
		int ml = mother.length();
		int sl = son.length();
		long count = 0;
		for (int i = 1; i <= ml - sl - 1; i++) {
			if ((mother.substring(i, i + sl)).contains(son)) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		try {
			while (sc.hasNextInt()) {
				new Main(sc.nextInt(), sc.next());
				caseNum++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}