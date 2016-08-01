package com.syntacticsugar.vooga.gameplayer.event;

import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;

public interface IEventListener {
	
	/**
	 * Registers this object with the given event manager.
	 * 
	 * @param eventmanager the event manager
	 */
	public void registerEventManager(IEventManager eventmanager);

}
