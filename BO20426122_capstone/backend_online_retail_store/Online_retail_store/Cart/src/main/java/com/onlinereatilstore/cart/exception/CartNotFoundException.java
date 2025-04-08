package com.onlinereatilstore.cart.exception;

/**
 * Exception to be thrown when a cart is not found.
 */
public class CartNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CartNotFoundException(String message) {
        super(message);
    }
}
