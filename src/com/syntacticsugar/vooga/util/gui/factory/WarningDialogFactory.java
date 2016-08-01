package com.syntacticsugar.vooga.util.gui.factory;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class WarningDialogFactory {

	public static boolean createWarningDialog(String warning) {
		// Create the custom dialog.
		Dialog<Boolean> dialog = new Dialog<Boolean>();
		dialog.setHeaderText(warning);
		// Set the button types.
		ButtonType done = new ButtonType("Go Back", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(done, ButtonType.CANCEL);
		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> dialogButton == done);
		Optional<Boolean> result = dialog.showAndWait();
		return result.get();
	}
	
}
