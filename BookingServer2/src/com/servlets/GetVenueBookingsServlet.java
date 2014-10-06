package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.Consts;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet that receives all bookings for specified venue, possibly filtered by status
 * Can be called only by venue's admin user.
 * Example:
 * http://localhost:8080/BookingServer2/get_venue_bookings?venueId=1 - Receive all bookings.
 * http://localhost:8080/BookingServer2/get_venue_bookings?venueId=1&status=2 - Receive only approved bookings.
 */
public class GetVenueBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");			
				
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		int filterStatus = Consts.STATUS_ALL;
		if(request.getParameterMap().containsKey("status")) {
			filterStatus = Integer.valueOf(request.getParameter("status"));
		}
		Map<String, Object> bookingsForVenue = VenuesDAO.getBookingsForVenue(venueId, filterStatus);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(bookingsForVenue);	
		response.getWriter().write(jsonResult);
	}

}
