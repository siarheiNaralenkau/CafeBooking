package com.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.dao.VenuesDAO;

/**
 * Servlet implementation class GetGroupedVenuesServlet
 * http://localhost:8080/BookingServer2/get_grouped_venues
 */
@WebServlet("/get_grouped_venues")
public class GetGroupedVenuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public GetGroupedVenuesServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, List<Venue>> venuesByCategory = VenuesDAO.getGroupedVenues();
		request.setAttribute("venues", venuesByCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/venues_by_category.jsp");
		dispatcher.forward(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
