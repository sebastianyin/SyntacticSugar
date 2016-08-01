package com.syntacticsugar.vooga.xml;

import java.io.File;

public interface IXML<T> {
	
	public void write(T object, File destination);
	
	public T read(File f);
}
