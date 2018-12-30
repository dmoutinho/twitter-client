package com.twitter.client.model.authentication;

import java.net.HttpURLConnection;

import com.twitter.client.exception.HTTPRequestAuthorizationException;

public abstract class Authentication {

	public abstract HttpURLConnection authorization(HttpURLConnection httpURLConnection) throws HTTPRequestAuthorizationException;
	
	public HttpURLConnection authorization(HttpURLConnection httpURLConnection, String autorization) {
		httpURLConnection.addRequestProperty("Authorization",autorization);
		return httpURLConnection;
	}
	
}