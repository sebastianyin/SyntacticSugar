package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.view.IViewController;

public interface IYard<T extends IViewController> {

	/**
	 * Adds the given object to the yard.
	 * 
	 * @param obj the object
	 */
	public void addToYard(IGameObject obj);
	
	/**
	 * Tells the yard to alter the universe's state.
	 * 
	 * @param the yard
	 */
	public void alterUniverse(T the);

}
