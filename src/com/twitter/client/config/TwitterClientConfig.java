package com.twitter.client.config;

import java.util.Map;

public class TwitterClientConfig extends Config {

	private static final String PATH_CONFIG = "/home/dmoutinhowork/Documents/Desenvolvimento/twitter-client/twitter-client.config";

	private static Map<String,String> config;
	
	public static String get(String key) {
		return Config.get(key,PATH_CONFIG,config);
	}
	
}