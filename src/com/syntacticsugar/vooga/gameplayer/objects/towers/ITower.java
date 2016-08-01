package com.syntacticsugar.vooga.gameplayer.objects.towers;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public interface ITower extends IGameObject{
	
	/**
	 * Returns the cost of this tower.
	 * @return the cost
	 */
	public int getCost();
	
	/**
	 * Gets the name of this tower.
	 * @return the name
	 */
	public String getName();
	
}
