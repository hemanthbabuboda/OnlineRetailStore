package com.onlineretailstore.shopping.entity;

public class ProductWrapper {

	String productName;
	String productDescription;
	Double productPrice;
	Integer quantity;

	public ProductWrapper() {
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductWrapper(String productName, String productDescription, Double productPrice, Integer quantity) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductWrapper [productName=" + productName + ", productDescription=" + productDescription
				+ ", productPrice=" + productPrice + ", quantity=" + quantity + "]";
	}

}
