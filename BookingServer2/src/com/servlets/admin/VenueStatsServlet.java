package com.servlets.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.constants.Consts;
import com.dao.BookingsDAO;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class VenueStatsServlet
 */
@WebServlet("/venue_stats")
public class VenueStatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	private static final String VENUE_ID = "venueId";
    
    public VenueStatsServlet() {
        super();    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		int page = 0;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Venue venue = VenuesDAO.getVenueForEdit(venueId);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> venueBookings = VenuesDAO.getBookingsForVenue(venueId, Consts.STATUS_ALL, page);
		result.put("venue", venue);
		result.put("bookings", venueBookings);
		Map<String, Object> bookingStats = BookingsDAO.getBookingStats(venueId, startDate, endDate);
		result.put("bookingStats", bookingStats);
		List<Map<String, Object>> bookingsRegistred = BookingsDAO.getBookingsForRegistredUsers(venueId, startDate, endDate);
		result.put("bookingsRegistred", bookingsRegistred);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);		
		response.getWriter().write(jsonResult);
	}	

}
