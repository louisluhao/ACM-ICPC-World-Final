package wf2012.d;

import static org.junit.Assert.*;
import static wf2012.d.Main.*;

import org.junit.Test;

public class MainTest {

	@Test
	public void testGetContainStringNumber() {
		assertEquals(1, getContainStringNumber("abccde", "abc"));
		assertEquals(2, getContainStringNumber("dedaebccde", "de"));
		assertEquals(1, getContainStringNumber("abc", "abc"));
	}
	
	@Test
	public void testGetContainStringNumberExclusive() {
		assertEquals(0, getContainStringNumberExclusive("abccde", "abc"));
		assertEquals(0, getContainStringNumberExclusive("dedaebccde", "de"));
		assertEquals(0, getContainStringNumberExclusive("abc", "abc"));
	}
	
	
	@Test
	public void testGetX(){
		Main test = new Main(20, "101");
		assertEquals(3, test.getX());
		
		StringBuffer target = new StringBuffer();
		for(int i= 0; i< 100000; i++){
			target.append("0");
		}
		Main test2 = new Main(25, target.toString());
		assertEquals(Main.pCacheSize, test2.getX());
		
		Main test3 = new Main(25, pCache.get(pCacheSize).substring(1));
	}

}
