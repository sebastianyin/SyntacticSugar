package simple.conditions;

import java.util.Collection;

import simple.obj.ISimpleObject;

public interface ISimpleCondition{

	/**
	 * Returns whether or not this condition is currently satisfied.
	 * @return
	 */
	public boolean isConditionMet();
	
	/**
	 * Checks to see if this condition has been satisfied by the last 
	 * frame update of the Universe.
	 * @param unmodifiableUniverse
	 * @return
	 */
	public boolean checkCondition(Collection<ISimpleObject> unmodifiableUniverse);
	
	/**
	 * Return the type of this game condition (ie. WINNING vs. LOSING). If a 
	 * WINNING condition has been triggered, the game manager moves on to the 
	 * next level. If the satisfied condition is a LOSING condition, the game 
	 * manager resets the current level to the last checkpoint.
	 * @return
	 */
	public SimpleConditionType returnType();
	
	

}
