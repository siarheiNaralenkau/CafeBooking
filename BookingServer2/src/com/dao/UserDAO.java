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

public class UserDAO {
	
	private static DataSource dataSource;
	
	private static final String ADD_USER_SQL = "INSERT INTO users(name, surname, email, phone, password, bonus_scores) VALUES(?, ?, ?, ?, ?, 100)";
	private static final String CHECK_USER_PRESENSE_SQL = "SELECT * FROM users WHERE email = ?";
	private static final String CHANGE_PASSWORD_SQL = "UPDATE users SET password = ? WHERE email = ?";
	private static final String GET_PASSWORD_SQL = "SELECT password from users where email = ?";
	private static final String CHECK_PASSWORD_SQL = "SELECT id, password FROM users WHERE email = ?";
	private static final String ADD_BONUS_SCORES_SQL = "UPDATE users set bonus_scores = bonus_scores + ? WHERE id = ?";
	private static final String REMOVE_BONUS_SCORES_SQL = "UPDATE users set bonus_scores = bonus_scores - ? WHERE id = ?";
	private static final String CREATE_REVIEW_SQL = "INSERT INTO reviews(venue_id, user_id, mark_food, mark_service, mark_atmosphere, mark_price_quality, "
			+ "comments_good, comments_bad) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_USER_DETAILS_SQL = "SELECT name, surname, email, phone, bonus_scores FROM users WHERE id = ?";
	private static final String GET_USER_BOOKINGS_SQL = "SELECT v.name as venue_name, b.visitor_contact_name, b.visitor_contact_phone, "
			+ "b.spent_money, b.booking_time, b.places_amount, bs.status as booking_status, b.notes, b.booking_created, "
			+ "b.table_no, b.visitor_spent_money from bookings b, venues v, booking_status bs WHERE user_id = ? and b.venue_id = v.id and b.status = bs.id";
	private static final String UPDATE_BONUS_HISTORY = "INSERT INTO bonus_history(user_id, venue_id, scores_change, change_time) VALUES(?, ?, ?, now())";
	private static final String GET_BONUS_HISTORY_SQL = "SELECT bh.scores_change, bh.change_time, v.name FROM bonus_history bh, venues v "
			+ "WHERE bh.venue_id = v.id AND bh.user_id = ? ORDER BY bh.change_time DESC";
	
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
	
	public static Map<String, Object> addScores(int userId, int venueId, int scores) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(ADD_BONUS_SCORES_SQL);
			ps.setInt(1, scores);
			ps.setInt(2, userId);
			ps.executeUpdate();			
			result.put("status", "success");
			updateBonusHistory(con, userId, venueId, scores);
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> removeScores(int userId, int venueId, int scores) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(REMOVE_BONUS_SCORES_SQL);
			ps.setInt(1, scores);
			ps.setInt(2, userId);
			ps.executeUpdate();			
			result.put("status", "success");
			updateBonusHistory(con, userId, venueId, -scores);
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> createReview(int venueId, int userId, float markFood, float markService, float markAtmosphere, float markPriceQuality,
			String comentsGood, String comentsBad) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();	
			ps = con.prepareStatement(CREATE_REVIEW_SQL);
			ps.setInt(1, venueId);
			ps.setInt(2, userId);
			ps.setFloat(3, markFood);
			ps.setFloat(4, markService);
			ps.setFloat(5, markAtmosphere);
			ps.setFloat(6, markPriceQuality);
			ps.setString(7, comentsGood);
			ps.setString(8, comentsBad);
			ps.executeUpdate();
			
			// Update venue rating			
			result.put("status", "success");
		} catch(SQLException e) {			
			result.put("status", "failure");
			result.put("error", e.getMessage());
		} finally {
			closeConnection(con, ps);
		}
		return result;
	}
	
	public static Map<String, Object> getUserDetails(int userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_USER_DETAILS_SQL);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result.put("name", rs.getString("name"));
				result.put("surname", rs.getString("surname"));
				result.put("email", rs.getString("email"));
				result.put("phone", rs.getString("phone"));
				result.put("bonus_scores", rs.getInt("bonus_scores"));				
				ps.close();
				List<Map<String, Object>> userBookings = new ArrayList<Map<String, Object>>();
				ps = con.prepareStatement(GET_USER_BOOKINGS_SQL);				
				ps.setInt(1, userId);
				ResultSet rsBookings = ps.executeQuery();
				while(rsBookings.next()) {
					Map<String, Object> bookingData = new HashMap<String, Object>();
					bookingData.put("venue_name", rsBookings.getString("venue_name"));
					bookingData.put("visitor_contact_name", rsBookings.getString("visitor_contact_name"));
					bookingData.put("visitor_contact_phone", rsBookings.getString("visitor_contact_phone"));
					bookingData.put("spent_money", rsBookings.getInt("spent_money"));
					bookingData.put("booking_time", rsBookings.getTimestamp("booking_time"));
					bookingData.put("places_amount", rsBookings.getInt("places_amount"));
					bookingData.put("booking_status", rsBookings.getString("booking_status"));
					bookingData.put("notes", rsBookings.getString("notes"));
					bookingData.put("booking_created", rsBookings.getTimestamp("booking_created"));
					bookingData.put("table_no", rsBookings.getString("table_no"));
					bookingData.put("visitor_spent_money", rsBookings.getInt("visitor_spent_money"));
					userBookings.add(bookingData);
				}
				result.put("bookings", userBookings);
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
	
	public static Map<String, Object> getBonusHistory(int userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(GET_BONUS_HISTORY_SQL);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			result.put("status", "success");
			List<Map<String, Object>> bonusHistory = new ArrayList<Map<String,Object>>();
			while(rs.next()) {
				Map<String, Object> historyEntry = new HashMap<String, Object>();
				historyEntry.put("scores_change", rs.getInt("scores_change"));
				historyEntry.put("change_time", rs.getTimestamp("change_time"));
				historyEntry.put("venue_name", rs.getString("name"));
				bonusHistory.add(historyEntry);
			}
			result.put("bonus_history", bonusHistory);
		} catch(SQLException e) {
			result.put("status", "failure");
			result.put("error", e.getMessage());
			e.printStackTrace();
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
	
	private static void updateBonusHistory(Connection con, int userId, int venueId, int scoresChange) throws SQLException {
		PreparedStatement ps = con.prepareStatement(UPDATE_BONUS_HISTORY);
		ps.setInt(1, userId);
		ps.setInt(2, venueId);
		ps.setInt(3, scoresChange);
		ps.executeUpdate();
		ps.close();
	}
}
