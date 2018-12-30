package com.twitter.client.exception;

public class HTTPRequestToException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public HTTPRequestToException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPRequestToException(String message) {
		super(message);
	}

	public HTTPRequestToException(Throwable cause) {
		super(cause);
	}

}