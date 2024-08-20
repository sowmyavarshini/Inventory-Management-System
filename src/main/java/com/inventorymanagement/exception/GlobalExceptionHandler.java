package com.inventorymanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

/**
 * Global exception handler for handling exceptions across the entire
 * application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Default constructor for GlobalExceptionHandler.
	 */
	public GlobalExceptionHandler() {
		// Default constructor for initializing the GlobalExceptionHandler.
	}
	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }

	    @ExceptionHandler(PasswordMismatchException.class)
	    public ResponseEntity<String> handlePasswordMismatchException(PasswordMismatchException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	    }

	/**
	 * Handles validation exceptions thrown when method arguments are not valid.
	 * 
	 * @param exception The MethodArgumentNotValidException exception.
	 * @return A response entity containing a map of field errors.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(
			final MethodArgumentNotValidException exception) {
		final Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			final String fieldName = ((FieldError) error).getField();
			final String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		if (LOG.isErrorEnabled()) {
			LOG.error("Validation failed: {}", errors);
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Data integrity violation: {}", ex.getMessage());
		}
		Map<String, String> errorMessages = new HashMap<>();
		errorMessages.put("error", ex.getMessage());
		return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles ResourceNotFoundException exceptions.
	 * 
	 * @param exception The ResourceNotFoundException exception.
	 * @return A response entity containing the error message and HTTP status
	 *         NOT_FOUND.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(final ResourceNotFoundException exception) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Resource not found: {}", exception.getMessage());
		}
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles BadRequestException exceptions.
	 * 
	 * @param exception The BadRequestException exception.
	 * @return A response entity containing the error message and HTTP status
	 *         BAD_REQUEST.
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(final BadRequestException exception) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Bad request: {}", exception.getMessage());
		}
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles DuplicateEntryException exceptions.
	 * 
	 * @param exception The DuplicateEntryException exception.
	 * @return A response entity containing the error message and HTTP status
	 *         CONFLICT.
	 */
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<String> handleDuplicateEntryException(final DuplicateEntryException exception) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Duplicate entry: {}", exception.getMessage());
		}
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}

	/**
	 * Handles RuntimeException exceptions.
	 * 
	 * @param exception The RuntimeException exception.
	 * @return A response entity containing the error message and HTTP status
	 *         INTERNAL_SERVER_ERROR.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(final RuntimeException exception) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Runtime exception: {}", exception.getMessage());
		}
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles generic Exception exceptions.
	 * 
	 * @param exception The Exception exception.
	 * @return A response entity containing an error response and HTTP status
	 *         INTERNAL_SERVER_ERROR.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(final Exception exception) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Unexpected error occurred: {}", exception.getMessage());
		}
		final ErrorResponse errorResponse = new ErrorResponse("Unexpected error occurred: " + exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles MethodArgumentTypeMismatchException exceptions.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
			final MethodArgumentTypeMismatchException exception) {
		final String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Expected type is '%s'.",
				exception.getValue(), exception.getName(), exception.getRequiredType().getSimpleName());
		if (LOG.isErrorEnabled()) {
			LOG.error("Method argument type mismatch: {}", errorMessage);
		}
		final ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomException.class)
	public String handleCustomException(CustomException ex) {
		if (LOG.isErrorEnabled()) {
			LOG.error("Custom exception: {}", ex.getMessage());
		}
		return ex.getMessage();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errorMessages = new HashMap<>();
		ex.getConstraintViolations().forEach(violation -> {
			String propertyPath = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			errorMessages.put(propertyPath, errorMessage);
		});

		if (LOG.isErrorEnabled()) {
			LOG.error("Constraint violation: {}", errorMessages);
		}

		return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
	}

}
