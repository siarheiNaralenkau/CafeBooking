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
import com.beans.HistoryEnrty;
import com.beans.Venue;
import com.beans.VenueDistanceComp;
import com.constants.BookingStatus;
import com.constants.Consts;
import com.utils.LocationUtil;

public class VenuesDAO {
	private static final String BOOK_QUERY = "INSERT INTO bookings(venue_id, visitor_contact_name, visitor_contact_phone, booking_time, places_amount, status, notes) " +
			"VALUES(?, ?, ?, ?, ?, " + BookingStatus.PENDING.getValue() + ", ?)";
	private static final String SORTED_COORDS_QUERY = "SELECT * FROM venues ORDER BY ABS(latitude-?), ABS(longitude-?)";
	private static final String UPDATE_HISTORY_QUERY = "INSERT INTO booking_history(booking_id, new_status, action_user) VALUES(?, ?, ?)";
	private static final String UPDATE_HISTORY_EXT_QUERY = "INSERT INTO booking_history(booking_id, new_status, action_user, new_places, new_time) VALUES(?, ?, ?, ?, ?)";
	private static final String GET_LAST_AUTOINCREMENT = "SELECT LAST_INSERT_ID() AS NEW_ID";
	private static final String GET_HISTORY_QUERY = "SELECT h.booking_id, h.new_status, h.action_user, h.change_time, b.venue_id, b.places_amount FROM booking_history h, bookings b WHERE h.booking_id = b.id AND b.venue_id = ?";
	private static final String UPDATE_STATUS_QUERY = "UPDATE bookings SET status = ? WHERE id = ?"; 
	private static final String GET_BOOKING_QUERY = "SELECT * FROM bookings WHERE id = ?";
	private static final String GET_VENUE_QUERY = "SELECT * FROM venues WHERE id = ?";
	private static final String BLOCK_BOOKING_QUERY = "UPDATE venues SET has_free_seats = ? WHERE id = ?";
	private static final String PENDING_BOOKINGS_QUERY = "SELECT * from bookings WHERE venue_id = ? and status = " + BookingStatus.PENDING.getValue();
	private static final String GET_BOOKINGS_QUERY = "SELECT * from bookings WHERE venue_id = ?";
	private static final String SET_ADMIN_QUERY = "UPDATE venues set admin_user = ? WHERE id = ?";
	private static final String DELETE_BOOKINGS_QUERY = "DELETE FROM bookings WHERE booking_time < NOW() - INTERVAL 1 DAY AND venue_id = ?";
	private static final String DELETE_BOOKING_QUERY = "UPDATE bookings SET status = " + BookingStatus.DELETED.getValue() + " WHERE booking_id = ?";
	private static final String UPDATE_BOOKING_QUERY = "UPDATE bookings SET status = " + BookingStatus.PENDING.getValue() + ", places_amount = ?, booking_time = ? WHERE id = ?";
	
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
	
	public static List<Venue> getVenues(Double lat, Double lng, Integer limit) {
		Double sLat = lat, sLng = lng;
		List<Venue> venues = new ArrayList<Venue>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			String query = new String(SORTED_COORDS_QUERY);
			if(limit != null) {
				query += " LIMIT ?";
			}
			ps = con.prepareStatement(query);
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
				LocationUtil.calcDistance(v, sLat, sLng);
				venues.add(v);
			}
			Collections.sort(venues, new VenueDistanceComp());
		} catch (SQLException e) { 
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return venues;
	}
	
	public static Map<String, Object> bookPlaces(int venue_id, String visitorName, String visitorPhone, Date bookingTime, byte places, String notes) {
		Map<String, Object> result = new HashMap<String, Object>();
		int qResult = 0;
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
			qResult = ps.executeUpdate();
			if(qResult > 0) {
				result.put("status", "success");
				result.put("places_booked", places);
				ps = con.prepareStatement(GET_LAST_AUTOINCREMENT);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					int bookingId = rs.getInt("NEW_ID");
					writeHistory(con, bookingId, (byte)1, visitorName);
					result.put("bookingId", bookingId);
				}				
			} 
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
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
	
	public static void writeHistory(Connection con, int bookingId, int newStatus, String actionUser, int newPlaces, Timestamp newTime) throws SQLException {
		PreparedStatement ps = con.prepareStatement(UPDATE_HISTORY_EXT_QUERY);
		ps.setInt(1, bookingId);
		ps.setInt(2, newStatus);
		ps.setString(3, actionUser);
		ps.setInt(4, newPlaces);
		ps.setTimestamp(5, newTime);
		int result = ps.executeUpdate();
		System.out.println("Updated history records: " + result);
		ps.close();
	}
	
	public static Map<String, Object> updateStatus(int bookingId, int newStatus, String actionUser) {
		Connection con = null;
		PreparedStatement ps = null;
		Map<String, Object> result = new HashMap<String, Object>();
		Booking booking = getBookingById(bookingId);
		Venue venue = getVenueById(booking.getVenueId());
		if( (newStatus == BookingStatus.APPROVED.getValue() || newStatus == BookingStatus.REJECTED.getValue() || 
				newStatus == BookingStatus.DELETED.getValue()) && !venue.getAdminUser().equals(actionUser)) {
			result.put("status", "failure");
			result.put("error", "User: " + actionUser + " is not allowed set status " + newStatus + " for booking");
		} else {
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(UPDATE_STATUS_QUERY);
				ps.setInt(1, newStatus);
				ps.setInt(2, bookingId);
				int updated = ps.executeUpdate();
				if(updated > 0) {
					result.put("status", "success");
					result.put("bookingId", bookingId);
					result.put("newBookingStatus", newStatus);
					writeHistory(con, bookingId, newStatus, actionUser);
				}
			} catch (SQLException e) {			
				result.put("status", "failure");
				result.put("error", e.getMessage());
			} finally {
				closeConnection(con, ps);
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
			ps = con.prepareStatement(GET_VENUE_QUERY);
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
	
	public static Map<String, Object> switchVenueStatus(String actionUser, int venueId, boolean enableBooking) {
		Map<String, Object> result = new HashMap<String, Object>();
		Venue venue = getVenueById(venueId);
		if(venue.getAdminUser().equals(actionUser)) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(BLOCK_BOOKING_QUERY);
				ps.setBoolean(1, enableBooking);
				ps.setInt(2, venueId);
				ps.executeUpdate();
				result.put("status", "success");
				result.put("venueName", venue.getName());
				result.put("bookingEnabled", enableBooking);
			} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage());
				result.put("status", "failure");
				result.put("error", e.getMessage());
			} finally {
				closeConnection(con, ps);
			}
		} else {
			result.put("status", "failure");
			result.put("error", "User: " + actionUser + " is not allowed to block booking for venue: " + venue.getName());
		}
		return result;
	}
	
	public static Map<String, Object> getBookingHistory(String actionUser, int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<HistoryEnrty> bookingHistory = new ArrayList<HistoryEnrty>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			Venue venue = getVenueById(venueId);
			if(!venue.getAdminUser().equals(actionUser)) {
				result.put("status", "failure");
				result.put("error", "User " + actionUser + " is not allowed to block booking for venue with id " + venueId);
			} else {
				ps = con.prepareStatement(GET_HISTORY_QUERY);
				ps.setInt(1, venueId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					HistoryEnrty he = new HistoryEnrty(rs.getInt("booking_id"), rs.getInt("new_status"), rs.getString("action_user"), 
							rs.getTimestamp("change_time"), rs.getInt("venue_id"), rs.getInt("places_amount"));
					bookingHistory.add(he);					
				}
				result.put("status", "success");
				result.put("bookingHistory", bookingHistory);
			}			
		} catch (SQLException e) {			
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> getPendingBookings(String actionUser, int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Booking> pendingBookings = new ArrayList<Booking>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			Venue venue = getVenueById(venueId);
			if(!venue.getAdminUser().equals(actionUser)) {
				result.put("status", "failure");
				result.put("error", "User " + actionUser + " is not allowed to block booking for venue with id " + venueId);
			} else {
				con = dataSource.getConnection();
				ps = con.prepareStatement(PENDING_BOOKINGS_QUERY);
				ps.setInt(1, venueId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Booking b = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
							rs.getTimestamp("booking_time"), rs.getInt("places_amount"), rs.getInt("status"), rs.getString("notes"), rs.getTimestamp("booking_created"));
					pendingBookings.add(b);
				}
				result.put("status", "success");
				result.put("pendingBookings", pendingBookings);
			}			
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> deleteBooking(String actionUser, int bookingId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Booking booking = getBookingById(bookingId);
			Venue venue = getVenueById(booking.getVenueId());
			if(!venue.getAdminUser().equals(actionUser)) {
				result.put("status", "failure");
				result.put("error", "User " + actionUser + " is not allowed to delete bookings for venue with id " + booking.getVenueId());
			} else {
				con = dataSource.getConnection();
				ps = con.prepareStatement(DELETE_BOOKING_QUERY);
				ps.setInt(1, bookingId);
				ps.executeUpdate();
				result.put("status", "success");
				result.put("bookingId", bookingId);
				result.put("newBookingStatus", BookingStatus.DELETED.getValue());
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
	}	
	
	public static Map<String, Object> getBookingsForVenue(String actionUser, int venueId, int filterStatus) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			Venue venue = getVenueById(venueId);
			if(!venue.getAdminUser().equals(actionUser)) {
				result.put("status", "failure");
				result.put("error", "User " + actionUser + " is not allowed receive bookings list for venue with id " + venueId);
			} else {
				List<Booking> bookings = new ArrayList<Booking>();
				con = dataSource.getConnection();
				String query = GET_BOOKINGS_QUERY;
				if(filterStatus != Consts.STATUS_ALL) {
					query += " AND status = " + filterStatus; 
				}
				ps = con.prepareStatement(query);
				ps.setInt(1, venueId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Booking b = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
							rs.getTimestamp("booking_time"), rs.getInt("places_amount"), rs.getInt("status"), rs.getString("notes"), rs.getTimestamp("booking_created"));
					bookings.add(b);
				}
				result.put("status", "success");
				result.put("venueId", venueId);
				result.put("bookings", bookings);
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
	}
	
	public static Map<String, Object> setVenueAdmin(String adminUser, int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SET_ADMIN_QUERY);
			ps.setString(1, adminUser);
			ps.setInt(2, venueId);
			ps.executeUpdate();
			result.put("status", "success");
			result.put("venueId", venueId);
			result.put("adminUser", adminUser);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> clearBookings(String actionUser, int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			Venue venue = getVenueById(venueId);
			if(!venue.getAdminUser().equals(actionUser)) {
				result.put("status", "failure");
				result.put("error", "User " + actionUser + " is not allowed receive bookings list for venue with id " + venueId);
			} else {				
				con = dataSource.getConnection();
				ps = con.prepareStatement(DELETE_BOOKINGS_QUERY);
				ps.setInt(1, venueId);
				int deleted = ps.executeUpdate();				
				result.put("status", "success");
				result.put("venueId", venueId);
				result.put("deletedBookings", deleted);
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
	}
	
	public static Map<String, Object> updateBooking(int places, Timestamp bookingTime, int bookingId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;		
		try {			
			Booking booking = getBookingById(bookingId);
			con = dataSource.getConnection();
			ps = con.prepareStatement(UPDATE_BOOKING_QUERY);
			ps.setInt(1, places);
			ps.setTimestamp(2, bookingTime);
			ps.setInt(3, bookingId);
			ps.executeUpdate();	
			writeHistory(con, bookingId, BookingStatus.PENDING.getValue(), booking.getVisitorName(), places, bookingTime);
			result.put("status", "success");
			result.put("bookingId", bookingId);
			result.put("booking_time", bookingTime);
			result.put("places_amount", places);
			result.put("status", BookingStatus.PENDING.getValue());
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
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
