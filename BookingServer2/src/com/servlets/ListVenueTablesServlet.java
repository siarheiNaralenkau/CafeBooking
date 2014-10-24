package com.servlets;

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
 * Servlet implementation class ListVenueTablesServlet
 * Example 1: Get all tables for venue
 * http://localhost:8080/BookingServer2/list_venue_tables?venueId=1
 * Example 2: Get tables for venue which are not booked
 * http://localhost:8080/BookingServer2/list_venue_tables?venueId=1&freeOnly=true
 * Example 3: Get tables for venue which are not booked and which are placed near window.
 * http://localhost:8080/BookingServer2/list_venue_tables?venueId=1&freeOnly=true&positionNotes=2 
 */
@WebServlet("/list_venue_tables")
public class ListVenueTablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VENUE_ID = "venueId";
	private static final String FREE_ONLY = "freeOnly";
	private static final String POSITION_NOTES = "positionNotes";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		Integer venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		boolean freeOnly = false;
		Integer positionNotes = null;
		if(request.getParameterMap().containsKey(FREE_ONLY)) {
			freeOnly = Boolean.valueOf(request.getParameter(FREE_ONLY));
		}
		if(request.getParameterMap().containsKey(POSITION_NOTES)) {
			positionNotes = Integer.valueOf(request.getParameter(POSITION_NOTES));
		}
		
		Map<String, Object> result = VenuesDAO.getVenueTables(venueId, freeOnly, positionNotes);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
