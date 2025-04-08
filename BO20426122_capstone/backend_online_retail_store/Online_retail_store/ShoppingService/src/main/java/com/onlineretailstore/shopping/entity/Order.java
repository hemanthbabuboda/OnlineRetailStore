package com.onlineretailstore.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "myOrder")

public class Order {

	@Id
	private Integer orderId;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<LineItem> lineItems = new ArrayList<LineItem>();

	public Order() {
	}

	public Order(Integer orderId, List<LineItem> items) {
		super();
		this.orderId = orderId;
		this.lineItems = items;
	}

	public Order(List<LineItem> items) {
		super();
		this.lineItems = items;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> items) {
		this.lineItems = items;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", lineItems=" + lineItems + "]";
	}

}
