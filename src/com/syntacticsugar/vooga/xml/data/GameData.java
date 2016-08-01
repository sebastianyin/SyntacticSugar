package com.syntacticsugar.vooga.xml.data;

import java.util.Collection;

public class GameData {
	private Collection<UniverseData> universes;
	private GlobalSettings settings;
	private String name;
	private String backgroundImage;
	
	public GameData(Collection<UniverseData> data, GlobalSettings s) {
		universes = data;
		settings = s;
	}
	
	public Collection<UniverseData> getUniverses() {
		return universes;
	}
	
	public GlobalSettings getSettings() {
		return settings;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public void setBackgroundImage(String path){
		backgroundImage = path;
	}
	
	public String getBackgroundImage(){
		return backgroundImage;
	}
}
