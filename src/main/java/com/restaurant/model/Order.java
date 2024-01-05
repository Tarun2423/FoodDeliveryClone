package com.restaurant.model;

public class Order {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
int id;
	String name,customer,items,amount,status;


	public Order(int id, String name, String customer, String items, String amount) {
		super();
		this.id = id;
		this.name = name;
		this.customer = customer;
		this.items = items;
		this.amount = amount;
	}

	public Order(int id, String name, String customer, String items, String amount,String status) {
		super();
		this.id = id;
		this.name = name;
		this.customer = customer;
		this.items = items;
		this.amount = amount;
		this.status=status;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
