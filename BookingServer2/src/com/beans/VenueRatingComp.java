package com.beans;

import java.util.Comparator;

public class VenueRatingComp implements Comparator<Venue> {

	public VenueRatingComp() {}
	
	@Override	
	public int compare(Venue v1, Venue v2) {
		int result;
		if(v1.getRating() < v2.getRating()) {
			result = 1;
		} else if(v1.getRating() > v2.getRating()) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}


}
