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
 * Disable or enable booking places in specified venue
 * Should be called by venue admin if there are no free places in venue
 * Example:
 * http://localhost:8080/BookingServer2/switch_venue_status?venueId=1&enableBooking=false
 */
public class SwitchVenueStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
              
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
				
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		boolean enableBooking = Boolean.valueOf(request.getParameter("enableBooking"));
		Map<String, Object> qResult = VenuesDAO.switchVenueStatus(venueId, enableBooking);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(qResult);			
		response.getWriter().write(jsonResult);
	}

}
