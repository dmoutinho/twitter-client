package com.twitter.client.util;

import java.util.Map;

public class Validator {

	public static boolean isNull(Object o) {
		return o==null;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || str.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Map str) {
		return isNull(str) || str.isEmpty();
	}
	
}