package com.twitter.client.consumer;

import java.util.HashMap;
import java.util.Map;

import com.twitter.client.config.TwitterClientConfig;
import com.twitter.client.http.HTTPRequest;
import com.twitter.client.http.HTTPResponse;
import com.twitter.client.model.authentication.OAuth2;
import com.twitter.client.util.Log;

public class TwitterFollowers {

	public static final String API_KEY = TwitterClientConfig.get("api.key");
	public static final String API_SECRETE_KEY = TwitterClientConfig.get("api.secrete.key");
	
	public static final String AUTHENTICATION_URL = TwitterClientConfig.get("authentication.url");
	
	public final static String FOLLOWERS_URL = "https://api.twitter.com/1.1/followers/list.json";

	public static void main(String[] args) {
		
		try {

			Map<String,String> params = new HashMap<>();
			params.put("cursor","1619764388236320894");
			params.put("screen_name","dmoutinho");
			params.put("skip_status","false");
			params.put("include_user_entities","true");
			params.put("count","1");
			
			HTTPResponse response = (new HTTPRequest())
				.authentication(new OAuth2(API_KEY,API_SECRETE_KEY,AUTHENTICATION_URL).body("grant_type=client_credentials"))
				.to(FOLLOWERS_URL)
				.get(params);
			
			Log.info(response.getPayload());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}