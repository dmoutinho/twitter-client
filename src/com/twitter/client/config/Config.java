package com.twitter.client.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.twitter.client.util.Log;
import com.twitter.client.util.Validator;

public class Config {
	
	private static Map<String,String> loadConfig(String pathConfig, Map<String,String> config) {

		try {
			
			Properties configFile = new Properties();
			configFile.load(new FileInputStream(pathConfig));
			
			config = new HashMap<>();
			
			for( Object key: configFile.keySet() ) {
				config.put((String)key,(String)configFile.get(key));
			}
			
			
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		
		return config;
	}
	
	protected static String get(String key, String pathConfig, Map<String,String> config) {
		
		if( Validator.isNull(config) ) {
			config = loadConfig(pathConfig,config);
		}
		
		String value = config.get(key);
		
		if ( Validator.isNullOrEmpty(value) ) {
			Log.error(key + " not found.");
		}
		
		return value;
	
	}
	
}