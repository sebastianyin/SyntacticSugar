package com.syntacticsugar.vooga.gameplayer.universe.map;

import javafx.geometry.Point2D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.PlayerChangeTileEvent;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.DecoratorTile;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;
import com.syntacticsugar.vooga.xml.data.MapData;


public class GameMap implements IGameMap {
	
	private static final double MAP_DIMENSIONS = 1000.0;
	private List<Point> myPlayerLocations;
	
	private final DecoratorTile[][] myTiles;
	private int numCols;
	private int numRows;
	
	@SuppressWarnings("unused")
	private Point myDestination;

	public GameMap(MapData mapData) {
		myPlayerLocations = new ArrayList<>();
		numCols = mapData.getMapSize();
		numRows = mapData.getMapSize();
		myTiles = new DecoratorTile[numCols][numRows];
		for (int row = 0; row < numCols; row++) {
			for (int col = 0; col < numRows; col++) {
				Point2D point = new Point2D(row*MAP_DIMENSIONS/numRows, col*MAP_DIMENSIONS/numCols);
				myTiles[row][col] = new DecoratorTile(mapData.getTileData(row, col), point, 
						MAP_DIMENSIONS/numCols,MAP_DIMENSIONS/numRows);
			}
		}
	}

	
	
	@Override
	public Collection<IGameTile> getTiles() {
		Collection<IGameTile> tiles = new ArrayList<>();
		for (int i=0; i<myTiles.length; i++) {
			for (int j=0; j<myTiles[0].length; j++) {
				tiles.add(myTiles[i][j]);
			}
		}
		return tiles;
	}

	@Override
	public boolean[][] isWalkable() {
		boolean[][] walk = new boolean[numCols][numRows];
		for (int row = 0; row < numCols; row++) {
			for (int col = 0; col < numRows; col++) {
				walk[row][col] = myTiles[row][col].isWalkable();
			}
		}
		return walk;
	}

	@Override
	public boolean[][] isPlaceable() {
		boolean[][] place = new boolean[numCols][numRows];
		for (int row = 0; row < numCols; row++) {
			for (int col = 0; col < numRows; col++) {
				place[row][col] = myTiles[row][col].isPlaceable();
			}
		}
		return place;
	}
	
	@Override
	public IGameTile getTile(Point2D point) {
		try {
			return getTileFromIJ(getMapIndexFromCoordinate(point));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public IGameTile getTileFromIJ (Point point) {
		return myTiles[point.x][point.y];
	}
	
	@Override
	public Point getMapIndexFromCoordinate(Point2D coordinate) {
		int r = (int) Math.floor((coordinate.getX() / MAP_DIMENSIONS) * numRows);
		int c = (int) Math.floor((coordinate.getY() / MAP_DIMENSIONS) * numCols);
		return new Point(r, c);
	}
	
	@Override
	public int getSize() {
		if (numRows == numCols) {
			return numRows;
		}
		return -1;
	}

	@Override
	public Map<Point, IGameTile> getPathFindingMap() {
		Map<Point, IGameTile> map = new HashMap<>();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				map.put(new Point(i,j), myTiles[i][j]);
			}
		}
		return map;
	}

	@Override
	public double getTileSize() {
		return (MAP_DIMENSIONS / (double) (numRows));
	}

	@Override
	public List<Point> getDestinationPoints() {
		List<Point> destinations = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (myTiles[i][j].isDestination()) {
					destinations.add(new Point(i,j));
				}
			}
		}
		return destinations;
	}

	@Override
	public Point2D getCoordinateFromMapIndex(Point index) {
		double x = (index.x * MAP_DIMENSIONS / (double) (numRows));
		double y = (index.y * MAP_DIMENSIONS / (double) (numCols));
		return new Point2D(x,y);
	}



	@Override
	public void onEvent(IGameEvent e) {
		try {
			PlayerChangeTileEvent event = (PlayerChangeTileEvent) e;
			myPlayerLocations.remove(event.getOld());
			myPlayerLocations.add(event.getNew());
		} catch (ClassCastException e2) {	}
	} 



	@Override
	public List<Point> getPlayerLocations() {
		return new ArrayList<>(myPlayerLocations);
	}

}
