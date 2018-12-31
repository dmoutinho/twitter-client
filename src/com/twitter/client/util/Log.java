package com.twitter.client.util;

import java.util.ArrayList;
import java.util.List;

public class Log {

	private static final String PATH_LOG = "/home/dmoutinhowork/Documents/Desenvolvimento/twitter-client/log/";
	
	private static List<String> logList = new ArrayList<>(); 
	
	public static void debug(String log) {
		System.out.println(log);
		logList.add(log);
	}
	
	public static void info(String log) {
		System.out.println(log);
		logList.add(log);	
	}

	public static void warn(String log) {
		System.err.println(log);
		logList.add(log);
	}
	
	public static void error(String log) {
		System.err.println(log);
		logList.add(log);
	}

	public static void finallyLog() {
		try {
			FileUtil.save(logList,PATH_LOG+System.currentTimeMillis()+".log");
			logList = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}