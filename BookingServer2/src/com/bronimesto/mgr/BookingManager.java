package com.bronimesto.mgr;

import java.io.IOException;
import java.util.List;

import com.beans.Booking;
import com.constants.Consts;
import com.dao.AdminDAO;
import com.dao.VenuesDAO;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class BookingManager {
	// Method sends notification about newly created booking to android admin application.	
	public static void notifyBookingCreated(int bookingId) {
		Booking booking = VenuesDAO.getBookingById(bookingId);
		List<String> regIds = AdminDAO.getRegIdsForVenue(booking.getVenueId());
		
		if(!regIds.isEmpty()) {
			Sender notificationSender = new Sender(Consts.ADMIN_APP_KEY);
			Message msgBookingCreated = new Message.Builder()
					.addData("event", "bookingCreated")
					.addData("bookingId", String.valueOf(booking.getId()))
					.addData("visitorName", booking.getVisitorName())
					.addData("visitorPhone", booking.getVisitorPhone())
					.addData("bookingTime", booking.getBookingTime().toString())
					.addData("placesAmount", String.valueOf(booking.getPlacesAmount()))
					.addData("notes", booking.getNotes())
					.addData("tableNumbers", booking.tableNumbersString())
					.addData("userId", String.valueOf(booking.getUserId()))
					.addData("status", "Pending")
					.addData("receiver", "admin")
					.build();
			try {
				MulticastResult gcmResult = notificationSender.send(msgBookingCreated, regIds, Consts.NUMBER_OF_RETRIES);
				System.out.println(gcmResult.getMulticastId());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Method sends notification about booking status update to client application	
		public static void notifyBookingStatusChanged(int bookingId, String receiver) {
			Booking booking = VenuesDAO.getBookingById(bookingId);
			List<String> regIds = AdminDAO.getRegIdsForVenue(booking.getVenueId());
			
			if(!regIds.isEmpty()) {
				Sender notificationSender = new Sender(Consts.CLIENT_APP_KEY);				
				// TODO Add parameter to indicate, for which application the message is sent(admin or client).		
				Message msgBookingCreated = new Message.Builder()
						.addData("event", "bookingStatusChanged")
						.addData("bookingId", String.valueOf(booking.getId()))
						.addData("visitorName", booking.getVisitorName())
						.addData("visitorPhone", booking.getVisitorPhone())
						.addData("bookingTime", booking.getBookingTime().toString())
						.addData("placesAmount", String.valueOf(booking.getPlacesAmount()))
						.addData("notes", booking.getNotes())
						.addData("tableNumbers", booking.tableNumbersString())
						.addData("userId", String.valueOf(booking.getUserId()))
						.addData("status", booking.getStatus())
						.addData("receiver", receiver)
						.build();
				try {
					MulticastResult gcmResult = notificationSender.send(msgBookingCreated, regIds, Consts.NUMBER_OF_RETRIES);
					System.out.println(gcmResult.getMulticastId());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
}
