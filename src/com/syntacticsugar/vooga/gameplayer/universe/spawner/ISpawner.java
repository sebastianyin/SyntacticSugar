package com.syntacticsugar.vooga.gameplayer.universe.spawner;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IEventListener;
import com.syntacticsugar.vooga.xml.data.SpawnerData;

public interface ISpawner extends GameEventListener, IEventListener {
	
	/**
	 * Tells the spawner to start spawning the next wave.
	 */
	public void nextWave();

	/**
	 * Updates the spawner.
	 */
	public void update();
	
	/**
	 * Returns the current wave number.
	 * 
	 * @return the wave number
	 */
	public int getWaveNum();
	
	/**
	 * Returns the current wave.
	 * 
	 * @return the current wave
	 */
	public Wave getCurrentWave();
	
	/**
	 * Saves the state of the spawner and returns a data object.
	 * @return the spawner data
	 */
	public SpawnerData saveGame();
	
	/**
	 * Returns the spawn rate of the spawner.
	 * 
	 * @return the spawn rate
	 */
	public Integer getSpawnRate();
}
