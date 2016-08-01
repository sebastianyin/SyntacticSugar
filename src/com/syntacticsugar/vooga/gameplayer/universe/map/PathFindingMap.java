package com.syntacticsugar.vooga.gameplayer.universe.map;

import java.awt.Point;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;

public interface PathFindingMap {
	
	/**
	 * Returns a map of map indices to tiles. Used for pathfinding.
	 * 
	 * @return the map
	 */
	public Map<Point, IGameTile> getPathFindingMap();
	
}
