package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.view.IViewAdder;
import com.syntacticsugar.vooga.gameplayer.universe.spawner.ISpawner;

public interface IObjectSpawner {
	
	/**
	 * Adds an object to the universe, and to a given view
	 * 
	 * @return the view
	 */
	public void addToUniverse(IViewAdder adder);
	
	/**
	 * Add a GameObject to the SpawnYard (ie. mark it for spawning)
	 * 
	 * @param toAdd the object
	 */
	public void addToSpawnYard(IGameObject toAdd);
	
	/**
	 * Return the universe's spawnyard.
	 * @return the spawnyard.
	 */
	public SpawnYard getSpawnYard();

	
	/**
	 * Return the universe's object spawner (This creates events holding objects that 
	 * are then passed into the spawnyard).
	 * 
	 * @return the spawner
	 */
	public ISpawner getSpawner();
	
}
