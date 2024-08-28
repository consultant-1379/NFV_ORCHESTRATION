package com.ericsson.oss.nfe.poc.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;

public class BuildEncoded64ZipString implements JavaDelegate {

	private Expression inputFiles;
	private Expression zipFile;
	private Expression encode;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String inputFilesList = inputFiles.getValue(execution).toString();
		String zipFileName = zipFile.getValue(execution).toString();
		String encode64 = encode.getValue(execution).toString();

		String appconfiDir = AppConfigLoader.getAppConfigDir();
		String tempDir = appconfiDir + File.separator + "workspace" + File.separator + execution.getProcessInstanceId();
		
		String zipFile = zipIt(tempDir, inputFilesList, zipFileName);
		if (encode64.equalsIgnoreCase("yes")) {
			String encodedString = encodeFileToBase64Binary(zipFile);
			execution.setVariable("encodedFiles", encodedString);
		} else {
			execution.setVariable("encodedFiles", "Files are zipped and stored at "+ zipFile);
		}
		
	}
	
	private String encodeFileToBase64Binary(String fileName) throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		is.close();
		return bytes;
	}

	public String zipIt(String directory, String files, String zipFileName) {

//		File folder = new File(directory);
//		File[] fileList = folder.listFiles();
		
		List<String> fileList = Arrays.asList(files.split(","));
				
		String zipFile = directory + File.separator + zipFileName;
		
		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);

			for (String file : fileList) {

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(directory + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			// remember close it
			zos.close();

			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return zipFile;
	}
	
}
