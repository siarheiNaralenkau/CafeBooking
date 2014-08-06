package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.beans.Venue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class GetVenuesListServlet
 */
@WebServlet("/GetVenuesListServlet")
public class GetVenuesListServlet extends HttpServlet {
		
	private DataSource ds;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVenuesListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("Servlet is responding!");
		
		try {			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bronimesto?user=admin&password=qwerty12Q");
			String query = "SELECT * from venues limit 10";
			Statement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Venue v = new Venue(rs.getLong("id"), rs.getString("unique_id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getFloat("latitude"),
						rs.getFloat("longitude"), rs.getString("category"), rs.getBoolean("has_free_seats"));
				writer.print(v.getName());
			}
			writer.print("Connection is successfull!");
		} catch (SQLException e) { 
			e.printStackTrace();		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("Servlet is responding!");
	}

}
