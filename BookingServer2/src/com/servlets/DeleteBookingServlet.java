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
 * Deletes a specified booking
 * Probably should be called by adming when the visitors have already left the venue.
 * Example: 
 * http://localhost:8080/BookingServer2/delete_booking?bookingId=33
 */
public class DeleteBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");				
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		Map<String, Object> result = VenuesDAO.deleteBooking(bookingId);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
