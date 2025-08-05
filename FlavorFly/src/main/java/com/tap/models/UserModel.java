package com.tap.models;

import java.util.Date;
public class UserModel {
    private int userid;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private Date createdDate;
    private Date LastLoginDate;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastLoginDate() {
		return LastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}
	public UserModel(int userid, String name, String username, String password, String email, String phone,
			String address, String role, Date createdDate, Date lastLoginDate) {
		super();
		this.userid = userid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		LastLoginDate = lastLoginDate;
	}
	@Override
	public String toString() {
		return "UserModel [userid=" + userid + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdDate="
				+ createdDate + ", LastLoginDate=" + LastLoginDate + "]";
	}
    public UserModel() {
    	
    }
	public UserModel(int userid, String name, String username, String password, String email, String phone,
			String address, String role) {
		super();
		this.userid = userid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
    
}
