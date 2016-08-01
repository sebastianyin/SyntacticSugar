package com.syntacticsugar.vooga.xml.data;

public class GlobalSettings {

	private int level;
	
	public GlobalSettings() {
		level = 1;
	}
	
	public GlobalSettings(int l) {
		level = l;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int l) {
		level = l;
	}
	
}
