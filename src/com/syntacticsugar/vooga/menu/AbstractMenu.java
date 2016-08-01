package com.syntacticsugar.vooga.menu;

import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.DirectionalFadeWizard;
import com.syntacticsugar.vooga.menu.fluidmenu.BackgroundCreator;
import com.syntacticsugar.vooga.util.properties.PropertiesManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class AbstractMenu implements IVoogaApp{
	
	private PropertiesManager myPropertiesManager;
	private Stage myStage;
	private Scene myScene;
	private VBox optionsBox;
	
	public AbstractMenu(String title){
		
		// Windows 10 - Bug Fix for JavaFX
		System.setProperty("glass.accessible.force", "false");
		myPropertiesManager = new PropertiesManager("com/syntacticsugar/vooga/resources/View");
		myStage = new Stage();
		myStage.setTitle(myPropertiesManager.getProperty("WindowTitle"));
		Pane pane = initializePane(title);
		//ImageView background = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("mainmenu-1.png"), myPropertiesManager.getDoubleProperty("DefaultWidth"), pane.getHeight(),true,true));
		//myScene = new Scene(BackgroundCreator.addBackground(background, pane));
		
		//myScene = new Scene(pane);
		
		// Make utility
		Image backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream("mainmenu-6.gif"));
		pane.setBackground(BackgroundCreator.setBackground(backgroundImage));
		myScene = new Scene(pane);
	
		myStage.setScene(myScene);
		animatedShowStage();
		
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	protected abstract void initializeOptions(BorderPane pane);
	
	private Pane initializePane(String gameName) {
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(myPropertiesManager.getDoubleProperty("DefaultWidth"));
		pane.setPrefHeight(myPropertiesManager.getDoubleProperty("DefaultHeight"));
		pane.getStylesheets().add("/com/syntacticsugar/vooga/gameplayer/css/menu.css");
		Label title = new Label(gameName);
		title.setId("menuTitle");
		BorderPane.setAlignment(title, Pos.TOP_RIGHT);
		pane.setTop(title);
		initializeOptions(pane);
		return pane;
	}
	
	protected Button createButton(String name, EventHandler<ActionEvent> onAction) {
		Button button = new Button(name);
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(onAction);
		return button;
	}

	protected void generateOptions(BorderPane myPane, Node... options){
		optionsBox = new VBox(myPropertiesManager.getDoubleProperty("OptionsBoxSpacing"));
		optionsBox.getChildren().addAll(options);
		optionsBox.getChildren().add(new HBox());
		optionsBox.setMaxWidth(myPropertiesManager.getDoubleProperty("ButtonWidth"));
		optionsBox.setId("options_box");
		BorderPane.setAlignment(optionsBox, Pos.TOP_RIGHT);
		myPane.setCenter(optionsBox);
	}
	
	protected String getProperty(String property){
		return myPropertiesManager.getProperty(property);
	}
	
	protected double getDoubleProperty(String property){
		return myPropertiesManager.getDoubleProperty(property);
	}
	
	protected void hideStage(){
		myStage.hide();
	}
	
	@Override
	public void assignCloseHandler(EventHandler<WindowEvent> onclose) {
		myStage.setOnCloseRequest(onclose);
	}
	
	protected void animatedShowStage() {
		DirectionalFadeWizard
			.applyEffect(optionsBox)
			.play();
		myStage.show();
	}
	
	protected void setBackgroundImage(String imagePath){
		ImageView parent = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("gameover.gif")));
		Pane pane = new Pane(parent);
		myStage.setScene(new Scene(pane));
	}



}
