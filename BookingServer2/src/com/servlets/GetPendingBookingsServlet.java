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
 * http://localhost:8080/BookingServer2/get_pending_bookings?venueId=1
 */
public class GetPendingBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		Map<String, Object> pendingBookings = VenuesDAO.getPendingBookings(venueId);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(pendingBookings);	
		response.getWriter().write(jsonResult);
	}

}
