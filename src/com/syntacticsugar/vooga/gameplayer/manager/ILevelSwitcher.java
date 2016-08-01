package com.syntacticsugar.vooga.gameplayer.manager;

import com.syntacticsugar.vooga.gameplayer.conditions.ConditionType;

public interface ILevelSwitcher {
	
	/**
	 * Switches the level, given a winning or losing condition type.
	 * @param type the condition type
	 */
	public void switchLevel(ConditionType type);

}
