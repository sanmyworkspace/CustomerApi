package com.tring.customer.rest.exception;

/**
 * @author akula
 *
 */
public class CustomerResourceException extends RuntimeException {

	/**
	 * User friendly exception message which can be send in response
	 */
	private String errorMessage;
	/**
	 * It can be application specific exception errorCode or errorCode return by Jaxrs service
	 */
	private int errorCode;

	public CustomerResourceException() {
	}

	public CustomerResourceException(String errorMessage, int errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
