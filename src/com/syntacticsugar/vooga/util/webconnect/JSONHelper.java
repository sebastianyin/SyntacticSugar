package com.syntacticsugar.vooga.util.webconnect;

import java.util.HashMap;
import java.util.Map;
import org.json.*;

import com.syntacticsugar.vooga.util.compress.Compressor;

public class JSONHelper {
	
	/**
	 * @param json
	 * @return decompressed xml from JSON
	 */
	public static String extractXML(JSONObject json) {
		try {
			String xml = json.getString("xml");
			xml = Compressor.decompress16(xml);
			return xml;
		} catch (JSONException e) {
			System.out.println("Failed to extract xml from JSON");
			return "";
		}
	}

	/**
	 * prints a json array
	 * @param arr
	 */
	public static void printArray(JSONArray arr) {
		for (int i = 0; i < arr.length(); i++) {
			try {
				JSONObject entry = (JSONObject) arr.get(i);
				System.out.println(entry.toString());
			} catch (JSONException e) {
				System.out.println("Failed to extract JSON from JSON array");
			}
		}
	}

	/**
	 * @param author
	 * @param gamename
	 * @param description
	 * @param xml
	 * @return JSON with included parameters
	 */
	public static JSONObject createXMLJSON(String author, String gamename, String description, String xml) {
		Map<String, String> map = new HashMap<String, String>();

		xml = Compressor.compress16(xml);
		map.put("xml", xml);
		map.put("author", author);
		map.put("gamename", gamename);
		map.put("description", description);

		return new JSONObject(map);
	}

	/**
	 * @param author
	 * @param gamename
	 * @param description
	 * @param xml
	 * @return JSON with included parameters
	 */
	public static JSONObject createCommentJSON(int id, String author, String comment) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("id", Integer.toString(id));
		map.put("author", author);
		map.put("comment", comment);

		return new JSONObject(map);
	}

}
