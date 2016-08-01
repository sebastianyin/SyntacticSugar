package com.syntacticsugar.vooga.xml;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;

import com.syntacticsugar.vooga.util.ResourceManager;

public class XMLFileFilter implements FileFilter {

	private static final Collection<String> IMAGE_FILE_EXTENSIONS = 
			Arrays.asList(ResourceManager.getString("xml-extensions").split(","));

	@Override
	public boolean accept(File pathname) {
		String ext = pathname.getPath().replaceAll("^.*\\.(.*)$", "$1").toLowerCase();
		return IMAGE_FILE_EXTENSIONS.contains(ext);
	}

}
