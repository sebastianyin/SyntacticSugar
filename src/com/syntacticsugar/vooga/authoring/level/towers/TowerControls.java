package com.syntacticsugar.vooga.authoring.level.towers;

import java.util.Observable;

import com.syntacticsugar.vooga.authoring.level.IDataSelector;
import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.xml.data.TowerData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class TowerControls extends Observable implements IVisualElement {
	
	private TitledPane myViewPane;
	private VBox myVBox;
	private Button myRemoveButton;
	private Button myClearButton;
	private Button myEditButton;
	IDataSelector<TowerData> myTowerView;

	public TowerControls(IDataSelector<TowerData> towers) {
		myVBox = new VBox();
		myTowerView = towers;
		myClearButton = GUIFactory.buildButton(ResourceManager.getString("clear_towers"), e -> myTowerView.clearData(), 150.0, 50.0);
		myRemoveButton = GUIFactory.buildButton(ResourceManager.getString("remove_selected"), e -> myTowerView.removeSelectedData(), 150.0, 50.0);
		myEditButton = GUIFactory.buildButton(ResourceManager.getString("edit_selected"), e -> editSelectedData(), 150.0, 50.0);
		myVBox.getChildren().addAll(myClearButton, myEditButton, myRemoveButton);
		myVBox.setAlignment(Pos.CENTER);
		myVBox.setSpacing(10);
		myVBox.setPadding(new Insets(10));
		
		myViewPane = GUIFactory.buildTitledPane(ResourceManager.getString("tower_controls"), myVBox);
	}
	
	private void editSelectedData() {
		setChanged();
		if (myTowerView.getSelectedData() == null) return;
		notifyObservers(myTowerView.getSelectedData());
	}
	
	@Override
	public Node getView() {
		return myViewPane;
	}
	
}
