package com.syntacticsugar.vooga.gameplayer.conditions;

public interface IConditions {

	/**
	 * Returns the win condition for the universe.
	 * @return the condition
	 */
	public IGameCondition getWinCondition();
	
	/**
	 * Returns the loss condition for the universe.
	 * @return the condition
	 */
	public IGameCondition getLossCondition();
	
}
