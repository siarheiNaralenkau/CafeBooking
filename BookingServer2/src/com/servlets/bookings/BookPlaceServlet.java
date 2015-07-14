package com.servlets.bookings;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bronimesto.mgr.BookingManager;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Puts a booking request in some cafe, restaurant, bar or club.
 * Requires POST request.
 * Example:
 * http://localhost:8080/BookingServer2/book_place?venueId=1&visitorName=Vasia&visitorPhone=1234567&places=2&bookingTime=15-08-2014 21:00&tableNumbers=1,2,3&email=naralenkov2010@gmail.com
 * http://localhost:8080/BookingServer2/book_place?venueId=1&userId=2&visitorPhone=1234567&places=2&bookingTime=15-08-2014 21:00&tableNumbers=1,2,3
 */

@WebServlet("/book_place")
public class BookPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VENUE_ID = "venueId";
	private static final String VISITOR_NAME = "visitorName";
	private static final String VISITOR_PHONE = "visitorPhone";
	private static final String PLACES = "places";
	private static final String NOTES = "notes";
	private static final String BOOKING_TIME = "bookingTime";
	private static final String TABLE_NUMBERS = "tableNumbers";	
	private static final String USER_ID = "userId";	
	private static final String REG_ID = "regId";
	private static final String EMAIL = "email";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("application/json; charset=UTF-8");
		String sBookingTime = "";
		String sVenueId;		
		int venueId = 0;
		Integer userId = null;
		try {						
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
			String tableNumbers = "";
			String regId = "";
			String email = "";
			if(request.getParameterMap().containsKey(NOTES)) {
				notes = request.getParameter(NOTES);				
			}
			if(request.getParameterMap().containsKey(TABLE_NUMBERS)) {
				tableNumbers = request.getParameter(TABLE_NUMBERS);				
			}
			if(request.getParameterMap().containsKey(USER_ID)) {
				userId = Integer.valueOf(request.getParameter(USER_ID));
			}
			if(request.getParameterMap().containsKey(REG_ID)) {
				regId = request.getParameter(REG_ID);
			}
			if(request.getParameterMap().containsKey(EMAIL)) {
				email = request.getParameter(EMAIL);
			}
			Map<String, Object> result = VenuesDAO.bookPlaces(venueId, visitorName, visitorPhone, bookingDate, places, notes, tableNumbers, userId, regId, email);
				
			result.put("Person name", visitorName);
			result.put("UserId", userId);
			
			// Send push notification to device for venue
			if(result.get("status").equals("success")) {
				BookingManager.notifyBookingCreated(Integer.valueOf(result.get("bookingId").toString()));
			}
			
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
