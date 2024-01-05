package com.restaurant.model;

public class Restaurant {
int id;
	String name,location,contact,rating,image,highlight,displayimages;


	public String getDisplayimages() {
		return displayimages;
	}

	public void setDisplayimages(String displayimages) {
		this.displayimages = displayimages;
	}

	public Restaurant(int id, String name, String location, String contact, String rating, String image,
			String highlight, String displayimages) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.contact = contact;
		this.rating = rating;
		this.image = image;
		this.highlight = highlight;
		this.displayimages = displayimages;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
