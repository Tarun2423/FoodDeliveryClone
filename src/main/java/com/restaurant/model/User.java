package com.restaurant.model;

public class User {
String name,password,email,address,dob;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public User(String name, String password, String email, String address, String dob) {
	super();
	this.name = name;
	this.password = password;
	this.email = email;
	this.address = address;
	this.dob = dob;
}
}
