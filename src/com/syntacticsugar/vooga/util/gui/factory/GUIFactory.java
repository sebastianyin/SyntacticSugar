package com.syntacticsugar.vooga.util.gui.factory;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GUIFactory {

	public static AnchorPane buildAnchorPane(Node left, Node right) {
		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(left, right);
		AnchorPane.setLeftAnchor(left, 0.0);
		AnchorPane.setRightAnchor(right, 0.0);
		AnchorPane.setBottomAnchor(left, 0.0);
		AnchorPane.setBottomAnchor(right, 0.0);
		AnchorPane.setTopAnchor(left, 0.0);
		AnchorPane.setTopAnchor(right, 0.0);
		return anchor;
	}
	
	public static TitledPane buildTitledPane(String title, Node content) {
		TitledPane pane = new TitledPane(title, content);
		pane.setAlignment(Pos.CENTER);
		pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pane.setCollapsible(false);
		return pane;
	}
	
	public static VBox buildTitledVBox(Node pane, String title){
		VBox titledPane = new VBox();
		titledPane.getChildren().add(GUIFactory.buildTitleNode(title));
		titledPane.getChildren().add(pane);
		return titledPane;
	}
	
	public static Button buildButton(String label, EventHandler<ActionEvent> action, Double width, Double height) {
		Button button = new Button(label);
		if (width!=null)  button.setMaxWidth(width);
		if (height!=null) button.setMaxHeight(height);
		button.setOnAction(action);
		return button;
	}

	public static HBox buildTitleNode(String text){
		Text titleText = new Text(text);
		HBox title = new HBox(titleText);
		title.setPadding(new Insets(5, 0, 0, 5));
		return title;
	}
	
	public static HBox buildButtonStrip(Collection<Node> buttons) {
		// limited to four because more would look ugly.
		HBox addRemoveStrip = new HBox();
		if (buttons.size() > 4){
			System.out.println("Can only do 4 buttons!");
			return null;
		}
		addRemoveStrip.getChildren().addAll(buttons);
		return addRemoveStrip;
	}
	
	public static Node buildTitleNodeWithButtons(String text, Collection<Node> buttons){
		HBox buttonStrip = buildButtonStrip(buttons);
		HBox titleNode = buildTitleNode(text);
		AnchorPane titleStrip = GUIFactory.buildAnchorPane(titleNode, buttonStrip);
		return titleStrip;
	}
	

	public static VBox buildTitledPaneWithButtons(Node pane, String title, Collection<Node> buttons){
		Node titleAndButtonStrip = buildTitleNodeWithButtons(title, buttons);
		VBox titledPaneWithButtons = new VBox();
		titledPaneWithButtons.getChildren().add(titleAndButtonStrip);
		titledPaneWithButtons.getChildren().add(pane);
		return titledPaneWithButtons;
	}
	
	
}
