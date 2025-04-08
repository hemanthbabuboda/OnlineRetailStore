package com.onlineretailstore.inventory.exception;

public class InvalidInventoryDataException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInventoryDataException(String message) {
        super(message);
    }
}