package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;


@WebServlet("/BookPlaceServlet")
public class BookPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VENUE_ID = "venueId";
	private static final String VISITOR_NAME = "visitorName";
	private static final String VISITOR_PHONE = "visitorPhone";
	private static final String PLACES = "places";
	private static final String NOTES = "notes";
	private static final String BOOKING_TIME = "bookingTime";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String sBookingTime = "";
		String sVenueId;
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
			VenuesDAO.bookPlaces(venueId, visitorName, visitorPhone, bookingDate, places, notes);
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
