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
 * Sets the admin user for specified venue.
 * Example
 * http://localhost:8080/BookingServer2/set_venue_admin?venueAdmin=admin&venueId=1
 */
public class SetVenueAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		String venueAdmin = request.getParameter("venueAdmin");
		Map<String, Object> result = VenuesDAO.setVenueAdmin(venueAdmin, venueId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
