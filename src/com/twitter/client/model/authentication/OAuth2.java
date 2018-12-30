package com.twitter.client.model.authentication;

import java.net.HttpURLConnection;

import com.twitter.client.exception.HTTPRequestAuthorizationException;
import com.twitter.client.exception.HTTPRequestException;
import com.twitter.client.exception.HTTPRequestToException;
import com.twitter.client.http.HTTPRequest;
import com.twitter.client.http.HTTPResponse;
import com.twitter.client.util.JsonUtil;
import com.twitter.client.util.Log;
import com.twitter.client.util.Validator;

public class OAuth2 extends Authentication {

	private String apiKey;
	private String apiSecreteKey;
	private String autthenticatioURL; 
	
	private String bearer;
	
	private String body;
	
	public OAuth2(String apiKey, String apiSecreteKey, String autthenticatioURL) {
		super();
		this.apiKey = apiKey;
		this.apiSecreteKey = apiSecreteKey;
		this.autthenticatioURL = autthenticatioURL;
		this.body = "";
	}
	
	@Override
	public HttpURLConnection authorization(HttpURLConnection httpURLConnection) throws HTTPRequestAuthorizationException {
		
		Log.info("OAuth2 - authorization - init");
		
		if( Validator.isNull(this.bearer) ) {
			
			try {
				
				HTTPResponse response = new HTTPRequest().authentication(new Basic(this.apiKey,this.apiSecreteKey))
															.to(this.autthenticatioURL)
															.post(body);
			
				this.bearer = JsonUtil.toJSON(response.getPayload()).getString("access_token");

				Log.info("OAuth2 - authorization - bearer: "+this.bearer);
				
			} catch (HTTPRequestException | HTTPRequestToException e) {
				throw new HTTPRequestAuthorizationException(e.getMessage());
			}
			
		}
		
		super.authorization(httpURLConnection,"Bearer "+this.bearer);

		Log.info("OAuth2 - authorization - end");
		
		return httpURLConnection;
		
	}

	public OAuth2 body(String body) {
		Log.info("OAuth2 - body - init");
		this.body = body;
		Log.info("OAuth2 - body - end");
		return this;
	}
	
}