package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
	private static final String SORTED_COORDS_QUERY = "select * from venues order by ABS(latitude-52), ABS(longitude-30) limit 30";
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
		PreparedStatement ps = con.prepareStatement(VENUES_LIST_QUERY);
		ps.setInt(1, limit);
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
