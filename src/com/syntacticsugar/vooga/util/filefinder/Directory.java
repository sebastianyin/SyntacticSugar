package com.syntacticsugar.vooga.util.filefinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Directory {
	private List<Directory> directories;
	private List<File> files;
	private String currentPath;
	private File currentFolder;

	public Directory(String path) throws IOException {
		this(path, 0);
	}

	/**
	 * 
	 * @param path
	 *            : directory path
	 * @param sort
	 *            (1: lastModified - recent first) <br/>
	 *            (-1: lastModified - old first) <br/>
	 *            (3: alphabetical)
	 * @throws IOException
	 */
	public Directory(String path, int sort) throws IOException {
		currentPath = path;
		currentFolder = new File(currentPath);
		directories = new ArrayList<Directory>();
		files = new ArrayList<File>();
		File[] listOfFiles = currentFolder.listFiles();
				
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i]);
			} else if (listOfFiles[i].isDirectory()) {
				Directory cDir = new Directory(listOfFiles[i].getCanonicalPath());
				directories.add(cDir);
			}
		}

		if (Math.abs(sort) == 1) {
			Collections.sort(files, new Comparator<File>() {
				public int compare(File f1, File f2) {
					return Long.compare(f1.lastModified(), f2.lastModified());
				}
			});
		} else if (Math.abs(sort) == 3) {
			Collections.sort(files, null);
		} else {
			Collections.sort(files, null);
		}

		if (sort < 0) {
			Collections.reverse(files);
		}
	}

	/**
	 * 
	 * @param ext
	 * @return all Directories in current Directory
	 */
	public List<Directory> getDirectories() {
		return directories;
	}

	/**
	 * 
	 * @param ext
	 * @return all files in the current directory
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * 
	 * @param ext
	 * @return all files with extension in the current directory
	 */
	public List<File> getFilesByExtension(String ext) {
		List<File> filesWithExtension = new ArrayList<File>();
		String[] validExt = extValidate(ext);
		for (int i = 0; i < files.size(); i++) {
			String filename = files.get(i).getName();
			if (filename.endsWith(validExt[0]) || filename.endsWith(validExt[1])) {
				filesWithExtension.add(files.get(i));
			}
		}
		return filesWithExtension;
	}

	/**
	 * 
	 * @param ext
	 * @return all files with extensions in the current directory
	 */
	public List<File> getFilesByExtensions(Set<String> ext) {
		List<File> filesWithExtensions = new ArrayList<File>();
		Set<String> validExt = new HashSet<String>();
		for (String s : ext) {
			String[] vExt = extValidate(s);
			validExt.addAll(Arrays.asList(vExt));
		}
		for (int i = 0; i < files.size(); i++) {
			String filename = files.get(i).getName();
			for (String s : validExt) {
				if (filename.endsWith(s)) {
					filesWithExtensions.add(files.get(i));
				}
			}
		}
		return filesWithExtensions;
	}

	/**
	 * 
	 * @param ext
	 * @return all files with string in name in the current directory
	 */
	public List<File> getFilesByContains(String str) {
		List<File> filesWithStr = new ArrayList<File>();
		for (int i = 0; i < files.size(); i++) {
			String filename = files.get(i).getName();
			if (filename.contains(str)) {
				filesWithStr.add(files.get(i));
			}
		}
		return filesWithStr;
	}
	
	/**
	 * 
	 * @param ext
	 * @return file called string in the current directory
	 */
	public File getFileByName(String str) {
		for (int i = 0; i < files.size(); i++) {
			String filename = files.get(i).getName();
			if (filename.equals(str)) {
				return files.get(i);
			}
		}
		return null;
	}

	public boolean hasDirectories() {
		return !directories.isEmpty();
	}

	/**
	 * adds a . to the extension if no dot is added makes ext lower case
	 */
	private String[] extValidate(String ext) {
		String[] validExt = new String[2];
		ext = ext.toLowerCase();
		if (ext.charAt(0) == '.') {
			validExt[0] = ext;
			validExt[1] = ext.toUpperCase();
			return validExt;
		}
		validExt[0] = "." + ext;
		validExt[1] = "." + ext.toUpperCase();
		return validExt;
	}
}
