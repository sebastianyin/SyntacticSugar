package com.syntacticsugar.vooga.authoring.level.map;

import java.util.Map;
import java.util.Observable;

import com.syntacticsugar.vooga.authoring.icon.Icon;
import com.syntacticsugar.vooga.authoring.level.IAddToSpawner;
import com.syntacticsugar.vooga.authoring.level.IManager;
import com.syntacticsugar.vooga.authoring.objectediting.IDataClipboard;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.TileData;

import javafx.scene.Node;

public class MapManager implements IManager {

	private MapView myMapDisplay;
	private MapControls myMapControls;
	
	public MapManager(IDataClipboard clip, IAddToSpawner iSpawn) throws Exception {
		System.out.println("Map Manager IOBJECT " + clip);
		myMapDisplay = new MapView(clip, iSpawn);
		myMapControls = new MapControls(myMapDisplay);
	}

	@Override
	public Observable getObservableController() {
		return myMapControls;
	}

	@Override
	public Node getControlNode() {
		return myMapControls.getView();
	}

	@Override
	public Node getViewNode() {
		return myMapDisplay.getView();
	}

	public MapData getMapData() {
		return myMapDisplay.getMapData();
	}
	
	public void setMapData(MapData loaded) {
		myMapDisplay.loadMapData(loaded);
	}
	
	public int getMapSize() {
		return myMapDisplay.getMapSize();
	}
	
	public double getMapGridWidth() {
		return myMapDisplay.getMapGridWidth();
	}
	
	public double getMapGridHeight() {
		return myMapDisplay.getMapGridHeight();
	}
	
	public Map<TileData, Icon> getTileIconMap() {
		return myMapDisplay.getTileIconMap();
	}

}
