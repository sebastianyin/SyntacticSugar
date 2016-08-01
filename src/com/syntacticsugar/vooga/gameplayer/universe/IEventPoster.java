package com.syntacticsugar.vooga.gameplayer.universe;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;

public interface IEventPoster {
	
	/**
	 * Posts a specified event.
	 * 
	 * @param event the event
	 */
	public void postEvent(IGameEvent event);
	
}
