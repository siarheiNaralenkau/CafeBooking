package com.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Booking;
import com.constants.BookingStatus;
import com.constants.Consts;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/*
 * Example:
 * http://localhost:8080/BookingServer2/check_booking_status?userName=Sergey&bookingId=2
 */
public class CheckBookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		String userName = request.getParameter("userName");
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		Booking booking = VenuesDAO.getBookingById(bookingId);
		// Check is the user, which requested booking status is the same user, which ordered a booking.
		if(!userName.equals(booking.getVisitorName())) {
			responseMap.put("success", false);
			responseMap.put("error", "The user " + userName + " is not allowed to receive status of booking " + bookingId);
		} else {
			responseMap.put("success", true);			
			int bStatus = booking.getStatus();			
			long createdTime = booking.getBookingCreated().getTime();
			Date now = new Date();
			long nowTime = now.getTime();
			// If booking was not approved during 20 minutes since creation - cancel it.			
			if(Math.abs(nowTime-createdTime) >= Consts.TWENTY_MINUTES_MS && bStatus == BookingStatus.PENDING.getValue()) {
				VenuesDAO.updateStatus(bookingId, BookingStatus.CANCELLED.getValue(), userName);
				bStatus = BookingStatus.CANCELLED.getValue(); 
			}
			responseMap.put("status", bStatus);
		}
		Gson gson = new Gson();
		String jsonResult = gson.toJson(responseMap);			
		response.getWriter().write(jsonResult);
	}

}
