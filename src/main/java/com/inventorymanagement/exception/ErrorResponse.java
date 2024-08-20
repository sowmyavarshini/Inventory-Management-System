package com.inventorymanagement.exception;

/**
 * ErrorResponse is used to encapsulate error messages in a standardized format
 * for API responses when an exception occurs.
 */
public class ErrorResponse {

	/**
	 * The error message to be included in the response.
	 */
	private String message;

	/**
	 * Constructor for ErrorResponse.
	 * 
	 * @param message The error message to be set.
	 */
	public ErrorResponse(final String message) {
		this.message = message;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return The error message.
	 */
	// Getter and setter
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param message The error message to be set.
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
}
