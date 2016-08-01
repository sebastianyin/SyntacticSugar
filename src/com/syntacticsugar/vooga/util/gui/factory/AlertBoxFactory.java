package com.syntacticsugar.vooga.util.gui.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBoxFactory {

	public static void createObject(String id) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(id);
		alert.showAndWait();
	}
	
}
