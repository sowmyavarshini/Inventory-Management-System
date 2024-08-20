package com.inventorymanagement.exception;

/**
 * Custom exception class to handle specific scenarios where a custom error
 * needs to be reported. This extends RuntimeException to indicate that it is an
 * unchecked exception.
 */
public class CustomException extends RuntimeException {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for CustomException with a detailed message.
	 * 
	 * @param message The detail message explaining the reason for the exception.
	 */
	public CustomException(final String message) {
		super(message);
	}

	/**
	 * Constructor for CustomException with a detailed message and a cause.
	 * 
	 * @param message The detail message explaining the reason for the exception.
	 * @param cause   The cause of the exception, which is another throwable that
	 *                triggered this exception.
	 */

}
