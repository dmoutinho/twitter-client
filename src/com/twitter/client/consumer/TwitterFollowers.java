package com.twitter.client.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.twitter.client.config.FollowersConfig;
import com.twitter.client.exception.HTTPRequestAuthorizationException;
import com.twitter.client.exception.HTTPRequestException;
import com.twitter.client.exception.HTTPRequestToException;
import com.twitter.client.http.HTTPRequest;
import com.twitter.client.http.HTTPResponse;
import com.twitter.client.model.authentication.Authentication;
import com.twitter.client.util.FileUtil;
import com.twitter.client.util.JsonUtil;
import com.twitter.client.util.Log;

public class TwitterFollowers {
	
	private final static String FOLLOWERS_URL = "https://api.twitter.com/1.1/followers/list.json";
	
	private static final String FOLLOWERS_PATH = "/home/dmoutinhowork/Documents/Desenvolvimento/twitter-client/followers/";

	private static List<String> followers;
	
	private static String getFollowers(String userName, String count, String cursor) throws HTTPRequestException, 
		HTTPRequestToException, HTTPRequestAuthorizationException  {
		
		Map<String,String> params = new HashMap<>();
		params.put("cursor",cursor);
		params.put("screen_name",userName);
		params.put("skip_status","false");
		params.put("include_user_entities","true");
		params.put("count",count);
		
		HTTPResponse response = (new HTTPRequest())
										.authentication( Authentication.getOAuth2() )
										.to(FOLLOWERS_URL)
										.get(params);
		
		return response.getPayload();
		
	}

	private static void saveFollowers(String userName) throws IOException {
		FileUtil.save(followers,FOLLOWERS_PATH+userName);
	}	
	
	private static void loadFollowers(JsonObject json) {
		
		JsonArray users = json.getJsonArray("users");
		
		Log.info("TwitterFollowers - saveFollowers - users.size: "+users.size());
		
		for(int i=0; i<users.size(); i++) {
			String screenName = users.getJsonObject(i).getString("screen_name");
			followers.add(screenName);
		}
	
	}
	
	public static void main(String[] args) {
		
		try {
			
			Log.info("TwitterFollowers - main - init");		
			
			followers = new ArrayList<>();
			
			String userName = FollowersConfig.get("user-name");
			String count = FollowersConfig.get("count");
			String cursor = "-1";
			
			String jsonResponse = null;
			JsonObject followers = null;

			while ( !"0".equals(cursor) ) {
				
				jsonResponse = getFollowers(userName,count,cursor);

				Log.info( jsonResponse );

				Log.info("TwitterFollowers - main - jsonResponse: "+jsonResponse);
				
				followers = JsonUtil.toJSON(jsonResponse);

				loadFollowers(followers);
				
				cursor = followers.getString("next_cursor_str");
				
				Log.info("TwitterFollowers - main - next: "+cursor);				
			}
			
			Log.info("TwitterFollowers - main - follwers: "+followers.size());				
			
			Log.info("TwitterFollowers - main - follwers: "+followers);
			
			saveFollowers(userName);
			
			Log.info("TwitterFollowers - main - end");					
						
		} catch (Exception e) {
			//e.printStackTrace();
			Log.error(e.getMessage());
		} finally {
			Log.finallyLog();
		}
		
	}
	
}