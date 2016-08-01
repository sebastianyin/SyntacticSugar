package com.syntacticsugar.vooga.authoring.level.towers;

import java.util.Observable;

import com.syntacticsugar.vooga.authoring.level.IManager;
import com.syntacticsugar.vooga.xml.data.TowerData;
import com.syntacticsugar.vooga.xml.data.TowerListData;

import javafx.scene.Node;

public class TowerManager implements IManager {

	private TowerView myTowerView;
	private TowerControls myTowerController;

	public TowerManager() {
		myTowerView = new TowerView();
		myTowerController = new TowerControls(myTowerView);
	}

	@Override
	public Observable getObservableController() {
		return myTowerController;
	}

	@Override
	public Node getControlNode() {
		return myTowerController.getView();
	}

	@Override
	public Node getViewNode() {
		return myTowerView.getView();
	}

	public TowerListData getTowerData() {
		return new TowerListData(myTowerView.getData());
	}
	
	public TowerView getTowerView() {
		return myTowerView;
	}

	public void addTowerData(TowerData data) {
		myTowerView.addData(data);
	}
}
