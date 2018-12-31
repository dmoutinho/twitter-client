package com.twitter.client.config;

import java.util.Map;

public class FollowersConfig extends Config {

	private static final String PATH_CONFIG = "/home/dmoutinhowork/Documents/Desenvolvimento/twitter-client/followers.config";
	
	private static Map<String,String> config;
	
	public static String get(String key) {
		return Config.get(key,PATH_CONFIG,config);
	}
	
}