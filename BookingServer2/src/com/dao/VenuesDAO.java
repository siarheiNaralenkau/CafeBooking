package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
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
import com.beans.ScheduleEntry;
import com.beans.Venue;
import com.beans.VenueDistanceComp;
import com.beans.VenueTable;
import com.constants.BookingStatus;
import com.constants.Consts;
import com.utils.LocationUtil;

public class VenuesDAO {
	private static final String BOOK_QUERY = "INSERT INTO bookings(venue_id, visitor_contact_name, visitor_contact_phone, booking_time, places_amount, status, notes, table_no) " +
			"VALUES(?, ?, ?, ?, ?, " + BookingStatus.PENDING.getValue() + ", ?, ?)";
	private static final String SORTED_COORDS_QUERY = "SELECT * FROM venues ORDER BY ABS(latitude-?), ABS(longitude-?)";
	private static final String UNSORTED_COORDS_QUERY = "SELECT * FROM venues ORDER BY name";
	private static final String UPDATE_HISTORY_QUERY = "INSERT INTO booking_history(booking_id, new_status) VALUES(?, ?)";
	private static final String UPDATE_HISTORY_EXT_QUERY = "INSERT INTO booking_history(booking_id, new_status, new_places, new_time) VALUES(?, ?, ?, ?)";
	private static final String GET_LAST_AUTOINCREMENT = "SELECT LAST_INSERT_ID() AS NEW_ID";
	private static final String GET_HISTORY_QUERY = "SELECT h.booking_id, h.new_status, h.change_time, b.venue_id, b.places_amount FROM booking_history h, bookings b WHERE h.booking_id = b.id AND b.venue_id = ?";
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
	private static final String ADD_DAY_SCHEDULE_QUERY = "INSERT INTO venue_schedule(venue_id, day, open_time, close_time) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_DAY_SCHEDULE_QUERY = "UPDATE venue_schedule set open_time = ?, close_time = ? WHERE day = ? AND venue_id = ?";
	private static final String GET_VENUE_SCHEDULE_QUERY = "SELECT v.day as day_id, v.open_time, v.close_time, w.name as day FROM venue_schedule v, week_days w WHERE w.id = v.day and venue_id = ?";		
	private static final String SWITCH_IN_SYSTEM_QUERY = "UPDATE venues set in_booking_system = ? WHERE id = ?";
	private static final String ADD_TABLE_QUERY = "INSERT INTO tables(venue_id, x_pos, y_pos, places, number, position_notes, photo_url) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";		
	private static final String SET_VENUE_PLAN_QUERY = "UPDATE venues set plan = ? WHERE id = ?";
	private static final String VENUE_DETAILS_QUERY = "SELECT unique_id, open_time, close_time, plan FROM venues where id = ?";
	private static final String VENUE_PHOTOS_QUERY = "SELECT url FROM venue_photos WHERE venue_id = ?";
		
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
		boolean isSorted = true;
		try {
			con = dataSource.getConnection();
			String query;
			if(sLat == null || sLng == null) {
				query = UNSORTED_COORDS_QUERY;
				isSorted = false;
				if(limit != null) {
					query += " LIMIT ?";
				}
				ps = con.prepareStatement(query);
				if(limit != null) {
					ps.setInt(1, limit);
				}
			} else {			
				query = new String(SORTED_COORDS_QUERY);
				if(limit != null) {
					query += " LIMIT ?";
				}
				ps = con.prepareStatement(query);			 
				ps.setDouble(1, sLat);
				ps.setDouble(2, sLng);
				if(limit != null) {
					ps.setInt(3, limit);
				}
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Venue v = new Venue(rs.getLong("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getDouble("latitude"),
						rs.getDouble("longitude"), rs.getString("category"), rs.getBoolean("has_free_seats"), rs.getString("icon_url"));
				if(isSorted) {
					LocationUtil.calcDistance(v, sLat, sLng);
				}
				venues.add(v);
			}
			if(isSorted) {
				Collections.sort(venues, new VenueDistanceComp());
			}
		} catch (SQLException e) { 
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return venues;
	}
	
	public static Map<String, Object> bookPlaces(int venue_id, String visitorName, String visitorPhone, Date bookingTime, byte places, String notes, String tableNumbers) {
		Map<String, Object> result = new HashMap<String, Object>();
		int qResult = 0;
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			Venue v = getVenueById(venue_id);
			if(v == null) {
				result.put("status", "failure");
				result.put("error", "There is no venue with id " + venue_id);
			} else {
				con = dataSource.getConnection();
				ps = con.prepareStatement(BOOK_QUERY);
				ps.setInt(1, venue_id);
				ps.setString(2, visitorName);
				ps.setString(3, visitorPhone);
				ps.setTimestamp(4, new Timestamp(bookingTime.getTime()));
				ps.setByte(5, places);
				ps.setString(6, notes);
				ps.setString(7, tableNumbers);
				qResult = ps.executeUpdate();
				if(qResult > 0) {
					result.put("status", "success");
					result.put("places_booked", places);
					ps = con.prepareStatement(GET_LAST_AUTOINCREMENT);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						int bookingId = rs.getInt("NEW_ID");
						writeHistory(con, bookingId, (byte)1);
						result.put("bookingId", bookingId);
					}				
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
	
	public static void writeHistory(Connection con, int bookingId, int newStatus) throws SQLException {		
		PreparedStatement ps = con.prepareStatement(UPDATE_HISTORY_QUERY);
		ps.setInt(1, bookingId);
		ps.setInt(2, newStatus);		
		int result = ps.executeUpdate();
		System.out.println("Updated history records: " + result);
		ps.close();
	}
	
	public static void writeHistory(Connection con, int bookingId, int newStatus, int newPlaces, Timestamp newTime) throws SQLException {
		PreparedStatement ps = con.prepareStatement(UPDATE_HISTORY_EXT_QUERY);
		ps.setInt(1, bookingId);
		ps.setInt(2, newStatus);		
		ps.setInt(3, newPlaces);
		ps.setTimestamp(4, newTime);
		int result = ps.executeUpdate();
		System.out.println("Updated history records: " + result);
		ps.close();
	}
	
	public static Map<String, Object> updateStatus(int bookingId, int newStatus) {
		Connection con = null;
		PreparedStatement ps = null;
		Map<String, Object> result = new HashMap<String, Object>();				
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
				writeHistory(con, bookingId, newStatus);
			}
		} catch (SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
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
				String sTableNumbers = rs.getString("table_no");				
				List<Integer> tableNumbers = new ArrayList<Integer>();
				if(sTableNumbers != null && !sTableNumbers.isEmpty()) {
					for(String sNum : sTableNumbers.split(",")) {
						tableNumbers.add(Integer.valueOf(sNum));
					}				
				}
				booking.setTableNumbers(tableNumbers);				
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
				venue = new Venue(rs.getInt("id"), rs.getString("unique_id"), rs.getString("name"),
						rs.getString("phone"), rs.getString("address"), rs.getString("city"), rs.getString("country"), 
						rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("category"), 
						rs.getBoolean("has_free_seats"), rs.getString("icon_url"));
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return venue;
	}
	
	public static Map<String, Object> switchVenueStatus(int venueId, boolean enableBooking) {
		Map<String, Object> result = new HashMap<String, Object>();
		Venue venue = getVenueById(venueId);		
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
		
		return result;
	}
	
	public static Map<String, Object> getBookingHistory(int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<HistoryEnrty> bookingHistory = new ArrayList<HistoryEnrty>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();				
			ps = con.prepareStatement(GET_HISTORY_QUERY);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				HistoryEnrty he = new HistoryEnrty(rs.getInt("booking_id"), rs.getInt("new_status"), 
						rs.getTimestamp("change_time"), rs.getInt("venue_id"), rs.getInt("places_amount"));
				bookingHistory.add(he);					
			}
			result.put("status", "success");
			result.put("bookingHistory", bookingHistory);			
		} catch (SQLException e) {			
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> getPendingBookings(int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Booking> pendingBookings = new ArrayList<Booking>();
		Connection con = null;
		PreparedStatement ps = null;
		try {						
			con = dataSource.getConnection();
			ps = con.prepareStatement(PENDING_BOOKINGS_QUERY);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			long nowTime = new Date().getTime();
			while(rs.next()) {
				Booking b = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), rs.getInt("status"), rs.getString("notes"), rs.getTimestamp("booking_created"));
				// Check if booking status is pending, and booking was created more then 20 minutes ago. If true - Disable booking.
				long createdTime = b.getBookingCreated().getTime();
				if(Math.abs(nowTime-createdTime) >= Consts.TWENTY_MINUTES_MS && b.getStatus() == BookingStatus.PENDING.getValue()) {					
					updateStatus(b.getId(), BookingStatus.REJECTED.getValue());
				} else {
					String sTableNumbers = rs.getString("table_no");
					List<Integer> bookedTables = new ArrayList<Integer>();
					if(sTableNumbers != null && !sTableNumbers.isEmpty()) {
						for(String sNum: sTableNumbers.split(",")) {
							bookedTables.add(Integer.valueOf(sNum));
						}				
					}
					b.setTableNumbers(bookedTables);				
					pendingBookings.add(b);
				}
			}
			result.put("status", "success");
			result.put("pendingBookings", pendingBookings);			
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> deleteBooking(int bookingId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {					
			con = dataSource.getConnection();
			ps = con.prepareStatement(DELETE_BOOKING_QUERY);
			ps.setInt(1, bookingId);
			ps.executeUpdate();
			result.put("status", "success");
			result.put("bookingId", bookingId);
			result.put("newBookingStatus", BookingStatus.DELETED.getValue());			
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
	}	
	
	public static Map<String, Object> getBookingsForVenue(int venueId, int filterStatus) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {						
			List<Booking> bookings = new ArrayList<Booking>();
			con = dataSource.getConnection();
			String query = GET_BOOKINGS_QUERY;
			if(filterStatus != Consts.STATUS_ALL) {
				query += " AND status = " + filterStatus; 
			}
			ps = con.prepareStatement(query);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			long nowTime = new Date().getTime();
			while(rs.next()) {
				Booking b = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), rs.getInt("status"), rs.getString("notes"), rs.getTimestamp("booking_created"));				
				String sTableNumbers = rs.getString("table_no");
				List<Integer> bookedTables = new ArrayList<Integer>();
				if(sTableNumbers != null && !sTableNumbers.isEmpty()) {
					for(String sNum : sTableNumbers.split(",")) {
						bookedTables.add(Integer.valueOf(sNum));
					}
				}
				// Check if booking status is pending, and booking was created more then 20 minutes ago. If true - Disable booking.
				long createdTime = b.getBookingCreated().getTime();
				if(Math.abs(nowTime-createdTime) >= Consts.TWENTY_MINUTES_MS && b.getStatus() == BookingStatus.PENDING.getValue()) {
					b.setStatus(BookingStatus.REJECTED.getValue());
					updateStatus(b.getId(), BookingStatus.REJECTED.getValue());
				}
				if(b.getStatus() == filterStatus || filterStatus == Consts.STATUS_ALL) {
					bookings.add(b);
				}
			}
			result.put("status", "success");
			result.put("venueId", venueId);
			result.put("bookings", bookings);
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
	
	public static Map<String, Object> clearBookings(int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {						
			con = dataSource.getConnection();
			ps = con.prepareStatement(DELETE_BOOKINGS_QUERY);
			ps.setInt(1, venueId);
			int deleted = ps.executeUpdate();				
			result.put("status", "success");
			result.put("venueId", venueId);
			result.put("deletedBookings", deleted);
			
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
			con = dataSource.getConnection();
			ps = con.prepareStatement(UPDATE_BOOKING_QUERY);
			ps.setInt(1, places);
			ps.setTimestamp(2, bookingTime);
			ps.setInt(3, bookingId);
			ps.executeUpdate();	
			writeHistory(con, bookingId, BookingStatus.PENDING.getValue(), places, bookingTime);
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
	
	public static Map<String, Object> setVenueSchedule(int venueId, ScheduleEntry[] schedule) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<Object, Object> existingSchedule = getVenueSchedule(venueId);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			for(ScheduleEntry daySchedule: schedule) {
				Integer dayId = daySchedule.getDayId();
				if(dayId == null) {
					dayId = 0;
				}
				if(existingSchedule.containsKey(dayId)) {
					// Update the schedule for specified day.					
					ps = con.prepareStatement(UPDATE_DAY_SCHEDULE_QUERY);
					ps.setString(1, daySchedule.getOpenTime());
					ps.setString(2, daySchedule.getCloseTime());
					ps.setInt(3, dayId);
					ps.setInt(4, venueId);
					ps.executeUpdate();					
				} else {	
					// Set the schedule for specified day.
					ps = con.prepareStatement(ADD_DAY_SCHEDULE_QUERY);
					ps.setInt(1, venueId);
					ps.setInt(2, dayId);				
					ps.setString(3, daySchedule.getOpenTime());
					ps.setString(4, daySchedule.getCloseTime());
					ps.executeUpdate();
				}
				result.put(String.valueOf(dayId), daySchedule.getOpenTime() + " - " + daySchedule.getCloseTime());
			}
			result.put("status", "success");
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<Object, Object> getVenueSchedule(int venueId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_VENUE_SCHEDULE_QUERY);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer day_id = rs.getInt("day_id");
				ScheduleEntry se = new ScheduleEntry(venueId, day_id, rs.getString("day"), rs.getString("open_time"), rs.getString("closeTime"));
				result.put(day_id, se);
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
	
	public static Map<Object, Object> switchInSystem(int venueId, boolean inSystemStatus) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SWITCH_IN_SYSTEM_QUERY);
			ps.setBoolean(1, inSystemStatus);
			ps.setInt(2, venueId);
			ps.executeUpdate();
			result.put("status", "success");
			result.put("venue_id", venueId);
			result.put("inSystem", inSystemStatus);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> addTable(Integer venueId, Integer xPos, Integer yPos, Integer places, Integer number, Integer positionNotes, String photoUrl) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(ADD_TABLE_QUERY);
			ps.setInt(1, venueId);
			if(xPos != null) {
				ps.setInt(2, xPos);
			} else {
				ps.setNull(2, Types.INTEGER);
			}
			if(yPos != null) {
				ps.setInt(3, yPos);
			} else {
				ps.setNull(3, Types.INTEGER);
			}
			ps.setInt(4, places);
			if(number != null) {
				ps.setInt(5, number);
			} else {
				ps.setNull(5, Types.INTEGER);
			}
			ps.setInt(6, positionNotes);
			ps.setString(7, photoUrl);
			ps.executeUpdate();
			result.put("status", "success");
			result.put("message", "Table with " + places + " places added to venue");
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return result;
	}
	
	public static Map<String, Object> getVenueTables(Integer venueId, boolean freeOnly, Integer positionNotes) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		List<VenueTable> tables = new ArrayList<VenueTable>();
		try {
			con = dataSource.getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM tables WHERE venue_id = ?");
			if(freeOnly) {
				query.append(" AND (booked_places=0 OR booked_time>NOW() + INTERVAL 1 DAY)");
			}
			if(positionNotes != null) {
				query.append(" AND position_notes = " + positionNotes);
			}
			ps = con.prepareStatement(query.toString());
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int posNotes = rs.getInt("position_notes");
				String sPosNotes = Consts.POSITION_NOTES.get(posNotes);
				VenueTable table = new VenueTable(rs.getInt("id"), rs.getInt("venue_id"), rs.getInt("x_pos"), rs.getInt("y_pos"), rs.getInt("places"), rs.getInt("number"),
						sPosNotes, rs.getBoolean("is_free"), rs.getInt("booked_places"), rs.getTimestamp("booked_time"), rs.getString("photo_url"));
				tables.add(table);
			}
			result.put("status", "success");
			result.put("tables", tables);
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> setVenuePlan(Integer venueId, String plan) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SET_VENUE_PLAN_QUERY);
			ps.setString (1, plan);
			ps.setInt(2, venueId);			
			int updated = ps.executeUpdate();
			if(updated <= 0) {
				result.put("status", "failure");
				result.put("error", "Venue with id: " + venueId + " doesn't exists");
			} else {
				result.put("status", "success");
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
	
	public static Map<String, Object> getVenueDetails(int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(VENUE_DETAILS_QUERY);
			ps.setInt(1, venueId);
			ResultSet venueRs = ps.executeQuery();
			if(!venueRs.next()) {
				result.put("status", "failure");
				result.put("error", "Venue with id: " + venueId + " doesn't exist");
			} else {
				String venueUID = venueRs.getString("unique_id");
				result.put("status", "success");
				result.put("venueUID", venueUID);
				result.put("open_time", venueRs.getString("open_time"));
				result.put("close_time", venueRs.getString("close_time"));
				result.put("plan", venueRs.getString("plan"));
				venueRs.close();
				List<String> photoURLs = new ArrayList<String>();
				ps = con.prepareStatement(VENUE_PHOTOS_QUERY);
				ps.setString(1, venueUID);
				ResultSet photosRs = ps.executeQuery();
				while(photosRs.next()) {
					photoURLs.add(photosRs.getString("url"));
				}
				result.put("photos", photoURLs);
				photosRs.close();
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
