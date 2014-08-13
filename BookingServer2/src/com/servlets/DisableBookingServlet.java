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
 * Disable booking places in specified venue
 * Should be called by venue admin if there are no free places in venue
 * Example:
 * http://localhost:8080/BookingServer2/disable_booking?actionUser=admin&venueId=1
 */
public class DisableBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
              
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		String actionUser = request.getParameter("actionUser");
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		Map<String, Object> qResult = VenuesDAO.blockBookingForVenue(actionUser, venueId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(qResult);			
		response.getWriter().write(jsonResult);
	}

}
