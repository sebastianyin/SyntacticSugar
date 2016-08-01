package com.syntacticsugar.vooga.social;

import org.json.JSONException;

import com.syntacticsugar.vooga.menu.IVoogaApp;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SocialCenter implements IVoogaApp{

	private XMLViewer myXMLViewer;
	private ObjectDataViewer myObjectDataViewer;
	private Stage myStage;
	
	public SocialCenter(){
		myObjectDataViewer = new ObjectDataViewer();
		myXMLViewer = new XMLViewer(new IDataViewUpdater(){

			@Override
			public void update(int selected) throws JSONException {
				myObjectDataViewer.update(selected);
			}

			@Override
			public void updateID(int selected) {
				myObjectDataViewer.updateID(selected);
			}
			
		});
		
		myStage = new Stage();
		Scene scene = new Scene(makeSceneNode(), 800, 500);
		scene.getStylesheets().add("/com/syntacticsugar/vooga/authoring/css/default.css");
		myStage.setScene(scene);
		myStage.show();
	}

	private HBox makeSceneNode(){
		HBox fullView = new HBox();
		VBox myLeft = new VBox();
		myLeft.getChildren().add(myXMLViewer.getView());
		myLeft.getChildren().add(myObjectDataViewer.getView());
		myLeft.setPrefWidth(700);
		fullView.getChildren().add(myLeft);
		VBox myRight = (VBox) myObjectDataViewer.getCommentBox();
		fullView.getChildren().add(myRight);
		return fullView;
	}
	
	@Override
	public void assignCloseHandler(EventHandler<WindowEvent> onclose) {
		myStage.setOnCloseRequest(onclose);
		
	}
	
	
	
}
