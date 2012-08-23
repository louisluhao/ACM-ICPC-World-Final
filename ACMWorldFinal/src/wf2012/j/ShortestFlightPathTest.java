package wf2012.j;

import static java.lang.Math.PI;
import static org.junit.Assert.assertEquals;
import static wf2012.j.ShortestFlightPath.*;

import org.junit.Test;

import wf2012.j.ShortestFlightPath.AirPort;

public class ShortestFlightPathTest {

	@Test
	public void test() {

	}

	@Test
	public void testGetGraph() {
		ShortestFlightPath test = new ShortestFlightPath();
		test.airPorts.add(new AirPort(0, 0));
		test.airPorts.add(new AirPort(0, 30));
		test.airPorts.add(new AirPort(30, 0));
		test.maxFlightDistance = 2000;
		test.buildGraph();
		System.out.println(test.airPorts.get(2).neighbors.keySet());
		System.out.println(test.airPorts.get(2).neighbors.values());
	}

}
