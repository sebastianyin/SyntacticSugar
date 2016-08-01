package simple.manager;

import simple.conditions.SimpleConditionType;

public interface ISimpleGameManager {
	
	/**
	 * Method to update a single frame of the game universe.
	 */
	public void updateGame();
	
	/**
	 * Checks the win/loss conditions of the game. Should be 
	 * called after every frame update.
	 */
	public void checkConditions();
	
	/**
	 * Depending on the value of the satisfied game condition (ie. 
	 * WINNING vs. LOSING), either proceeds onward to the next level 
	 * in the game, or resets the current level from the last checkpoint.
	 * @param satisfiedCondition
	 */
	public void switchLevel(SimpleConditionType satisfiedCondition);

}
