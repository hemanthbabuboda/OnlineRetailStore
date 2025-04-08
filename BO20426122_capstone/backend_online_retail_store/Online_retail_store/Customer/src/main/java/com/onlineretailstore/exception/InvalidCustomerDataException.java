package com.onlineretailstore.exception;

public class InvalidCustomerDataException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidCustomerDataException(String message) {
        super(message);
    }
}
