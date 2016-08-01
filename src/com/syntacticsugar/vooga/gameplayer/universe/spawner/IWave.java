package com.syntacticsugar.vooga.gameplayer.universe.spawner;

import java.util.Collection;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public interface IWave {
	
	/**
	 * Returns the next object in the wave.
	 * 
	 * @return the object
	 */
	public IGameObject getObj();
	
	/**
	 * Returns the current size of the wave.
	 * 
	 * @return the wave size
	 */
	public int getWaveSize();
	
	/**
	 * Returns the number of this wave.
	 * 
	 * @return the wave number
	 */
	public int getWaveNum();
	
	/**
	 * Returns a Collection of all the objects in the wave.
	 * 
	 * @return the objects
	 */
	public Collection<IGameObject> getAllObjs();

}
