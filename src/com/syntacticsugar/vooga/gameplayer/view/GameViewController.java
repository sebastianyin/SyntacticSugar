package com.syntacticsugar.vooga.gameplayer.view;

import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.objects.IViewableObject;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IUniverseView;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;
import com.syntacticsugar.vooga.gameplayer.view.gameview.GameView;
import com.syntacticsugar.vooga.gameplayer.view.implementation.TileView;

public class GameViewController implements IViewRemover, IViewAdder {

	private Map<IViewableObject, ObjectView> myViewMap;
	private GameView myGameView;

	public GameViewController(double size) {
		myViewMap = new HashMap<IViewableObject, ObjectView>();
		myGameView = new GameView(size);
	}

	public void addViewObject(IViewableObject obj) {
		ObjectView newView = new ObjectView(obj.getPath(), obj.getBoundingBox(), myGameView);
		myViewMap.put(obj, newView);
	}

	public Map<IViewableObject, ObjectView> getViewMap() {
		return myViewMap;
	}

	public GameView getGameView() {
		return myGameView;
	}

	public void removeViewObject(IViewableObject obj) {
		try {
			ObjectView object = myViewMap.get(obj);
			myGameView.getView().getChildren().remove(object.getViewPane());
			myViewMap.remove(obj);
		} catch (NullPointerException exception) {

		}

	}

	public void displayLevel(IUniverseView universe, IEventPoster poster) {
		myGameView.resetComponents();
		if(!universe.getAvailableTowers().isEmpty()){
			myGameView.initializeAvailableTowers(universe.getAvailableTowers(), universe, poster);
			universe.observeMoney(myGameView.getTowerObserver());
		}
		//myGameView.initializeAvailableTowers(universe.getAvailableTowers(), universe, poster);
		myGameView.setWaveButton(event -> universe.getSpawner().nextWave());
		universe.observeScore(myGameView.getScoreObserver());
		//universe.observeMoney(myGameView.getTowerShop());
		for (IGameTile tile : universe.getMap().getTiles()) {
			addTileObject(tile);
		}
	}

	private void addTileObject(IGameTile obj) {
		TileView newView = new TileView(obj.getPath(), obj.getBoundingBox(), myGameView, obj.isWalkable());
		// newView.addObserver(this);
		if(myGameView.getTowerObserver() != null){
			myGameView.initializeTowerTileObserver(newView);
		}
		myViewMap.put(obj, newView);
	}

}
