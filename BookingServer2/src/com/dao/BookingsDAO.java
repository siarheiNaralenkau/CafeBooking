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
import com.constants.BookingStatus;
import com.constants.Consts;

public class BookingsDAO {
	private static DataSource dataSource;
	
	private static final String SET_BOOKING_SPENT_SQL = "UPDATE bookings SET status=?, spent_money = ? WHERE id = ?";
	private static final String SET_VISITOR_SPENT_SQL = "UPDATE bookings SET visitor_spent_money = ? WHERE id = ?";
	
	private static final String GET_BOOKINGS_BY_STATUS_SQL = "SELECT status, COUNT(*) AS amount FROM bookings WHERE venue_id = ? and booking_created >= ? and booking_created <= ? GROUP BY status";
	private static final String GET_SPENT_STATS_SQL = "select MIN(spent_money) as min_spent, MAX(spent_money) as max_spent, AVG(spent_money) as avg_spent, SUM(spent_money) as sum_spent FROM bookings WHERE venue_id=? "
			+ "and spent_money is not NULL and spent_money != 0 and booking_created >= ? and booking_created <= ?";	
	
	private static final String BOOKINGS_REGISTRED = "Select count(*) as bookings_count, b.user_id, u.name, u.surname, u.email, u.phone, u.bonus_scores" 
			+ " from bookings b, users u where b.venue_id = ? and b.user_id = u.id and b.booking_created > ? and b.booking_created < ?"
			+ " group by b.user_id";
	private static final String SPENT_MONEY_SQL = "SELECT sum(spent_money) as spent from bookings where user_id = ? and"
			+ " (spent_valid=1 OR spent_valid is null) and venue_id=? and booking_time > ? and booking_time < ?";
	private static final String CLAIMS_ALL_SQL = "SELECT count(*) as claims_all from bookings where user_id = ? and spent_valid is not null and venue_id=?"
			+ " and booking_time > ? and booking_time < ?";
	private static final String CLAIMS_ACTIVE_SQL = "SELECT count(*) as claims_active from bookings where user_id = ? and spent_valid = 0 and venue_id=?"
			+ " and booking_time > ? and booking_time < ?";
	
	private static final String DELETE_VISITOR_BOOKING_SQL = "DELETE FROM bookings WHERE id = ?";
	
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
	
	private static int getSpentMoney(int venueId, String dateFrom, String dateTo, int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SPENT_MONEY_SQL);
			ps.setInt(1, userId);
			ps.setInt(2, venueId);
			ps.setString(3, dateFrom);
			ps.setString(4, dateTo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("spent");
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	private static int getClaimsAll(int venueId, String dateFrom, String dateTo, int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(CLAIMS_ALL_SQL);
			ps.setInt(1, userId);
			ps.setInt(2, venueId);
			ps.setString(3, dateFrom);
			ps.setString(4, dateTo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("claims_all");
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	private static int getClaimsActive(int venueId, String dateFrom, String dateTo, int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(CLAIMS_ACTIVE_SQL);
			ps.setInt(1, userId);
			ps.setInt(2, venueId);
			ps.setString(3, dateFrom);
			ps.setString(4, dateTo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("claims_active");
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}	
	
	public static List<Map<String, Object>> getBookingsForRegistredUsers(int venueId, String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(BOOKINGS_REGISTRED);
			ps.setInt(1, venueId);
			ps.setString(2, startDate + " 00:00");
			ps.setString(3, endDate + " 23:59");
			ResultSet rs = ps.executeQuery();			
			while(rs.next()) {
				Map<String, Object> userInfo = new HashMap<String, Object>();
				int userId = rs.getInt("user_id");
				userInfo.put("id", userId);
				userInfo.put("name", rs.getString("name"));
				userInfo.put("surname", rs.getString("surname"));
				userInfo.put("phone", rs.getString("phone"));
				userInfo.put("email", rs.getString("email"));
				userInfo.put("bookingsCount", rs.getInt("bookings_count"));
				userInfo.put("bonusScores", rs.getInt("bonus_scores"));
				userInfo.put("spentMoney", getSpentMoney(venueId, startDate, endDate, userId));
				userInfo.put("claimsAll", getClaimsAll(venueId, startDate, endDate, userId));
				userInfo.put("claimsActive", getClaimsActive(venueId, startDate, endDate, userId));
				result.add(userInfo);
			}			
			rs.close();
		} catch(SQLException e) {			
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> setBookingSpent(int spentMoney, int bookingId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			int newStatus;
			ps = con.prepareStatement(SET_BOOKING_SPENT_SQL);
			if(spentMoney == 0) {
				newStatus = 6;
				
			} else {
				newStatus = 5;				
			}
			ps.setInt(1, newStatus);
			ps.setInt(2, spentMoney);
			ps.setInt(3, bookingId);
			ps.executeUpdate();
			
			VenuesDAO.writeHistory(con, bookingId, newStatus);
			
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
	
	public static Map<String, Object> setVisitorSpent(int spentMoney, int bookingId) {	
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = dataSource.getConnection();												
			ps = con.prepareStatement(SET_VISITOR_SPENT_SQL);
			ps.setInt(1, spentMoney);			
			ps.setInt(2, bookingId);
			ps.executeUpdate();
			result.put("status", "success");
		}  catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> getBookingStats(int venueId, String startDate, String endDate) { 		
		Map<String, Object> result = new HashMap<String, Object>();
				
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		int bookingsCount = 0;		
		try {
			con = dataSource.getConnection();			
						
			// Get booking info grouped by status.			
			ps = con.prepareStatement(GET_BOOKINGS_BY_STATUS_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, startDate + " 00:00");
			ps.setString(3, endDate + " 23:59");
			rs = ps.executeQuery();			
			int approved = 0;
			int pending = 0;
			int rejected = 0;
			int cancelled = 0;
			int expired = 0;
			int completed = 0;			
			while(rs.next()) {
				int status = rs.getInt("status");
				if(status == BookingStatus.PENDING.getValue()) {
					pending = rs.getInt("amount");
				}
				if(status == BookingStatus.APPROVED.getValue()) {
					approved = rs.getInt("amount");
				}
				if(status == BookingStatus.CANCELLED.getValue()) {
					cancelled = rs.getInt("amount");
				}
				if(status == BookingStatus.REJECTED.getValue()) {
					rejected = rs.getInt("amount");
				}
				if(status == BookingStatus.EXPIRED.getValue()) {
					expired = rs.getInt("amount");
				}
				if(status == BookingStatus.CLOSED.getValue()) {
					completed = rs.getInt("amount");
				}								
			}
			bookingsCount = pending + approved + cancelled + rejected + expired + completed;
			result.put("bookingsCreated", bookingsCount);
			result.put("bookingsPending", pending);
			result.put("bookingsApproved", approved);
			result.put("bookingsRejected", rejected);
			result.put("bookingsCancelled", cancelled);
			result.put("bookingsExpired", expired);
			result.put("bookingsCompleted", completed);
			double percentUnvisited = 0;
			if(expired != 0 && bookingsCount != 0) {
				percentUnvisited = ((double)expired / (double)bookingsCount) * 100;
			}
			result.put("percentUnvisited", String.format("%.2f", percentUnvisited) + "%");
			
			rs.close();
			ps.close();
			
			// Get booking check info.
			ps = con.prepareStatement(GET_SPENT_STATS_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, startDate + " 00:00");
			ps.setString(3, endDate + " 23:59");
			rs = ps.executeQuery();
			rs.next();
			int minSpent = rs.getInt("min_spent");
			int maxSpent = rs.getInt("max_spent");
			int avgSpent = rs.getInt("avg_spent");
			int sumSpent = rs.getInt("sum_spent");
			int dept5 = sumSpent/20;
			result.put("minCheck", minSpent > 0 ? minSpent : "");
			result.put("maxCheck", maxSpent > 0 ? maxSpent : "");
			result.put("avgCheck", avgSpent > 0 ? avgSpent : "");
			result.put("sumSpent", sumSpent > 0 ? sumSpent : "");
			result.put("dept5", dept5 > 0 ? dept5 : "");
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
		
	public static Map<String, Object> deleteVisitorBooking(int bookingId) {		
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			Booking booking = VenuesDAO.getBookingById(bookingId);
			if(booking != null) {
				if(booking.getStatus().equals(Consts.STATUS_BY_CODE.get(BookingStatus.CANCELLED.getValue())) || 
						booking.getStatus().equals(Consts.STATUS_BY_CODE.get(BookingStatus.REJECTED.getValue()))) {
					con = dataSource.getConnection();												
					ps = con.prepareStatement(DELETE_VISITOR_BOOKING_SQL);
					ps.setInt(1, bookingId);
					ps.executeUpdate();
					result.put("status", Consts.STATUS_SUCCESS);
					result.put("deletedBookingId", bookingId);
				} else {			
					result.put("status", Consts.STATUS_FAILURE);
					result.put("error", "Only cancelled or rejected bookings can be deleted by visitor");
				}
			} else {
				result.put("status", Consts.STATUS_FAILURE);
				result.put("error", String.format("Booking with id = %d does not exists", bookingId));
			}
		}  catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
}
