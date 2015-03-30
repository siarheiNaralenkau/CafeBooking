package com.bronimesto.mgr;

import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.beans.Booking;
import com.beans.Venue;
import com.constants.Consts;
import com.dao.AdminDAO;
import com.dao.VenuesDAO;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.sun.mail.smtp.SMTPTransport;

public class BookingManager {
	// Method sends notification about newly created booking to android admin application.	
	public static void notifyBookingCreated(int bookingId) {
		Booking booking = VenuesDAO.getBookingById(bookingId);
		List<String> regIds = AdminDAO.getRegIdsForVenue(booking.getVenueId());
		Venue venue = VenuesDAO.getVenueById(booking.getVenueId());
		
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
					.addData("venueId", String.valueOf(booking.getVenueId()))
					.addData("venueName", venue.getName())
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
		Venue venue = VenuesDAO.getVenueById(booking.getVenueId());
		
		if(!regIds.isEmpty()) {
			Sender notificationSender = new Sender(Consts.ADMIN_APP_KEY);				
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
					.addData("venueId", String.valueOf(booking.getVenueId()))
					.addData("venueName", venue.getName())
					.build();
			try {
				MulticastResult gcmResult = notificationSender.send(msgBookingCreated, regIds, Consts.NUMBER_OF_RETRIES);
				System.out.println(gcmResult.getMulticastId());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void notifyBookingStatusChanged(int bookingId, String receiver, String regId) {
		Booking booking = VenuesDAO.getBookingById(bookingId);					
		Sender notificationSender = new Sender(Consts.ADMIN_APP_KEY);	
		Venue venue = VenuesDAO.getVenueById(booking.getVenueId());
		
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
				.addData("venueId", String.valueOf(booking.getVenueId()))
				.addData("venueName", venue.getName())
				.build();
		try {
			Result gcmResult = notificationSender.send(msgBookingCreated, regId, Consts.NUMBER_OF_RETRIES);
			System.out.println(gcmResult.getMessageId());
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void notifyBookingSpentDefinedAdmin(int bookingId) {
		Booking booking = VenuesDAO.getBookingById(bookingId);
		Sender notificationSender = new Sender(Consts.ADMIN_APP_KEY);
		Message msgSpentDefined = new Message.Builder()
			.addData("event", "bookingSpentDefined")
			.addData("bookingId", String.valueOf(booking.getId()))
			.addData("venueId", String.valueOf(booking.getVenueId()))
			.addData("checkSum", String.valueOf(booking.getSpentMoney()))
			.build();
		try {
			Result gcmResult = notificationSender.send(msgSpentDefined, booking.getRegId(), Consts.NUMBER_OF_RETRIES);
			System.out.println(gcmResult.getMessageId());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void notifyBookingSpentDefinedVisitor(int bookingId) {
		Booking booking = VenuesDAO.getBookingById(bookingId);
		List<String> regIds = AdminDAO.getRegIdsForVenue(booking.getVenueId());
		Sender notificationSender = new Sender(Consts.ADMIN_APP_KEY);
		Message msgVisitorSpentDefined = new Message.Builder()
			.addData("event", "visitorSpentDefined")
			.addData("bookingId", String.valueOf(booking.getId()))
			.addData("visitorCheckSum", String.valueOf(booking.getVisitorSpentMoney()))
			.build();
		try {
			MulticastResult gcmResult = notificationSender.send(msgVisitorSpentDefined, regIds, Consts.NUMBER_OF_RETRIES);
			System.out.println(gcmResult.getMulticastId());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendBookingStatusChangeEmail(String email, String venueName, String bookingTime, String newStatus) {
		String mailSubject = "Изменение статуса вашей брони";
		String mailText = String.format("Статус вашей брони в %s на время %s изменен на %s.", venueName, bookingTime, newStatus);
		sendMail(mailSubject, mailText, email);
	}
	
	public static void sendPasswordEmail(String email, String password) {		
		String mailSubject = "Восстановление пароля для bronimesto,by";
		String mailText = "Ваш пароль: " + password;
		sendMail(mailSubject, mailText, email);						 
	}
	
	public static void sendMail(String subject, String text, String addressList) {
		String hostName = "smtp.gmail.com";		
		String username = "bronimestoby";
		String mailServerPassword = "qwerty12Q";								
				
		String from = "bronimestoby@gmail.com";
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		
		Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        
        props.put("mail.smtps.quitwait", "false");
        
        Session session = Session.getInstance(props, null);
        
        final MimeMessage msg = new MimeMessage(session);
        
        try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(addressList, false));
			msg.setSubject(subject, "utf-8");			
			msg.setText(text, "utf-8");
			msg.setSentDate(new Date());
			SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
			t.connect(hostName, username, mailServerPassword);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
