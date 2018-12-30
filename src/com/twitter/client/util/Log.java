package com.twitter.client.util;

public class Log {

	public static void debug(String log) {
		System.out.println(log);
	}
	
	public static void info(String log) {
		System.out.println(log);
	}

	public static void warn(String log) {
		System.err.println(log);
	}
	
	public static void error(String log) {
		System.err.println(log);
	}
	
}
