package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.implementations;

import javafx.geometry.Point2D;

public abstract class AbstractTile{

	@SuppressWarnings("unused")
	private Point2D myPoint;
	
	public AbstractTile(Point2D point) {
		this.myPoint = point;
	}
	
	/**
	 * Returns true if the tile is walkable.
	 * @return the walkability
	 */
	public abstract boolean isWalkable();

	/**
	 * Returns true if an object can be placed upon the tile.
	 * @return the placeability
	 */
	public abstract boolean isPlaceable();

}
