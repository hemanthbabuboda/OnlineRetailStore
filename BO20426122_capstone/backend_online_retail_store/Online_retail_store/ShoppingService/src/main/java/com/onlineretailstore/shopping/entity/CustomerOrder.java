package com.onlineretailstore.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerOrder {

	@Id
	private Integer customerId;
	@OneToMany(cascade = { CascadeType.MERGE })
	private List<Order> order = new ArrayList<Order>();

	public CustomerOrder() {
	}

	public CustomerOrder(Integer customerId, List<Order> order) {
		super();
		this.customerId = customerId;
		this.order = order;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "CustomerOrder [customerId=" + customerId + ", order=" + order + "]";
	}

}
