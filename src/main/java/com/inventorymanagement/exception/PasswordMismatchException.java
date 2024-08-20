package com.inventorymanagement.exception;

public class PasswordMismatchException extends RuntimeException {
	public PasswordMismatchException(String username) {
		super("Password mismatch for user: " + username);
	}
}
