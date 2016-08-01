package com.syntacticsugar.vooga.gameplayer.universe.map;

import javafx.geometry.Point2D;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;

public interface IGameMap extends PathFindingMap, GameEventListener {

	/**
	 * Returns a Collection of all the tiles in the map.
	 * 
	 * @return a collection of game tiles
	 */

	public Collection<IGameTile> getTiles();

	/**
	 * Returns a 2-dimensional boolean array of values denoting
	 * if each tile is walkable.
	 * 
	 * @return the walkable array
	 */
	public boolean[][] isWalkable();

	/**
	 * Returns a 2-dimensional boolean array of values denoting
	 * if each tile is placeable.
	 * 
	 * @return the placeable array
	 */
	public boolean[][] isPlaceable();
	
	/**
	 * Returns the map index of the tile that contains the given point.
	 * @param coordinate the point
	 * @return the map index
	 * @throws Exception out of bounds exception
	 */
	public Point getMapIndexFromCoordinate(Point2D coordinate);
	
	/**
	 * Returns the top-left point of the game tile corresponding to
	 * the given map index.
	 * 
	 * @param index the map index
	 * @return the point
	 */
	public Point2D getCoordinateFromMapIndex(Point index);
	
	/**
	 * Returns the size of the array (number of columns = number of rows)
	 * 
	 * @return size the size
	 */
	public int getSize();
	
	/**
	 * Returns the width/height of any given tile
	 * 
	 * @return size the size
	 */
	public double getTileSize();
	
	/**
	 * Returns a list of points that are destinations
	 * 
	 * @return destinations the destination points
	 */
	public List<Point> getDestinationPoints();
	
	/**
	 * Returns the tile which contains the given point.
	 * 
	 * @param point the point
	 * @return the tile
	 */
	public IGameTile getTile(Point2D point);

	/**
	 * Return a list of all the player locations on the map
	 * @return points: list of player locations in Point format
	 */
	public List<Point> getPlayerLocations();

	/**
	 * Returns the tile corresponding to the given map index.
	 * @param point the map index
	 * @return the tile
	 */
	public IGameTile getTileFromIJ(Point point);
}
