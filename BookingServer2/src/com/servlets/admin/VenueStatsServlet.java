package com.servlets.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.constants.Consts;
import com.dao.VenuesDAO;

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
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		Venue venue = VenuesDAO.getVenueForEdit(venueId);
		Map<String, Object> venueBookings = VenuesDAO.getBookingsForVenue(venueId, Consts.STATUS_ALL);
		request.setAttribute("venue", venue);
		request.setAttribute("bookings", venueBookings);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/venue_stats.jsp");
		dispatcher.forward(request, response);
	}	

}
