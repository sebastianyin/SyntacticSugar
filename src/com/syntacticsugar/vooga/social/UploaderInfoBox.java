package com.syntacticsugar.vooga.social;

import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UploaderInfoBox {

	private TextField myAuthor, myDescription, myGameName;
	private VBox myView;
	private IUploader myUploadInterface;
	private Stage myStage;
	
	public UploaderInfoBox(IUploader uploadInterface) {
		myUploadInterface = uploadInterface;
		initializeVariables();
		Button button = GUIFactory.buildButton("OK", e-> postDataObject(), 
				Double.MAX_VALUE, Double.MAX_VALUE);
		myView.setSpacing(10);
		myView.setId("uploader-info-box");
		myView.getChildren().addAll(
		GUIFactory.buildTitledPane("Author Name", myAuthor),
		GUIFactory.buildTitledPane("Game Name", myGameName),
		GUIFactory.buildTitledPane("Game Description", myDescription),
		button);
		VBox.setVgrow(button, Priority.ALWAYS);
		Scene scene = new Scene(myView);
		scene.getStylesheets().add("com/syntacticsugar/vooga/authoring/css/default.css");
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.initModality(Modality.WINDOW_MODAL);
		myStage.showAndWait();
	}
	
	private void initializeVariables(){
		myAuthor = new TextField();
		myDescription = new TextField();
		myGameName = new TextField();
		myView = new VBox();
	}
	
	private void postDataObject(){
		if (myAuthor.getText().length() == 0 || 
				myDescription.getText().length() == 0 ||
				myGameName.getText().length() == 0){
			AlertBoxFactory.createObject(ResourceManager.getString("fill_all_fields"));
			return;
		}
		myUploadInterface.postXML(myAuthor.getText(), 
				myGameName.getText(), myDescription.getText());
		myStage.close();
	}
	
}
