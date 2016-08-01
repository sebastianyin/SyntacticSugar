package com.syntacticsugar.vooga.util.dirview;

import java.io.File;
import java.util.Collection;

public interface IConverter<T> {

	/**
	 * Return small data representations of the contents within a directory.
	 * The purpose of this interface is to create data structures that can be
	 * easily converted to visual representations (strings, images, etc), instead
	 * of trying to process and represent entire arbitrary File objects.
	 * 
	 * This method can be overwritten to only grab Files of a certain extension, 
	 * and returns arbitrary data representations of each File according to the
	 * application's needs.
	 * 
	 * @param directory
	 * @return
	 */
	public Collection<T> getContents(File directory);
	
}
