package com.ericsson.oss.nfe.poc.model;

import java.io.File;
import java.util.List;

public interface FileLoader {
	
	/**
	 * process @file and loads its content to collection @fileContent
	 * 
	 * @param file
	 * @param fileConent
	 */
	void loadFile(File file, List<Line> fileContent);
	
	/**
	 * after process finished, renamed files
	 * 
	 * @param file
	 */
	void renameFile(File file);
	
	/**
	 * applies specific filter and lists all files under the directory. 
	 * 
	 * @param directory
	 * @return
	 */
	File[] listFiles(File directory);

	
	/**
	 * 
	 * @return name of the FileLoader
	 */
	String getName();
}
