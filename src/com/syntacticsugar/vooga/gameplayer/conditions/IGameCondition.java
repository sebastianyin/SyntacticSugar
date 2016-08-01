package com.syntacticsugar.vooga.gameplayer.conditions;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;

public interface IGameCondition extends GameEventListener{
	
	
	/**
	 * Return the type of this game condition (ie. WINNING vs. LOSING). If a 
	 * WINNING condition has been triggered, the game manager moves on to the 
	 * next level. If the satisfied condition is a LOSING condition, the game 
	 * manager resets the current level to the last checkpoint.
	 * @return the condition type
	 */
	public ConditionType returnType();
	
	/**
	 * Registers the object with the given event manager.
	 * @param manager the event manager to register the condition with
	 */
	public void registerManager(IEventPoster manager);

}
