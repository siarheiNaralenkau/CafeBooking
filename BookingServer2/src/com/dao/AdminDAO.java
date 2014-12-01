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

public class AdminDAO {
	private static DataSource dataSource;
	
	private static final String ADD_DEVICE_SQL = "INSERT INTO admin_devices(venue_id, registration_id) VALUES(?, ?)";
	private static final String GET_REG_ID_SQL = "SELECT registration_id FROM admin_devices WHERE venue_id = ?";
	
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
			ps = con.prepareStatement(ADD_DEVICE_SQL);
			ps.setInt(1, venueId);
			ps.setString(2, registrationId);
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
}
