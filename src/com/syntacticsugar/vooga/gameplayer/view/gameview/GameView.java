package com.syntacticsugar.vooga.gameplayer.view.gameview;

import java.util.Collection;
import java.util.Observer;

import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IUniverseView;
import com.syntacticsugar.vooga.gameplayer.view.gamepanels.GlobalDataPanel;
import com.syntacticsugar.vooga.gameplayer.view.gamepanels.TowerShop;
import com.syntacticsugar.vooga.gameplayer.view.implementation.TileView;
import com.syntacticsugar.vooga.xml.data.TowerData;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameView extends BorderPane implements ISimpleGameView, IScalingFactorContainer {

	private double mySize;
	private Pane gameField;
	private GlobalDataPanel myInfoBox;
	private TowerShop myTowerShop;

	public GameView(double size) {
		this.getStylesheets().add("/com/syntacticsugar/vooga/gameplayer/css/game.css");
		this.setFocusTraversable(true);
		mySize = size;
		initializeComponents();
	}

	public void resetComponents() {
		this.getChildren().clear();
		initializeComponents();
	}

	private void initializeComponents() {
		gameField = new Pane();
		this.setCenter(gameField);
		myInfoBox = new GlobalDataPanel();
		this.setBottom(myInfoBox);
	}

	public double getScalingFactor() {
		return (1.0 / 1000) * mySize;
	}

	public void addObjectView(Pane myViewPane) {
		gameField.getChildren().add(myViewPane);
	}

	public void removeObjectView(Pane myViewPane) {
		gameField.getChildren().remove(myViewPane);
	}

	public Pane getView() {
		return gameField;
	}

	public void initializeTowerTileObserver(TileView tile) {
		myTowerShop.addObserver(tile);
		tile.addObserver(myTowerShop);
	}

	public void initializeAvailableTowers(Collection<TowerData> availableTowers, IUniverseView universe,
			IEventPoster poster) {
		myTowerShop = new TowerShop();
		this.setRight(myTowerShop.getContent());
		myTowerShop.initialize(availableTowers, universe, poster);

	}

	public Observer getScoreObserver() {
		return myInfoBox;
	}

	public Observer getTowerObserver() {
		return myTowerShop;
	}

	public void setWaveButton(EventHandler<MouseEvent> onMouseClicked) {
		myInfoBox.setWaveButton(onMouseClicked);
	}
}
