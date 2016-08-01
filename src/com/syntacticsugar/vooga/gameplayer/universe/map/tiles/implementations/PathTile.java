package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.implementations;

import javafx.geometry.Point2D;

public class PathTile extends AbstractTile {

	public PathTile(Point2D point) {
		super(point);
	}
	
	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public boolean isPlaceable() {
		return false;
	}

}
