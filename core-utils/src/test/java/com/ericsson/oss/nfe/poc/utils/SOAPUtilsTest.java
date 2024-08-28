package com.ericsson.oss.nfe.poc.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

public class SOAPUtilsTest {

	@Ignore @Test
	public void testextractXPath() {

		// System.out.println(SOAPUtil.extractXPath("/Envelope/*",FileUtils.loadFileAsString("authResp.xml")));

		String xpathExpr = "/SOAP-ENV:Envelope/SOAP-ENV:Body/com:openSessionReply/session/sessionId";
		//String xpathExpr = "/SOAP-ENV:Envelope/SOAP-ENV:Body/xoscfg:execCliResponse";
		XPath xpath = XPathFactory.newInstance().newXPath();
		 HashMap map = new HashMap();
		map.put( "SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
		
		NamespaceContext nx;
		xpath.setNamespaceContext(new SOAPUtil.SimpleNameSpaceContext());
		
		
	/*	InputSource inputSource = new InputSource(new InputStreamReader(
				FileUtils.class.getClassLoader().getResourceAsStream(
						"cfgCmd.xml")));*/
		
		InputSource inputSource = new InputSource(new ByteArrayInputStream(FileUtils.loadFileAsString("authResp.xml").getBytes()));
		
		try {

			Node oneN = (Node) xpath.evaluate(xpathExpr, inputSource,
					XPathConstants.NODE);

			System.out.println(oneN.getTextContent());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testPOST() {

		String xmlReq = FileUtils.loadFileAsString("authResp.xml");

 		 
		try {
			System.out.println(new SOAPUtil().doPOSTRequest(xmlReq, "http://localhost:8080/WSTest/HelloWorld", new ArrayList<HeaderTupple>()));
			
		} catch (RESTInvokeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 

	}

}
