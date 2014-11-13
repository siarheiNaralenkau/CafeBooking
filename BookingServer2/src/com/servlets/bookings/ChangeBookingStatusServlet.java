package com.servlets.bookings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Booking;
import com.constants.BookingStatus;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Change the booking status
 * User who requested booking can only Cancel his booking,
 * Venue admin user can Approve, Reject, or Delete booking.
 * Paarameters:
 * adminUser - Venue administrator
 * adminPassword - Venue administrator's password
 * visitor - Name of person who booked table.
 * Requests examples
 *  -- Approve booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=2&
 * -- Cancel booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=3
 * -- Reject booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=4
 */

@WebServlet("/change_booking_status")
public class ChangeBookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ADMIN_USER = "adminUser";
	private static final String ADMIN_PASSWORD = "adminPassword";
	private static final String BOOKING_ID = "bookingId";
	private static final String NEW_STATUS = "newStatus";
	private static final String VISITOR = "visitor";
	private static final String USER_ID = "userId";
           
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");		
		int bookingId = Integer.valueOf(request.getParameter(BOOKING_ID));
		int newStatus = Integer.valueOf(request.getParameter(NEW_STATUS));	
		Booking booking = VenuesDAO.getBookingById(bookingId);
		int venueId = booking.getVenueId();
		int bookingStatus = booking.getStatus();
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(request.getParameterMap().containsKey(ADMIN_USER)) {
			// If status of booking is 'pending' - admin can approve or reject booking
			// If status is 'approved' - admin only can close booking after visitor leaves.
			String adminUser = request.getParameter(ADMIN_USER);
			String adminPassword = request.getParameter(ADMIN_PASSWORD);
			Map<String, Object> checkAdmin = VenuesDAO.checkAdmin(adminUser, adminPassword, venueId);
			if(checkAdmin.get("status").equals("success")) {
				if(bookingStatus == BookingStatus.PENDING.getValue() && (newStatus == BookingStatus.APPROVED.getValue() || newStatus == BookingStatus.REJECTED.getValue())) {
					result = VenuesDAO.updateStatus(bookingId, newStatus);
				} else if(bookingStatus == BookingStatus.APPROVED.getValue() && newStatus == BookingStatus.CLOSED.getValue() ) {
					result = VenuesDAO.updateStatus(bookingId, newStatus);
				} else {
					result.put("status", "failure");
					result.put("error", "Invalid operation: admin can't change booking status from " + String.valueOf(bookingStatus) + " to " + String.valueOf(newStatus));
				}
			}
		} else if(request.getParameterMap().containsKey(VISITOR)) {
			// Only user, who created booking, can change it status to cancelled.						
			String visitorName = request.getParameter(VISITOR);
			if(visitorName.equals(booking.getVisitorName()) && newStatus == BookingStatus.CANCELLED.getValue() ) {
				result = VenuesDAO.updateStatus(bookingId, newStatus);
			}
 		} else if(request.getParameterMap().containsKey(USER_ID)) {
			// Only user, who created booking, can change it status to cancelled.						
			int userId = Integer.valueOf(request.getParameter(USER_ID));
			if(userId == booking.getUserId() && newStatus == BookingStatus.CANCELLED.getValue() ) {
				result = VenuesDAO.updateStatus(bookingId, newStatus);
			}
 		} else {
 			result.put("status", "failure");
 			result.put("error", "Invalid operation: user on new status are wrong");
 		}
				
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);			
		response.getWriter().write(jsonResult);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
