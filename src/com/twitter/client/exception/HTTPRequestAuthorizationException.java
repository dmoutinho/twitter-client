package com.twitter.client.exception;

public class HTTPRequestAuthorizationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public HTTPRequestAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPRequestAuthorizationException(String message) {
		super(message);
	}

	public HTTPRequestAuthorizationException(Throwable cause) {
		super(cause);
	}

}