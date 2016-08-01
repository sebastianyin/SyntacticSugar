package com.syntacticsugar.vooga.gameplayer.universe.score;

import java.util.Observer;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;

public interface IScore extends GameEventListener{
	
	/**
	 * Returns the current score.
	 * 
	 * @return the score
	 */
	public int getScore();
	
	/**
	 * Tells an object to observe this score.
	 * @param observer the object
	 */
	public void addObserver(Observer observer);
	
	/**
	 * Registers the object with the given event manager.
	 * 
	 * @param poster the event manager
	 */
	public void registerEventManager(IEventPoster poster);
	
}
