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
import com.beans.VenuePhoto;
import com.beans.VenueRatingComp;
import com.beans.VenueTable;
import com.constants.BookingStatus;
import com.constants.Consts;
import com.utils.LocationUtil;

public class VenuesDAO {
	private static final String BOOK_QUERY = "INSERT INTO bookings(venue_id, visitor_contact_name, visitor_contact_phone, booking_time, places_amount, status, notes, table_no, user_id, reg_id, email) " +
			"VALUES(?, ?, ?, ?, ?, " + BookingStatus.PENDING.getValue() + ", ?, ?, ?, ?, ?)";
	private static final String VENUES_LIST_SQL = "SELECT * FROM venues order by name asc";
	private static final String UPDATE_HISTORY_QUERY = "INSERT INTO booking_history(booking_id, new_status) VALUES(?, ?)";
	private static final String UPDATE_HISTORY_EXT_QUERY = "INSERT INTO booking_history(booking_id, new_status, new_places, new_time) VALUES(?, ?, ?, ?)";
	private static final String GET_LAST_AUTOINCREMENT = "SELECT LAST_INSERT_ID() AS NEW_ID";
	private static final String GET_HISTORY_QUERY = "SELECT h.booking_id, h.new_status, h.change_time, b.venue_id, b.places_amount FROM booking_history h, bookings b WHERE h.booking_id = b.id AND b.venue_id = ?";
	private static final String UPDATE_STATUS_QUERY = "UPDATE bookings SET status = ? WHERE id = ?"; 
	private static final String GET_BOOKING_QUERY = "SELECT * FROM bookings WHERE id = ? order by booking_time desc";
	private static final String GET_VENUE_QUERY = "SELECT venues.*, (SELECT COUNT(*) FROM reviews WHERE venue_id = ?) as reviews_amount FROM venues WHERE id = ?";
	private static final String BLOCK_BOOKING_QUERY = "UPDATE venues SET has_free_seats = ? WHERE id = ?";
	private static final String PENDING_BOOKINGS_QUERY = "SELECT * from bookings WHERE venue_id = ? and status = " + BookingStatus.PENDING.getValue() + " order by booking_time desc";
	private static final String GET_BOOKINGS_QUERY = "SELECT * from bookings WHERE venue_id = ? order by booking_time desc";
	private static final String SET_ADMIN_QUERY = "UPDATE venues set admin_user = ? WHERE id = ?";
	private static final String DELETE_BOOKINGS_QUERY = "DELETE FROM bookings WHERE booking_time < NOW() - INTERVAL 1 DAY AND venue_id = ?";	
	private static final String UPDATE_BOOKING_QUERY = "UPDATE bookings SET status = " + BookingStatus.PENDING.getValue() + ", places_amount = ?, booking_time = ? WHERE id = ?";	
	private static final String ADD_DAY_SCHEDULE_QUERY = "INSERT INTO venue_schedule(venue_id, day, open_time, close_time) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_DAY_SCHEDULE_QUERY = "UPDATE venue_schedule set open_time = ?, close_time = ? WHERE day = ? AND venue_id = ?";
	private static final String GET_VENUE_SCHEDULE_QUERY = "SELECT v.day as day_id, v.open_time, v.close_time, w.name as day FROM venue_schedule v, week_days w WHERE w.id = v.day and venue_id = ?";		
	private static final String SWITCH_IN_SYSTEM_QUERY = "UPDATE venues set in_booking_system = ? WHERE id = ?";
	private static final String ADD_TABLE_QUERY = "INSERT INTO tables(venue_id, x_pos, y_pos, places, number, position_notes, photo_url) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";		
	private static final String SET_VENUE_PLAN_QUERY = "UPDATE venues set plan = ? WHERE id = ?";	
	private static final String VENUES_BY_CATEGORY_SQL = "SELECT id, name, category FROM venues ORDER BY category, name";
	private static final String UPDATE_VENUE_SQL = "UPDATE venues SET name = ?, phone = ?, address = ?, admin_user = ?, "
			+ "tables_amount = ?, icon_url = ?, open_time = ?, close_time = ?, cuisine = ?, has_wifi = ?, take_credit_cards = ?, "
			+ "has_outdoors_seats = ?, category = ?, admin_password = ?, avg_check = ? WHERE id = ?";
	private static final String GET_VENUE_PHOTOS_SQL = "SELECT id, url FROM venue_photos WHERE venue_id = ?";
	private static final String DELETE_VENUE_PHOTO_SQL = "DELETE FROM venue_photos WHERE id = ?";
	private static final String GET_VENUE_RATING_SQL = "SELECT AVG((mark_food + mark_service + mark_atmosphere + mark_price_quality)/4) as rating FROM reviews WHERE venue_id = ?";
	private static final String GET_AVG_PAYMENT_SQL = "SELECT FLOOR(AVG(SPENT_MONEY)) as avg_payment FROM bookings WHERE venue_id = ?";
	
	private static final String VENUE_LOADING_SQL = "SELECT tables_amount, free_tables_amount, free_tables_amount/tables_amount as loading FROM venues WHERE id = ?";
	private static final String VENUE_BOOKINGS_SQL = "SELECT b.id, b.user_id, b.visitor_contact_name, b.visitor_contact_phone, b.spent_money, "
			+ "b.booking_time, b.places_amount, b.notes, b.booking_created, b.table_no, bs.status FROM bookings b, booking_status bs "
			+ "where bs.id = b.status AND venue_id = ? ORDER BY b.booking_created desc";
	
	private static final String GET_ADMIN_PASSWORD_SQL = "SELECT admin_password FROM venues where id = ?";
	
	private static final String GET_REVIEWS_SQL = "SELECT r.mark_food, r.mark_service, r.mark_atmosphere, r.mark_price_quality, r.comments_good, r.comments_bad, r.created, u.name FROM reviews r, users u WHERE venue_id = ? and r.user_id = u.id";
	
	private static final String UPLOAD_VENUE_PHOTO_SQL = "INSERT INTO venue_photos(venue_id, url, delete_hash) VALUES(?, ?, ?)";
	
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
	
	public static List<Venue> getVenues(Double lat, Double lng, Integer limit, Boolean hasFreeTables, Boolean ratingOrder, 
			Boolean hasWifi, Boolean takeCreditCards, Boolean hasOutdoorsPlaces, String type, String cuisine) {		
		List<Venue> venues = new ArrayList<Venue>();
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = dataSource.getConnection();
			String query = VENUES_LIST_SQL;
			query += applyVenueFilters(hasFreeTables, hasWifi, takeCreditCards, hasOutdoorsPlaces, type, cuisine);	
			System.out.println("Get venues query:\n" + query);
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Venue v = new Venue(rs.getInt("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getDouble("latitude"),
						rs.getDouble("longitude"), rs.getString("category"), rs.getBoolean("has_free_seats"), rs.getString("icon_url"));
				v.setFreeTablesAmount(rs.getInt("free_tables_amount"));
				v.setTablesAmount(rs.getInt("tables_amount"));
				v.setOpenTime(rs.getString("open_time"));
				v.setCloseTime(rs.getString("close_time"));
				v.setCuisine(rs.getString("cuisine"));
				v.setHasWifi(rs.getBoolean("has_wifi"));
				v.setTakeCreditCards(rs.getBoolean("take_credit_cards"));
				v.setHasOutdoorsSeats(rs.getBoolean("has_outdoors_seats"));
				v.setAvgPayment(rs.getString("avg_check"));
				v.setRating(getVenueRating(con, v.getId()));
				if(lat != null && lng != null) {
					LocationUtil.calcDistance(v, lat, lng);
				}
				venues.add(v);
			}
			if(ratingOrder) {
				Collections.sort(venues, new VenueRatingComp());				
			} else if (lat != null && lng != null) {
				Collections.sort(venues, new VenueDistanceComp());
			}
			if(limit != null) {
				venues = venues.subList(0, limit);
			}
		} catch (SQLException e) { 
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		
		return venues;
	}
	
	/*
	 * Add filters to venue. 
	 */
	private static String applyVenueFilters(boolean hasFreeTables, 
			boolean hasWifi, boolean takeCreditCards, boolean hasOutdoorsPlaces, String type, String cuisine) {
		String filter = "";
		List<String> filters = new ArrayList<String>();
		if(hasFreeTables) {
			filters.add("has_free_seats = true");
		}
		if(hasWifi) {
			filters.add("has_wifi = true");
		}
		if(takeCreditCards) {
			filters.add("take_credit_cards = true");
		}
		if(hasOutdoorsPlaces) {
			filters.add("has_outdoors_seats = true");
		}
		if(type != null) {
			filters.add("category = '" + type + "'");
		}
		if(cuisine != null) {
			filters.add("cuisine = '" + cuisine + "'");
		}
		
		if(filters.size() > 0) {
			filter += " WHERE " + filters.get(0);
			for(int i = 1; i < filters.size(); i++) {
				filter += " AND " + filters.get(i);
			}
		}
		return filter;
	}		
	
	public static Map<String, Object> bookPlaces(int venue_id, String visitorName, String visitorPhone, Date bookingTime, byte places, String notes, String tableNumbers, Integer userId, String regId, String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		int qResult = 0;
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			Venue v = getVenueById(venue_id);
			if(v == null) {
				result.put("status", "failure");
				result.put("error", "There is no venue with id " + venue_id);
			}
			else if(userId == null && email.isEmpty()) {
				result.put("status", "failure");
				result.put("error", "For unregistred user bookings email must be specified!");
			}
			else {
				con = dataSource.getConnection();
				ps = con.prepareStatement(BOOK_QUERY);
				ps.setInt(1, venue_id);
				ps.setString(2, visitorName);
				ps.setString(3, visitorPhone);
				ps.setTimestamp(4, new Timestamp(bookingTime.getTime()));
				ps.setByte(5, places);
				ps.setString(6, notes);
				ps.setString(7, tableNumbers);
				if(userId != null) {
					ps.setInt(8, userId);
				} else {
					ps.setNull(8, Types.INTEGER);
				}
				ps.setString(9, regId);
				ps.setString(10, email);
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
					rs.close();
					ps.close();
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
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), Consts.STATUS_BY_CODE.get(rs.getInt("status")), rs.getString("notes"), rs.getTimestamp("booking_created"));
				String sTableNumbers = rs.getString("table_no");				
				List<Integer> tableNumbers = new ArrayList<Integer>();
				if(sTableNumbers != null && !sTableNumbers.isEmpty()) {
					for(String sNum : sTableNumbers.split(",")) {
						tableNumbers.add(Integer.valueOf(sNum));
					}				
				}
				booking.setTableNumbers(tableNumbers);
				booking.setUserId(rs.getInt("user_id"));

				booking.setSpentMoney(rs.getInt("spent_money"));
				
				int spentValid = rs.getInt("spent_valid");
				int venueSpent = rs.getInt("spent_money");
				int visitorSpent = rs.getInt("visitor_spent_money");
				booking.setSpentValid(getSpentValid(venueSpent, visitorSpent, spentValid));		

				booking.setRegId(rs.getString("reg_id"));
				booking.setEmail(rs.getString("email"));
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
			ps.setInt(2, venueId);
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
	
	public static Map<String, Object> getBookingHistory(int venueId, int page) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<HistoryEnrty> bookingHistory = new ArrayList<HistoryEnrty>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();	
			String query = GET_HISTORY_QUERY;
			// Limit the amount of returned records.			
			if(page > 0) {
				int offset = Consts.RECORDS_BY_PAGE * (page - 1);
				String limit = " LIMIT " + offset + "," + Consts.RECORDS_BY_PAGE;
				System.out.println(limit);
				query += limit;
			}
			ps = con.prepareStatement(query);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				HistoryEnrty he = new HistoryEnrty(rs.getInt("booking_id"), Consts.STATUS_BY_CODE.get(rs.getInt("new_status")), 
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
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), Consts.STATUS_BY_CODE.get(rs.getInt("status")), rs.getString("notes"), rs.getTimestamp("booking_created"));
				b.setUserId(rs.getInt("user_id"));
				// Check if booking status is pending, and booking was created more then 20 minutes ago. If true - Disable booking.
				long createdTime = b.getBookingCreated().getTime();
				if(Math.abs(nowTime-createdTime) >= Consts.TWENTY_MINUTES_MS && b.getStatus().equals("PENDING")) {					
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
			
	
	public static Map<String, Object> getBookingsForVenue(int venueId, String filterStatus, int page) {
		// TODO - Verify spend valid logic		
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {						
			List<Booking> bookings = new ArrayList<Booking>();
			con = dataSource.getConnection();
			String query = GET_BOOKINGS_QUERY;
			if(!filterStatus.equals("ALL")) {
				query += " AND status = " + Consts.CODE_BY_STATUS.get(filterStatus); 
			}
			// Limit the amount of returned records.			
			if(page > 0) {
				int offset = Consts.RECORDS_BY_PAGE * (page - 1);
				String limit = " LIMIT " + offset + "," + Consts.RECORDS_BY_PAGE;
				System.out.println(limit);
				query += limit;
			}
			ps = con.prepareStatement(query);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			long nowTime = new Date().getTime();
			while(rs.next()) {
				Booking b = new Booking(rs.getInt("id"), rs.getInt("venue_id"), rs.getString("visitor_contact_name"), rs.getString("visitor_contact_phone"),
						rs.getTimestamp("booking_time"), rs.getInt("places_amount"), Consts.STATUS_BY_CODE.get(rs.getInt("status")), rs.getString("notes"), rs.getTimestamp("booking_created"));				
				String sTableNumbers = rs.getString("table_no");
				List<Integer> bookedTables = new ArrayList<Integer>();
				if(sTableNumbers != null && !sTableNumbers.isEmpty()) {
					for(String sNum : sTableNumbers.split(",")) {
						bookedTables.add(Integer.valueOf(sNum));
					}
				}
				b.setUserId(rs.getInt("user_id"));
				b.setSpentMoney(rs.getInt("spent_money"));
				b.setVisitorSpentMoney(rs.getInt("visitor_spent_money"));
				
				int spentValid = rs.getInt("spent_valid");
				int spentMoney = rs.getInt("spent_money");
				int visitorSpentMoney = rs.getInt("visitor_spent_money");
				b.setSpentValid(getSpentValid(spentMoney, visitorSpentMoney, spentValid));							
				
				// Check if booking status is pending, and booking was created more then 20 minutes ago. If true - Disable booking.
				long createdTime = b.getBookingCreated().getTime();
				if(Math.abs(nowTime-createdTime) >= Consts.TWENTY_MINUTES_MS && b.getStatus().equals("PENDING")) {
					b.setStatus("REJECTED");
					updateStatus(b.getId(), BookingStatus.REJECTED.getValue());
				}
				if(b.getStatus().equals(filterStatus) || filterStatus.equals("ALL")) {					
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
			ps = con.prepareStatement(GET_VENUE_QUERY);
			ps.setInt(1, venueId);
			ps.setInt(2, venueId);
			ResultSet rs = ps.executeQuery();			
			if(rs.next()) {
				Venue venue = new Venue();
				String venueUid = rs.getString("unique_id");
				venue.setId(rs.getInt("id"));
				venue.setRating(rs.getFloat("rating"));
				venue.setFreeTablesAmount(rs.getInt("free_tables_amount"));
				venue.setUniqueId(venueUid);
				venue.setName(rs.getString("name"));
				venue.setPhone(rs.getString("phone"));
				venue.setAddress(rs.getString("address"));
				venue.setCity(rs.getString("city"));
				venue.setCountry(rs.getString("country"));
				venue.setLat(rs.getFloat("latitude"));
				venue.setLng(rs.getFloat("longitude"));
				venue.setCategory(rs.getString("category"));
				venue.setHasFreeSeats(rs.getBoolean("has_free_seats"));				
				venue.setTablesAmount(rs.getInt("tables_amount"));
				venue.setIconUrl(rs.getString("icon_url"));
				venue.setOpenTime(rs.getString("open_time"));
				venue.setCloseTime(rs.getString("close_time"));
				venue.setPlan(rs.getString("plan"));
				venue.setCuisine(rs.getString("cuisine"));
				venue.setHasWifi(rs.getBoolean("has_wifi"));
				venue.setTakeCreditCards(rs.getBoolean("take_credit_cards"));
				venue.setHasOutdoorsSeats(rs.getBoolean("has_outdoors_seats"));				
				List<VenuePhoto> photos = getVenuePhotos(con, venueUid);
				venue.setPhotos(photos);
				venue.setRating(getVenueRating(con, venueId));
				venue.setAvgPayment(rs.getString("avg_check"));
				venue.setReviewsAmount(rs.getInt("reviews_amount"));
				result.put("status", "success");
				result.put("venue", venue);
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
	
	public static Map<String, List<Venue>> getGroupedVenues() {
		Map<String, List<Venue>> result = new HashMap<String, List<Venue>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(VENUES_BY_CATEGORY_SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String category = rs.getString("category");
				Venue v = new Venue();
				v.setId(rs.getInt("id"));
				v.setName(rs.getString("name"));
				if(result.containsKey(category)) {
					result.get(category).add(v);
				} else {
					List<Venue> venuesOfCategory = new ArrayList<Venue>();
					venuesOfCategory.add(v);
					result.put(category, venuesOfCategory);
				}
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}	
	
	public static Venue getVenueForEdit(int venueId) {
		Venue venue = new Venue();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_VENUE_QUERY);
			ps.setInt(1, venueId);
			ps.setInt(2, venueId);
			ResultSet rs = ps.executeQuery();					
			if(rs.next()) {
				String venueUid = rs.getString("unique_id");
				venue.setId(rs.getInt("id"));
				venue.setRating(rs.getFloat("rating"));
				venue.setFreeTablesAmount(rs.getInt("free_tables_amount"));
				venue.setUniqueId(venueUid);
				venue.setName(rs.getString("name"));
				venue.setPhone(rs.getString("phone"));
				venue.setAddress(rs.getString("address"));
				venue.setCity(rs.getString("city"));
				venue.setCountry(rs.getString("country"));
				venue.setLat(rs.getFloat("latitude"));
				venue.setLng(rs.getFloat("longitude"));
				venue.setCategory(rs.getString("category"));
				venue.setHasFreeSeats(rs.getBoolean("has_free_seats"));
				venue.setAdminUser(rs.getString("admin_user"));
				venue.setInBookingSystem(rs.getBoolean("in_booking_system"));
				venue.setTablesAmount(rs.getInt("tables_amount"));
				venue.setIconUrl(rs.getString("icon_url"));
				venue.setOpenTime(rs.getString("open_time"));
				venue.setCloseTime(rs.getString("close_time"));
				venue.setPlan(rs.getString("plan"));
				venue.setCuisine(rs.getString("cuisine"));
				venue.setHasWifi(rs.getBoolean("has_wifi"));
				venue.setTakeCreditCards(rs.getBoolean("take_credit_cards"));
				venue.setHasOutdoorsSeats(rs.getBoolean("has_outdoors_seats"));
				venue.setAdminPassword(rs.getString("admin_password"));
				venue.setAvgPayment(rs.getString("avg_check"));
				List<VenuePhoto> photos = getVenuePhotos(con, venueUid);
				venue.setPhotos(photos);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return venue;
	}
	
	public static Map<String, Object> updateVenue(int venueId, String name, String phone, String address, 
			String adminUser, int tablesAmount, String iconUrl, String openTime, 
			String closeTime, String cuisine, boolean hasWifi, boolean takeCreditCards, boolean hasOutdoorsSeats, String category, String adminPassword, String avgPayment) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(UPDATE_VENUE_SQL);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, address);			
			ps.setString(4, adminUser);
			ps.setInt(5, tablesAmount);
			ps.setString(6, iconUrl);
			ps.setString(7, openTime);
			ps.setString(8, closeTime);
			ps.setString(9, cuisine);
			ps.setBoolean(10, hasWifi);
			ps.setBoolean(11, takeCreditCards);
			ps.setBoolean(12, hasOutdoorsSeats);
			ps.setString(13, category);
			ps.setString(14, adminPassword);
			ps.setString(15, avgPayment);
			ps.setInt(16, venueId);
			ps.executeUpdate();
			result.put("status", "success");
		} catch(SQLException e) {
			e.printStackTrace();
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<VenuePhoto> getVenuePhotos(Connection con, String venueUid) throws SQLException {
		PreparedStatement ps = con.prepareStatement(GET_VENUE_PHOTOS_SQL);;
		List<VenuePhoto> photos = new ArrayList<VenuePhoto>();		
		ps.setString(1, venueUid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			photos.add(new VenuePhoto(rs.getInt("id"), rs.getString("url")));
		}
		rs.close();
		ps.close();
		return photos;
	}
	
	public static Float getVenueRating(Connection con, int venueId) throws SQLException {
		Float rating = null;
		PreparedStatement ps = con.prepareStatement(GET_VENUE_RATING_SQL);
		ps.setInt(1, venueId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			rating = rs.getFloat("rating");
		}
		return rating;
	}
	
	public static Integer getVenueAvgPayment(Connection con, int venueId) throws SQLException {
		Integer avgPayment = null;
		PreparedStatement ps = con.prepareStatement(GET_AVG_PAYMENT_SQL);
		ps.setInt(1, venueId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			avgPayment = rs.getInt("avg_payment");
		}
		return avgPayment;
	}
	
	public static boolean deleteVenuePhoto(int photoId) {
		boolean result;
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			con = dataSource.getConnection();
			ps = con.prepareStatement(DELETE_VENUE_PHOTO_SQL);
			ps.setInt(1, photoId);
			ps.executeUpdate();
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}		

	public static Map<String, Object> checkAdmin(String adminUser, String adminPassword, int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			con = dataSource.getConnection();
			ps = con.prepareStatement("SELECT admin_user, admin_password FROM venues WHERE id = ?");
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(!adminUser.equals(rs.getString("admin_user"))) {
					result.put("status", "failure");
					result.put("error", "Incorrect admin login");
				} else if(!adminPassword.equals(rs.getString("admin_password"))) {
					result.put("status", "failure");
					result.put("error", "Incorrect admin password");
				} else {
					result.put("status", "success");
				}
			} else {
				result.put("status", "failure");
				result.put("error", "No venue with id: " + venueId);
			}
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();			
		} finally {
			closeConnection(con, ps);
		}
		return result;		
	}
	
	public static Map<String, Object> setFreeTables(int venueId, int freeTables) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			con = dataSource.getConnection();
			ps = con.prepareStatement("UPDATE venues SET free_tables_amount = ? WHERE id = ?");
			ps.setInt(1, freeTables);
			ps.setInt(2, venueId);
			ps.executeUpdate();
			result.put("status", "success");
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();			
		} finally {
			closeConnection(con, ps);
		}
		return result;	
	}
	
	public static Map<String, Object> getVenueStatus(int venueId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			con = dataSource.getConnection();
			ps = con.prepareStatement(VENUE_LOADING_SQL);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result.put("status", "success");
				result.put("tables_amount", rs.getInt("tables_amount"));
				result.put("free_tables_amount", rs.getInt("free_tables_amount"));
				result.put("loading", rs.getFloat("loading"));			
				
				ps.close();
				ps = con.prepareStatement(VENUE_BOOKINGS_SQL);
				ps.setInt(1, venueId);
				rs = ps.executeQuery();
				List<Map<String, Object>> bookings = new ArrayList<Map<String,Object>>();			
				while(rs.next()) {
					Map<String, Object> booking = new HashMap<String, Object>();
					booking.put("id", rs.getInt("id"));
					booking.put("user_id", rs.getInt("user_id"));
					booking.put("visitor_contact_name", rs.getString("visitor_contact_name"));
					booking.put("visitor_contact_phone", rs.getString("visitor_contact_phone"));
					booking.put("spent_money", rs.getInt("spent_money"));
					booking.put("booking_time", rs.getTimestamp("booking_time"));
					booking.put("places_amount", rs.getInt("places_amount"));
					booking.put("notes", rs.getString("notes"));
					booking.put("booking_created", rs.getTimestamp("booking_created"));
					booking.put("booked_table_numbers", rs.getString("table_no"));
					booking.put("status", rs.getString("status"));
					bookings.add(booking);
				}
				result.put("bookings", bookings);
			} else {
				result.put("status", "failure");
				result.put("error", "No venue with id: " + venueId);
			}
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();			
		} finally {
			closeConnection(con, ps);
		}
		return result;	
	}
	
	public static Map<String, Object> checkAdminPassword(int venueId, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {	
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_ADMIN_PASSWORD_SQL);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			if(rs.next() && rs.getString("admin_password").equals(password)) {
				result.put("status", "success");				
			} else {
				result.put("status", "failure");
				result.put("error", "Incorrect venue id or admin password for venue");
			}
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<Map<String, Object>> getReviews(int venueId) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_REVIEWS_SQL);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();			
			while(rs.next()) {
				Map<String, Object> review = new HashMap<String, Object>();
				review.put("mark_food", rs.getFloat("mark_food"));
				review.put("mark_service", rs.getFloat("mark_service"));
				review.put("mark_atmosphere", rs.getFloat("mark_atmosphere"));
				review.put("mark_price_quality", rs.getFloat("mark_price_quality"));
				review.put("comments_good", rs.getString("comments_good"));
				review.put("comments_bad", rs.getString("comments_bad"));
				review.put("create_date", rs.getString("created"));
				review.put("username", rs.getString("name"));
				result.add(review);
			}
			rs.close();
		}  catch(SQLException e) {
			Map<String, Object> errorResult = new HashMap<String, Object>();
			errorResult.put("status", "failure");
			errorResult.put("error", e.getMessage());
			e.printStackTrace();			
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	 
	public static Map<String, Object> uploadVenuePhotos(int venueId, List<Map<String, String>> photos) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		int photosUploaded = 0;
		try {
			Venue venue = getVenueById(venueId);
			String uniqueId = venue.getUniqueId();
			con = dataSource.getConnection();
			ps = con.prepareStatement(UPLOAD_VENUE_PHOTO_SQL);
			for(Map<String, String> photo: photos) {
				ps.setString(1, uniqueId);
				ps.setString(2, photo.get("imageUrl"));
				ps.setString(3, photo.get("deleteHash"));
				ps.executeUpdate();
				++photosUploaded;
			}
			result.put("status", "success");
			result.put("photosUploaded", photosUploaded);
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();			
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static int getSpentValid(int venueSpent, int visitorSpent, int spentValid) {
		/**
		 * Spent valid statuses:
		 * 0 - No Venue sum, no visitor sum
		 * 1 - No visitor sum
		 * 2 - Visitor sum is checking
		 * 3 - Visitor sum is correct
		 * 4 - Visitor sum is incorrect		
		 */
		int result = 0;
		if(venueSpent == 0 && visitorSpent == 0) {
			result = 0;
		} else if(venueSpent > 0 && visitorSpent == 0) {
			result = 1;
		} else if(venueSpent > 0 && visitorSpent > 0 && spentValid == 0) {
			result = 2;
		} else if(venueSpent > 0 && visitorSpent > 0 && spentValid == 1) {
			result = 3;
		} else if(venueSpent > 0 && visitorSpent > 0 && spentValid == 2) {
			result = 4;
		}
		return result;
	}
}
