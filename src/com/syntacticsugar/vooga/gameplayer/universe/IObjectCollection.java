package com.syntacticsugar.vooga.gameplayer.universe;

import java.util.Collection;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public interface IObjectCollection extends IObjectSpawner, 
										   IObjectDespawner, 
										   IObjectAdder, 
										   IObjectRemover {

	/**
	 * Returns all game objects within this container.
	 * 
	 * @return the objects
	 */
	public Collection<IGameObject> getGameObjects();
	
	/**
	 * Returns the first player object in the container.
	 * 
	 * @return the player
	 */
	public IGameObject getPlayer();
	
		
}