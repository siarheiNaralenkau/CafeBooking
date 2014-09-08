package com.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;


/**
 * Puts a booking request in some cafe, restaurant, bar or club.
 * Requires POST request.
 * Example:
 * http://localhost:8080/BookingServer2/book_place?venueId=1&visitorName=Vasia&visitorPhone=1234567&places=2&bookingTime=15-08-2014 21:00&tableNumber=1
 */

public class BookPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VENUE_ID = "venueId";
	private static final String VISITOR_NAME = "visitorName";
	private static final String VISITOR_PHONE = "visitorPhone";
	private static final String PLACES = "places";
	private static final String NOTES = "notes";
	private static final String BOOKING_TIME = "bookingTime";
	private static final String TABLE_NUMBER = "tableNumber";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		String sBookingTime = "";
		String sVenueId;
		Integer tableNumber = null;
		int venueId = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			sVenueId = request.getParameter(VENUE_ID);
			venueId = Integer.valueOf(sVenueId);
			String visitorName = request.getParameter(VISITOR_NAME);
			String visitorPhone = request.getParameter(VISITOR_PHONE);
			// Booking time in format "DD-MM-YYYY HH:mm".
			sBookingTime = request.getParameter(BOOKING_TIME);
			if(sBookingTime.isEmpty()) {
				throw new ParseException("", 0);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date bookingDate = sdf.parse(sBookingTime);
			byte places = Byte.valueOf(request.getParameter(PLACES));
			String notes = "";
			if(request.getParameterMap().containsKey(NOTES)) {
				notes = request.getParameter(NOTES);
			}
			if(request.getParameterMap().containsKey(TABLE_NUMBER)) {
				String sTableNumber = request.getParameter(TABLE_NUMBER);
				if(sTableNumber != null && !sTableNumber.isEmpty()) {
					tableNumber = Integer.valueOf(request.getParameter(TABLE_NUMBER));
				}
			}
			Map<String, Object> result = VenuesDAO.bookPlaces(venueId, visitorName, visitorPhone, bookingDate, places, notes, tableNumber);
			Gson gson = new Gson();
			String jsonResult = gson.toJson(result);	
			response.getWriter().write(jsonResult);
		} catch (NumberFormatException e) {
			String errorMessage = "Wrong venue_id parameter, whould be integer";
			System.out.println("ERROR: " + errorMessage);
			throw new ServletException(errorMessage);
		} catch (ParseException e) {
			String errorMessage = "Incorrect time parameter. Expecting String in format 'dd-MM-YYYY HH:mm', got " + sBookingTime;
			System.out.println("ERROR: " + errorMessage);
			throw new ServletException(errorMessage);	
		}
	}

}
