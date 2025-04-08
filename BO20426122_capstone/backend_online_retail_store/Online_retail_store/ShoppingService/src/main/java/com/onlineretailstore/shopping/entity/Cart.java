package com.onlineretailstore.shopping.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Cart {

	@Id
	private Integer cartId;
	@OneToMany(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<LineItem> lineItem = new ArrayList<LineItem>();

	public Cart() {
	}

	public Cart(Integer cartId, List<LineItem> lineItem) {
		super();
		this.cartId = cartId;
		this.lineItem = lineItem;
	}

	public Cart(List<LineItem> lineItem) {
		super();
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
	public String toString() {
		return "Cart [cartId=" + cartId + ", lineItem=" + lineItem + "]";
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

}
