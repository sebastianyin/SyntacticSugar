package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects;

import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.IGameTile;

public interface ITileEffect {

	/**
	 * Updates the tile effect in the universe.
	 * @param universe the universe
	 */
	public void update(IGameUniverse universe);
	
	/**
	 * Sets the tile that this effect is associated with.
	 * @param tile the tile
	 */
	public void setTile(IGameTile tile);
	
	/**
	 * Returns the name of the effect
	 * @return the name
	 */
	public String getEffectName();
	
}
