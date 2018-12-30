package com.twitter.client.util;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonUtil {

	public static JsonObject toJSON(String strJSON) {
		 JsonReader jsonReader = Json.createReader(new StringReader(strJSON));
		 JsonObject jsonObject = jsonReader.readObject();
		 jsonReader.close();
		 return jsonObject;
	}
	
}