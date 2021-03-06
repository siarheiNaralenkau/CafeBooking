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
 * Receives the booking history for specified venue.
 * Can be called only by venue admin.
 * Example: 
 * http://localhost:8080/BookingServer2/get_history?actionUser=admin&venueId=1&page=1
 */
@WebServlet("/get_history")
public class GetHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		int page = 0;
		if(request.getParameterMap().containsKey("page")) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		Map<String, Object> bookingHistory = VenuesDAO.getBookingHistory(venueId, page);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(bookingHistory);	
		response.getWriter().write(jsonResult);
	}

}
