package com.syntacticsugar.vooga.xml.data;

import java.io.Serializable;

import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects.ITileEffect;

public class TileData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String myImagePath;
	private TileImplementation myImplementation;
	private boolean isDestination;
	private ITileEffect myEffect;

	public TileData(IGameTile tile) {
		myImagePath = tile.getPath();
		if (tile.isWalkable()) {
			myImplementation = TileImplementation.Path;
		} else {
			myImplementation = TileImplementation.Scenery;
		}
		isDestination = tile.isDestination();
		myEffect = tile.getTileEffect();
	}

	public TileData(String imagePath) {
		setImagePath(imagePath);
		setImplementation(TileImplementation.Path);
		setDestination(false);
	}

	public void setImagePath(String path) {
		this.myImagePath = path;
	}

	public void setImplementation(TileImplementation impl) {
		this.myImplementation = impl;
		if (impl.equals(TileImplementation.Scenery))
			setDestination(false);
	}

	public void setDestination(boolean isDestination) {
		this.isDestination = isDestination;
	}

	public String getImagePath() {
		return this.myImagePath;
	}

	public TileImplementation getImplementation() {
		return this.myImplementation;
	}

	public Boolean isDestination() {
		return this.isDestination;
	}

	public String getEffectName() {
		if (myEffect != null)
			return myEffect.getEffectName();
		else
			return "None";
	}

	public ITileEffect getEffect() {
		return myEffect;
	}

	public void setEffect(ITileEffect effect) {
		myEffect = effect;
	}
}
