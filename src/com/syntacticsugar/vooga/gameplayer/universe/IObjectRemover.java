package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public interface IObjectRemover {

	/**
	 * Remove this game object from the container.
	 * 
	 * @param toRemove the object
	 */
	public void removeGameObject(IGameObject obj);
	
}
