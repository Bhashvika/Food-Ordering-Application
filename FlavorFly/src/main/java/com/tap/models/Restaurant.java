package com.tap.models;

import java.sql.Time;

public class Restaurant {
     private int restaurantid;
     private String name;
     private String address;
     private String phonenumber;
     private String cuisineType;
     private Time DeliveryTime;
     private int AdminUserId;
     private int rating;
     private boolean isActive;
     private String imagePath;
	public int getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}
	public Time getDeliveryTime() {
		return DeliveryTime;
	}
	public void setDeliveryTime(Time deliveryTime) {
		DeliveryTime = deliveryTime;
	}
	public int getAdminUserId() {
		return AdminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		AdminUserId = adminUserId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String ImagePath) {
		this.imagePath = ImagePath;
	}
	public Restaurant(int restaurantid, String name, String address, String phonenumber, String cuisineType,
			Time deliveryTime, int adminUserId, int rating, boolean isActive, String ImagePath) {
		super();
		this.restaurantid = restaurantid;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.cuisineType = cuisineType;
		this.DeliveryTime = deliveryTime;
		this.AdminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = ImagePath;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantid=" + restaurantid + ", name=" + name + ", address=" + address + ", phonenumber="
				+ phonenumber + ", CuisineType=" + cuisineType + ", DeliveryTime=" + DeliveryTime + ", AdminUserId="
				+ AdminUserId + ", rating=" + rating + ", isActive=" + isActive + ", ImagePath=" + imagePath + "]";
	}
     public Restaurant() {
    	 
     }
}
