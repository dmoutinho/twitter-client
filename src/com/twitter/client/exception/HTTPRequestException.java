package com.twitter.client.exception;

public class HTTPRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public HTTPRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPRequestException(String message) {
		super(message);
	}

	public HTTPRequestException(Throwable cause) {
		super(cause);
	}

}