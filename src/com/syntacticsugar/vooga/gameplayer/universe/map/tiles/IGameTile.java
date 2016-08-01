package com.syntacticsugar.vooga.gameplayer.universe.map.tiles;

import com.syntacticsugar.vooga.gameplayer.objects.IViewableObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects.ITileEffect;

import javafx.geometry.Point2D;

public interface IGameTile extends IViewableObject, ITowerHolder{

	/**
	 * Returns whether or not this Tile can be walked on.
	 * @return the walkability
	 */
	public boolean isWalkable();
	
	/**
	 * Returns whether or not this Tile can have a Tower placed on it.
	 * @return the placeability
	 */
	public boolean isPlaceable();
	
	/**
	 * Returns whether or not this Tile is a global destination point on the map.
	 * @return whether the tile is a destination
	 */
	public boolean isDestination();
	
	/**
	 * Returns the point corresponding to the centerpoint of this Tile.
	 * @return the center of the tile
	 */
	public Point2D getPoint();
	
	/**
	 * Sets the effect that the tile holds.
	 * @param effect the effect
	 */
	public void setTileEffect(ITileEffect effect);
	
	/**
	 * Returns the effect that the tile holds.
	 * @return the effect
	 */
	public ITileEffect getTileEffect();
	
	/**
	 * Updates the tile in the universe.
	 * @param universe the universe
	 */
	public void updateSelf(IGameUniverse universe);
	/**
	 * Set this point as a destination.
	 */
	public void setDestination(boolean dest);
	
}
