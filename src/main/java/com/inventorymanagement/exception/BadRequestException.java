package com.inventorymanagement.exception;

/**
 * Custom exception class to handle scenarios where a bad request is made. This
 * exception extends the base Exception class to indicate that it is a checked
 * exception.
 */
public class BadRequestException extends Exception {

	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for BadRequestException.
	 */
	public BadRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with a detailed message, cause, suppression enabled/disabled, and
	 * stack trace writable/disabled.
	 * 
	 */
	public BadRequestException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with a detailed message and cause.
	 * 
	 * @param message The detail message explaining the reason for the exception.
	 * @param cause   The cause of the exception.
	 */
	public BadRequestException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with a detailed message.
	 * 
	 * @param message The detail message explaining the reason for the exception.
	 */
	public BadRequestException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with a cause.
	 * 
	 * @param cause The cause of the exception.
	 */
	public BadRequestException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
