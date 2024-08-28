package com.ericsson.oss.nfe.poc.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.Base64Coder;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;
import com.jayway.jsonpath.JsonPath;

public class ECMFileUploadPOSTRequest implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	
	
	private Expression requestString;

	private Expression filename;
	
	private Expression endpointURL;

	private Expression outPutVariable;
	
 
	public void execute(DelegateExecution execution) {
		
		log.info("------------------------------GenericOpenStackRESTInvoker task started ----------------- ");
 		 
		doGenericRESTCall(execution);	 

		log.info("Variables" + execution.getVariables());
		
 	}

	private void doGenericRESTCall(DelegateExecution execution) {
		
		
		String inputrequest = requestString.getValue(execution).toString();

		String filenameVar = filename.getValue(execution).toString();
		
		String endpointURLStr = endpointURL.getValue(execution).toString();		

		String outputVarName = outPutVariable.getExpressionText(); 
		
		String responseStr = "";
 		execution.setVariable("restCallPass", false);
		
		
		List<HeaderTupple> headers = new ArrayList<HeaderTupple>();
		
		String xauthHeader = (String)execution.getVariable("osauthToken");
		if(!StringUtils.isEmpty(xauthHeader))
		{
			HeaderTupple ht = new HeaderTupple("X-Auth-Token", xauthHeader);
			headers.add(ht);
		}
		
		try {
			doPOSTRequest(filenameVar,endpointURLStr,headers);
		} catch (RESTInvokeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		execution.setVariable(outputVarName, responseStr);
	}
	
	
	
	public String doPOSTRequest(String filenameVar,String endpointURL,List<HeaderTupple> headers)throws RESTInvokeException
	{

		log.debug("entering doPOSTRequest");		 

	//	String filenameVar = "/home/test/tempfile8434139167634921341.qcow2";
 
		log.info("Endpoint URL build --> : "+endpointURL);

		String returnResponse = ""; 
 		try {

			File file = new File (filenameVar);
			InputStream is = new FileInputStream(file);

		    // Get the size of the file
		    long length = file.length();

		    // You cannot create an array using a long type.
		    // It needs to be an int type.
		    // Before converting to an int type, check
		    // to ensure that file is not larger than Integer.MAX_VALUE.
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    }

		    // Create the byte array to hold the data
		    byte[] bytes = new byte[1024*100];

		    String imageId = ""; 
		    String checksum = createCheckSum(filenameVar);// "8DB10B38";// 5f0e0074d85d8a229c2bbcbe989581a4
		    // Read in the bytes
		    int offset = 0;
		    int numRead = 0;
		    while (offset < length
		           && (numRead=is.read(bytes, 0, bytes.length)) >= 0) {
		    	
		    	log.info(" numRead " + numRead + " offset " + offset);		
		    	StringBuilder buf = new StringBuilder(bytes.length);
		    	buf.append(Base64Coder.encode(bytes,numRead));
		    	String chunkdata = buf.toString();
		    	String bodystart;
		    	if (offset == 0)
		    	{	
		    		bodystart = "{\"tenantName\":\"vepc\"," 
		    	
						+ "\"image\": {"   
						+ "\"name\":\"AFR\","
						+ "\"isPublic\":\"false\","
						+ "\"fileChecksum\": \"" + checksum + "\","
						+ "\"chunkSize\":\"" + numRead  +"\","
						+ "\"fileName\": \"" + filenameVar +"\","
						+ "\"chunkData\": \"" + chunkdata +"\""
	 					+"}}";
		    	}
		    	else
		    	{
		    		bodystart = "{\"tenantName\":\"vepc\"," 
		    		    	
						+ "\"image\": {"   
						+ "\"name\":\"AFR\","
						+ "\"isPublic\":\"false\","
						+ "\"chunkSize\":\"" + numRead  +"\","
						+ "\"imageId\": \"" + imageId +"\","
						+ "\"fileName\": \"" + filenameVar +"\","
						+ "\"chunkData\": \"" + chunkdata +"\""
	 					+"}}";
		    	}
		    	
		    	
		    	String respvoStr = "";
		    	try
				{
		    		 ECMRESTUtil ecmrestUtil = new ECMRESTUtil();
		    		 String contentRange = "bytes "+offset+"-"+(offset+numRead-1)+"/"+(length);
		    		// "0-1200/15000"
		    		 ecmrestUtil.setHeader("Content-Range", contentRange);
					 respvoStr = ecmrestUtil.doPOSTRequest(bodystart, endpointURL);
					 log.info(" partial response  doPOSTRequest " + respvoStr);
					 String jimageId = JsonPath.read(respvoStr, "$.image.id");
					 log.info(" partial response  jimageId " + jimageId);
					// String temp = jimageId.get(0).get(imageId).toString();
					// log.info(" partial response  temp " + temp);
					 imageId=jimageId;
					
				} catch (RESTInvokeException e) {
					throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest POST:" + respvoStr + " "+e);
				}
		        offset += numRead;
		    }

		    // Ensure all the bytes have been read in
		    if (offset < bytes.length) {
		        throw new IOException("Could not completely read file "+file.getName());
		    }

		    // Close the input stream and return bytes
		    is.close();
		   
 		}
		catch (Exception e)
		{
			log.error(" exiting doPOSTRequest",e);
		}
			
			
			
		log.info(" exiting doPOSTRequest");

		return "";
	}
	
	private static String readResponse(InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";

	}
	
	
	private String createCheckSum(String fileName)
	{
		 
		long checksum=-1;
		        try {

		            CheckedInputStream cis = null;
		            long fileSize = 0;
		            try {
		                // Computer CRC32 checksum
		                cis = new CheckedInputStream(
		                        new FileInputStream(fileName), new CRC32());

		                fileSize = new File(fileName).length();
		                
		            } catch (FileNotFoundException e) {
		                System.err.println("File not found.");
		                System.exit(1);
		            }

		            byte[] buf = new byte[128];
		            while(cis.read(buf) >= 0) {
		            }

		             checksum = cis.getChecksum().getValue();
		            System.out.println(checksum + " " + fileSize + " " + fileName);

		        } catch (IOException e) {
		            e.printStackTrace();
		            System.exit(1);
		        }


		  
		        Long.toHexString(checksum);
		        return  Long.toHexString(checksum);
		  

		}


	
}