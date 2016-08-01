package com.syntacticsugar.vooga.social;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.util.webconnect.JSONHelper;
import com.syntacticsugar.vooga.util.webconnect.WebConnector;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CommentViewer implements IVisualElement {

	private VBox myView;
	private ListView<Node> myListView;
	private List<JSONObject> myCommentList;
	private int mySelectedItemID;
	private Button mySendButton;
	private TextArea myComment;
	private TextField myAuthorName;
	@SuppressWarnings("unused")
	private boolean justPosted;
	private boolean firstTime;

	public CommentViewer() {
		justPosted = true;
		firstTime = true;
		myView = makeView();
		myCommentList = new ArrayList<JSONObject>();
	}
	
	private VBox makeView(){
		myView = new VBox();
		myListView = new ListView<Node>();
		TitledPane pane = GUIFactory.buildTitledPane(ResourceManager.getString("comments"), myListView);
		myView.getChildren().addAll(pane, makeCommentBox());
		return myView;
	}
	
	private Node makeCommentBox(){
		VBox commentBox = new VBox();
		myAuthorName = new TextField();
		myComment = new TextArea(ResourceManager.getString("post_comment"));
		mySendButton = GUIFactory.buildButton(ResourceManager.getString("post_button"),
				e-> postComment(myAuthorName.getText(), myComment.getText()),
					  100.0, null);
		setFieldProperties();
		Node titledAuthorField = GUIFactory.buildTitledPane(ResourceManager.getString("author_name"),
				myAuthorName);
		HBox pane = new HBox();
		pane.getChildren().addAll(titledAuthorField, mySendButton);
		commentBox.getChildren().addAll(pane, myComment);
		return commentBox;
	}
	
	private void setFieldProperties(){
		myComment.setOnMouseClicked( e->
			myComment.clear());
		myComment.setWrapText(true);
		mySendButton.setMaxHeight(Integer.MAX_VALUE);
		mySendButton.setMaxWidth(Integer.MAX_VALUE);
		HBox.setHgrow(mySendButton, Priority.ALWAYS);

	}
	
	private void setFieldDefaults(){
		myComment.setText(ResourceManager.getString("post_comment"));
		myAuthorName.clear();
	}

	private void pullCurrentGameComments() throws JSONException {
		JSONArray commentArray = WebConnector.getComments(mySelectedItemID);
		myCommentList = convertJSONToList(commentArray);
	}

	private List<JSONObject> convertJSONToList(JSONArray commentArray) throws JSONException {
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i<commentArray.length(); i++){
			list.add(commentArray.getJSONObject(i));
		}		
		return list;
	}
	
	private void postComment(String author, String content) {
		if (author.length() == 0 || content.length() == 0) return;
		if (firstTime) return;
		if (mySelectedItemID == Integer.MIN_VALUE) return;
		
		try {
			WebConnector.postComment(JSONHelper.createCommentJSON(mySelectedItemID, author, content));
			update();
			populateList();
			setFieldDefaults();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}
	
	private void populateList() throws JSONException {
		clearListview();
		for (JSONObject obj: myCommentList){
			System.out.println(obj.get("comment"));
			addElementToList(makeListElement(obj.getString("date"), obj.getString("time"),
					obj.getString("author"), obj.getString("comment")));
		}
	}
	
	private void clearListview(){
		myListView.getItems().clear();
	}
	
	private void addElementToList(Node element) {
		myListView.getItems().add(element);
	}
	
	
	private Node makeListElement(String date, String time, String author, String comment){
		VBox commentElement = new VBox();
		VBox dateAndTime = new VBox();
		Text Date = new Text(date);
		Text Time = new Text(time);
		Text Author = new Text(author);
		applyTextStyle(Date, Time, Author);
		Text Comment = new Text(comment);
		dateAndTime.getChildren().addAll(Date, Time);
		AnchorPane tag = GUIFactory.buildAnchorPane(Author, dateAndTime);		
		TextFlow content = new TextFlow(Comment);
		content.setMaxWidth(250);
		commentElement.getChildren().addAll(tag, content);
		return commentElement;		
	}
	
	public void updateFromDataViewer(int id) throws JSONException{
		mySelectedItemID = id;
		update();
	}

	private void applyTextStyle(Text... text) {
		for (Text textElement : text) {
			textElement.setFill(Color.GREY);
			textElement.setStyle("-fx-font: 11 arial;");
		}
	}

	public void update() throws JSONException {
		pullCurrentGameComments();
		populateList();
		firstTime = false;
	}
	
	@ Override 
	public Node getView(){
		return myView;
	}

}
