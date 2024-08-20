package com.inventorymanagement.exception;

/**
 * Custom exception class to handle scenarios where there is an attempt to
 * create or insert a duplicate entry in the system.
 */
public class DuplicateEntryException extends RuntimeException {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for DuplicateEntryException.
	 * 
	 * @param message The detail message explaining the reason for the exception.
	 */
	public DuplicateEntryException(final String message) {
		super(message);
	}
}
