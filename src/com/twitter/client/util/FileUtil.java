package com.twitter.client.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class FileUtil {

	public static void save(List<String> lines, String filePath) throws IOException {
		new File(filePath).createNewFile();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
			for(String line : lines) {
				writer.write(line+"\n");
			}
			writer.flush();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}