package com.utils;

import com.beans.Venue;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class LocationUtil {
	public static void calcDistance(Venue v, double lat, double lng) {
		Coordinate latPos = new DegreeCoordinate(lat);
		Coordinate lngPos = new DegreeCoordinate(lng);
		Point pos = new Point(latPos, lngPos);
		
		Coordinate latV = new DegreeCoordinate(v.getLat());
		Coordinate lngV = new DegreeCoordinate(v.getLng());
		Point pV = new Point(latV, lngV);
		
		double distance = EarthCalc.getDistance(pos, pV);
		v.setDistance(distance);
	}
}
