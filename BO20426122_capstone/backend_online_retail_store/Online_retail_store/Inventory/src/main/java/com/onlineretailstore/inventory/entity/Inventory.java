package com.onlineretailstore.inventory.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Inventory implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @NotNull(message = "Product ID is required.")
    @Min(value = 1, message = "Product ID must be greater than 0.")
    private Integer productId;

    @NotNull(message = "Quantity is required.")
    @Min(value = 0, message = "Quantity must be 0 or greater.")
    private Integer quantity;

    public Inventory() {
		super();
	}

	public Inventory(Integer inventoryId,
			@NotNull(message = "Product ID is required.") @Min(value = 1, message = "Product ID must be greater than 0.") Integer productId,
			@NotNull(message = "Quantity is required.") @Min(value = 0, message = "Quantity must be 0 or greater.") Integer quantity) {
		super();
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, productId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(inventoryId, other.inventoryId) && Objects.equals(productId, other.productId)
				&& Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}

	// Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
