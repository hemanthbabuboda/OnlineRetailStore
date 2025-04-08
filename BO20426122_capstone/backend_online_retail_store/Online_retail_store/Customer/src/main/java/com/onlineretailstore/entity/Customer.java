package com.onlineretailstore.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	@NotBlank(message = "Customer name cannot be empty")
	private String customerName;

	@NotBlank(message = "Customer email cannot be empty")
	@Email(message = "Email should be valid")
	private String customerEmail;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotEmpty(message = "At least one billing address is required")
	private List<CustomerAddress> customerBillingAddress;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotEmpty(message = "At least one shipping address is required")
	private List<CustomerAddress> customerShippingAddress;

	public Customer() {

	}

	public Customer(int customerId, @NotBlank(message = "Customer name cannot be empty") String customerName,
			@NotBlank(message = "Customer email cannot be empty") @Email(message = "Email should be valid") String customerEmail,
			@NotEmpty(message = "At least one billing address is required") List<CustomerAddress> customerBillingAddress,
			@NotEmpty(message = "At least one shipping address is required") List<CustomerAddress> customerShippingAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerBillingAddress = customerBillingAddress;
		this.customerShippingAddress = customerShippingAddress;
	}

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

	public List<CustomerAddress> getCustomerBillingAddress() {
		return customerBillingAddress;
	}

	public void setCustomerBillingAddress(List<CustomerAddress> customerBillingAddress) {
		this.customerBillingAddress = customerBillingAddress;
	}

	public List<CustomerAddress> getCustomerShippingAddress() {
		return customerShippingAddress;
	}

	public void setCustomerShippingAddress(List<CustomerAddress> customerShippingAddress) {
		this.customerShippingAddress = customerShippingAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer cust = (Customer) o;
		return Objects.equals(customerId, cust.customerId);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerBillingAddress=" + customerBillingAddress + ", customerShippingAddress="
				+ customerShippingAddress + "]";
	}
}
