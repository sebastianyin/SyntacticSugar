package com.syntacticsugar.vooga.gameplayer.manager;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;

public interface IEventManager extends IEventPoster{
	
	/**
	 * Registers the the given object with this event manager.
	 * @param obj the object
	 */
	public void registerListener(GameEventListener obj);

	/**
	 * Removes the given object from this event manager.
	 * @param obj the object
	 */
	public void removeListener(GameEventListener obj);
	
}
