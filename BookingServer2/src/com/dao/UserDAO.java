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

public class UserDAO {
	
	private static DataSource dataSource;
	
	private static final String ADD_USER_SQL = "INSERT INTO users(name, surname, email, phone, password, bonus_scores) VALUES(?, ?, ?, ?, ?, 100)";
	private static final String CHECK_USER_PRESENSE_SQL = "SELECT * FROM users WHERE email = ?";
	
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
		return result;
	}
}
