package com.ericsson.oss.nfe.poc.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.ericsson.oss.nfe.poc.model.sapc.SapcSubscriberFileLoader;

public abstract class FileLoaderFactory {

	private static final Map<String, FileLoader> fileLoaderMap;

	static {
		fileLoaderMap = new HashMap<String, FileLoader>();
		final ServiceLoader<FileLoader> loader = ServiceLoader.load(FileLoader.class);
		for (final FileLoader fileLoader : loader) {
			fileLoaderMap.put(fileLoader.getName(), fileLoader);
		}
	}

	public static FileLoader instance(final String fileLoaderType){
		FileLoader fileLoader = fileLoaderMap.get(fileLoaderType);
		if(fileLoader == null){
			return new SapcSubscriberFileLoader(); 
		}
		return fileLoader;
	}

}
