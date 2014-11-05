package com.servlets.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckTimeZoneServlet
 * http://localhost:8080/BookingServer2/CheckTimeZoneServlet
 */
@WebServlet("/CheckTimeZoneServlet")
public class CheckTimeZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		String dateString = sdf.format(now);
		response.getWriter().write("Current time: " + dateString);
	}

}
