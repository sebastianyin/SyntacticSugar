package com.syntacticsugar.vooga.util.webconnect;

import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.syntacticsugar.vooga.util.webconnect.WebConnector;

public class WebConnectorTest {
	public static void main(String args[]) throws Exception {

		//getXMLsTest();
		//getXMLTest(11);
		//postXMLTest();
		//deleteXMLTest(67);
		//postCommentTest();
		//getCommentsTest(20);
	}
	
	public static void postCommentTest() {
		int id = 20;
		String author = "Strange person";
		String comment = "TESTING COMMENTS";
		JSONObject request = JSONHelper.createCommentJSON(id, author, comment);
		System.out.println("Sending Request: " + request.toString());
		String output = WebConnector.postComment(request);
		System.out.println("Output: " + output);
	}
	
	public static void getCommentsTest(int gameid) throws JSONException {
		JSONArray jsonOutput = WebConnector.getComments(gameid);
		System.out.println(jsonOutput.toString());
	}

	public static void getXMLsTest() {
		JSONObject jsonOutput = WebConnector.getXMLs();
		try {
			JSONArray fields = jsonOutput.getJSONArray("xmls");
			System.out.println(fields.toString());
			JSONHelper.printArray(fields);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getXMLTest(int gameindex) {
		JSONObject jsonOutput = WebConnector.getXML(gameindex);
		System.out.println("Output: " + jsonOutput);
		System.out.println("XML: " + JSONHelper.extractXML(jsonOutput));
	}

	public static void postXMLTest() {
		String author = "the one with date";
		String gamename = "SS";
		String description = "game djlfakjdlkfjdlkfjdljflkdlfjdlfjk goes here";
		new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		//String xml = FileHelper.readFile(f);
		String xml = "<testxml>soem shittejjiejiefe</testxml>";
		JSONObject request = JSONHelper.createXMLJSON(author, gamename, description, xml);
		System.out.println("Sending Request: " + request.toString());
		String output = WebConnector.postXML(request);
		System.out.println("Output: " + output);
	}
	
	public static void deleteXMLTest(int gameindex) {
		String response = WebConnector.deleteXML(gameindex);
		System.out.println("Output: " + response);
	}
}
