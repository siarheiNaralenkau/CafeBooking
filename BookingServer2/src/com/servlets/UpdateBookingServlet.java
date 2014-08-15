package com.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Booking;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet for updating existing booking.
 * Booking time and amount of booked places can be updated.
 * Booking time should be in format "dd-MM-YYYY HH:mm"
 * Examples:
 * http://bronimesto.by:8080/BookingServer2/update_booking?bookingId=9&places=4 -- Update places
 * http://bronimesto.by:8080/BookingServer2/update_booking?bookingId=9&bookingTime=15-08-2014 19:00 -- Update time
 * http://bronimesto.by:8080/BookingServer2/update_booking?bookingId=9&places=4&bookingTime=15-08-2014 19:00 -- Update places and time
 */
@WebServlet("/update_booking")
public class UpdateBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!request.getParameterMap().containsKey("places") && !request.getParameterMap().containsKey("bookingTime")) {
			result.put("status", "failure");
			result.put("error", "Nothing to update!");
		} else {
			try {
				int bookingId = Integer.valueOf(request.getParameter("bookingId"));
				Booking booking = VenuesDAO.getBookingById(bookingId);
				int places = booking.getPlacesAmount();
				Timestamp bookingTime = booking.getBookingTime();
				if(request.getParameterMap().containsKey("places")) {
					places = Integer.valueOf(request.getParameter("places"));
				}
				if(request.getParameterMap().containsKey("bookingTime")) {
					String sBookingTime = request.getParameter("bookingTime");
					Date dBookingTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(sBookingTime);
					bookingTime = new Timestamp(dBookingTime.getTime());
				}
				VenuesDAO.updateBooking(places, bookingTime, bookingId);
			} catch (Exception e) {
				result.put("status", "failure");
				result.put("error", e.getMessage());				
			}
		}
					
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
