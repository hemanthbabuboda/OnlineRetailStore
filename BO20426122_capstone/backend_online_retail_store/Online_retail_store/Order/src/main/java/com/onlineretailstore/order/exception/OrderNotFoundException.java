package com.onlineretailstore.order.exception;

/**
 * Custom exception thrown when an order is not found.
 */
public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
        super(message);
    }
}
