package com.inventorymanagement.exception;

/**
 * Custom exception class to handle resource not found scenarios.
 */
public class ResourceNotFoundException extends Exception {

	/**
	 * Serial Version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with all parameters.
	 * 
	 * @param message            The detail message.
	 * @param cause              The cause of the exception.
	 * @param enableSuppression  Whether or not suppression is enabled or disabled.
	 * @param writableStackTrace Whether or not the stack trace should be writable.
	 */
	public ResourceNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with message and cause.
	 * 
	 * @param message The detail message.
	 * @param cause   The cause of the exception.
	 */
	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with message.
	 * 
	 * @param message The detail message.
	 */
	public ResourceNotFoundException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with cause.
	 * 
	 * @param cause The cause of the exception.
	 */
	public ResourceNotFoundException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
