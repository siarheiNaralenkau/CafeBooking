package com.beans;

import java.util.Comparator;

public class VenueDistanceComp implements Comparator<Venue> {

	public VenueDistanceComp() {
		
	}

	@Override
	public int compare(Venue v1, Venue v2) {
		int result;
		if(v1.getDistance() > v2.getDistance()) {
			result = 1;
		} else if(v1.getDistance() < v2.getDistance()) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

}
