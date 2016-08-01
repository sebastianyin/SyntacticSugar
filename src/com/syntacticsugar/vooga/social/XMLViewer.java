package com.syntacticsugar.vooga.social;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.filechooser.FileChooserUtil;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.util.webconnect.JSONHelper;
import com.syntacticsugar.vooga.util.webconnect.WebConnector;
import com.syntacticsugar.vooga.xml.XMLHandler;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser.ExtensionFilter;

public class XMLViewer implements IVisualElement {

	private VBox myView;
	private ListView<Node> myListView;
	private int mySelectedItemID = Integer.MIN_VALUE;
	private IDataViewUpdater myUpdater;

	public XMLViewer(IDataViewUpdater updater){
		myUpdater = updater;
		myView = makeView();
		populateListFromDatabase();
	}	
	
	private VBox makeView(){
		myListView = new ListView<Node>();
		myView = new VBox();
		myView.getChildren().addAll(makeTitleNode(makeButtonStrip()), myListView);
		return myView;
	}
	
	private Node makeTitleNode(Node buttons){
		HBox title = GUIFactory.buildTitleNode("Game Name | Author Name");
		return GUIFactory.buildAnchorPane(title, buttons);
	}
	
	private Node makeButtonStrip(){
		Button download = GUIFactory.buildButton(ResourceManager.getString("download"), e->downloadSelectedItem(), 100.00, null);
		Button upload = GUIFactory.buildButton(ResourceManager.getString("upload"), e->uploadItem(), 100.00, null);
		Button refresh = GUIFactory.buildButton(ResourceManager.getString("refresh"), e-> refresh(), 100.0, null);
		HBox buttonStrip = new HBox();
		buttonStrip.getChildren().addAll(refresh, download, upload);
		return buttonStrip;
	}
	
	
	private void populateListFromDatabase() {
		JSONObject XMLs = WebConnector.getXMLs();
			JSONArray array;
			try {
				array = XMLs.getJSONArray("xmls");
				for(int i = 0; i< array.length(); i++){
					JSONObject current = (JSONObject) array.get(i);
					addElementToList(makeListElement(current.getString("gamename"),
							current.getString("author"), (int) current.get("id")));
				}
			} catch (JSONException e) {
				e.printStackTrace();	
		}
	}
	

	private Node makeListElement(String itemName, String itemData, int itemID) {
		HBox listElement = new HBox();
		HBox game = GUIFactory.buildTitleNode(itemName);
		HBox author = GUIFactory.buildTitleNode(itemData);
		game.setPrefWidth(300);
		listElement.getChildren().addAll(game, author);
						
		listElement.setOnMouseClicked(e-> {setCurrentlySelected(itemID); try {
			myUpdater.update(itemID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}});
		return listElement;
	}
	
	private void uploadItem() {
		makeUploadFileChooser();
	}

	private void downloadSelectedItem() {
		if (mySelectedItemID == Integer.MIN_VALUE)
			return;
		else {
			FileChooserUtil.saveFile("Choose a save location.", ".xml", null, selected -> {
					XMLHandler.writeXMLToFile(JSONHelper.extractXML(WebConnector.getXML(mySelectedItemID)),
							selected.toPath().toString());					
			});
		}
	}

	private void makeUploadFileChooser() {// EventHandler<ActionEvent> action) {
		ExtensionFilter filter = new ExtensionFilter("XML files", "*.xml", "*.XML");
		FileChooserUtil.loadFile("Choose an XML game file", filter, null, selected -> {
			new UploaderInfoBox(new IUploader() {
				@Override
				public void postXML(String author, String gamename, String description) {
					WebConnector.postXML(
							JSONHelper.createXMLJSON(author, gamename, 
									description, XMLHandler.fileToString(selected)));
				}
			});
			refresh();
		});
	}

	private void addElementToList(Node element){
		myListView.getItems().add(element);
	}
	
	private void refresh() {
		clearList();
		populateListFromDatabase();
	}
	
	private void clearList(){
		myListView.getItems().clear();
	}
	
	private void setCurrentlySelected(int id){
		mySelectedItemID = id;
	}
	
	public int getCurrentlySelected(){
		return mySelectedItemID;
	}

	@Override
	public Node getView() {
		return myView;
	}

}
