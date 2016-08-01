package com.syntacticsugar.vooga.util.webconnect;

import java.io.File;
import java.nio.file.Files;

public class FileHelper {
	public static void main(String args[]) {

		File selectedFile = new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		
		readFile(selectedFile);
		
	}

	public static String readFile(File f) {
		try {
			byte[] inputBytes = Files.readAllBytes(f.toPath());
			return new String(inputBytes);
		} catch (Exception e) {

		}
		return "";
	}
}
