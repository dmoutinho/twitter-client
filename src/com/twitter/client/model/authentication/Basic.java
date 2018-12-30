package com.twitter.client.model.authentication;

import java.net.HttpURLConnection;
import java.util.Base64;

import com.twitter.client.util.Log;

public class Basic extends Authentication {

	private String user;
	private String password;
	
	public Basic(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	@Override
	public HttpURLConnection authorization(HttpURLConnection httpURLConnection) {
		Log.info("Basic - authorization - init");
		super.authorization(httpURLConnection,"Basic " + Base64.getEncoder().encodeToString((this.user+":"+this.password).getBytes()));
		Log.info("Basic - authorization - end");
		return httpURLConnection;
	}
    
}
