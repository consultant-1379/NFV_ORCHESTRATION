package com.ericsson.oss.nfe.cli.client.startconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

/**
 * Helper class prepare the Start Instance Configuration.
 * 
 */
public class InstanceStartConfiguration {

	/**
	 * @param path
	 *            path to the property file
	 * @return JSON string parsed from properties key-value pair
	 */
	public static JSONObject getInputJsonStr(String path) {
		JSONObject input = parseProptoJson(getProperty(path));
		if (input == null) {
			System.err.println("Having problem accesing path:'" + path + "'");
			System.err
					.println("User input file doesn't exist or the format of your config file isn't correct\n");

		}
		return input;

	}

	public static Properties getProperty(String path) {
		Properties prop = new Properties();

		InputStream input;
		try {
			input = new FileInputStream(new File(path));
			prop.load(input);

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return prop;
	}

	public static JSONObject parseProptoJson(Properties prop) {
		if (prop == null)
			return null;
		JSONObject jsonObject = new JSONObject();
		JSONObject variable = new JSONObject();
		System.out
				.println("-----------------------------------------------------------------------------------");
		jsonObject.put("variables", variable);
		System.out.println("Setting variables for instance initialization:");
		for (String key : prop.stringPropertyNames()) {
			JSONObject pair = new JSONObject();
			pair.put("value", prop.get(key));
			variable.put(key, pair);
			System.out.println("\t" + key + "->" + prop.get(key));
		}
		System.out
				.println("-----------------------------------------------------------------------------------");
		return jsonObject;
	}

	public static JSONObject readConfig(String form) {
		JSONObject templateform = new JSONObject(form);
		JSONArray controlgroups = templateform.getJSONArray("controlGroups");
		JSONArray required = new JSONArray();
		JSONObject defaultvars = new JSONObject();

		for (int i = 0; i < controlgroups.length(); i++) {
			JSONArray controlsarr = controlgroups.getJSONObject(i)
					.getJSONObject("controlGroup").getJSONArray("controls");
			JSONObject controls = controlsarr.getJSONObject(0);
			if (!controls.has("initialValue")) {
				required.put(controls.getJSONObject("variable").getString("name"));
			} else {
				String name = controls.getJSONObject("variable").getString("name");
				JSONObject initvals = controls.getJSONObject("initialValue");
				initvals.remove("source");
				initvals.put("required", JSONObject.NULL);
				defaultvars.put(name, initvals);
			}

		}
		JSONObject inputform = new JSONObject();
		inputform.put("variables",
				new JSONObject().put("required", required).put("default", defaultvars));

		return inputform;

	}

	public static boolean verifyConfig(JSONObject userform, JSONObject templateform) {

		JSONObject userinput = userform.getJSONObject("variables");
		JSONArray requiredVars = templateform.getJSONObject("variables").getJSONArray(
				"required");
		JSONObject defaultVars = templateform.getJSONObject("variables").getJSONObject(
				"default");

		System.out
				.println("-----------------------------------------------------------------------------------");
		System.out.println("Verifying input properties to check any missings...");
		// verify the mandatory required values
		for (int i = 0; i < requiredVars.length(); i++) {
			if (!userinput.has(requiredVars.getString(i))
					|| userinput.getJSONObject(requiredVars.getString(i)).getString("value")
							.isEmpty()) {
				System.err.println("\tvariable:" + requiredVars.getString(i) + " is missing");
				System.err.println("\tPlease provide missing required property values");
				System.err.println("\tThe starting process stopped");
				return false;
			}
		}
		// verify the default values compared to what in the defaultVars
		for (Iterator<String> it = defaultVars.keys(); it.hasNext();) {
			String key = it.next();

			if (!userinput.has(key)
					|| userinput.getJSONObject(key).getString("value").isEmpty()) {

				JSONArray array = defaultVars.getJSONObject(key).getJSONArray("values");
				System.out.println("\tVariable:'" + key
						+ "' is missing or empty, possible values are:" + array);
				System.out.println("\tvalue:'" + array.get(0) + "' is used.");
				userinput.put(key, new JSONObject().put("value", array.get(0)));
			}
		}

		System.out.println("Following variables are set for starting this instance.");
		for (Iterator<String> it = userinput.keys(); it.hasNext();) {
			String key = it.next();
			System.out.println("\tvariable:'" + key + "' -> " + "value:'"
					+ userinput.getJSONObject(key).getString("value") + "'");
		}

		System.out
				.println("-----------------------------------------------------------------------------------");
		return true;
	}
}
