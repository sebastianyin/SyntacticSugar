package com.syntacticsugar.vooga.social;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public abstract class ListViewer {

	protected Node myView;
	protected ListView<Node> myListView;
	protected Insets myPadding = new Insets(10, 10, 10, 10);
	
	public ListViewer(){
		myView = new VBox();
	}

	protected Node makeContentBox() {
		myListView = new ListView<Node>();
		return myListView;
	}

	protected void addElementToList(Node item) {
		myListView.getItems().add(item);
	}
	
	protected void clearList(){
		myListView.getItems().clear();
	}
	
	public Node getView() {
		return myView;
	}
	
	

	
}
