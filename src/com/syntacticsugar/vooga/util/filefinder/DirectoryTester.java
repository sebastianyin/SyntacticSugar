package com.syntacticsugar.vooga.util.filefinder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DirectoryTester {
	public static void main(String args[]) throws IOException {
		/*
		 * Directory d = new Directory("working/resources/tiles", 0); List<File>
		 * allFiles = d.getFiles(); print(allFiles);
		 * 
		 * List<File> pngFiles = d.getFilesByExtension(".png"); print(pngFiles);
		 * 
		 * List<File> pngFiles2 = d.getFilesByExtension("png");
		 * print(pngFiles2);
		 * 
		 */
		
		FileFinder f = new FileFinder();

		//List<File> images = f.getImages("working/resources/tiles");
		//print(images);
		
		//List<File> tileImages = f.getTileImages();
		//print(tileImages);
		
		List<File> enemyImages = f.getEnemyImages();
		print(enemyImages);
		
		String x = f.getGameXML("game1.xml"); 
		System.out.println(x);
		
		String y = f.getSaveXML("save1.xml"); 
		System.out.println(y);
	}

	private static void print(List<File> list) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
