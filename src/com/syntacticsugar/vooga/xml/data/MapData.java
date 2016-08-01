package com.syntacticsugar.vooga.xml.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;

public class MapData {

	private TileData[][] myTileData;
	private int myMapSize;
	
	public MapData(IGameMap map) {
		myTileData = new TileData[map.getSize()][map.getSize()];
		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {
				Map<Point, IGameTile> pMap = map.getPathFindingMap();
				IGameTile tile = pMap.get(new Point(i,j));
				myTileData[i][j] = new TileData(tile);
			}
		}
		myMapSize = map.getSize();
	}
	
	public MapData(int numTiles, String tileImage) {
		myMapSize = numTiles;
		myTileData = new TileData[numTiles][numTiles];
		initializeTileData(numTiles, tileImage);
	}
	
	private void initializeTileData(int numTiles, String tileImage) {
		for (int i=0; i<numTiles; i++) {
			for (int j=0; j<numTiles; j++) {
				myTileData[i][j] = new TileData(tileImage);
				myTileData[i][j].setImplementation(TileImplementation.Path);
			}
		}
	}
	
	public TileData getTileData(int i, int j) {
		if (i<myTileData.length && j<myTileData.length) 
			return myTileData[i][j];
		throw new IndexOutOfBoundsException("Specified tile is out of map bounds.");
	}
	
	public void setTileData(TileData tileData,int i, int j) {
		System.out.println("Tile Data Length is "+myTileData.length);
		if (i<myTileData.length && j<myTileData.length) {
			myTileData[i][j] = tileData;
			return;
		} else{
		throw new IndexOutOfBoundsException("Specified tile is out of map bounds.");
		}
	}
	
	public Collection<TileData> getTiles() {
		Collection<TileData> tiles = new ArrayList<>();
		for (int i=0; i<myTileData.length; i++) {
			for (int j=0; j<myTileData[0].length; j++) {
				tiles.add(getTileData(i, j));
			}
		}
		return Collections.unmodifiableCollection(tiles);
	}
	
	public int getMapSize() {
		return myMapSize;
	}
	
}
