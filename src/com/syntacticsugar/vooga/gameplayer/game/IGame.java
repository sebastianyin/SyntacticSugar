package com.syntacticsugar.vooga.gameplayer.game;

import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.xml.data.UniverseData;

public interface IGame {
	
	/**
	 * Increments the level; returns the universe associated with the new level.
	 * @return the next universe
	 */
	public IGameUniverse nextLevel();
	
	/**
	 * Returns the universe specified by the level number.
	 * @param i the level number
	 * @return the universe
	 */
	public IGameUniverse getLevel(int i);
	
	/**
	 * Saves the entire game given the data for the current universe.
	 * @param d the universe data
	 */
	public void saveGame(UniverseData d);

}
