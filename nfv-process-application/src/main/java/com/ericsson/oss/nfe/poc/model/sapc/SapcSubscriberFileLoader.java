package com.ericsson.oss.nfe.poc.model.sapc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.model.FileLoader;
import com.ericsson.oss.nfe.poc.model.Line;

public class SapcSubscriberFileLoader implements FileLoader{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static String EXECUTED_FILE_MARKER = ".executed";
	public static String SAPC_LINE_SPLITTER = ",";
	
	@Override
	public void loadFile(File file, List<Line> fileConent){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			SapcSubscriberLine sapcSubscriberLine;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.equalsIgnoreCase("")){
					logger.error("invalid sapc line [contains no content], skipping line , [{}]" , line);
					continue;
				}
				if(!line.contains(SAPC_LINE_SPLITTER)){
					logger.error("invalid sapc line [contains invalid/no splitter], skipping line , [{}]" , line);
					continue;
				}
				String[] lineArray = line.split(SAPC_LINE_SPLITTER);
				if(lineArray.length != 2 || lineArray[0] == null || lineArray[1] == null){
					logger.error("invalid sapc line [has to contain 2 not null elements 1-subscriber id(numeric) 2-groupId, splitted by "
							+ SAPC_LINE_SPLITTER + "  ] skipping line , [{}]" , line);
					continue;
				}
				sapcSubscriberLine = new SapcSubscriberLine(lineArray[0], lineArray[1]);
				if(sapcSubscriberLine.isValid()){
					logger.trace("Valid file content.");
					fileConent.add(sapcSubscriberLine);	
				}else{
					logger.error("invalid sapc line [line has to contain subscriber id(numeric) "
							+ SAPC_LINE_SPLITTER + "  and groupId] , [{}]" , line);
				}
			}
			bufferedReader.close();
		} catch (Exception exception) {
			logger.error("Exception received processing sapc input file : " , exception);
		}
	}

	@Override
	public void renameFile(File file) {
		file.renameTo(new File(file.getAbsolutePath() + EXECUTED_FILE_MARKER));
	}

	@Override
	public File[] listFiles(File directory) {
		if(!directory.isDirectory()){
			logger.error("[{}] This is not a directory " , directory == null ? "" : directory.getName());
			return null;
		}
		
		File[] listOfFiles = directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.endsWith(EXECUTED_FILE_MARKER);
			}
		});
		
		if(listOfFiles == null || listOfFiles.length == 0){
			logger.error("no files under directory.");
			return null;
		}
		return listOfFiles;
	}

	@Override
	public String getName() {
		return "sapc";
	}
}
