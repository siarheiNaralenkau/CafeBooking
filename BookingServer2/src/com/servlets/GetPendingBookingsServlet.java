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
 * Receive the list of pending bookings for specified servlet.
 * Should be called by venue admin user to check the pending bookings for his venue.
 * Example:
 * http://localhost:8080/BookingServer2/get_pending_bookings?actionUser=admin&venueId=1
 */
public class GetPendingBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		String actionUser = request.getParameter("actionUser");
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		Map<String, Object> pendingBookings = VenuesDAO.getPendingBookings(actionUser, venueId);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(pendingBookings);	
		response.getWriter().write(jsonResult);
	}

}
