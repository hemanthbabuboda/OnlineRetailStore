package com.onlineretailstore.inventory.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Product implements Serializable{
    
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Product name is required and cannot be blank.")
    @Size(max = 25, message = "Product name cannot exceed 25 characters.")
    private String productName;

    @Size(max = 500, message = "Product description cannot exceed 500 characters.")
    private String productDescription;

    @NotNull(message = "Product price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0.")
    private Double productPrice;

    
    
    public Product() {
		super();
	}

	public Product(Integer productId,
			@NotBlank(message = "Product name is required and cannot be blank.") @Size(max = 25, message = "Product name cannot exceed 25 characters.") String productName,
			@Size(max = 500, message = "Product description cannot exceed 500 characters.") String productDescription,
			@NotNull(message = "Product price is required.") @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0.") Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productDescription, productId, productName, productPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName)
				&& Objects.equals(productPrice, other.productPrice);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + "]";
	}

	// Getters and Setters
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
}
