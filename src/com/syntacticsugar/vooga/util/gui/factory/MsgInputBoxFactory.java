package com.syntacticsugar.vooga.util.gui.factory;

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

public class MsgInputBoxFactory {

	private double value;
	private TextField textField;

	public MsgInputBoxFactory(String str){
	    Stage dialogStage = new Stage();
	    BorderPane brd_pan = new BorderPane();
	    Scene scene = new Scene(brd_pan,300,150);
	    dialogStage.setScene(scene);
	    dialogStage.setTitle("Message Box");
	    dialogStage.initModality(Modality.APPLICATION_MODAL);
	    
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
	    dialogStage.showAndWait();
	}
	
	public double getInputValue() {	
		if (textField.getText().isEmpty() || !isNumber(textField.getText())) {
			throw new NumberFormatException();
		}
		return 	Double.parseDouble(textField.getText());
	}
	
	private boolean isNumber(String string) {
	    try {
	        Double.parseDouble(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	public double getValue() {
		return value;
	}

}
