package com.beans;

public class Venue {

	public Venue(long id, String uniqueId, String name, String phone,
			String address, String city, String country, double lat,
			double lng, String category, boolean hasFreeSeats, String iconUrl) {
		super();
		this.id = id;
		this.uniqueId = uniqueId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
		this.category = category;
		this.hasFreeSeats = hasFreeSeats;
		this.iconUrl = iconUrl;
	}
	
	public Venue(long id, String uniqueId, String name, String phone,
			String address, String city, String country, double lat,
			double lng, String category, boolean hasFreeSeats, String adminUser, String iconUrl) {
		super();
		this.id = id;
		this.uniqueId = uniqueId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
		this.category = category;
		this.hasFreeSeats = hasFreeSeats;
		this.adminUser = adminUser;
		this.iconUrl = iconUrl;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isHasFreeSeats() {
		return hasFreeSeats;
	}
	public void setHasFreeSeats(boolean hasFreeSeats) {
		this.hasFreeSeats = hasFreeSeats;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}	
	public String getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	
	public void setInBookingSystem(boolean inBookingSystem) {
		this.inBookingSystem = inBookingSystem;
	}
	
	public boolean getInBookingSystem() {
		return inBookingSystem;
	}
	
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	
	public Venue() {
		// TODO Auto-generated constructor stub
	}
	
	private long id;
	private String uniqueId;
	private String name;
	private String phone;
	private String address;
	private String city;
	private String country;
	private double lat;
	private double lng;
	private String category;
	private boolean hasFreeSeats;
	private double distance;
	private String adminUser;
	private boolean inBookingSystem;
	private String iconUrl;
}
