package com.syntacticsugar.vooga.gameplayer.universe;

import java.util.Collection;
import java.util.Observer;

import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;
import com.syntacticsugar.vooga.gameplayer.universe.money.IMoney;
import com.syntacticsugar.vooga.gameplayer.universe.score.IScore;
import com.syntacticsugar.vooga.xml.data.TowerData;

/**
 * This class contains the interface between the game universe and the view
 *
 */

public interface IUniverseView extends IObjectCollection {
	
	/**
	 * Returns a Collection of available towers for placing.
	 * 
	 * @return the towers
	 */
	public Collection<TowerData> getAvailableTowers();
	
	/**
	 * Returns the Score manager.
	 * 
	 * @return the score manager
	 */
	public IScore getScore();
	
	/**
	 * Returns the money manager.
	 * 
	 * @return the money manager
	 */
	public IMoney getMoney();
	
	/**
	 * Tells an object to observe the score manager.
	 * 
	 * @param observer the object
	 */
	public void observeScore(Observer observer);
	
	/**
	 * Tells an object to observe the money manager.
	 * 
	 * @param observer the object
	 */
	public void observeMoney(Observer observer);
	
	/**
	 * Returns the game map.
	 * 
	 * @return the map
	 */
	public IGameMap getMap();

}
