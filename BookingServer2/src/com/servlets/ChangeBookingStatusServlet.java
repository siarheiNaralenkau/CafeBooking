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
 * Change the booking status
 * User who requested booking can only cancel his booking,
 * Venue admin user can Approve, Reject, or Delete booking.
 * Requests examples
 *  -- Approve booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=2
 * -- Cancel booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=3
 * -- Reject booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=4
 */

@WebServlet("/ChangeBookingStatusServlet")
public class ChangeBookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");		
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		int newStatus = Integer.valueOf(request.getParameter("newStatus"));		
		
		Map<String, Object> result = VenuesDAO.updateStatus(bookingId, newStatus);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);			
		response.getWriter().write(jsonResult);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
