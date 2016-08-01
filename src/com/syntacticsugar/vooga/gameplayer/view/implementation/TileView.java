package com.syntacticsugar.vooga.gameplayer.view.implementation;

import java.util.Observable;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.objects.BoundingBox;
import com.syntacticsugar.vooga.gameplayer.view.ObjectView;
import com.syntacticsugar.vooga.gameplayer.view.gameview.GameView;
import com.syntacticsugar.vooga.gameplayer.view.gameview.ISimpleGameView;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class TileView extends ObjectView {

	private boolean walkable;
	private boolean selectMode;
	private double towerWidth;
	private double towerHeight;

	public TileView(String path, BoundingBox box, GameView myGameView, Boolean isPath) {
		super(path, box, myGameView);
		walkable = isPath;
		selectMode = false;
		towerHeight = box.getHeight();
		towerWidth = box.getWidth();
		initializeHoverProperties(myGameView);
	}

	private void initializeHoverProperties(ISimpleGameView myGameView) {
		//StackPane myPane = getViewPane();
		Node tileView = getViewPane().getChildren().get(0);
		tileView.setOnMouseClicked(e -> selected(myGameView));
		tileView.setOnMouseEntered(e -> changeOpacity(getViewPane(), 0.75));
		tileView.setOnMouseExited(e -> changeOpacity(getViewPane(), 1));
	}

	private void changeOpacity(StackPane myPane, double value) {
		if (!walkable && selectMode) {
			myPane.setOpacity(value);
		}
	}

	private void selected(ISimpleGameView myGameView) {
		if (!walkable && selectMode) {
			chooseDirection(myGameView);
		}
	}

	private void chooseDirection(ISimpleGameView myGameView) {
		DirectionArrows myArrows = new DirectionArrows(this, myGameView);
		myArrows.setLayoutX(
				this.getViewPane().getLayoutX()- this.getViewPane().getWidth());
		myArrows.setLayoutY(
				this.getViewPane().getLayoutY() - this.getViewPane().getWidth());
		myGameView.addObjectView(myArrows);
	}

	public void informTowerControl(Direction direction, DirectionArrows dirArrows, ISimpleGameView gameView) {
		TowerPlaceInfo towerInfo = new TowerPlaceInfo(this.getOriginalCoordinates().getX(),
				this.getOriginalCoordinates().getY(), direction, towerWidth, towerHeight);
		gameView.removeObjectView(dirArrows);
		setChanged();
		notifyObservers(towerInfo);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		selectMode = (boolean) arg1;
	}

}
