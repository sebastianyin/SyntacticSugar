package com.syntacticsugar.vooga.authoring.level.spawner;

import java.util.Observable;

import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class SpawnerControls extends Observable implements IVisualElement {

	private VBox myVBox;
	private Button myAddButton;
	private Button myRemoveButton;
	private Button myClearButton;
	private Button myEditButton;
	private TitledPane myViewPane;
	private TextField mySpawnInput;
	private int mySavedSpawnRate;
	private Button mySpawnButton;

	public SpawnerControls(SpawnerManager tabs) {
		myVBox = new VBox();
		myAddButton = GUIFactory.buildButton(ResourceManager.getString("create_wave"), e -> tabs.append(), 150.0, 50.0);
		myClearButton = GUIFactory.buildButton(ResourceManager.getString("clear_wave"), e -> tabs.remove(), 150.0,
				50.0);
		myRemoveButton = GUIFactory.buildButton(ResourceManager.getString("remove_selected"),
				e -> tabs.getCurrentView().removeSelectedData(), 150.0, 50.0);
		myEditButton = GUIFactory.buildButton(ResourceManager.getString("edit_selected"),
				e -> editItem(tabs.getCurrentView().getSelectedData()), 150.0, 50.0);
		mySpawnButton = GUIFactory.buildButton(ResourceManager.getString("set_spawn_rate"),
				e -> tabs.setCurrentWaveSpawnRate(getSavedSpawnRate()), 150.0, 50.0);
		mySpawnInput = new TextField();
		mySpawnInput.setPromptText(ResourceManager.getString("int_SpawnRate"));

		myVBox.getChildren().addAll(mySpawnInput, mySpawnButton, myAddButton, myClearButton, myEditButton,
				myRemoveButton);
		myVBox.setAlignment(Pos.TOP_CENTER);
		myVBox.setSpacing(7);
		myVBox.setPadding(new Insets(5, 10, 5, 10));

		myViewPane = GUIFactory.buildTitledPane(ResourceManager.getString("spawn_controls"), myVBox);
	}

	private void editItem(ObjectData toEdit) {
		setChanged();
		notifyObservers(toEdit);
	}

	@Override
	public Node getView() {
		return myViewPane;
	}

	public void saveSpawnRate() {
		try {
			mySavedSpawnRate = Integer.parseInt(mySpawnInput.getText());
		} catch (Exception e) {
			AlertBoxFactory.createObject(ResourceManager.getString("not_integer"));
		}
	}

	private int getSavedSpawnRate() {
		saveSpawnRate();
		return mySavedSpawnRate;
	}

}
