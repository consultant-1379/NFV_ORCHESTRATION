package com.ericsson.oss.nfe.rest.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ericsson.oss.nfe.config.ConfigurationServiceLocal;

@Path("/file")
public class UploadFileService {

	private final static String UPLOADED_FILE_PATH = System.getProperty("APPCONFIGDIR", "") + File.separator + "tmp" + File.separator;

	@Inject
	private ConfigurationServiceLocal configBean;

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {

		String fileName = "";
		String filePath = UPLOADED_FILE_PATH;
		
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				writeFile(bytes, filePath + fileName);

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		
		filePath = filePath.replace(File.separator, "/");

		configBean.addToAppconfigFile("UPLOADED_FILE", filePath + fileName, "Uploaded file");
		configBean.addToAppconfigFile("UPLOADED_FILE_NAME", fileName, "Uploaded file name");
		configBean.addToAppconfigFile("UPLOADED_FILE_PATH", filePath, "Uploaded file path");
		
		return Response.status(200).entity("Uploaded file name : " + fileName + " is saved at location : " + UPLOADED_FILE_PATH).build();

	}

	/**
	 * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
	 * name="file"; filename="filename.extension"] }
	 **/
	// get uploaded filename, is there a easy way in RESTEasy?
	@GET
	@Path("/getfileName")
	public String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

	@GET
	@Path("/test")
	public String test() {
		String fileName = "test.xml";
		String filePath = UPLOADED_FILE_PATH;
		System.out.println("************************************************************");
		System.out.println(filePath);
		filePath = filePath.replace(File.separator, "/");
		System.out.println(filePath);
		System.out.println("************************************************************");
		configBean.addToAppconfigFile("UPLOADED_FILE", filePath + fileName, "Uploaded file");
		return configBean.getAllAppPropsMap().get("UPLOADED_FILE");

	}

}