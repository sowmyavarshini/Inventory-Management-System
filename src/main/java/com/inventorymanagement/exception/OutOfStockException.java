package com.inventorymanagement.exception;

/**
 * Custom exception class to handle scenarios where a product is out of stock.
 */
public class OutOfStockException extends RuntimeException {
	/**
	 * Serial Version UID for Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new OutOfStockException with the specified detail message.
	 * 
	 * @param message the detail message, which is saved for later retrieval by the
	 *                {@link Throwable#getMessage()} method.
	 */
	public OutOfStockException(final String message) {
		super(message);
	}
}
