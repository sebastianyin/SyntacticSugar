package com.syntacticsugar.vooga.authoring.parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.WarningDialogFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ParameterInputFactory {

	private static Map<Node, Boolean> isSet;

	public static void createInputFields(Object toEdit) {
		isSet = new HashMap<>();
		EditableClass a = toEdit.getClass().getDeclaredAnnotation(EditableClass.class);
		if (a == null)
			return;
		Collection<Node> inputFields = inspectEditableFields(toEdit);
		VBox container = new VBox();
		container.getChildren().addAll(inputFields);
		container.setSpacing(10);
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(10));
		Scene scene = new Scene(container);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(a.className());
		stage.setOnCloseRequest(e -> checkValidity(e));
		stage.showAndWait();

	}

	private static void checkValidity(WindowEvent e) {
		for (Boolean b : isSet.values())
			if (!b) {
				boolean result = WarningDialogFactory.createWarningDialog("Not all parameters have been set!");
				if (result) 
					e.consume();
				break;
			}
	}

	private static Collection<Node> inspectEditableFields(Object toEdit) {
		Collection<Node> inputNodes = new ArrayList<>();
		Class<?> c = toEdit.getClass();
		while (c.getAnnotation(EditableClass.class) != null) {
			Collection<Node> inputFields = new ArrayList<>();
			for (Method m : c.getDeclaredMethods()) {
				if (m.isAnnotationPresent(EditableField.class)) {
					m.setAccessible(true);
					EditableField a = m.getAnnotation(EditableField.class);
					String editLabel = a.inputLabel();
					String defaultVal = a.defaultVal();
					inputFields.add(createTextField(toEdit, editLabel, defaultVal, m));
				}
			}
			if (inputFields.size() > 0) {
				Label classLabel = new Label(c.getAnnotation(EditableClass.class).className());
				classLabel.setAlignment(Pos.CENTER);
				inputNodes.add(classLabel);
				inputNodes.addAll(inputFields);
			}
			c = c.getSuperclass();
		}
		return inputNodes;
	}

	private static Node createTextField(Object toEdit, String inputLabel, String defaultVal, Method toCall) {
		TextField t = new TextField();
		t.setText(defaultVal);
		GridPane anchor = new GridPane();
		addConstraints(anchor);
		Button b = new Button("OK");
		Label label = new Label(inputLabel);
		anchor.getChildren().addAll(label, t, b);
		GridPane.setConstraints(label, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(t, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(b, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(5));
		anchor.setAlignment(Pos.CENTER);

		isSet.put(anchor, false);

		t.setOnAction(e -> processInput(anchor, toEdit, t.getText(), toCall));
		b.setOnAction(e -> processInput(anchor, toEdit, t.getText(), toCall));

		return anchor;
	}

	private static void processInput(Node key, Object toEdit, String input, Method toCall) {
		try {
			toCall.invoke(toEdit, input);
			isSet.put(key, true);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			AlertBoxFactory.createObject("Assignment unsuccessful - Java Reflection error");
		}
	}

	private static void addConstraints(GridPane grid) {
		grid.getColumnConstraints().addAll(
				colWithWidth(25), 
				colWithWidth(50),
				colWithWidth(25));
	}

	private static ColumnConstraints colWithWidth(double percentWidth) {
		ColumnConstraints c = new ColumnConstraints();
		c.setPercentWidth(percentWidth);
		return c;
	}

}
