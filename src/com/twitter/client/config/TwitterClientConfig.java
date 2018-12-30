package com.twitter.client.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.twitter.client.util.Log;
import com.twitter.client.util.Validator;

public class TwitterClientConfig {

	private static final String PATH_CONFIG = "/home/dmoutinhowork/Documents/Desenvolvimento/twitter-client/twitter-client.config";
	
	private static Map<String,String> config;
	
	private static void loadConfig() {

		try {
			
			Properties configFile = new Properties();
			configFile.load(new FileInputStream(PATH_CONFIG));
			
			config = new HashMap<>();
			
			for( Object key: configFile.keySet() ) {
				config.put((String)key,(String)configFile.get(key));
			}
			
			
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		
	}
	
	public static String get(String key) {
		
		if( Validator.isNull(config) ) {
			loadConfig();
		}
		
		String value = config.get(key);
		
		if ( Validator.isNullOrEmpty(value) ) {
			Log.error(key + "not found.");
		}
		
		return value;
	
	}
	
}