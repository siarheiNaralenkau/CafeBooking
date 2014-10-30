package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.dao.VenuesDAO;

/**
 * Servlet implementation class EditVenueOpenServlet
 * http://localhost:8080/BookingServer2/edit_venue_open?venueId=1
 */
@WebServlet("/edit_venue_open")
public class EditVenueOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String VENUE_ID = "venueId";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditVenueOpenServlet() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		Venue venue = VenuesDAO.getVenueForEdit(venueId);
		request.setAttribute("venue", venue);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_venue.jsp");
		dispatcher.forward(request, response);
	}	

}
