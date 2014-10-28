package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	
	private static DataSource dataSource;
	
	private static final String ADD_USER_SQL = "INSERT INTO users(name, surname, email, phone, password, bonus_scores) VALUES(?, ?, ?, ?, ?, 100)";
	private static final String CHECK_USER_PRESENSE_SQL = "SELECT * FROM users WHERE email = ?";
	private static final String CHANGE_PASSWORD_SQL = "UPDATE users SET password = ? WHERE email = ?";
	private static final String GET_PASSWORD_SQL = "SELECT password from users where email = ?";
	private static final String CHECK_PASSWORD_SQL = "SELECT id, password FROM users WHERE email = ?";
	
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
	
	public static Map<String, Object> registerUser(String name, String surname, String email, String phone, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {	
			con = dataSource.getConnection();
			boolean isUserExists = checkUser(con, email);
			if(isUserExists) {
				result.put("status", "failure");
				result.put("error", "User with email " + email + " already exists");				
			} else {
				ps = con.prepareStatement(ADD_USER_SQL);
				ps.setString(1, name);
				ps.setString(2, surname);
				ps.setString(3, email);
				ps.setString(4, phone);
				ps.setString(5, password);
				int updated = ps.executeUpdate();
				System.out.println("Updated: " + updated);
				result.put("status", "success");									
			}
		} catch (SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> changePassword(String email, String newPassword) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(CHANGE_PASSWORD_SQL);
			ps.setString(1, newPassword);
			ps.setString(2, email);
			int updated = ps.executeUpdate();
			System.out.println("Updated: " + updated);
			result.put("status", "success");
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> getPassword(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_PASSWORD_SQL);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result.put("status", "success");
				result.put("password", rs.getString("password"));
			} else {
				result.put("status", "failure");
				result.put("error", "Пользователь " + email + " не существует");
			}
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(CHECK_PASSWORD_SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String storedPassword = rs.getString("password");
				if(storedPassword.equals(password)) {
					result.put("status", "success");
					result.put("userId", rs.getInt("id"));
					result.put("message", "Вы успешно авторизованы в системе");
				} else {
					result.put("status", "failure");
					result.put("error", "Неверный пароль для пользователя " + username);
				}
			} else {
				result.put("status", "failure");
				result.put("error", "Пользователь " + username + " не существует");
			}
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	private static boolean checkUser(Connection con, String email) throws SQLException {
		boolean userExists = false;
		PreparedStatement ps = con.prepareStatement(CHECK_USER_PRESENSE_SQL);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			userExists = true;
		}
		ps.close();
		return userExists;
	}
	
}
