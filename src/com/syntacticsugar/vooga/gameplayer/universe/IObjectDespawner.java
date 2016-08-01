package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.view.IViewRemover;

public interface IObjectDespawner {

	/**
	 * Add a GameObject to the Graveyard (ie. mark it for despawn)
	 * 
	 * @param toRemove the object
	 */
	public void addToGraveYard(IGameObject toRemove);

	/**
	 * Remove an object from the universe, and from a given view.
	 * 
	 * @param remover the view
	 */
	public void removeFromUniverse(IViewRemover remover);

	/**
	 * Returns the universe's graveyard.
	 * @return the graveyard
	 */
	public GraveYard getGraveYard();

}
