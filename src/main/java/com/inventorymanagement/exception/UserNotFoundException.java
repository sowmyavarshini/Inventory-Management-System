package com.inventorymanagement.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String username) {
		super("User not found with username: " + username);
	}
}