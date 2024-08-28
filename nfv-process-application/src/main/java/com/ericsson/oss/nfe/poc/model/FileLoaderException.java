package com.ericsson.oss.nfe.poc.model;

public class FileLoaderException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FileLoaderException(){
		super();
	}
	
	public FileLoaderException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public FileLoaderException(String exceptionMessage, Exception exception){
		super(exceptionMessage, exception);
	}

}
