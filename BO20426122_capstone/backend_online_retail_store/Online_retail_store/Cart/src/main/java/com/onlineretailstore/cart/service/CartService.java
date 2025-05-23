package com.onlineretailstore.cart.service;

import com.onlinereatilstore.cart.entity.Cart;
import com.onlinereatilstore.cart.exception.CartNotFoundException;

/**
 * Service interface for managing shopping carts.
 */
public interface CartService {

	/**
	 * Adds a new cart.
	 *
	 * @param cust the cart to be added
	 * @return the added cart
	 */
	Cart addCart(Cart cust);

	/**
	 * Searches for a cart by ID.
	 *
	 * @param cartId the ID of the cart to search for
	 * @return the cart if found
	 * @throws CartNotFoundException if the cart is not found
	 */
	Cart searchCart(Integer cartId) throws CartNotFoundException;

	/**
	 * Updates an existing cart by ID.
	 *
	 * @param cartId the ID of the cart to update
	 * @param cart   the updated cart information
	 * @return the updated cart
	 * @throws CartNotFoundException if the cart is not found
	 */
	Cart updateCart(Integer cartId, Cart cart) throws CartNotFoundException;

	/**
	 * Deletes a cart by ID.
	 *
	 * @param cartId the ID of the cart to delete
	 * @return a message indicating the result
	 * @throws CartNotFoundException if the cart is not found
	 */
	String deleteCart(Integer cartId) throws CartNotFoundException;
}
