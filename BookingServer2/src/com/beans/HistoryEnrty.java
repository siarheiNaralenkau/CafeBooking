package com.beans;

import java.sql.Timestamp;

public class HistoryEnrty {
	private int bookingId;
	private int status;
	private String actionUser;
	private Timestamp changeTime;
	private int venueId;
	private int placesAmount;
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getActionuser() {
		return actionUser;
	}

	public void setActionuser(String actionuser) {
		this.actionUser = actionuser;
	}

	public Timestamp getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public int getPlacesAmount() {
		return placesAmount;
	}

	public void setPlacesAmount(int placesAmount) {
		this.placesAmount = placesAmount;
	}

	public HistoryEnrty() {
		super();
	}

	public HistoryEnrty(int bookingId, int status, String actionUser,
			Timestamp changeTime, int venueId, int placesAmount) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.actionUser = actionUser;
		this.changeTime = changeTime;
		this.venueId = venueId;
		this.placesAmount = placesAmount;
	}

}
