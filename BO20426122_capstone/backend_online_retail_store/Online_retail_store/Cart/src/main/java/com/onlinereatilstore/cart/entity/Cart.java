package com.onlinereatilstore.cart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer cartId;
	@OneToMany(cascade=CascadeType.ALL)
	List<LineItem> lineItem = new ArrayList<LineItem>();	

	public Cart() {
	}

	public Cart(Integer cartId, List<LineItem> lineItem) {
		super();
		this.cartId = cartId;
		this.lineItem = lineItem;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public List<LineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, lineItem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(lineItem, other.lineItem);
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", lineItem=" + lineItem + "]";
	}
	
	
}
