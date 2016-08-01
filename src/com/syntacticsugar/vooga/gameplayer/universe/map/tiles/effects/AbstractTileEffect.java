package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects;

import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;
import com.syntacticsugar.vooga.util.ResourceManager;

public abstract class AbstractTileEffect implements ITileEffect {
	
	private static final String TILE_EFFECT_FREQ = "tile_effect_freq";
	
	private int myFrameCount;
	private int myUpdateFrequency;
	
	protected IGameTile myTile;
	
	public AbstractTileEffect() {
		myFrameCount = 0;
		myUpdateFrequency = Integer.parseInt(ResourceManager.getString(TILE_EFFECT_FREQ));
	}

	@Override
	public void update(IGameUniverse universe) {
		if (myFrameCount >= myUpdateFrequency) {
			doEffect(universe);
			myFrameCount = 0;
		}
		
		myFrameCount++;
	}

	@Override
	public void setTile(IGameTile tile) {
		myTile = tile;
	}

	abstract protected void doEffect(IGameUniverse universe);
}
