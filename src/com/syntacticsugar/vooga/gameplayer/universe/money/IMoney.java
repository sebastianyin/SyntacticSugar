package com.syntacticsugar.vooga.gameplayer.universe.money;

import java.util.Observer;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;

public interface IMoney extends GameEventListener{
	
	/**
	 * Returns the current amount of money.
	 * 
	 * @return the money
	 */
	public int getMoney();
	
	/**
	 * Tells an object to observe the money.
	 * @param observer the object
	 */
	public void addObserver(Observer observer);

}
