package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.beans.Booking;
import com.beans.Venue;
import com.beans.VenueDistanceComp;
import com.constants.BookingStatus;
import com.constants.Consts;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class VenuesDAO {
	private static final String BOOK_QUERY = "INSERT INTO bookings(venue_id, visitor_contact_name, visitor_contact_phone, booking_time, places_amount, status, notes) " +
			"VALUES(?, ?, ?, ?, ?, " + BookingStatus.PENDING.getValue() + ", ?)";
	private static final String SORTED_COORDS_QUERY = "SELECT * FROM venues ORDER BY ABS(latitude-?), ABS(longitude-?)";
	private static final String UPDATE_HISTORY_QUERY = "INSERT INTO booking_history(booking_id, new_status, action_user) VALUES(?, ?, ?)";
	private static final String GET_LAST_AUTOINCREMENT = "SELECT LAST_INSERT_ID() AS NEW_ID";
	private static final String GET_HISTORY_QUERY = "SELECT h.id, h.booking_id, h.new_status, h.action_user, h.change_time, b.venue_id FROM booking_history h, bookings b WHERE b.id = h.id AND b.venue_id = ?";
	private static final String UPDATE_STATUS_QUERY = "UPDATE bookings SET status = ? WHERE id = ?"; 
	private static final String GET_BOOKING_QUERY = "SELECT * FROM bookings WHERE id = ?";
	private static final String GET_VENUE_QUERY = "SELECT * FROM venues WHERE id = ?";
	private static final String BLOCK_BOOKING_QUERY = "UPDATE venues SET has_free_seats = false WHERE id = ?";
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
	
	public static List<Venue> getVenues(Double lat, Double lng, Integer limit) throws SQLException {
		Double sLat = lat, sLng = lng;
		List<Venue> venues = new ArrayList<Venue>();
		Connection con = dataSource.getConnection();
		String query = new String(SORTED_COORDS_QUERY);
		if(limit != null) {
			query += " LIMIT ?";
		}
		PreparedStatement ps = con.prepareStatement(query);
		if(sLat == null ) {
			sLat = Consts.DEFAULT_LAT;  			
		} 
		if(sLng == null) {
			sLng = Consts.DEFAULT_LNG;
		} 
		ps.setDouble(1, sLat);
		ps.setDouble(2, sLng);
		if(limit != null) {
			ps.setInt(3, limit);
		}		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Venue v = new Venue(rs.getLong("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
					rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getDouble("latitude"),
					rs.getDouble("longitude"), rs.getString("category"), rs.getBoolean("has_free_seats"));
			calcDistance(v, sLat, sLng);
			venues.add(v);
		}
		Collections.sort(venues, new VenueDistanceComp());
		return venues;
	}
	
	public static int bookPlaces(int venue_id, String visitorName, String visitorPhone, Date bookingTime, byte places, String notes) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOK_QUERY);
			ps.setInt(1, venue_id);
			ps.setString(2, visitorName);
			ps.setString(3, visitorPhone);
			ps.setTimestamp(4, new Timestamp(bookingTime.getTime()));
			ps.setByte(5, places);
			ps.setString(6, notes);
			result = ps.executeUpdate();
			if(result > 0) {
				result = places;
				ps = con.prepareStatement(GET_LAST_AUTOINCREMENT);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					int bookingId = rs.getInt("NEW_ID");
					writeHistory(con, bookingId, (byte)1, visitorName);
				}				
			} 
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {			
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static void writeHistory(Connection con, int bookingId, int newStatus, String actionUser) throws SQLException {		
		PreparedStatement ps = con.prepareStatement(UPDATE_HISTORY_QUERY);
		ps.setInt(1, bookingId);
		ps.setInt(2, newStatus);
		ps.setString(3, actionUser);
		int result = ps.executeUpdate();
		System.out.println("Updated history records: " + result);
		ps.close();
	}
	
	public static int updateStatus(int bookingId, int newStatus, String actionUser) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(UPDATE_STATUS_QUERY);
			ps.setInt(1, newStatus);
			ps.setInt(2, bookingId);
			int updated = ps.executeUpdate();
			if(updated > 0) {
				result = newStatus;
				writeHistory(con, bookingId, newStatus, actionUser);
			}
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				System.out.println("Unable to close connection");
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	public static Booking getBookingById(int bookingId) {
		Connection con = null;
		PreparedStatement ps = null;
		Booking booking = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_BOOKING_QUERY);
			ps.setInt(1, bookingId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				booking = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), rs.getInt("status"), rs.getString("notes"), rs.getTimestamp("booking_created"));
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return booking;
	}
	
	public static Venue getVenueById(int venueId) {
		Connection con = null;
		PreparedStatement ps = null;
		Venue venue = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_BOOKING_QUERY);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				venue = new Venue(rs.getInt("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getDouble("latitude"), rs.getDouble("longitude"),
						rs.getString("category"), rs.getBoolean("has_free_seats"), rs.getString("admin_user"));
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return venue;
	}
	
	public static Map<String, Object> blockBookingForVenue(String actionUser, int venueId) {
		Map<String, Object> resultStatus = new HashMap<String, Object>();
		Venue venue = getVenueById(venueId);
		if(venue.getAdminUser().equals(actionUser)) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(BLOCK_BOOKING_QUERY);
				ps.setInt(1, venueId);
				ps.executeUpdate();
				resultStatus.put("status", "success");
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
				resultStatus.put("status", "failure");
				resultStatus.put("error", e.getMessage());
			} finally {
				closeConnection(con, ps);
			}
		} else {
			resultStatus.put("status", "failure");
			resultStatus.put("error", "User: " + actionUser + " is not allowed to block booking for venue: " + venue.getName());
		}
		return resultStatus;
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
	
	private static void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(ps != null) { 
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Unable to close connection!");
			System.out.println(e.getMessage());
		}			
	}
}
