package com.ericsson.oss.nfe.poc.tasks;


import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.FILE_DETECTOR_TYPE;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.INPUT_DIRECTORY;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.model.FileLoader;
import com.ericsson.oss.nfe.poc.model.FileLoaderException;
import com.ericsson.oss.nfe.poc.model.FileLoaderFactory;
import com.ericsson.oss.nfe.poc.model.Line;

/**
 * 
 * File Detector is a generic file loader which detects and loads all files under directory specified at appconfig-properties(input.directory.{file.detector.type})
 * variable named 'file.detector.type' is coming from the flow and if this type not specified this is 'sapc' by default
 * 
 * input.directory.{file.detector.type} has to exist in the appconfig.properties, this will indicate input file directory name.
 * All files under this directory will be processed and logged.
 * 
 *
 */
public class FileDetector implements JavaDelegate {
	
	private Expression outputVariable;
	
	private static final char DOT = '.';
	
	private static final String DEFAULT_FILE_TYPE = "sapc";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Object fileDetectorType = execution.getVariable(FILE_DETECTOR_TYPE);
		String fileDetectorTypeString = resolveFileDetectorType(fileDetectorType);
		String inputDirectory = AppConfigLoader.getProperty(INPUT_DIRECTORY + DOT + fileDetectorTypeString);
		validateInputDirectory(fileDetectorTypeString, inputDirectory);
		FileLoader fileLoader = FileLoaderFactory.instance(fileDetectorTypeString);
		validateFileLoader(fileDetectorTypeString, fileLoader);
		File[] listOfFiles = fileLoader.listFiles(new File(inputDirectory));
		isDirectoryEmpty(fileDetectorType, listOfFiles);
		List<Line> fileContent = new ArrayList<Line>();
		processFileContent(fileLoader, listOfFiles, fileContent);
		//String outputvarName = outputVariable.getExpressionText();
		execution.setVariable("output", fileContent);
	}

	private void processFileContent(FileLoader fileLoader, File[] listOfFiles,
			List<Line> fileConent) {
		for (File file : listOfFiles) {
			if(!file.isFile()){
				logger.error("file [{}] is not a file " , file.getName());
				continue;
			}
			fileLoader.loadFile(file, fileConent);
		    fileLoader.renameFile(file);
		}
	}

	private void isDirectoryEmpty(Object fileDetectorType, File[] listOfFiles) {
		if(listOfFiles == null){
			logger.info("There is no [{}] input file. "  , fileDetectorType);
			throw new FileLoaderException("input directory for file type " + fileDetectorType + " does not contain any file");
		}
	}

	private void validateInputDirectory(String fileDetectorTypeString,
			String inputDirectory) {
		if(inputDirectory == null || inputDirectory.equalsIgnoreCase("")){
			logger.error("input directory for file type [{}] is not resolved" , fileDetectorTypeString);
			throw new FileLoaderException("input directory for file type " +  fileDetectorTypeString + " is not resolved");
		}
	}

	private void validateFileLoader(String fileDetectorTypeString,
			FileLoader fileLoader) {
		if(fileLoader == null){
			logger.error("fileLoader is not able to resolved for type [{}]. File Detector cannot process files. Check Meta-Inf folder" , fileDetectorTypeString);
			throw new FileLoaderException("fileLoader is not able to resolved for type " + fileDetectorTypeString);
		}
	}

	private String resolveFileDetectorType(Object fileDetectorType) {
		String fileDetectorTypeString = "";
		if(fileDetectorType == null || !(fileDetectorType instanceof String)){
			logger.info("file Detector Type is null, default file type[sapc] will be used.");
			fileDetectorTypeString = DEFAULT_FILE_TYPE;
		}else{
			fileDetectorTypeString = (String)fileDetectorType;
		}
		return fileDetectorTypeString;
	}
 
}
