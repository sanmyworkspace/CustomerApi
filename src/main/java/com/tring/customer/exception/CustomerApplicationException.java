package com.tring.customer.exception;

/**
 * @author akula
 * This is an application specific exception which can be used to wrap and 
 * propagate all kinds of exceptions through different layers
 */
public class CustomerApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private String errorCode;

	public CustomerApplicationException() {
	}

	public CustomerApplicationException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public CustomerApplicationException(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
