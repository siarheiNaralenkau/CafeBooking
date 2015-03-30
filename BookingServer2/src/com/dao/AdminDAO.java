package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.beans.Booking;
import com.constants.Consts;

public class AdminDAO {
	private static DataSource dataSource;
	
	private static final String ADD_DEVICE_SQL = "INSERT INTO admin_devices(venue_id, registration_id) VALUES(?, ?)";
	private static final String GET_REG_ID_SQL = "SELECT registration_id FROM admin_devices WHERE venue_id = ?";
	private static final String CHECK_DEVICE_PRESENSE_SQL = "SELECT * FROM admin_devices WHERE venue_id = ? AND registration_id = ?";	
	
	private static final String BOOKING_STATS_SQL = "SELECT v.id, v.name," 
			+ " (select count(*) from bookings where venue_id = v.id and status=5 and booking_time BETWEEN ? and ?) as successfull_bookings,"
			+ " (select count(*) from bookings where venue_id = v.id and status=6 and booking_time BETWEEN ? and ?) as expired_bookings,"
			+ " (select sum(spent_money) from bookings where venue_id = v.id and booking_time BETWEEN ? and ?) as check_sum"
			+ " FROM venues v order by successfull_bookings desc";
	
	private static final String BOOKING_STATS_UNREG_SQL = "SELECT id, visitor_contact_name, visitor_contact_phone, count(*) as bookings_count, sum(spent_money) as money_spent from bookings" 
			+ " where venue_id = ? and user_id IS NULL and booking_time > ? and booking_time < ? group by visitor_contact_name";	
	
	private static final String BOOKINGS_FOR_USER_SQL = "SELECT id, DATE(booking_time) as booking_date, TIME(booking_time) as booking_time, spent_money, visitor_spent_money, notes" 
			+ " from bookings where venue_id = ? and user_id = ? and booking_time > ? and booking_time < ?";	
	
	private static final String BOOKINGS_FOR_UNREG_USER_SQL = "SELECT id, DATE(booking_time) as booking_date, TIME(booking_time) as booking_time, spent_money, visitor_spent_money, notes" 
			+ " from bookings where venue_id = ? and visitor_contact_name = ? and booking_time > ? and booking_time < ?";
	
	private static final String RESOLVE_BOOKING_AGREE_SQL = "UPDATE bookings SET spent_money = ?, spent_valid = 1 WHERE id = ?";
	private static final String RESOLVE_BOOKING_DISAGREE_SQL = "UPDATE bookings SET spent_valid = 1 WHERE id = ?";
	private static final String UPDATE_USER_BONUS = "UPDATE users SET bonus_scores = bonus_scores + ? WHERE id = ?";
	
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
	
	public static Map<String, Object> addDeviceToNotify(int venueId, String registrationId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {			
			con = dataSource.getConnection();
			
			ps = con.prepareStatement(CHECK_DEVICE_PRESENSE_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, registrationId);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				ps.close();
				ps = con.prepareStatement(ADD_DEVICE_SQL);
				ps.setInt(1, venueId);
				ps.setString(2, registrationId);
				ps.executeUpdate();
				result.put("status", "success");
			} else {
				result.put("status", "failure");
				result.put("error", "Device with such registration ID already exists in database");
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
	
	public static List<String> getRegIdsForVenue(int venueId) {
		List<String> result = new ArrayList<String>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_REG_ID_SQL);
			ps.setInt(1, venueId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(rs.getString("registration_id"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<Map<String, Object>> getBookingStatisctics(String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOKING_STATS_SQL);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.setString(5, startDate);
			ps.setString(6, endDate);
			ResultSet rs = ps.executeQuery();			
			while(rs.next()) {
				int successVisits = rs.getInt("successfull_bookings");
				int expiredVisits = rs.getInt("expired_bookings");
				double percentUnvisited = 0;
				if(expiredVisits != 0) {
					percentUnvisited = ((double)expiredVisits / (double)(successVisits + expiredVisits) ) * 100;
				}
				int checkSum = rs.getInt("check_sum");
				int venueDebt = checkSum * 5 / 100;
				Map<String, Object> venueStats = new HashMap<String, Object>();
				venueStats.put("id", rs.getInt("id"));
				venueStats.put("name", rs.getString("name"));				
				venueStats.put("successVisits", successVisits);
				venueStats.put("percentUnvisited", String.format("%.2f", percentUnvisited) + "%");
				venueStats.put("checkSum", checkSum);
				venueStats.put("venueDebt", venueDebt);
				result.add(venueStats);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<Map<String, Object>> getBookingsForUnregUsers(int venueId, String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOKING_STATS_UNREG_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {				
				Map<String, Object> userData = new HashMap<String, Object>();
				userData.put("id", rs.getInt("id"));
				userData.put("name", rs.getString("visitor_contact_name"));
				userData.put("phone", rs.getString("visitor_contact_phone"));
				userData.put("bookingsCount", rs.getInt("bookings_count"));
				userData.put("spentMoney", rs.getInt("money_spent"));
				result.add(userData);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<Map<String, Object>> getBookingsForUser(int venueId, int userId, String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String userName = UserDAO.getUserById(userId).getName();
			Map<String, Object> uName = new HashMap<String, Object>();
			uName.put("userName", userName);
			result.add(uName);
			
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOKINGS_FOR_USER_SQL);
			ps.setInt(1, venueId);
			ps.setInt(2, userId);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, Object> bookingData = new HashMap<String, Object>();
				bookingData.put("id", rs.getInt("id"));
				bookingData.put("date", rs.getString("booking_date"));
				bookingData.put("time", rs.getString("booking_time"));
				int spentMoney = rs.getInt("spent_money");
				int bonusScores = spentMoney/Consts.BONUS_EXCHANGE_SCORE;
				bookingData.put("venue_sum", spentMoney);
				bookingData.put("user_sum", rs.getInt("visitor_spent_money"));
				bookingData.put("bonus_scores", bonusScores);
				bookingData.put("notes", rs.getString("notes"));
				result.add(bookingData);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static List<Map<String, Object>> getBookingsUnregUser(int venueId, int bookingId, String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Connection con = null;
		PreparedStatement ps = null;				
		try {
			Booking booking = VenuesDAO.getBookingById(bookingId);
			String userName = booking.getVisitorName();
			Map<String, Object> uName = new HashMap<String, Object>();
			uName.put("userName", userName);
			result.add(uName);
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOKINGS_FOR_UNREG_USER_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, userName);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, Object> bookingData = new HashMap<String, Object>();
				bookingData.put("id", rs.getInt("id"));
				bookingData.put("date", rs.getString("booking_date"));
				bookingData.put("time", rs.getString("booking_time"));
				int spentMoney = rs.getInt("spent_money");				
				bookingData.put("venue_sum", spentMoney);
				bookingData.put("user_sum", rs.getInt("visitor_spent_money"));				
				bookingData.put("notes", rs.getString("notes"));
				bookingData.put("userName", userName);
				result.add(bookingData);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static void resolveSumConflict(int bookingId, int newCheckSum, String resolution) {
		Booking booking = VenuesDAO.getBookingById(bookingId);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			if(resolution.equals("agree")) {
				// Update booking venue sum and bonus scores here, set spent_valid to true.
				int oldCheckSum = booking.getSpentMoney();
				int userId = booking.getUserId();
				int bonusScoreChange = (newCheckSum - oldCheckSum)/Consts.BONUS_EXCHANGE_SCORE;
				ps = con.prepareStatement(RESOLVE_BOOKING_AGREE_SQL);
				ps.setInt(1, newCheckSum);
				ps.setInt(2, bookingId);
				ps.executeUpdate();
				UserDAO.addScores(userId, booking.getVenueId(), bonusScoreChange);
			} else {
				// Only set spent_valid to true(resolve conflict).
				ps = con.prepareStatement(RESOLVE_BOOKING_DISAGREE_SQL);
				ps.setInt(1, bookingId);
				ps.executeUpdate();
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}
}
