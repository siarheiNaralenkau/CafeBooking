package com.beans;

public class Venue {
	public Venue() {
		super(); 
	}
	public Venue(long id, String uniqueId, String name, String phone,
			String address, String city, String country, float latitude,
			float longitude, String category, boolean has_free_seats) {
		super();
		this.id = id;
		this.uniqueId = uniqueId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.has_free_seats = has_free_seats;
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isHas_free_seats() {
		return has_free_seats;
	}
	public void setHas_free_seats(boolean has_free_seats) {
		this.has_free_seats = has_free_seats;
	}
	private long id;
	private String uniqueId;
	private String name;
	private String phone;
	private String address;
	private String city;
	private String country;
	private float latitude;
	private float longitude;
	private String category;
	private boolean has_free_seats;
}
