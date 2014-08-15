package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet deletes old bookings for specified venue.
 * Should be called only by venue's admin user.
 * Example:
 * http://localhost:8080/BookingServer2/clear_bookings?actionUser=admin&venueId=1
 */
public class ClearBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		String actionUser = request.getParameter("actionUser");
		Map<String, Object> result = VenuesDAO.clearBookings(actionUser, venueId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}