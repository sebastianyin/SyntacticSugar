package com.syntacticsugar.vooga.util.properties;

import java.util.ResourceBundle;

public class PropertiesManager {
	
	ResourceBundle myProperties;
	
	public PropertiesManager(String propertiesUrl){
		myProperties = ResourceBundle.getBundle(propertiesUrl);
	}
	
	public String getProperty(String key){
		return myProperties.getString(key);
	}
	
	public double getDoubleProperty(String key){
		return Double.parseDouble(getProperty(key));
	}
}
