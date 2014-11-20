package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookingsDAO {
	private static DataSource dataSource;
	
	private static final String SET_BOOKING_SPENT_SQL = "UPDATE bookings SET status=6, spent_money = ? WHERE id = ?";
	private static final String SET_VISITOR_SPENT_SQL = "UPDATE bookings SET visitor_spent_money = ? WHERE id = ?";
	
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
	
	public static Map<String, Object> setBookingSpent(int spentMoney, int bookingId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(SET_BOOKING_SPENT_SQL);
			ps.setInt(1, spentMoney);
			ps.setInt(2, bookingId);
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
}
