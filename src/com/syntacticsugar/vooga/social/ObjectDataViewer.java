package com.syntacticsugar.vooga.social;

import org.json.JSONException;
import org.json.JSONObject;

import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.util.webconnect.WebConnector;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ObjectDataViewer {

	private Node myView;
	private ListView<Node> myListView;
	private int mySelectedItemID = Integer.MIN_VALUE;
	private JSONObject myData;
	private CommentViewer myCommentBox;

	public ObjectDataViewer() {
		super();
		myCommentBox = new CommentViewer();
		myView = makeView();		
	}

	private Node makeView() {
		myListView = new ListView<Node>();
		TitledPane view = GUIFactory.buildTitledPane("Information", myListView);
		view.setMaxWidth(Integer.MAX_VALUE);
		return view;
	}
	
	private void populateList(JSONObject object) {
		clearList();
		try {
			while(object.keys().hasNext()){
				String key = (String) object.keys().next();
				String value = object.get(key).toString();
				Node listElement = makeListElement(key, value);
				object.remove(key);
				addElementToList(listElement);
			}
			myCommentBox.updateFromDataViewer(mySelectedItemID);
			} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private JSONObject getJSONObject(int id) {
		mySelectedItemID = id;
		return WebConnector.getXMLData(id);	
	}

	private Node makeListElement(String key, String value) {
		Node keyNode = GUIFactory.buildTitleNode(ResourceManager.getString(key));
		TextFlow valueNode = new TextFlow(new Text(value));
		valueNode.setMaxWidth(150);
		valueNode.setTextAlignment(TextAlignment.RIGHT);
		Node element = GUIFactory.buildAnchorPane(keyNode, valueNode);
		return element;
	}

	public void update(int id) throws JSONException {
		myData = getJSONObject(id);
		mySelectedItemID = id;
		populateList(myData);
	}
	
	public void updateID(int id){
		mySelectedItemID = id;
	}
	
	private void clearList(){
		myListView.getItems().clear();
	}
	
	private void addElementToList(Node element){
		myListView.getItems().add(element);
	}
	
	public Node getView(){
		return myView;
	}
	
	public Node getCommentBox(){
		return myCommentBox.getView();
	}
}
