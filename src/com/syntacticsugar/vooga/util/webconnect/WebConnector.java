package com.syntacticsugar.vooga.util.webconnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebConnector {

	private final static String GET_GAMES_URL = "http://easywebapi.com/api/xml";
	private final static String GET_GAME_URL = "http://easywebapi.com/api/xml/";
	private final static String POST_GAME_URL = "http://easywebapi.com/api/newxml";
	private final static String DELETE_GAME_URL = "http://easywebapi.com/api/deletexml/";
	private final static String POST_COMMENT_URL = "http://easywebapi.com/api/newcomment";
	private final static String GET_COMMENTS_URL = "http://easywebapi.com/api/comment/";

	/**
	 * POST XML data to web API
	 * @param json
	 * @return id of json inserted
	 */
	public static String postXML(JSONObject json) {
		return WebConnectorHelper.post(json, POST_GAME_URL);
	}

	/**
	 * GET JSON of xml metadata from web API
	 * @return JSON of game metadata
	 */
	public static JSONObject getXMLs() {
		return WebConnectorHelper.get(GET_GAMES_URL);
	}

	/**
	 * GET JSON of game including xml
	 * @param gameindex
	 * @return return JSON of game by index
	 */
	public static JSONObject getXML(int gameindex) {
		return WebConnectorHelper.get(GET_GAME_URL + gameindex);
	}

	/**
	 * Get everything except XML from JSON
	 * @param gameindex
	 * @return everything except XML
	 */
	public static JSONObject getXMLData(int gameindex){
		JSONObject fullXML = getXML(gameindex);
		fullXML.remove("xml");
		return fullXML;
	}
	
	/**
	 * DELETE XML from database
	 * @param gameindex
	 * @return success message
	 */
	public static String deleteXML(int gameindex) {
		return WebConnectorHelper.delete(DELETE_GAME_URL + gameindex);
	}

	/**
	 * POST Comment for a specific game
	 * @param json
	 * @return success message
	 */
	public static String postComment(JSONObject json) {
		return WebConnectorHelper.post(json, POST_COMMENT_URL);
	}
	
	/**
	 * GET Comment for a specific game
	 * @param gameindex
	 * @return JSONArray of comments for a specific game
	 * @throws JSONException
	 */
	public static JSONArray getComments(int gameindex) throws JSONException {
		return WebConnectorHelper.get(GET_COMMENTS_URL+gameindex).getJSONArray("comments");
	}
}
