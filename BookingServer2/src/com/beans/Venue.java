package com.beans;

import java.util.List;

public class Venue {

	public Venue(int id, String uniqueId, String name, String phone,
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	}
	
	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public int getFreeTablesAmount() {
		return freeTablesAmount;
	}

	public void setFreeTablesAmount(int freeTablesAmount) {
		this.freeTablesAmount = freeTablesAmount;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public int getTablesAmount() {
		return tablesAmount;
	}

	public void setTablesAmount(int tablesAmount) {
		this.tablesAmount = tablesAmount;
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

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public boolean isHasWifi() {
		return hasWifi;
	}

	public void setHasWifi(boolean hasWifi) {
		this.hasWifi = hasWifi;
	}

	public boolean isTakeCreditCards() {
		return takeCreditCards;
	}

	public void setTakeCreditCards(boolean takeCreditCards) {
		this.takeCreditCards = takeCreditCards;
	}

	public boolean isHasOutdoorsSeats() {
		return hasOutdoorsSeats;
	}

	public void setHasOutdoorsSeats(boolean hasOutdoorsSeats) {
		this.hasOutdoorsSeats = hasOutdoorsSeats;
	}

	public List<VenuePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<VenuePhoto> photos) {
		this.photos = photos;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAvgPayment() {
		return avgPayment;
	}

	public void setAvgPayment(String avgPayment) {
		this.avgPayment = avgPayment;
	}

	public int getReviewsAmount() {
		return reviewsAmount;
	}

	public void setReviewsAmount(int reviewsAmount) {
		this.reviewsAmount = reviewsAmount;
	}

	private int id;
	private Float rating;
	private int freeTablesAmount;	
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
	private String adminUser;	
	private String adminPassword;
	private boolean inBookingSystem;
	private int tablesAmount;
	private String iconUrl;
	private String openTime;
	private String closeTime;
	private String plan;
	private String cuisine;
	private boolean hasWifi;
	private boolean takeCreditCards;
	private boolean hasOutdoorsSeats;
	private List<VenuePhoto> photos;
	private String avgPayment;		
	
	private double distance;
	
	private int reviewsAmount;
}
