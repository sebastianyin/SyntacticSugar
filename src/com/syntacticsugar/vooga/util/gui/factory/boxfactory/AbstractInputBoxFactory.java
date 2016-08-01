package com.syntacticsugar.vooga.util.gui.factory.boxfactory;

import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class AbstractInputBoxFactory implements IInputBoxFactory {

	@SuppressWarnings("unused")
	private String value;
	private TextField textField;
	private Stage dialogStage;
	private BorderPane brd_pan;

	public AbstractInputBoxFactory(String str){
		stageInit();
		textFieldInit(str);
		buttonInit();
		
	    dialogStage.showAndWait();
	}
	
	private void stageInit() {
		dialogStage = new Stage();
	    brd_pan = new BorderPane();
	    Scene scene = new Scene(brd_pan,300,150);
	    dialogStage.setScene(scene);
	    dialogStage.setTitle("Message Box");
	    dialogStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void textFieldInit(String str) {
		HBox textFieldBox = new HBox();
	    textFieldBox.setAlignment(Pos.CENTER);
	    Label textFieldLable = new Label(str);
	    textFieldLable.setPrefWidth(150);
	    textField = new TextField();
	    textField.setMaxWidth(100);
	    textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent keyEvent) {
	        	if (keyEvent.getCode().toString() == "ENTER") {
	        		value = getInputValue();
	    			dialogStage.close();
	        	} else if (keyEvent.getCode().toString() == "ESC") {
	        		dialogStage.close();
	        	}
 	        }
	    });
	    textFieldBox.getChildren().addAll(textFieldLable,textField);
	    brd_pan.setCenter(textFieldBox);
	}
	
	private void buttonInit() {
		HBox navBox = new HBox();
	    navBox.setSpacing(50);
	    navBox.setAlignment(Pos.CENTER);
	    Button btn_cancel = new Button("Cancel");
	    btn_cancel.setOnAction(e -> {
	    	dialogStage.close();
	    });
	    Button btn_ok = new Button("Ok");
	    btn_ok.setOnAction(e -> {
	    	try {
	    		value = getInputValue();
	    		dialogStage.close();
	    	}
	    	catch (NumberFormatException ex) {
	    		AlertBoxFactory.createObject("Bad input, please input an interger number");
	    		textField.clear();
	    	}
	    });
	    navBox.getChildren().addAll(btn_cancel,btn_ok);
	    brd_pan.setBottom(navBox);
	}
	
	public String getInputValue() {
		return textField.getText();
	}
	
	public void setValue(String str) {
		value = str;
	}
}
