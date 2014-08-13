package com.beans;

import java.sql.Timestamp;

public class Booking {

	private int id;
	private int venueId;
	private String visitorName;
	private String visitorPhone;
	private Timestamp bookingTime;
	private int placesAmount;
	private int status;
	private String notes;
	private Timestamp bookingCreated;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorPhone() {
		return visitorPhone;
	}

	public void setVisitorPhone(String visitorPhone) {
		this.visitorPhone = visitorPhone;
	}

	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getPlacesAmount() {
		return placesAmount;
	}

	public void setPlacesAmount(int placesAmount) {
		this.placesAmount = placesAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Timestamp getBookingCreated() {
		return bookingCreated;
	}

	public void setBookingCreated(Timestamp bookingCreated) {
		this.bookingCreated = bookingCreated;
	}

	public Booking() {
		
	}

	public Booking(int id, int venueId, String visitorName,
			String visitorPhone, Timestamp bookingTime, int placesAmount,
			int status, String notes, Timestamp bookingCreated) {
		super();
		this.id = id;
		this.venueId = venueId;
		this.visitorName = visitorName;
		this.visitorPhone = visitorPhone;
		this.bookingTime = bookingTime;
		this.placesAmount = placesAmount;
		this.status = status;
		this.notes = notes;
		this.bookingCreated = bookingCreated;
	}

}
