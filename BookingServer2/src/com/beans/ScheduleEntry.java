package com.beans;

public class ScheduleEntry {
	private int venueId;
	private Integer dayId;
	private String day;
	private String openTime;
	private String closeTime;
			
	public ScheduleEntry() {
		super();	
	}
	
	public ScheduleEntry(int venueId, int dayId, String day,
			String openTime, String closeTime) {
		super();
		this.venueId = venueId;
		this.dayId = dayId;
		this.day = day;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
}
