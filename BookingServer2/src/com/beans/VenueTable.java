package com.beans;

import java.sql.Timestamp;

public class VenueTable {

	private Integer id;
	private Integer venueId;
	private Integer xPos;
	private Integer yPos;
	private Integer places;
	private Integer number;
	private String positionNotes;
	private Boolean isFree;
	private Integer bookedPlaces;
	private Timestamp bookedTime;
	private String photoUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public Integer getxPos() {
		return xPos;
	}

	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Integer getyPos() {
		return yPos;
	}

	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getPositionNotes() {
		return positionNotes;
	}

	public void setPositionNotes(String positionNotes) {
		this.positionNotes = positionNotes;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public Integer getBookedPlaces() {
		return bookedPlaces;
	}

	public void setBookedPlaces(Integer bookedPlaces) {
		this.bookedPlaces = bookedPlaces;
	}

	public Timestamp getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Timestamp bookedTime) {
		this.bookedTime = bookedTime;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public VenueTable(Integer id, Integer venueId, Integer xPos, Integer yPos, Integer places,
			Integer number, String positionNotes, boolean isFree, Integer bookedPlaces,
			Timestamp bookedTime, String photoUrl) {
		super();
		this.id = id;
		this.venueId = venueId;
		this.xPos = xPos;
		this.yPos = yPos;
		this.places = places;
		this.number = number;
		this.positionNotes = positionNotes;
		this.isFree = isFree;
		this.bookedPlaces = bookedPlaces;
		this.bookedTime = bookedTime;
		this.photoUrl = photoUrl;
	}

	public VenueTable() {
		super();
	}
}
