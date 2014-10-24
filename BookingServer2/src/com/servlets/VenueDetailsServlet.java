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
 * Servlet implementation class VenueDetailsServlet
 * Example:
 * http://bronimesto.by:8080/BookingServer2/get_venue_details?venueId=1
 */
@WebServlet("/get_venue_details")
public class VenueDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		
		Map<String, Object> result = VenuesDAO.getVenueDetails(venueId);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
