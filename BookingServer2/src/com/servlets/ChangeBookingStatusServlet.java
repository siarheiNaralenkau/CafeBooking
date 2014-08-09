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
 * Servlet implementation class ChangeBookingStatusServlet
 */
/**
 * Requests examples
 *  -- Approve booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=2&actionUser=admin
 * -- Cancel booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=3&actionUser=Sergey
 * -- Reject booking --
 * http://localhost:8080/BookingServer2/change_booking_status?bookingId=2&newStatus=4&actionUser=adming
 */

@WebServlet("/ChangeBookingStatusServlet")
public class ChangeBookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		int newStatus = Integer.valueOf(request.getParameter("newStatus"));
		String actionUser = request.getParameter("actionUser");
		System.out.println("Approved: " + BookingStatus.APPROVED.getValue());
		System.out.println("Rejected: " + BookingStatus.REJECTED.getValue());
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
