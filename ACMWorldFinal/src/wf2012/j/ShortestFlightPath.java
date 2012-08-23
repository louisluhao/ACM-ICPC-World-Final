package wf2012.j;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestFlightPath {

	public static final double R = 6370;
	
	double maxFlightDistance;
	List<AirPort> airPorts = new ArrayList<AirPort>();

	static class AirPort {
		double phi;
		double theta;
		double x;
		double y;
		Map<AirPort, Double> neighbors = new HashMap<AirPort, Double>();

		public AirPort(double phi, double theta) {
			this.phi = phi;
			this.theta = theta;
			x = phiToLocation(phi);
			y = thetaToLocation(theta);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(x);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(y);
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
			AirPort other = (AirPort) obj;
			if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
				return false;
			if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "AirPort phi=" + phi + ", theta=" + theta;
		}
		
		
	}

	protected static double getDistance(AirPort a1, AirPort a2) {
		// compute point location
		double x1 = a1.x;
		double y1 = a1.y;
		double x2 = a2.x;
		double y2 = a2.y;
		return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	private static double phiToLocation(double phi) {
		return phi / 360 * 2 * PI * R;
	}

	private static double thetaToLocation(double theta) {
		return (theta + 90) / 180 * PI * R;
	}

	public void buildGraph() {
		for (AirPort ap1 : airPorts) {
			for(AirPort ap2 : airPorts){
				if(!ap1.equals(ap2) && !ap1.neighbors.containsKey(ap2)){
					double distance = getDistance(ap1, ap2);
					//if distance lower than max flight distance
					//then the straight flight line must be shortest
					if(distance < maxFlightDistance * 2){
						ap1.neighbors.put(ap2, distance);
						ap2.neighbors.put(ap1, distance);
					}
				}
			}
		}
	}

	public static void main(String[] args) {

	}

}
