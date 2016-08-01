package com.syntacticsugar.vooga.util.fileutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileHandler {
	public static void writeFile(File f, String str) {
		try {
			PrintWriter out = new PrintWriter(f.getPath());
			out.println(str);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to write XML to file");
		}
	}
}
