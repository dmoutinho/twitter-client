package com.twitter.client.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.twitter.client.exception.HTTPRequestAuthorizationException;
import com.twitter.client.exception.HTTPRequestException;
import com.twitter.client.exception.HTTPRequestToException;
import com.twitter.client.model.authentication.Authentication;
import com.twitter.client.util.Log;
import com.twitter.client.util.Validator;

public class HTTPRequest {

	private final static String MESSAGE_TO_UNDEFINED = "HTTPRequest.to() is undefined.";
	
	private final static String METHOD_GET = "GET";
	private final static String METHOD_POST = "POST";
	
	private String to;
	private HttpURLConnection connection;
	private Authentication authentication;
	
	public HTTPRequest to(String to) {
		this.to = to;
		return this;
	}
	
	private void initConnection() throws HTTPRequestException, HTTPRequestToException, HTTPRequestAuthorizationException {
	    
		Log.info("HTTPRequest - initConnection - init");
		
		try {
			
			if( !Validator.isNullOrEmpty(this.to) ) {
				
				Log.info("HTTPRequest - initConnection - to: "+this.to);
				
			    this.connection = (HttpURLConnection) (new URL(this.to)).openConnection();							
			
			} else {
				throw new HTTPRequestToException(MESSAGE_TO_UNDEFINED);
			}
			
			
			if( !Validator.isNull(this.authentication) ) {
				this.authentication.authorization(this.connection);
			}
			
		} catch (IOException e) {
			throw new HTTPRequestException(e.getMessage(),e.getCause());
		}
		
		Log.info("HTTPRequest - initConnection - end");
		
	}
	
	private HTTPResponse invoke(String method) throws HTTPRequestException, 
		HTTPRequestToException, HTTPRequestAuthorizationException {

		Log.info("HTTPRequest - invoke - init");
		
		HTTPResponse httpResponse = null;
		
		try {
			
		    String response  = new BufferedReader(new InputStreamReader(this.connection.getInputStream())).readLine();			
		
		    httpResponse = new HTTPResponse(HTTPResponse.HTTP_SUCCCES,"",response);
		    
			Log.info("HTTPRequest - invoke - httpResponse: "+httpResponse);
			
		} catch (IOException e) {
			
			Log.info("HTTPRequest - invoke - error: "+e.getMessage());
		
			httpResponse = new HTTPResponse(HTTPResponse.HTTP_ERROR,e.getMessage(),"");
		
		}
				
		Log.info("HTTPRequest - invoke - end");
		
		return httpResponse;
		
	}
	
	public HTTPRequest authentication(Authentication authentication) {
		Log.info("HTTPRequest - authentication - init");
		this.authentication = authentication;
		Log.info("HTTPRequest - authentication - end");
		return this;
	}
	
	public HTTPResponse post(String body) throws HTTPRequestException, 
		HTTPRequestToException, HTTPRequestAuthorizationException {
		
		Log.info("HTTPRequest - post - init");
		
		this.initConnection();

	    this.connection.setDoOutput(true);
	    
	    try {
			
	    	DataOutputStream out = new DataOutputStream(this.connection.getOutputStream());
			out.writeBytes(body);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			throw new HTTPRequestException(e.getMessage(),e.getCause());
		}
		
		Log.info("HTTPRequest - post - end");
		
		return this.invoke(METHOD_POST);
	
	}

	public HTTPResponse get(Map<String,String> params) throws HTTPRequestException, 
		HTTPRequestToException, HTTPRequestAuthorizationException  {

		Log.info("HTTPRequest - get - init");

		if( !Validator.isNullOrEmpty(params) ) {
			
			StringBuilder strB = new StringBuilder();
			
			strB.append(this.to);
			strB.append("?");

			for(String key : params.keySet()) {
				strB.append(key + "=" + params.get(key) + "&");
			}
			
			this.to = strB.toString(); 
			
		}
		
		this.initConnection();

		Log.info("HTTPRequest - get - end");
		
		return this.invoke(METHOD_GET);

	}	
	
}