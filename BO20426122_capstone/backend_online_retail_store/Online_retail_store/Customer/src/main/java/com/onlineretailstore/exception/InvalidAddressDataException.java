package com.onlineretailstore.exception;

public class InvalidAddressDataException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidAddressDataException(String message) {
        super(message);
    }
}
