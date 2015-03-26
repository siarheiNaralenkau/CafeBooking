package com.servlets.bookings;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet that receives all bookings for specified venue, possibly filtered by status, and 100 records per page...
 * page = 0 Means all records.
 * Can be called only by venue's admin user.
 * Example: 
 * http://localhost:8080/BookingServer2/get_venue_bookings?venueId=1&page=1 - Receive all bookings.
 * http://localhost:8080/BookingServer2/get_venue_bookings?venueId=1&status=APPROVED&page=1 - Receive only approved bookings.
 */
@WebServlet("/get_venue_bookings")
public class GetVenueBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");			
				
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		String filterStatus = "ALL";
		int page = 0;
		if(request.getParameterMap().containsKey("status")) {
			filterStatus = request.getParameter("status");
		}
		if(request.getParameterMap().containsKey("page")) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		Map<String, Object> bookingsForVenue = VenuesDAO.getBookingsForVenue(venueId, filterStatus, page);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(bookingsForVenue);	
		response.getWriter().write(jsonResult);
	}

}
