package com.syntacticsugar.vooga.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceManager {

	private static final ResourceBundle myResources = addBundle();
	
	private static ResourceBundle addBundle() {
		return ResourceBundle.getBundle("com/syntacticsugar/vooga/resources/Resources");
	}
	
	public static String getString(String key) {
		return myResources.getString(key);
	}
	
	public static Enumeration<String> getKeys() {
		return myResources.getKeys();
	}
	
	public static boolean containsKey(String key) {
		return myResources.containsKey(key);
	}
	
	public static InputStream getResource(Object obj, String path) {
		return obj.getClass().getClassLoader().getResourceAsStream(path);
	}
	
}
