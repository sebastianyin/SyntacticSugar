package com.syntacticsugar.vooga.util.filefinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class FileFinder {
	private final String DEFAULT_RESOURCE_PACKAGE = "com.syntacticsugar.vooga/resources/strings/";

	private Set<String> tileDirectory;
	private Set<String> enemyDirectory;
	private Set<String> towerDirectory;
	private Set<String> obstacleDirectory;
	private Set<String> projectileDirectory;
	private Set<String> playerDirectory;
	private Set<String> itemDirectory;
	private Set<String> gameDirectory;
	private Set<String> saveDirectory;

	private Set<String> imgFormats;
	private Set<String> xmlFormats;

	public FileFinder() {
		ResourceBundle fileFormatResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "FileFormats");
		ResourceBundle directoriesResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Directories");
		
		imgFormats = new HashSet<String>(Arrays.asList(fileFormatResource.getString("img").split("\\s+")));
		xmlFormats = new HashSet<String>(Arrays.asList(fileFormatResource.getString("xml").split("\\s+")));
		
		tileDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("tiles").split("\\s+")));
		enemyDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("enemies").split("\\s+")));
		towerDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("towers").split("\\s+")));
		obstacleDirectory = new HashSet<String>(
				Arrays.asList(directoriesResource.getString("obstacles").split("\\s+")));
		projectileDirectory = new HashSet<String>(
				Arrays.asList(directoriesResource.getString("projectiles").split("\\s+")));
		playerDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("players").split("\\s+")));
		itemDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("items").split("\\s+")));
		
		gameDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("xmlgames").split("\\s+")));
		saveDirectory = new HashSet<String>(Arrays.asList(directoriesResource.getString("xmlsaves").split("\\s+")));

	}
	
	public List<File> chooseXML() {
		List<File> fs = getXMLFiles(gameDirectory);
		return fs;
	}

	public List<File> getImages(String path) {
		try {
			Directory d = new Directory(path);
			return d.getFilesByExtensions(imgFormats);
		} catch (IOException e) {
			e.printStackTrace(); // TODO
		}
		return new ArrayList<File>();
	}

	public List<File> getTileImages() {
		return getBySet(tileDirectory);
	}

	public List<File> getEnemyImages() {
		return getBySet(enemyDirectory);
	}

	public List<File> getTowerImages() {
		return getBySet(towerDirectory);
	}

	public List<File> getObstacleImages() {
		return getBySet(obstacleDirectory);
	}

	public List<File> getProjectileImages() {
		return getBySet(projectileDirectory);
	}

	public List<File> getPlayerImages() {
		return getBySet(playerDirectory);
	}

	public List<File> getItemImages() {
		return getBySet(itemDirectory);
	}
	
	public List<File> getGameXMLFiles() {
		return getXMLFiles(gameDirectory);
	}
	
	public List<File> getSaveXMLFiles() {
		return getXMLFiles(saveDirectory);
	}
	
	public String getGameXML(String name) {
		return getXMLString(name, gameDirectory);
	}
	
	public String getSaveXML(String name) {
		return getXMLString(name, saveDirectory);
	}
	
	public List<String> getGameXMLsContents() {
		return readXMLs(gameDirectory);
	}
	
	public List<String> getSaveXMLsContents() {
		return readXMLs(saveDirectory);
	}

	private List<File> getBySet(Set<String> set) {
		List<File> fs = new ArrayList<File>();
		for (String s : set) {
			fs.addAll(getImages(s));
		}
		return fs;
	}
	
	private List<String> readXMLs(Set<String> directories) {
		List<String> xmls = new ArrayList<String>();
		for (String s : directories) {
			try {
				Directory d = new Directory(s);
				List<File> fs  = d.getFilesByExtensions(xmlFormats);
				for (int i=0; i<fs.size(); i++) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(fs.get(i)));
						String line;
						StringBuilder sb = new StringBuilder();

						while((line=br.readLine())!= null){
						    sb.append(line.trim());
						}
						br.close();
						xmls.add(sb.toString());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return xmls;
	}
	
	private String getXMLString(String name, Set<String> directories) {
		for (String s : directories) {
			try {
				Directory d = new Directory(s);
				File f;
				if ((f = d.getFileByName(name))!=null) {
					return fileToXML(f);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ""; // TODO
	}
	
	private List<File> getXMLFiles(Set<String> directories) {
		List<File> xmls = new ArrayList<File>();
		for (String s : directories) {
			try {
				Directory d = new Directory(s);
				List<File> fs  = d.getFilesByExtensions(xmlFormats);
				xmls.addAll(fs);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return xmls;
	}
	
	private String fileToXML(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			StringBuilder sb = new StringBuilder();

			while((line=br.readLine())!= null){
			    sb.append(line.trim());
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // TODO
	}
}
