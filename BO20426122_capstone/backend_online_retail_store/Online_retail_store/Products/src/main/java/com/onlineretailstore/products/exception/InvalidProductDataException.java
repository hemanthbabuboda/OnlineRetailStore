package com.onlineretailstore.products.exception;

public class InvalidProductDataException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidProductDataException(String message) {
        super(message);
    }
}