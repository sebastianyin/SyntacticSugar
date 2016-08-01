package com.syntacticsugar.vooga.util.gui.factory;

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

public class StringInputBoxFactory {

	private String value;
	private TextField textField;

	public StringInputBoxFactory(String str) {
		Stage dialogStage = new Stage();
		BorderPane brd_pan = new BorderPane();
		Scene scene = new Scene(brd_pan, 300, 150);
		dialogStage.setScene(scene);
		dialogStage.setTitle("Message Box");
		dialogStage.initModality(Modality.APPLICATION_MODAL);

		HBox textFieldBox = new HBox();
		textFieldBox.setAlignment(Pos.CENTER);
		Label textFieldLable = new Label(str);
		textFieldLable.setPrefWidth(150);
		textField = new TextField();
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
		
		textField.setMaxWidth(100);
		textFieldBox.getChildren().addAll(textFieldLable, textField);
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
			value = getInputValue();
			dialogStage.close();
		});
		navBox.getChildren().addAll(btn_cancel, btn_ok);

		brd_pan.setBottom(navBox);
		dialogStage.showAndWait();
	}

	public String getInputValue() {
		if (textField.getText().isEmpty() || textField.getText().contains(" ")) {
			throw new IllegalArgumentException();
		}
		return textField.getText();
	}

	public String getValue() {
		return value;
	}

}
