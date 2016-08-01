package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public interface IObjectAdder {

	/**
	 * Adds a single object to the container.
	 * 
	 * @param gameObject the object
	 */
	public void addGameObject(IGameObject toAdd);
	
}
