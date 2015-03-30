package com.servlets.bookings;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Booking;
import com.beans.Venue;
import com.bronimesto.mgr.BookingManager;
import com.constants.BookingStatus;
import com.constants.Consts;
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
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=APPROVED&adminUser=admin&adminPassword=admin
 * -- Cancel booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=CANCELLED&visitorName=Vasia
 * -- Reject booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=REJECTED&userId=2
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
		Set<Integer> availableStatuses = new HashSet<Integer>();
		int bookingId = Integer.valueOf(request.getParameter(BOOKING_ID));		
		int newStatus = Consts.CODE_BY_STATUS.get(request.getParameter(NEW_STATUS));	
		Booking booking = VenuesDAO.getBookingById(bookingId);
		int venueId = booking.getVenueId();
		String bookingStatus = booking.getStatus();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// Fill the graph of possible statuses for update		
		if(request.getParameterMap().containsKey(ADMIN_USER)) {
			if(bookingStatus.equals("PENDING")) {
				availableStatuses.add(BookingStatus.APPROVED.getValue());
				availableStatuses.add(BookingStatus.REJECTED.getValue());
			} else if(bookingStatus.equals("APPROVED")) {
				availableStatuses.add(BookingStatus.CLOSED.getValue());
				availableStatuses.add(BookingStatus.EXPIRED.getValue());
			}
		} else if(request.getParameterMap().containsKey(VISITOR) || request.getParameterMap().containsKey(USER_ID)) {
			availableStatuses.add(BookingStatus.CANCELLED.getValue());
		}
		
		// If request is send by venue administrator, check his credentials and updated status to one of possible values				
		if(request.getParameterMap().containsKey(ADMIN_USER)) {			
			String adminUser = request.getParameter(ADMIN_USER);
			String adminPassword = request.getParameter(ADMIN_PASSWORD);
			Map<String, Object> checkAdmin = VenuesDAO.checkAdmin(adminUser, adminPassword, venueId);
			if(checkAdmin.get("status").equals("success")) {
				if(availableStatuses.contains(newStatus)) {
					result = VenuesDAO.updateStatus(bookingId, newStatus);
				} else {
					result.put("status", "failure");
		 			result.put("error", "Invalid operation: Admin user is not allowed to update status to this values");
				}
			} else {
				result.put("status", "failure");
	 			result.put("error", "Inalid admin login or invalid admin password");
			}				
		} else if(request.getParameterMap().containsKey(VISITOR)) {
			// Only user, who created booking, can change it status to cancelled.						
			String visitorName = request.getParameter(VISITOR);
			if(!visitorName.equals(booking.getVisitorName())) {
				result.put("status", "failure");
	 			result.put("error", "User's name doesn't match booking creator's name");
			} else if(!availableStatuses.contains(newStatus)) {
				result.put("status", "failure");
	 			result.put("error", "Invalid operation: Visitor is not allowed to update status to this values");
			} else {
				result = VenuesDAO.updateStatus(bookingId, newStatus);
			}
 		} else if(request.getParameterMap().containsKey(USER_ID)) {
			// Only user, who created booking, can change it status to cancelled.						
			int userId = Integer.valueOf(request.getParameter(USER_ID));
			if(userId != booking.getUserId()) {
				result.put("status", "failure");
	 			result.put("error", "User's name doesn't match booking creator's name");
			} else if(!availableStatuses.contains(newStatus)) {
				result.put("status", "failure");
	 			result.put("error", "Invalid operation: Visitor is not allowed to update status to this values");
			} else {
				result = VenuesDAO.updateStatus(bookingId, newStatus);
			}
 		} else {
 			result.put("status", "failure");
 			result.put("error", "Only venue admin or booking creator are able to change booking status");
 		}
		
		// Notify client application that booking status was updated.		
		if("success".equals(result.get("status").toString())) {			
			result.put("newBookingStatus", Consts.STATUS_BY_CODE.get(newStatus));			
			if(newStatus == 1 || newStatus == 3) {				
				BookingManager.notifyBookingStatusChanged(bookingId, "admin");
			} else {
				String clientRegistrationId = booking.getRegId();
				BookingManager.notifyBookingStatusChanged(bookingId, "client", clientRegistrationId);				
			}			
			
			Venue venue = VenuesDAO.getVenueById(venueId);
			String venueName = venue.getName();
			String bookingTime = booking.getBookingTime().toLocaleString();
			String newStatusString = Consts.STATUS_BY_CODE.get(newStatus);
			String email = booking.getEmail();
			if(!email.isEmpty()) {
				BookingManager.sendBookingStatusChangeEmail(email, venueName, bookingTime, newStatusString);
			}
		}
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);			
		response.getWriter().write(jsonResult);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
