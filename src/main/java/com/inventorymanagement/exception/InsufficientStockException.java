package com.inventorymanagement.exception;

/**
 * Custom exception class to handle scenarios where a product has insufficient
 * stock.
 */
public class InsufficientStockException extends RuntimeException {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new InsufficientStockException with the specified detail
	 * message. This message provides information about the reason for the
	 * exception, such as insufficient stock for a requested operation.
	 */
	public InsufficientStockException(final String message) {
		super(message);
	}

}
