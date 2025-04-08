package com.onlineretailstore.shopping.entity;

import java.util.ArrayList;
import java.util.List;

public class CompleteCustomerOrderDetails {

	private Customer customer;
	private List<Order> orders = new ArrayList<Order>();

	public CompleteCustomerOrderDetails() {
	}

	public CompleteCustomerOrderDetails(Customer customer, List<Order> orders) {
		super();
		this.customer = customer;
		this.orders = orders;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "CompleteCustomerOrderDetails [customer=" + customer + ", orders=" + orders + "]";
	}

}
