package com.syntacticsugar.vooga.authoring.level;

import java.util.ArrayList;
import java.util.Map;

import com.syntacticsugar.vooga.xml.data.TileData;

import javafx.scene.image.ImageView;

@FunctionalInterface
public interface IChangeTileImage {

	public void changeImage(ArrayList<TileData> selection, Map<TileData, ImageView> imageMap);
	
}
