package com.syntacticsugar.vooga.authoring.level.map;

import com.syntacticsugar.vooga.authoring.objectediting.IDataDisplay;
import com.syntacticsugar.vooga.xml.data.TileData;

import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;

public interface IMapDisplay extends IDataDisplay<TileData> {

	public void selectAllTiles();
	
	public Point2D getDropPoint(DragEvent e);
	
}
