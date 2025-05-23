package com.onlineretailstore.shopping.entity;

import java.util.List;
import java.util.Objects;

public class Customer {

	private int customerId;
	private String customerName;
	private String customerEmail;
	private List<Address> customerBillingAddress;
	private List<Address> customerShippingAddress;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Customer() {
	}

	public Customer(int customerId, String customerName, String customerEmail, List<Address> customerBillingAddress,
			List<Address> customerShippingAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerBillingAddress = customerBillingAddress;
		this.customerShippingAddress = customerShippingAddress;
	}

	public List<Address> getCustomerBillingAddress() {
		return customerBillingAddress;
	}

	public void setCustomerBillingAddress(List<Address> customerBillingAddress) {
		this.customerBillingAddress = customerBillingAddress;
	}

	public List<Address> getCustomerShippingAddress() {
		return customerShippingAddress;
	}

	public void setCustomerShippingAddress(List<Address> customerShippingAddress) {
		this.customerShippingAddress = customerShippingAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerBillingAddress=" + customerBillingAddress + ", customerShippingAddress="
				+ customerShippingAddress + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerBillingAddress, customerEmail, customerId, customerName, customerShippingAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerBillingAddress, other.customerBillingAddress)
				&& Objects.equals(customerEmail, other.customerEmail) && customerId == other.customerId
				&& Objects.equals(customerName, other.customerName)
				&& Objects.equals(customerShippingAddress, other.customerShippingAddress);
	}

}
