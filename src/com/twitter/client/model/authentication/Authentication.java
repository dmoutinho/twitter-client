package com.twitter.client.model.authentication;

import java.net.HttpURLConnection;

import com.twitter.client.config.TwitterClientConfig;
import com.twitter.client.exception.HTTPRequestAuthorizationException;

public abstract class Authentication {

	private static final String API_KEY = TwitterClientConfig.get("api.key");
	private static final String API_SECRETE_KEY = TwitterClientConfig.get("api.secrete.key");
	
	private static final String AUTHENTICATION_URL = TwitterClientConfig.get("authentication.url");
	
	public abstract HttpURLConnection authorization(HttpURLConnection httpURLConnection) throws HTTPRequestAuthorizationException;
	
	public HttpURLConnection authorization(HttpURLConnection httpURLConnection, String autorization) {
		httpURLConnection.addRequestProperty("Authorization",autorization);
		return httpURLConnection;
	}
	
	public static OAuth2 getOAuth2() {
		return new OAuth2(API_KEY,API_SECRETE_KEY,AUTHENTICATION_URL).body("grant_type=client_credentials");
	}
	
}