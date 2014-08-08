package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.beans.Venue;
import com.beans.VenueDistanceComp;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class VenuesDAO {

	private static final String VENUES_LIST_QUERY = "SELECT * FROM venues LIMIT ?";
	private static final String BOOK_QUERY = "INSERT INTO bookings(venue_id, visitor_contact_name, visitor_contact_phone, booking_time, places_amount, status, notes) " +
			"VALUES(?, ?, ?, ?, ?, 1, ?)";
	private static final String SORTED_COORDS_QUERY = "select * from venues order by ABS(latitude-?), ABS(longitude-?) LIMIT ?";
	private static DataSource dataSource;
	
	static {		
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/bronimesto");
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
	}
	
	public static List<Venue> getVenues(double lat, double lng, int limit) throws SQLException {
		List<Venue> venues = new ArrayList<Venue>();
		Connection con = dataSource.getConnection();
		PreparedStatement ps = con.prepareStatement(SORTED_COORDS_QUERY);
		ps.setDouble(1, lat);
		ps.setDouble(2, lng);
		ps.setInt(3, limit);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Venue v = new Venue(rs.getLong("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
					rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getDouble("latitude"),
					rs.getDouble("longitude"), rs.getString("category"), rs.getBoolean("has_free_seats"));
			calcDistance(v, lat, lng);
			venues.add(v);
		}
		Collections.sort(venues, new VenueDistanceComp());
		return venues;
	}
	
	public static int bookPlaces(int venue_id, String visitorName, String visitorPhone, Date bookingTime, byte places, String notes) throws SQLException {
		int result = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement ps = con.prepareStatement(BOOK_QUERY);
		ps.setInt(1, venue_id);
		ps.setString(2, visitorName);
		ps.setString(3, visitorPhone);
		ps.setDate(4, new java.sql.Date(bookingTime.getTime()));
		ps.setByte(5, places);
		ps.setString(6, notes);
		result = ps.executeUpdate();
		if(result > 0) {
			return places;
		} else {
			return result;
		}
	}
	
	private static void calcDistance(Venue v, double lat, double lng) {
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
