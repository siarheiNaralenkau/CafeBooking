package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.BookingStatus;
import com.constants.Consts;
import com.dao.VenuesDAO;

/**
 * Change the booking status
 * User who requested booking can only cancel his booking,
 * Venue admin user can Approve, Reject, or Delete booking.
 * Requests examples
 *  -- Approve booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=2&actionUser=admin
 * -- Cancel booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=3&actionUser=Sergey
 * -- Reject booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=4&actionUser=admin
 */

@WebServlet("/ChangeBookingStatusServlet")
public class ChangeBookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");		
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		int newStatus = Integer.valueOf(request.getParameter("newStatus"));
		String actionUser = request.getParameter("actionUser");
		
		if( (newStatus == BookingStatus.APPROVED.getValue() || newStatus == BookingStatus.REJECTED.getValue()) && (!actionUser.equals(Consts.ADMIN))) {
			System.out.println("You are not allowed to perform this operation!");
		} else {
			VenuesDAO.updateStatus(bookingId, newStatus, actionUser);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
