package com.onlineretailstore.shopping.entity;

public class Product {

	public Product() {
	}

	private Integer productId;
	private String productName;
	private String productDescription;
	private Double productPrice;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getproductDescription() {
		return productDescription;
	}

	public void setproductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getproductPrice() {
		return productPrice;
	}

	public void setproductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Product(Integer productId, String productName, String productDescription, Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	public Product(String productName, String productDescription, Double productPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + "]";
	}

}
